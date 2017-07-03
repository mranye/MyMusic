package com.tarena.music.fragment;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.Volley;
import com.tarena.music.R;
import com.tarena.music.entity.Album;

/**
 * 显示每条唱片信息
 */
@SuppressLint("ValidFragment")
public class AlbumFragment extends Fragment {
	private Album album;

	// /////通过构造方法传入对应的数据//////
	public AlbumFragment(Album album) {
		this.album = album;
	}

	@SuppressWarnings("static-access")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = View.inflate(getActivity(), R.layout.fragment_album, null);
		// /初始化////////////////////////
		TextView tvName = (TextView) v.findViewById(R.id.album_tv_name);
		ImageView iv = (ImageView) v.findViewById(R.id.album_iv);
		// //////////填充数据/////////////////////
		tvName.setText(album.getAlbumname());
		// //////////专辑图异步加载////////////
		String url = album.getArtistpic();
		// 获得请求队列
		RequestQueue queue = Volley.newRequestQueue(getActivity());
		// 创建图片加载器
		ImageLoader imageLoader = new ImageLoader(queue, new BitmapCache());
		// 创建出图片加载监听器
		ImageListener listener = imageLoader.getImageListener(iv, R.drawable.a,
				R.drawable.b);
		imageLoader.get(url, listener);

		return v;
	}

	/**
	 * 把此类的实例对象交给ImageLoder来管理图片缓存机制
	 */
	class BitmapCache implements ImageCache {
		// 创建容器集合用来管理缓存的数据
		LruCache<String, Bitmap> lruCache;

		// /////////在构造方法中初始化/////////
		public BitmapCache() {
			// 需要设置当前lruCache缓存大小
			int maxSize = 5 * 1024 * 1024;
			lruCache = new LruCache<String, Bitmap>(maxSize) {
				@Override
				protected int sizeOf(String key, Bitmap bitmap) {
					// /////计算bitmap高度和每行的字节数的乘//////////
					return bitmap.getRowBytes() * bitmap.getHeight();
				}
			};
		}
		/** 获得当前的bitmap */
		@Override
		public Bitmap getBitmap(String arg0) {
			// TODO Auto-generated method stub
			return lruCache.get(arg0);
		}

		/** 当网络中获得了当前bitmap对象时调用此方法存入缓存中 **/
		@Override
		public void putBitmap(String arg0, Bitmap arg1) {
			lruCache.put(arg0, arg1);
		}

	}

}
