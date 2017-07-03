package com.tarena.music.http;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tarena.music.notification.IParser;
import com.tarena.music.parser.ParserUtil;
import com.tarena.music.util.ConfigUitl;

/**
 * 负责网络请求任务,访问成功后 调用解析工具解析json字符
 * 
 */
public class HttpUtil implements ConfigUitl {
	/**
	 * 负责网络请求任务,通过歌曲名或歌手名完成查询json数据
	 * 
	 * @param name
	 *            歌曲名 歌手名那个
	 * @param coentxt
	 *            上下文对象,用来传递给解析对象使用,使用发送广播
	 */
	public void getMusicByName(String name, final Context context) {
		// 请求队列
		RequestQueue queue = Volley.newRequestQueue(context);
		String url = BADU_URL_SEARCH + name;
		// 请求任务
		StringRequest sr = new StringRequest(url, new Listener<String>() {
			public void onResponse(String json) {
				// 调用解析方法解析数据
				ParserUtil pu = new ParserUtil();
				pu.parserJSONSearch(json, context);
			}
		}, new ErrorListener() {
			public void onErrorResponse(VolleyError arg0) {

			}
		});
		// 将sr任务添加队列
		queue.add(sr);

	}

	/***
	 * 此方法是由TJFragment调用完成网络请求
	 * 
	 * @param context
	 *            上下文对象
	 * @param type
	 *            歌曲分类
	 * @param parser
	 *            回调对象
	 */
	public void getMusics(Context context, int type, final IParser parser) {
		/** 使用Volley框架实现获得JSON数据 */
		// 1 获得Volley请求队列RequestQueue
		RequestQueue queue = Volley.newRequestQueue(context);
		// 2拼接url
		String url = BAIDU_URL_START + type + BAIDU_URL_END;
		// 3创建网络访问任务
		// 字符串请求网络任务
		StringRequest sr = new StringRequest(url, new Listener<String>() {
			public void onResponse(String json) {
				// 成功获得数据后执行此处代码
				// 调用解析类的解析方法解析json字符串
				// 此处代码执行在主线程
				// Log.i("TAG", Thread.currentThread().getName()+"");
				ParserUtil pu = new ParserUtil();
				pu.parserJSON(json, parser);
			}
		}, new ErrorListener() {
			public void onErrorResponse(VolleyError arg0) {
				// 访问网络失败至此处代码
				// 服务器端未返回任何数据
				Log.i("TAG", arg0.getMessage());
			}
		});
		// 4 将字符串请求网络数据任务交给消息队列
		queue.add(sr);

	}

	/**
	 * 通过id查找对应的歌曲详情 主要使用songLink下载地址
	 * 
	 * @param context
	 *            上下文对象
	 * @param id
	 *            歌曲的id
	 */

	public void downFileMusic(final Context context, String id) {
		/** 使用Volley框架访问百度服务器 */
		// 队列
		RequestQueue queue = Volley.newRequestQueue(context);
		// 任务
		String url = BADU_URL_SEARCH_ID + id;
		StringRequest sr = new StringRequest(url, new Listener<String>() {
			public void onResponse(String arg0) {
				// /////解析JSON数据///////
				try {
					JSONObject object = new JSONObject(arg0);
					// 获取data:对应的JSONObject对象
					JSONObject dataObject = object.getJSONObject("data");
					// 获得songList对应的JSONArray对象
					JSONArray array = dataObject.getJSONArray("songList");
					// 获得含有下载地址的JSONObject对象
					JSONObject o = array.getJSONObject(0);
					// 获取下载地址 songLink
					final String songLink = o.getString("songLink");
					final String songName = o.getString("songName");
					Log.i("TAG", songLink);
					// // 如何在app中启动浏览器功能
					// // 创建意图
					// Intent intent=new Intent();
					// //设置意图信息
					// intent.setAction("android.intent.action.VIEW");
					// //准备网络地址
					// Uri musicUri=Uri.parse(songLink);
					// //封装到intent中
					// intent.setData(musicUri);
					// //启动浏览器
					// context.startActivity(intent);
					new Thread() {
						public void run() {
							try {
								getMusicFileDown(songLink, songName);
							} catch (Exception e) {
								e.printStackTrace();
							}
						};
					}.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, new ErrorListener() {
			public void onErrorResponse(VolleyError arg0) {

			}
		});
		// 放入的队列中
		queue.add(sr);

	}

	/**
	 * 实现使用HttpURlConncation下载指定的文件 放到mnt/sdcard/musics
	 * 
	 * @param songLink
	 *            歌曲下载地址
	 * @param songName
	 *            存储文件名
	 * @throws Exception
	 */
	protected void getMusicFileDown(String songLink, String songName)
			throws Exception {
		// 1 准备存储路径 mnt/sdcard
		// 获取本机SdCard的根目录
		String path = Environment.getExternalStorageDirectory() + "/music/";
		// 2 封装路径到URL中
		URL url = new URL(songLink);
		// 3 通过url获得HttpURLConnection
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		Log.i("TAG", "下载连接成功");
		// 4 获取输入流对象
		InputStream is = conn.getInputStream();
		// 5 创建输出流
		// 创建保存文件的对象File
		File f = new File(path + songName + ".mp3");
		// 判断文件是否存在
		if (f.exists()) {
			return;
		}
		// 输出流
		OutputStream os = new FileOutputStream(f);
		// 先定义一个字节缓存池
		byte[] arry = new byte[1024];
		// 循环读取数据并写入到文件中
		int count = 0;
		int size = 0;
		Log.i("TAG", "开始读取数据");
		while ((count = is.read(arry)) != -1) {
			// 读取arry中字节数据,从0开始到count有效数据个数
			os.write(arry, 0, count);
			size = size + count;
			Log.i("TAG", "数据读取中" + size);
		}
		os.close();
		is.close();
		Log.i("TAG", "下载成功");

	}
}
