package com.tarena.music.parser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.tarena.music.entity.Album;
import com.tarena.music.entity.BMusic;
import com.tarena.music.entity.SearMusic;
import com.tarena.music.notification.IParser;
import com.tarena.music.util.ConfigUitl;

/**
 * 
 * 负责解析HttpUtil类调用时传递过来的JSON字符串 解析结束后,回调接口IParser完成跟新界面
 * 
 */
public class ParserUtil implements ConfigUitl {
	/**
	 * 解析通过歌手名或者歌曲名获得JSON数据 1 获得的歌曲列表 2 获得唱片列表
	 * 
	 * @param json
	 *            JSON数据
	 * @param context
	 *            用来发送广播
	 */
	public void parserJSONSearch(String json, Context context) {
		try {
			// 处理返回的歌曲列表
			ArrayList<SearMusic> sList = new ArrayList<SearMusic>();
			// 获得最外层JSONObject对象
			JSONObject object = new JSONObject(json);
			// 返回的专辑类表信息
			ArrayList<Album> aList = new ArrayList<Album>();
			getAlbumMusic(context, aList, object);
			getSongMusic(context, sList, object);

		} catch (Exception e) {
			e.printStackTrace();

			/** 发送广播通知歌曲列表更新 */
			Intent intent = new Intent(BROAD_SERCAH_ACTION);
			// 放入动作
			intent.putExtra(BROAD_SERCAH_TYPE_KEY, -1);// 1 表示数据是歌曲列表
			intent.putExtra("text", "查不到歌曲");
			context.sendBroadcast(intent);

		}

	}

	/**
	 * 解析并封装唱片信息列表,最后发送广播通知界面更新
	 * 
	 * @param context
	 *            用来发送广播
	 * @param aList
	 *            用来存储唱片信息
	 * @param object
	 *            最外层JSONObject对象
	 * @throws JSONException
	 */
	private void getAlbumMusic(Context context, ArrayList<Album> aList,
			JSONObject object) throws JSONException {
		// 获得唱片信息数组对象
		JSONArray arr = object.getJSONArray("album");
		// 遍历arr唱片数组,封装数据
		for (int i = 0; i < arr.length(); i++) {
			Album a = new Album();
			JSONObject o = arr.getJSONObject(i);
			// 开始封装
			// 唱片名称
			String albumname = o.getString("albumname");
			a.setAlbumname(albumname);
			// 唱片歌手名称
			String artistname = o.getString("artistname");
			a.setArtistname(artistname);
			// 唱片图片地址
			String artistpic = o.getString("artistpic");
			a.setArtistpic(artistpic);
			// 唱片id
			String albumid = o.getString("albumid");
			a.setAlbumid(albumid);
			// 将a实体类对象放入到集合中
			aList.add(a);
		}
		// ////////发送广播通知更新界面//////////////
		// 使用Intent封装广播意图
		Intent intent = new Intent(BROAD_SERCAH_ACTION);
		// 放入动作 唱片信息 2 歌曲信息 1
		intent.putExtra(BROAD_SERCAH_TYPE_KEY, 2);
		// 使用Bundle封装集合对象
		Bundle bundle = new Bundle();
		bundle.putSerializable(BROAD_SERCAH_DATA_ALBUM_KEY, aList);
		// /即将bundle放入到intent中
		intent.putExtra(BROAD_SERCH_BUNDLE_KEY, bundle);
		// 发送广播
		context.sendBroadcast(intent);

	}

	/**
	 * 解析并封装song歌曲信息列表
	 * 
	 * @param context
	 *            用来发送广播使用
	 * @param sList
	 *            封装song歌曲信息列表
	 * @param object
	 *            JSONObject 整体数据最外层json对象
	 * @throws JSONException
	 *             JSON解析异常
	 */
	private void getSongMusic(Context context, ArrayList<SearMusic> sList,
			JSONObject object) throws JSONException {
		// /获得song对应的JSONArray对象
		JSONArray array = object.getJSONArray("song");
		// /循环遍历当前的array
		for (int i = 0; i < array.length(); i++) {
			JSONObject o = array.getJSONObject(i);
			SearMusic sm = new SearMusic();
			// songname
			String songname = o.getString("songname");
			sm.setSongname(songname);
			// songid
			String songid = o.getString("songid");
			sm.setSongid(songid);
			// artistname
			String artistname = o.getString("artistname");
			sm.setArtistname(artistname);
			// 将本次循环获得歌曲对象放入到集合中
			sList.add(sm);
		}
		/** 发送广播通知歌曲列表更新 */
		Intent intent = new Intent(BROAD_SERCAH_ACTION);
		// 放入动作
		intent.putExtra(BROAD_SERCAH_TYPE_KEY, 1);// 1 表示数据是歌曲列表
		// 将数据源集合sList放入到Bundle
		// Bundle 在安卓中产常见于组件之间数据的封装使用
		Bundle bundle = new Bundle();
		bundle.putSerializable(BROAD_SERCAH_DATA_SONG_KEY, sList);
		// 将Bundle放入到intent中
		intent.putExtra(BROAD_SERCH_BUNDLE_KEY, bundle);
		// 发送广播更新界面
		context.sendBroadcast(intent);
	}

	/**
	 * 负责解析json字符,解析成功后调用parser的 parserSuccess跟新界面
	 * 
	 * @param json
	 *            json数据
	 * @param parser
	 *            回调接口
	 */
	public void parserJSON(String json, IParser parser) {
		try {
			// 最外层大括号
			JSONObject object = new JSONObject(json);
			// 获得song_list的数组JSON数据
			JSONArray ja = object.getJSONArray("song_list");
			/** 取出ja数组中所有的数据封装到List集合 */
			List<BMusic> list = new ArrayList<BMusic>();
			// 遍历ja数组
			for (int i = 0; i < ja.length(); i++) {
				BMusic b = new BMusic();
				// 获得当前i位置上的JSONObject对象
				JSONObject o = ja.getJSONObject(i);
				// 获取歌曲语言类型
				try {
					String language = o.getString("language");
					b.setLanguage(language);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					// 获得歌曲大图片地址
					String pic_big = o.getString("pic_big");
					b.setPic_big(pic_big);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					// 获得歌曲小图片地址
					String pic_small = o.getString("pic_small");
					b.setPic_small(pic_small);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					// 获取国家信息
					String country = o.getString("country");
					b.setCountry(country);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					// 发布日期publishtime
					String publishtime = o.getString("publishtime");
					b.setPublishtime(publishtime);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					// 歌词下载地址
					String lrclink = o.getString("lrclink");
					b.setLrclink(lrclink);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					// 关注度
					String hot = o.getString("hot");
					b.setHot(hot);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					// 歌曲id
					String song_id = o.getString("song_id");
					b.setSong_id(song_id);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					// 歌曲名称
					String title = o.getString("title");
					b.setTitle(title);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					// 歌手名称
					String author = o.getString("author");
					b.setAuthor(author);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					// 专辑id
					String album_id = o.getString("album_id");
					b.setAlbum_id(album_id);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					// 专辑名称
					String album_title = o.getString("album_title");
					b.setAlbum_title(album_title);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// /以上将所需要的数据放入到实体类b中
				// 将封装的实体类b放入到集合中
				list.add(b);
			}
			// 解析成功以后回调接口方法
			parser.parserSuccess(list);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
