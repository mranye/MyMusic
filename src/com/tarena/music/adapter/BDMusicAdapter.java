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

		/** ���ϴ���������ı�����ʵ�� */
		iamgeAsyDown(vh, b);
		// ////////////////////////////////////
		/** �Ը���ͼ����¼����� */
		vh.ivMore.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				/*** �������Ի������ */

				// �������Ի��������
				AlertDialog.Builder buidler = new Builder(context);
				buidler.setTitle("��ʾ");// ����
				// /��ʾ��Ϣ
				buidler.setMessage("��ѡ���" + b.getTitle() + "����");
				// ���һ����ť �ղ�
				buidler.setPositiveButton("�ղ�",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// ʹ�����ݿ�洢�ղع�����������
								ContentValues values = new ContentValues();
								values.put("title", b.getTitle());
								values.put("songname", b.getAuthor());
								values.put("songid", b.getSong_id());
								values.put("pic", b.getPic_big());
								MusicApplication.smsu.insert("smusic", values);
							}
						});
				// ���һ����ť ����
				buidler.setNegativeButton("����",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// ���������������� ��ǰ��������������
								// ��õ�ǰ������id
								String songid = b.getSong_id();
								// ������������
								HttpUtil hu = new HttpUtil();
								hu.downFileMusic(context, songid);
							}
						});
				// /����������ʹ�õĶԻ��������ʾ
				buidler.create().show();
			}
		});
		return convertView;
	}

	private void iamgeAsyDown(ViewHolder vh, BMusic b) {
		// ////////////////////////////////
		/*** ����Ĵ������ͼƬ���첽���� */
		/** ʹ��Volley����е�ImageRequst������� */
		// 1 ��ȡ�������
		RequestQueue queue = Volley.newRequestQueue(context);
		// 2 ����ImageLoader����
		ImageLoader il = new ImageLoader(queue,// �����������
				new BitmapCache());// ����������
		// ��ȡImageLoaderdListener����
		// �˶���ʵ����ָ�����ص��Ǹ��ؼ�,������ͼƬ,����ʧ��ʱͼƬ
		ImageListener listener = ImageLoader.getImageListener(vh.ivPic,
				R.drawable.a, R.drawable.b);
		// ʹ��ImageLoader�������ͼƬ,
		// ͼƬ��ַ url ImageListener
		il.get(b.getPic_small(), listener);
	}

	/**
	 * ʵ����ͼƬ��Դ һ������
	 * 
	 * @author Administrator
	 * 
	 */
	class BitmapCache implements ImageCache {
		// ׼����Ŵӷ���˶�ȡ��ͼƬ���ݼ��϶���
		// ���Ƽ��ϵ��ڴ��С
		private LruCache<String, Bitmap> mCache;

		// �ڹ��췽���д�����mCache��С
		public BitmapCache() {
			// ��ʼ��mCache
			// maxSize ��ʾmCache����ʹ�ö����ڴ�
			int maxSize = 5 * 1024 * 1024;
			mCache = new LruCache<String, Bitmap>(maxSize) {
				// ��������ÿ��λͼ�����С
				protected int sizeOf(String key, Bitmap bitmap) {
					// ����ʹ���ڴ��С
					int size = bitmap.getRowBytes()// ÿ�ж����ֽ�
							* bitmap.getHeight();// �ж�����
					return size;
				}
			};
		}

		// ImageLoader�ڻ�ȡmCache������bitmap����
		public Bitmap getBitmap(String arg0) {
			return mCache.get(arg0);
		}

		/**
		 * �����ϻ�ȡbitmap�������ô˷������뵽mCache������ arg0 ����ʱ��key arg1 �����bitmapλͼ����
		 */
		public void putBitmap(String arg0, Bitmap arg1) {
			// ��bitmap���뵽mCache������
			mCache.put(arg0, arg1);
		}

	}

	/**
	 * ImageRequest����ʧ�ܻص��˴�����
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
