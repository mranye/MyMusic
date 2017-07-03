package com.tarena.music.adapter;

import java.util.List;

import com.tarena.music.R;
import com.tarena.music.entity.SearMusic;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SeatchMusicAdapter extends BaseAdapter {
    private Context context;
    private List<SearMusic> list;
    
    public SeatchMusicAdapter(Context context,
    		             List<SearMusic> list) {
    	this.context=context;
    	this.list=list;
	}
	public int getCount() {
		return list==null?0:list.size();
	}
	public Object getItem(int position) {
		return list.get(position);
	}
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh=null;
        if(convertView==null){
        	vh=new ViewHolder();
        	convertView=View.inflate
        	(context, R.layout.list_item_search, null);
        	////////
        	vh.tvArtist=(TextView) 
       convertView.findViewById(R.id.search_tv_artist);
        	vh.tvSong=(TextView)
        convertView.findViewById(R.id.search_tv_song);
        	////////////////////
        	convertView.setTag(vh);
        }else{
            vh=(ViewHolder) convertView.getTag();
        }
		////////////////
        SearMusic sm=list.get(position);
        vh.tvArtist.setText(sm.getArtistname());
        vh.tvSong.setText(sm.getSongname());
		
		return convertView;
	}
	
	class ViewHolder{
		TextView tvSong;
		TextView tvArtist;
		
	}
	
	
	

}
