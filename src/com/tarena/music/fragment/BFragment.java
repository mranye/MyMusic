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
 * ʵ�ֱ�������������ʾ
 * 
 * @author Administrator
 * 
 */
public class BFragment extends Fragment implements ConfigUitl {
	private ListView lv;
	private MusicAdapter adapter;

	// private List<Music> muiscs;

	/** �˷�������ָ�����صĲ����ļ� */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	      Log.i("TAG", "onCreateView");
		/** ��ü��ز����ļ��Ķ���v **/
		View v = View.inflate(getActivity(),// ��ǰactivity
				R.layout.fragment_b, // ���صĲ����ļ�
				null);
		/** ��ʼ��ListView */
		initViews(v);
		initListeners();
		/** ���˶���v���� */
		return v;
	}
	
	@Override
	public void onStart() {
		Log.i("TAG", "onStart");
		/**����lsitView*/
		adapter.notifyDataSetChanged();
		super.onStart();
	}
	
	

	private void initListeners() {
		// TODO Auto-generated method stub
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				/** ������ĸ���λ�ø�ֵ��index */
				MusicApplication.index = position;
				/*** ��װ���ݲ���ת���� */
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
		// /** �������Դ */
		// ScanMusics scanMusics = new ScanMusics();
		// muiscs = scanMusics.query(getActivity());
		/** ���������� */
		 adapter = new MusicAdapter(getActivity(),
				MusicApplication.data);
		/** �������� */
		lv.setAdapter(adapter);

	}

}
