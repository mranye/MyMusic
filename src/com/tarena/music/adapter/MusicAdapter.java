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
	
	
/**通过构造方法传入对应的数据源和上下文对象*/
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
		    /**获得动画iv*/
			vh.iv=(ImageView) 
					convertView.findViewById(R.id.list_item_iv);
			convertView.setTag(vh);
		}else{
			vh=(ViewHodler) convertView.getTag();
		}
		/**设置数据源*/
		Music m=mMusics.get(position);
		vh.tvSong.setText(m.getSong());
		vh.tvSinger.setText(m.getSinger());
		/**处理当前播放歌曲是否显示播放动画效果*/
		if(MusicApplication.index==position){
			vh.iv.setVisibility(View.VISIBLE);
			/**手动启动动画*/
			//获得绘制动画对象
			AnimationDrawable ad=
					(AnimationDrawable) 
					vh.iv.getDrawable();
			//开始动画
			ad.start();
		}else{
			vh.iv.setVisibility(View.GONE);
		}
		
		return convertView;
	}
  class ViewHodler{
	  TextView tvSong;
	  TextView tvSinger;
	  /*添加动画*/
	  ImageView iv;
  }
}
