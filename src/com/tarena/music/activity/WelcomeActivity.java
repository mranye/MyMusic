package com.tarena.music.activity;

import com.tarena.music.R;
import com.tarena.music.R.drawable;
import com.tarena.music.R.id;
import com.tarena.music.R.layout;
import com.tarena.music.R.menu;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 这个是一个欢迎界面
 * 
 * @author Administrator
 * 
 */
public class WelcomeActivity extends Activity {
	/** 声明三个imageview控件 */
	ImageView iv0, iv1, iv2;
	/** 新手指导界面控件 */
	private ViewPager vPager;
	private int[] res;

	/** 2.0 倒计时1 声明TextView控件 */
	TextView tv;
	/** 2.0倒计时 2\3声明Handler对象 */
	Handler handler = new Handler() {
		/** 当调用handlerXxxMessgae,会直接回到此方法 */
		public void handleMessage(android.os.Message msg) {
			int what = msg.what;
			if (what == 100) {
				 startActivity(
				 new Intent(WelcomeActivity.this,MainActivity.class));
				 finish();
				return;
			}

			/** 判断当前倒计时的值 2 1 0 */
			switch (what) {
			case 2:
				vPager.setCurrentItem(0);
				break;
			case 1:
				vPager.setCurrentItem(1);
				break;
			case 0:
				vPager.setCurrentItem(2);
				break;
			}
			tv.setText(what + "秒倒计时");
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/** 执行设置界面之前判断是否为第一次登陆 */
		/** 获得偏好设置对象 */
		SharedPreferences sp = getPreferences(MODE_PRIVATE);
		/** 获得偏好设置中是否第一次启动应用 */
		boolean frist = sp.getBoolean("frist", false);
		if (!frist) {
			setContentView(R.layout.activity_welcome);
			initWelcome();
		} else {
			setContentView(R.layout.activity_welcome1);
			new Thread(){
				public void run() {
					SystemClock.sleep(2000);
					handler.sendEmptyMessage(100);
				};
			}.start();
			
		}
		/** 将启动的状态写入到sp */
		/** 获得写入对象Editor */
		Editor editor = sp.edit();
		editor.putBoolean("frist", true);
		/** 注意 :一定要提交 */
		editor.commit();
		
	}

	private void initWelcome() {
		/** 初始化控件 */
		initViews();
		/** 初始化事件监听 */
		initListeners();
		/** 在初始化控件后启动工作线程 */
		new Thread() {
			public void run() {
				/** 循环每隔1秒钟发送一次通知 */
				for (int i = 2; i >= 0; i--) {
					SystemClock.sleep(1000);
					/** 通过handler发送信息 */
					handler.sendEmptyMessage(i);
				}
				/** 发送跳转他通知 */
				SystemClock.sleep(1000);
				handler.sendEmptyMessage(100);
			};
		}.start();
	}

	/** 设置各个控件的事件监听 */
	private void initListeners() {
		/*** viewpager事件监听 */
		vPager.setOnPageChangeListener(new OnPageChangeListener() {
			/** 当页面滑动结束后执行此方法 */
			@Override
			public void onPageSelected(int arg0) {
				Log.i("TAG", "onPageSelected" + arg0);
				switch (arg0) {
				case 0:
					iv0.setImageResource(R.drawable.zhusediaodiandian);
					iv1.setImageResource(R.drawable.baisediandian);
					iv2.setImageResource(R.drawable.baisediandian);
					break;
				case 1:
					iv0.setImageResource(R.drawable.baisediandian);
					iv1.setImageResource(R.drawable.zhusediaodiandian);
					iv2.setImageResource(R.drawable.baisediandian);
					break;
				case 2:
					iv0.setImageResource(R.drawable.baisediandian);
					iv1.setImageResource(R.drawable.baisediandian);
					iv2.setImageResource(R.drawable.zhusediaodiandian);
					break;
				}
			}

			/** 当页面滑动中执行的方法 */
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				Log.i("TAG", "onPageScrolled" + arg0 + "," + arg1 + "," + arg2);

			}

			/** 当开始,滑动中,滑动结束 */
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				Log.i("TAG", "onPageScrollStateChanged" + arg0);

			}
		});

	}

	/**
	 * 用来初始化控件
	 */
	private void initViews() {

		iv0 = (ImageView) findViewById(R.id.welcome_iv_0);
		iv1 = (ImageView) findViewById(R.id.welcome_iv_1);
		iv2 = (ImageView) findViewById(R.id.welcome_iv_2);
		tv = (TextView) findViewById(R.id.weclome_tv_time);
		/** 1初始化viewpager */
		vPager = (ViewPager) findViewById(R.id.welcome_vp_lead);
		/** 2 准备数据源 */
		res = new int[] { R.drawable.a, R.drawable.b, R.drawable.c };
		/** 5设置适配器 */
		vPager.setAdapter(new MyPagerAdaper());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/** 3声明成员内部类MyPagerAdapter继承PagerAdpager */
	class MyPagerAdaper extends PagerAdapter {
		/** 计算需要多少个滑动界面 */
		@Override
		public int getCount() {
			return res == null ? 0 : res.length;
		}

		/** 此方法表示当前是否是传入的控件对象和当前对象是否一致 */
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		/** 实例化每个界面 */
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			/** 创建ImageView对象 */
			ImageView iv = new ImageView(WelcomeActivity.this);
			/** 将图片资源设置到iv上 */
			iv.setImageResource(res[position]);
			/** 将设置好的iv添加到container */
			container.addView(iv);
			/** 返回当前显示的控件iv */
			return iv;
		}

		/** 自动管理需要移除图片资源控件 */
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// container.removeView((View)object);
		}

	}

}
