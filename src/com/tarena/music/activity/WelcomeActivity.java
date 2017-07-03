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
 * �����һ����ӭ����
 * 
 * @author Administrator
 * 
 */
public class WelcomeActivity extends Activity {
	/** ��������imageview�ؼ� */
	ImageView iv0, iv1, iv2;
	/** ����ָ������ؼ� */
	private ViewPager vPager;
	private int[] res;

	/** 2.0 ����ʱ1 ����TextView�ؼ� */
	TextView tv;
	/** 2.0����ʱ 2\3����Handler���� */
	Handler handler = new Handler() {
		/** ������handlerXxxMessgae,��ֱ�ӻص��˷��� */
		public void handleMessage(android.os.Message msg) {
			int what = msg.what;
			if (what == 100) {
				 startActivity(
				 new Intent(WelcomeActivity.this,MainActivity.class));
				 finish();
				return;
			}

			/** �жϵ�ǰ����ʱ��ֵ 2 1 0 */
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
			tv.setText(what + "�뵹��ʱ");
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/** ִ�����ý���֮ǰ�ж��Ƿ�Ϊ��һ�ε�½ */
		/** ���ƫ�����ö��� */
		SharedPreferences sp = getPreferences(MODE_PRIVATE);
		/** ���ƫ���������Ƿ��һ������Ӧ�� */
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
		/** ��������״̬д�뵽sp */
		/** ���д�����Editor */
		Editor editor = sp.edit();
		editor.putBoolean("frist", true);
		/** ע�� :һ��Ҫ�ύ */
		editor.commit();
		
	}

	private void initWelcome() {
		/** ��ʼ���ؼ� */
		initViews();
		/** ��ʼ���¼����� */
		initListeners();
		/** �ڳ�ʼ���ؼ������������߳� */
		new Thread() {
			public void run() {
				/** ѭ��ÿ��1���ӷ���һ��֪ͨ */
				for (int i = 2; i >= 0; i--) {
					SystemClock.sleep(1000);
					/** ͨ��handler������Ϣ */
					handler.sendEmptyMessage(i);
				}
				/** ������ת��֪ͨ */
				SystemClock.sleep(1000);
				handler.sendEmptyMessage(100);
			};
		}.start();
	}

	/** ���ø����ؼ����¼����� */
	private void initListeners() {
		/*** viewpager�¼����� */
		vPager.setOnPageChangeListener(new OnPageChangeListener() {
			/** ��ҳ�滬��������ִ�д˷��� */
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

			/** ��ҳ�滬����ִ�еķ��� */
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				Log.i("TAG", "onPageScrolled" + arg0 + "," + arg1 + "," + arg2);

			}

			/** ����ʼ,������,�������� */
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				Log.i("TAG", "onPageScrollStateChanged" + arg0);

			}
		});

	}

	/**
	 * ������ʼ���ؼ�
	 */
	private void initViews() {

		iv0 = (ImageView) findViewById(R.id.welcome_iv_0);
		iv1 = (ImageView) findViewById(R.id.welcome_iv_1);
		iv2 = (ImageView) findViewById(R.id.welcome_iv_2);
		tv = (TextView) findViewById(R.id.weclome_tv_time);
		/** 1��ʼ��viewpager */
		vPager = (ViewPager) findViewById(R.id.welcome_vp_lead);
		/** 2 ׼������Դ */
		res = new int[] { R.drawable.a, R.drawable.b, R.drawable.c };
		/** 5���������� */
		vPager.setAdapter(new MyPagerAdaper());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/** 3������Ա�ڲ���MyPagerAdapter�̳�PagerAdpager */
	class MyPagerAdaper extends PagerAdapter {
		/** ������Ҫ���ٸ��������� */
		@Override
		public int getCount() {
			return res == null ? 0 : res.length;
		}

		/** �˷�����ʾ��ǰ�Ƿ��Ǵ���Ŀؼ�����͵�ǰ�����Ƿ�һ�� */
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		/** ʵ����ÿ������ */
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			/** ����ImageView���� */
			ImageView iv = new ImageView(WelcomeActivity.this);
			/** ��ͼƬ��Դ���õ�iv�� */
			iv.setImageResource(res[position]);
			/** �����úõ�iv��ӵ�container */
			container.addView(iv);
			/** ���ص�ǰ��ʾ�Ŀؼ�iv */
			return iv;
		}

		/** �Զ�������Ҫ�Ƴ�ͼƬ��Դ�ؼ� */
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// container.removeView((View)object);
		}

	}

}
