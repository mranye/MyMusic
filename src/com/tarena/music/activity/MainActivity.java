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

	/** 定义集合保存所有的Fragment */
	ArrayList<Fragment> fragments = new ArrayList<Fragment>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();
		/*** 初始化事件监听 */
		initListeners();
	}

	private void initListeners() {
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {// 点击按钮的id
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
		/** viewpager控制rg */
		vp.setOnPageChangeListener(new OnPageChangeListener() {
			/**
			 * 页面滑动结束后执行此处代码 arg0 表示当前页面位置
			 */
			@Override
			public void onPageSelected(int p) {
				switch (p) {
				case 0:
					/** 推荐按钮点击 */
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
			/** 此方法在滑动中执行 */
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}
			/**
			 * 次方法表示滑动状态 开始 滑动中 滑动结束 arg0 表示滑动状态 0 1 2
			 */
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}
	private void initViews() {
		vp = (ViewPager) findViewById(R.id.main_vp);
		/** 将所有Fragment放入到list集合中 */
		fragments.add(new TJFragment());
		fragments.add(new BFragment());
		fragments.add(new WDFragment());
		/** 创建适配器对象adapter */
		MyFragmentAdapter adapter = new MyFragmentAdapter(
				getSupportFragmentManager(), this, fragments);
		vp.setAdapter(adapter);
		/** 获得RadioGroup */
		rg = (RadioGroup) findViewById(R.id.main_rg);
	}
	/** 点击两次返回按键 退出程序 */
	/**
	 * 重写onKeyDown keyCode 就是按键的对应值 event 触屏事件
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
	 * 实现2秒钟内点击两次返回按键退出程序
	 */
	private void exit() {
		// 如果当前时间-退出事件exitTime>2000
		if (System.currentTimeMillis() - exitTime > 2000) {
			// /在2秒之外触发返回按键
			// 提示用户再次点击退出按键测退出
			Toast.makeText(getApplicationContext(), "再次点击退出程序", 0).show();
			// 设置exitTime为当前时间
			exitTime = System.currentTimeMillis();
		} else {
			// 退出动作
			finish();
		}

	}

}
