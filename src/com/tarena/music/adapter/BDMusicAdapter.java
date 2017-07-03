package com.tarena.music.adapter;

import java.util.List;

import org.xutils.ex.DbException;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.Volley;
import com.tarena.music.MusicApplication;
import com.tarena.music.R;
import com.tarena.music.entity.BMusic;
import com.tarena.music.entity.SMusic;
import com.tarena.music.http.HttpUtil;

@SuppressLint("CutPasteId")
public class BDMusicAdapter extends BaseAdapter {
	private Context context;
	private List<BMusic> list;

	public BDMusicAdapter(Context context, List<BMusic> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list != null ? list.size() : 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressWarnings("null")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh = null;
		if (convertView == null) {
			vh = new ViewHolder();
			convertView = View.inflate(context, R.layout.list_item_music, null);
			vh.ivPic = (ImageView) convertView.findViewById(R.id.bd_lv_item_iv);
			vh.ivMore = (ImageView) convertView
					.findViewById(R.id.bd_lv_item_iv_more);
			vh.tvTitle = (TextView) convertView
					.findViewById(R.id.bd_lv_item_tv_title);
			vh.tvSonger = (TextView) convertView
					.findViewById(R.id.bd_lv_item_tv_songer);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}
		final BMusic b = list.get(position);
		vh.tvTitle.setText(b.getTitle());
		vh.tvSonger.setText(b.getAuthor());
		final ImageView iv = (ImageView) convertView
				.findViewById(R.id.bd_lv_item_iv);

		/** 以上代码完成了文本内容实现 */
		iamgeAsyDown(vh, b);
		// ////////////////////////////////////
		/** 对更多图标的事件监听 */
		vh.ivMore.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				/*** 创建出对话框对象 */

				// 创建出对话框管理器
				AlertDialog.Builder buidler = new Builder(context);
				buidler.setTitle("提示");// 标题
				// /提示信息
				buidler.setMessage("请选择对" + b.getTitle() + "操作");
				// 添加一个按钮 收藏
				buidler.setPositiveButton("收藏",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// 使用数据库存储收藏过的音乐数据
								ContentValues values = new ContentValues();
								values.put("title", b.getTitle());
								values.put("songname", b.getAuthor());
								values.put("songid", b.getSong_id());
								values.put("pic", b.getPic_big());
								MusicApplication.smsu.insert("smusic", values);
							}
						});
				// 添加一个按钮 下载
				buidler.setNegativeButton("下载",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// 触发请求网络数据 当前歌曲的详情数据
								// 获得当前歌曲的id
								String songid = b.getSong_id();
								// 发起网络请求
								HttpUtil hu = new HttpUtil();
								hu.downFileMusic(context, songid);
							}
						});
				// /创建出可以使用的对话框对象并显示
				buidler.create().show();
			}
		});
		return convertView;
	}

	private void iamgeAsyDown(ViewHolder vh, BMusic b) {
		// ////////////////////////////////
		/*** 下面的代码完成图片的异步加载 */
		/** 使用Volley框架中的ImageRequst请求完成 */
		// 1 获取请求队列
		RequestQueue queue = Volley.newRequestQueue(context);
		// 2 创建ImageLoader对象
		ImageLoader il = new ImageLoader(queue,// 请求任务队列
				new BitmapCache());// 缓存管理对象
		// 获取ImageLoaderdListener对象
		// 此对象实现了指定加载到那个控件,加载中图片,加载失败时图片
		ImageListener listener = ImageLoader.getImageListener(vh.ivPic,
				R.drawable.a, R.drawable.b);
		// 使用ImageLoader对象加载图片,
		// 图片地址 url ImageListener
		il.get(b.getPic_small(), listener);
	}

	/**
	 * 实现了图片资源 一级缓存
	 * 
	 * @author Administrator
	 * 
	 */
	class BitmapCache implements ImageCache {
		// 准备存放从服务端读取的图片数据集合对象
		// 控制集合的内存大小
		private LruCache<String, Bitmap> mCache;

		// 在构造方法中处理缓存mCache大小
		public BitmapCache() {
			// 初始化mCache
			// maxSize 表示mCache可以使用多少内存
			int maxSize = 5 * 1024 * 1024;
			mCache = new LruCache<String, Bitmap>(maxSize) {
				// 用来计算每个位图对象大小
				protected int sizeOf(String key, Bitmap bitmap) {
					// 计算使用内存大小
					int size = bitmap.getRowBytes()// 每行多少字节
							* bitmap.getHeight();// 有多少行
					return size;
				}
			};
		}

		// ImageLoader在获取mCache缓存中bitmap对象
		public Bitmap getBitmap(String arg0) {
			return mCache.get(arg0);
		}

		/**
		 * 从网上获取bitmap对象后调用此方法放入到mCache集合中 arg0 放入时的key arg1 放入的bitmap位图对象
		 */
		public void putBitmap(String arg0, Bitmap arg1) {
			// 将bitmap放入到mCache集合中
			mCache.put(arg0, arg1);
		}

	}

	/**
	 * ImageRequest请求失败回调此处代码
	 */
	class MusicErrListener implements ErrorListener {

		@Override
		public void onErrorResponse(VolleyError arg0) {
			// TODO Auto-generated method stub
			Log.i("TAG", arg0.getLocalizedMessage());
		}

	}

	class ViewHolder {
		ImageView ivPic;
		TextView tvTitle;
		TextView tvSonger;
		ImageView ivMore;

	}

}
