package com.tarena.music.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.tarena.music.R;
import com.tarena.music.adapter.MyFragmentAdapter;
import com.tarena.music.fragment.BFragment;
import com.tarena.music.fragment.TJFragment;
import com.tarena.music.fragment.WDFragment;

public class MainActivity extends FragmentActivity {
	private ViewPager vp;
	private RadioGroup rg;

	/** ���弯�ϱ������е�Fragment */
	ArrayList<Fragment> fragments = new ArrayList<Fragment>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();
		/*** ��ʼ���¼����� */
		initListeners();
	}

	private void initListeners() {
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {// �����ť��id
				switch (checkedId) {
				case R.id.main_rb_tuijian:
					vp.setCurrentItem(0);
					break;
				case R.id.main_rb_bendi:
					vp.setCurrentItem(1);
					break;
				case R.id.main_rb_wode:
					vp.setCurrentItem(2);

					break;
				}
			}
		});
		/** viewpager����rg */
		vp.setOnPageChangeListener(new OnPageChangeListener() {
			/**
			 * ҳ�滬��������ִ�д˴����� arg0 ��ʾ��ǰҳ��λ��
			 */
			@Override
			public void onPageSelected(int p) {
				switch (p) {
				case 0:
					/** �Ƽ���ť��� */
					RadioButton tj = (RadioButton) rg
							.findViewById(R.id.main_rb_tuijian);
					tj.setChecked(true);
					break;
				case 1:
					RadioButton bd = (RadioButton) rg
							.findViewById(R.id.main_rb_bendi);
					bd.setChecked(true);
					break;
				case 2:
					RadioButton wd = (RadioButton) rg
							.findViewById(R.id.main_rb_wode);
					wd.setChecked(true);
					break;
				}
			}
			/** �˷����ڻ�����ִ�� */
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}
			/**
			 * �η�����ʾ����״̬ ��ʼ ������ �������� arg0 ��ʾ����״̬ 0 1 2
			 */
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}
	private void initViews() {
		vp = (ViewPager) findViewById(R.id.main_vp);
		/** ������Fragment���뵽list������ */
		fragments.add(new TJFragment());
		fragments.add(new BFragment());
		fragments.add(new WDFragment());
		/** ��������������adapter */
		MyFragmentAdapter adapter = new MyFragmentAdapter(
				getSupportFragmentManager(), this, fragments);
		vp.setAdapter(adapter);
		/** ���RadioGroup */
		rg = (RadioGroup) findViewById(R.id.main_rg);
	}
	/** ������η��ذ��� �˳����� */
	/**
	 * ��дonKeyDown keyCode ���ǰ����Ķ�Ӧֵ event �����¼�
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exit();
		}
		return false;
	}
	private long exitTime = 0;

	/**
	 * ʵ��2�����ڵ�����η��ذ����˳�����
	 */
	private void exit() {
		// �����ǰʱ��-�˳��¼�exitTime>2000
		if (System.currentTimeMillis() - exitTime > 2000) {
			// /��2��֮�ⴥ�����ذ���
			// ��ʾ�û��ٴε���˳��������˳�
			Toast.makeText(getApplicationContext(), "�ٴε���˳�����", 0).show();
			// ����exitTimeΪ��ǰʱ��
			exitTime = System.currentTimeMillis();
		} else {
			// �˳�����
			finish();
		}

	}

}
