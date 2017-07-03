package com.tarena.music.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.tarena.music.R;
import com.tarena.music.adapter.MyFragmentAdapter;
import com.tarena.music.adapter.SeatchMusicAdapter;
import com.tarena.music.entity.Album;
import com.tarena.music.entity.SearMusic;
import com.tarena.music.fragment.AlbumFragment;
import com.tarena.music.http.HttpUtil;
import com.tarena.music.util.ConfigUitl;

public class SearchActivity extends FragmentActivity implements ConfigUitl {
	private ImageView ivBack, ivSearch;
	private EditText etName;
	private ViewPager vp;
	private ListView lv;

	private SearchMusicBroadcase revice;

	private ProgressDialog pd;
	private View v;

	@SuppressWarnings("static-access")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		// 注册广播接受器
		revice = new SearchMusicBroadcase();
		IntentFilter filter = new IntentFilter();
		filter.addAction(BROAD_SERCAH_ACTION);

		registerReceiver(revice, filter);

		Intent intent = getIntent();
		String name = intent.getStringExtra(SEARCH_KEY_NAME);

		/** 请求网络连接 */
		HttpUtil hu = new HttpUtil();
		hu.getMusicByName(name, SearchActivity.this);
		pd = ProgressDialog.show(SearchActivity.this, "查询:" + name, "查询中 ...");
		/* 初始化 */
		initViews();
		initListener();
	}

	private void initListener() {
		ivBack.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});

		ivSearch.setOnClickListener(new OnClickListener() {
			@SuppressWarnings("static-access")
			public void onClick(View v) {
				// 获得输入框中的数据
				String name = etName.getText().toString();
				if ("".equals(name.trim())) {
					etName.setError("请输入内容");
					return;
				}
				// //////////////
				HttpUtil hp = new HttpUtil();
				hp.getMusicByName(name, SearchActivity.this);
				pd = ProgressDialog.show(SearchActivity.this, "查询:" + name,
						"查询中 ...");
			}
		});

	}

	/**
	 * 初始化各种控件
	 */
	private void initViews() {
		ivBack = (ImageView) findViewById(R.id.search_iv_back);
		ivSearch = (ImageView) findViewById(R.id.search_iv_search);
		etName = (EditText) findViewById(R.id.search_et_name);
		vp = (ViewPager) findViewById(R.id.search_vp);
//		v=View.inflate(this, R.layout.list_header, null);
//		vp=(ViewPager) v.findViewById(R.id.list_hear_vp);
		lv = (ListView) findViewById(R.id.search_lv);
		adapter = new SeatchMusicAdapter(
				getApplicationContext(), null);
		// ////////////
//		lv.addHeaderView(v);
		lv.setAdapter(adapter);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(revice);
	}
	private SeatchMusicAdapter adapter;

	/**
	 * 1 获得song数据 2 获得albume 唱片数据
	 */
	class SearchMusicBroadcase extends BroadcastReceiver {

		@SuppressWarnings("unchecked")
		public void onReceive(Context context, Intent intent) {
			// 关闭进度条
			if (pd != null)
				pd.cancel();
			// 获得发送过来的歌曲信息类型
			// 1 代表 song类型 2 代表唱片类型
			int type = intent.getIntExtra(BROAD_SERCAH_TYPE_KEY, -1);
			if (type == -1) {
				Toast.makeText(getApplicationContext(), "查不到歌曲", 0).show();
			}

			/** 判断type是否为1 */
			if (type == 1) {
				// /更新ListView
				// 获得intent中的bundle对象
				Bundle bundle = intent.getBundleExtra(BROAD_SERCH_BUNDLE_KEY);
				// 从bundle中获取list集合
				List<SearMusic> list = (List<SearMusic>) bundle
						.getSerializable(BROAD_SERCAH_DATA_SONG_KEY);
				// ////////////////////////
				adapter = new SeatchMusicAdapter(
						getApplicationContext(), list);
				// ////////////
				lv.addHeaderView(v);
				lv.setAdapter(adapter);
			}
			if (type == 2) {
				// /更新viewPager 唱片信息更新
				// 获取封装数据的bundle对象
				Bundle bundleAlbum = intent
						.getBundleExtra(BROAD_SERCH_BUNDLE_KEY);
				// 获得获得封装唱片集合对象
				List<Album> list = (List<Album>) bundleAlbum
						.getSerializable(BROAD_SERCAH_DATA_ALBUM_KEY);
				Log.i("TAG", list.toString());
				// 处理ViewPager
				// ////////将list集合转成List<Fragment>///////////////////
				List<Fragment> fList = new ArrayList<Fragment>();
				for (Album a : list) {
					fList.add(new AlbumFragment(a));
				}
				MyFragmentAdapter adpater = 
						new MyFragmentAdapter(
						getSupportFragmentManager(), 
						SearchActivity.this, fList);
               vp.setAdapter(adpater);
               adapter.notifyDataSetChanged();
			}

		}

	}

}
