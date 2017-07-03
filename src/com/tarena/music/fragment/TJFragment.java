package com.tarena.music.fragment;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;

import com.tarena.music.R;
import com.tarena.music.activity.SearchActivity;
import com.tarena.music.adapter.BDMusicAdapter;
import com.tarena.music.entity.BMusic;
import com.tarena.music.http.HttpUtil;
import com.tarena.music.notification.IParser;
import com.tarena.music.util.ConfigUitl;
import com.tarena.music.util.Utils;

@SuppressLint("NewApi")
public class TJFragment extends Fragment implements IParser, ConfigUitl {
	private ImageView ivMenu, ivSearch;
	private EditText etSearch;
	private ListView lv;
	private BDMusicAdapter adapter;

	private ProgressDialog pd;
	private List<BMusic> mList;

	/** 此方法可以指定加载的布局文件 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		/** 获得加载布局文件的对象v **/
		View v = View.inflate(getActivity(),// 当前activity
				R.layout.fragment_tj, // 加载的布局文件
				null);
		/** 初始化所有控件 */
		initViews(v);
		/*** 初始化事件监听 */
		initListeners();
		/** 启动网络访问 */
		HttpUtil hu = new HttpUtil();
		/** 歌曲类型为:新歌榜 */
		hu.getMusics(getActivity(), XGB, this);
		pd = ProgressDialog.show(getActivity(), "热歌榜", "加载中...");
		/** 将此对象v返回 */
		return v;
	}

	/** 设置菜单 ivMenu ivSearch lv的事件监听 */
	private void initListeners() {
		/***设置ListView的事件监听*/
		lv.setOnItemClickListener(
				new OnItemClickListener() {
			public void onItemClick(
					AdapterView<?> parent, View view,
					int position, long id) {
				
				
				
			}
		});
		ivSearch.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// 获取输入框中的内容
				String name = etSearch.getText().toString();

				// 判断name是否为""
				if ("".equals(name.trim())) {
					etSearch.setError("请输入内容");
					return;
				}
				// 启动查询界面,同时把name值传递给SearchActivity
				Intent intent = new Intent(getActivity(), 
						SearchActivity.class);
				// 封装name到intent中
				intent.putExtra(SEARCH_KEY_NAME, name);
				// 启动SearchActivity
				startActivity(intent);

			}
		});

		ivMenu.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				/** 2创建PopupMenu */
				// 1 获得PopupMenu对象
				PopupMenu menu = new PopupMenu(getActivity(), ivMenu);// 显示在那个控件上
				// 2 将创建好的菜单布局设置到当前menu上
				menu.getMenuInflater().// 获得菜单渲染器
						inflate(R.menu.popu_menu, // 菜单内容
								menu.getMenu());// 当前弹出式菜单的父类
				// 3 设置弹出式菜单的点击事件
				// item表示:点击菜单中的item数据
				menu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
					public boolean onMenuItemClick(MenuItem item) {
						// 获得点击对象的内容
						String name = (String) item.getTitle();
						// 通过name获得对应的请求type
						int type = Utils.getTypeByName(name);
						// 启动网络流程
						HttpUtil hu = new HttpUtil();
						hu.getMusics(getActivity(), type, TJFragment.this);
						/** 显示对话框 */
						pd = ProgressDialog.show(getActivity(), name, "加载中...");
						return false;
					}
				});
				// 4 显示菜单
				menu.show();
			}
		});
	}

	/** 实现所有控件的初始化 */
	private void initViews(View v) {
		ivSearch = (ImageView) v.findViewById(R.id.bd_iv_search);
		ivMenu = (ImageView) v.findViewById(R.id.bd_iv_menu);
		etSearch = (EditText) v.findViewById(R.id.bd_et_search);
		lv = (ListView) v.findViewById(R.id.bd_tj_lv_musices);
	}

	@Override
	public void parserSuccess(List<BMusic> list) {
		/**给lv事件监听提供数据源*/
		mList=list;
		// 经过log日志输出得出正确的数据
		adapter = new BDMusicAdapter(getActivity(), list);
		lv.setAdapter(adapter);
		/** 销毁显示的对话 */
		pd.cancel();
	}
}
