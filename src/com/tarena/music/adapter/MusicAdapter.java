package com.tarena.music.adapter;

import java.util.List;

import com.tarena.music.MusicApplication;
import com.tarena.music.R;
import com.tarena.music.entity.Music;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MusicAdapter extends BaseAdapter{
	private Context mContext;
	private List<Music> mMusics;
	
	
/**ͨ�����췽�������Ӧ������Դ�������Ķ���*/
	public MusicAdapter(Context context,List<Music> muiscs){
		mContext=context;
		mMusics=muiscs;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mMusics==null?0:mMusics.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mMusics.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
       ViewHodler vh=null;
		if(convertView==null){
			vh=new ViewHodler();
			convertView=View.inflate(mContext, R.layout.list_view_item_muisc,null);
			vh.tvSong=(TextView) convertView.findViewById(R.id.lv_item_song);
			vh.tvSinger=(TextView) convertView.findViewById(R.id.lv_item_singer);
		    /**��ö���iv*/
			vh.iv=(ImageView) 
					convertView.findViewById(R.id.list_item_iv);
			convertView.setTag(vh);
		}else{
			vh=(ViewHodler) convertView.getTag();
		}
		/**��������Դ*/
		Music m=mMusics.get(position);
		vh.tvSong.setText(m.getSong());
		vh.tvSinger.setText(m.getSinger());
		/**����ǰ���Ÿ����Ƿ���ʾ���Ŷ���Ч��*/
		if(MusicApplication.index==position){
			vh.iv.setVisibility(View.VISIBLE);
			/**�ֶ���������*/
			//��û��ƶ�������
			AnimationDrawable ad=
					(AnimationDrawable) 
					vh.iv.getDrawable();
			//��ʼ����
			ad.start();
		}else{
			vh.iv.setVisibility(View.GONE);
		}
		
		return convertView;
	}
  class ViewHodler{
	  TextView tvSong;
	  TextView tvSinger;
	  /*��Ӷ���*/
	  ImageView iv;
  }
}
