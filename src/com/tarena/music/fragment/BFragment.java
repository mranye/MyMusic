package com.tarena.music.fragment;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.tarena.music.MusicApplication;
import com.tarena.music.R;
import com.tarena.music.activity.PlayMusicActivity;
import com.tarena.music.adapter.MusicAdapter;
import com.tarena.music.entity.Music;
import com.tarena.music.mode.ScanMusics;
import com.tarena.music.util.ConfigUitl;

/***
 * 实现本地音乐数据显示
 * 
 * @author Administrator
 * 
 */
public class BFragment extends Fragment implements ConfigUitl {
	private ListView lv;
	private MusicAdapter adapter;

	// private List<Music> muiscs;

	/** 此方法可以指定加载的布局文件 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	      Log.i("TAG", "onCreateView");
		/** 获得加载布局文件的对象v **/
		View v = View.inflate(getActivity(),// 当前activity
				R.layout.fragment_b, // 加载的布局文件
				null);
		/** 初始化ListView */
		initViews(v);
		initListeners();
		/** 将此对象v返回 */
		return v;
	}
	
	@Override
	public void onStart() {
		Log.i("TAG", "onStart");
		/**跟新lsitView*/
		adapter.notifyDataSetChanged();
		super.onStart();
	}
	
	

	private void initListeners() {
		// TODO Auto-generated method stub
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				/** 将点击的歌曲位置赋值给index */
				MusicApplication.index = position;
				/*** 封装数据并跳转界面 */
				Music m = MusicApplication.data.get(position);
				Intent intent = new Intent(getActivity(),
						PlayMusicActivity.class);
				intent.putExtra(ENTITY_KEY, m);
				startActivity(intent);
			}
		});
	}

	private void initViews(View v) {
		lv = (ListView) v.findViewById(R.id.fragment_bd_lv);
		// /** 获得数据源 */
		// ScanMusics scanMusics = new ScanMusics();
		// muiscs = scanMusics.query(getActivity());
		/** 适配器创建 */
		 adapter = new MusicAdapter(getActivity(),
				MusicApplication.data);
		/** 绑定适配器 */
		lv.setAdapter(adapter);

	}

}
