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
 * ��ʾÿ����Ƭ��Ϣ
 */
@SuppressLint("ValidFragment")
public class AlbumFragment extends Fragment {
	private Album album;

	// /////ͨ�����췽�������Ӧ������//////
	public AlbumFragment(Album album) {
		this.album = album;
	}

	@SuppressWarnings("static-access")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = View.inflate(getActivity(), R.layout.fragment_album, null);
		// /��ʼ��////////////////////////
		TextView tvName = (TextView) v.findViewById(R.id.album_tv_name);
		ImageView iv = (ImageView) v.findViewById(R.id.album_iv);
		// //////////�������/////////////////////
		tvName.setText(album.getAlbumname());
		// //////////ר��ͼ�첽����////////////
		String url = album.getArtistpic();
		// ����������
		RequestQueue queue = Volley.newRequestQueue(getActivity());
		// ����ͼƬ������
		ImageLoader imageLoader = new ImageLoader(queue, new BitmapCache());
		// ������ͼƬ���ؼ�����
		ImageListener listener = imageLoader.getImageListener(iv, R.drawable.a,
				R.drawable.b);
		imageLoader.get(url, listener);

		return v;
	}

	/**
	 * �Ѵ����ʵ�����󽻸�ImageLoder������ͼƬ�������
	 */
	class BitmapCache implements ImageCache {
		// ���������������������������
		LruCache<String, Bitmap> lruCache;

		// /////////�ڹ��췽���г�ʼ��/////////
		public BitmapCache() {
			// ��Ҫ���õ�ǰlruCache�����С
			int maxSize = 5 * 1024 * 1024;
			lruCache = new LruCache<String, Bitmap>(maxSize) {
				@Override
				protected int sizeOf(String key, Bitmap bitmap) {
					// /////����bitmap�߶Ⱥ�ÿ�е��ֽ����ĳ�//////////
					return bitmap.getRowBytes() * bitmap.getHeight();
				}
			};
		}
		/** ��õ�ǰ��bitmap */
		@Override
		public Bitmap getBitmap(String arg0) {
			// TODO Auto-generated method stub
			return lruCache.get(arg0);
		}

		/** �������л���˵�ǰbitmap����ʱ���ô˷������뻺���� **/
		@Override
		public void putBitmap(String arg0, Bitmap arg1) {
			lruCache.put(arg0, arg1);
		}

	}

}
