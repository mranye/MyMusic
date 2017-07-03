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

	/** �˷�������ָ�����صĲ����ļ� */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		/** ��ü��ز����ļ��Ķ���v **/
		View v = View.inflate(getActivity(),// ��ǰactivity
				R.layout.fragment_tj, // ���صĲ����ļ�
				null);
		/** ��ʼ�����пؼ� */
		initViews(v);
		/*** ��ʼ���¼����� */
		initListeners();
		/** ����������� */
		HttpUtil hu = new HttpUtil();
		/** ��������Ϊ:�¸�� */
		hu.getMusics(getActivity(), XGB, this);
		pd = ProgressDialog.show(getActivity(), "�ȸ��", "������...");
		/** ���˶���v���� */
		return v;
	}

	/** ���ò˵� ivMenu ivSearch lv���¼����� */
	private void initListeners() {
		/***����ListView���¼�����*/
		lv.setOnItemClickListener(
				new OnItemClickListener() {
			public void onItemClick(
					AdapterView<?> parent, View view,
					int position, long id) {
				
				
				
			}
		});
		ivSearch.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// ��ȡ������е�����
				String name = etSearch.getText().toString();

				// �ж�name�Ƿ�Ϊ""
				if ("".equals(name.trim())) {
					etSearch.setError("����������");
					return;
				}
				// ������ѯ����,ͬʱ��nameֵ���ݸ�SearchActivity
				Intent intent = new Intent(getActivity(), 
						SearchActivity.class);
				// ��װname��intent��
				intent.putExtra(SEARCH_KEY_NAME, name);
				// ����SearchActivity
				startActivity(intent);

			}
		});

		ivMenu.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				/** 2����PopupMenu */
				// 1 ���PopupMenu����
				PopupMenu menu = new PopupMenu(getActivity(), ivMenu);// ��ʾ���Ǹ��ؼ���
				// 2 �������õĲ˵��������õ���ǰmenu��
				menu.getMenuInflater().// ��ò˵���Ⱦ��
						inflate(R.menu.popu_menu, // �˵�����
								menu.getMenu());// ��ǰ����ʽ�˵��ĸ���
				// 3 ���õ���ʽ�˵��ĵ���¼�
				// item��ʾ:����˵��е�item����
				menu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
					public boolean onMenuItemClick(MenuItem item) {
						// ��õ�����������
						String name = (String) item.getTitle();
						// ͨ��name��ö�Ӧ������type
						int type = Utils.getTypeByName(name);
						// ������������
						HttpUtil hu = new HttpUtil();
						hu.getMusics(getActivity(), type, TJFragment.this);
						/** ��ʾ�Ի��� */
						pd = ProgressDialog.show(getActivity(), name, "������...");
						return false;
					}
				});
				// 4 ��ʾ�˵�
				menu.show();
			}
		});
	}

	/** ʵ�����пؼ��ĳ�ʼ�� */
	private void initViews(View v) {
		ivSearch = (ImageView) v.findViewById(R.id.bd_iv_search);
		ivMenu = (ImageView) v.findViewById(R.id.bd_iv_menu);
		etSearch = (EditText) v.findViewById(R.id.bd_et_search);
		lv = (ListView) v.findViewById(R.id.bd_tj_lv_musices);
	}

	@Override
	public void parserSuccess(List<BMusic> list) {
		/**��lv�¼������ṩ����Դ*/
		mList=list;
		// ����log��־����ó���ȷ������
		adapter = new BDMusicAdapter(getActivity(), list);
		lv.setAdapter(adapter);
		/** ������ʾ�ĶԻ� */
		pd.cancel();
	}
}
