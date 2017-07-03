package com.tarena.music.activity;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.tarena.music.R;
import com.tarena.music.entity.Music;
import com.tarena.music.mode.PlayMusicService;
import com.tarena.music.util.ConfigUitl;

public class PlayMusicActivity extends Activity implements ConfigUitl {
	private TextView tvSong;
	private TextView tvSinger;
	private TextView tvTime;
	private TextView tvCurrTime;

	private SeekBar sb;

	private ImageButton ibPro;
	private ImageButton ibPlay;
	private ImageButton ibNext;

	private Music music;

	/** 定义boolean变量记录当前是否为播放按钮 */
	private boolean isPlay = true;

	/** 定义boolean变量记录是否为播放状态 */
	private boolean isPlaying = false;

	private MyMusicUpDataBrodcase receiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_paly);
		
		
		/** 取出从DFragment中传递过来的Music实体类 */
		/** 获得启动当前Activity的intent对象 */
		Intent intent = getIntent();
		/** 从intent中取出实体类music */
		music = (Music) intent.getSerializableExtra(ENTITY_KEY);
		/**这就可以直接启动服务播放音乐*/
		playService();
		/**设置播放按钮的状态  代表当前是暂停按钮*/
		isPlay=false;
		// Log.i("tag", music.toString());
		/** 注册广播接收器 */
		/** 创建广播接收器对象 */
		receiver = new MyMusicUpDataBrodcase();
		/** 创建广播接收器过滤器 */
		IntentFilter filter = new IntentFilter();
		filter.addAction(BROD_ACTION_UPDATA);
		filter.addAction(BROD_ACTION_TITLE);
		/** 注册 */
		registerReceiver(receiver, filter);

		initViews();
		initListeners();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		/** 注销广播接收器 */
		unregisterReceiver(receiver);
	}

	/** 初始化控件 */
	private void initViews() {
		tvSong = (TextView) findViewById(R.id.paly_tv_song);
		tvSinger = (TextView) findViewById(R.id.paly_tv_singer);
		tvTime = (TextView) findViewById(R.id.paly_tv_time);
		tvCurrTime = (TextView) findViewById(R.id.paly_tv_curr_time);
		/** 设置歌曲名称和歌手名称 */
		tvSong.setText(music.getSong());
		tvSinger.setText(music.getSinger());

		sb = (SeekBar) findViewById(R.id.paly_sb);

		ibPro = (ImageButton) findViewById(R.id.paly_ib_pro);
		ibNext = (ImageButton) findViewById(R.id.paly_ib_next);
		ibPlay = (ImageButton) findViewById(R.id.paly_ib_paly);
        ibPlay.setImageResource(R.drawable.pause_seleclor);
	}

	/** 负责开始播放歌曲动作 */
	private void playService() {
		/** 启动service播放歌曲 */
		Intent intent = new Intent(PlayMusicActivity.this,
				PlayMusicService.class);
		/** 封装数据 */
		intent.putExtra(ENTITY_KEY, music);
		/*** 封装当前意图 */
		intent.putExtra(PLAY_ACTION, MUSIC_PLAY);
		startService(intent);
	}

	/** 负责暂停播放歌曲动作 */
	private void puaseService() {
		/** 启动service播放歌曲 */
		Intent intent = new Intent(PlayMusicActivity.this,
				PlayMusicService.class);
		/*** 封装当前意图 */
		intent.putExtra(PLAY_ACTION, MUSIC_PUASE);
		startService(intent);
	}

	/** 负责继续播放歌曲动作 */
	private void playingService() {
		/** 启动service播放歌曲 */
		Intent intent = new Intent(PlayMusicActivity.this,
				PlayMusicService.class);
		/*** 封装当前意图 */
		intent.putExtra(PLAY_ACTION, MUSIC_PLAYING);
		startService(intent);
	}

	/** 初始化事件监听 */
	private void initListeners() {
		/** 下一首歌曲按钮 */
		ibNext.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				nextMusicToServicer();
			}
		});
		/** 上一首歌曲按钮 */
		ibPro.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				proMusicToServicer();

			}
		});
		ibPlay.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (isPlay) {
					/** 当播放按钮点击后修改当前为暂停按钮 */
					ibPlay.setImageResource(R.drawable.pause_seleclor);
					/** 判断是否在播放状态 */
					if (isPlaying) {
						playingService();
					} else {
						playService();
						isPlaying = true;
					}
					isPlay = false;
				} else {
					/** 当暂停按钮点击后修改当前为播放按钮 */
					ibPlay.setImageResource(R.drawable.paly_seleclor);
					puaseService();
					isPlay = true;
					/***因为进入界面开始播放状态*/
					isPlaying = true;
				}
			}

		});

		sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			/** 拖动停止后执行此方法 */
			public void onStopTrackingTouch(SeekBar seekBar) {
			}

			/** 拖动开始时执行此方法 */
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			/**
			 * 拖动进度执行此方法 formUser 表示此拖动来自用户操作 progress 拖动进度条位置
			 */
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if (fromUser) {
					seekProgessToPlay(progress);
				}
			}
		});

	}

	/** 实现通知播放服务切换到下一首 */
	protected void nextMusicToServicer() {
		/** 启动service播放歌曲 */
		Intent intent = new Intent(PlayMusicActivity.this,
				PlayMusicService.class);
		/*** 封装当前意图 上一首 */
		intent.putExtra(PLAY_ACTION, MUSIC_NEXT);
		startService(intent);

	}

	/** 实现通知播放服务切换到上一首 */
	protected void proMusicToServicer() {
		/** 启动service播放歌曲 */
		Intent intent = new Intent(PlayMusicActivity.this,
				PlayMusicService.class);
		/*** 封装当前意图 上一首 */
		intent.putExtra(PLAY_ACTION, MUSIC_PRO);
		startService(intent);
	}

	/**
	 * 启动服务跟新当前播放歌曲位置
	 * 
	 * @param progress
	 *            歌曲位置
	 */
	/**
	 * @param progress
	 */
	protected void seekProgessToPlay(int progress) {
		/** 意图 */
		Intent intent = new Intent(this, PlayMusicService.class);
		/** 意图动作 */
		intent.putExtra(PLAY_ACTION, MUSIC_PROGESS);
		/** 封装的数据 */
		intent.putExtra(PLAY_PROGESS_KEY, progress);
		startService(intent);

	}

	/** 广播接收器类的创建 */
	class MyMusicUpDataBrodcase extends BroadcastReceiver {
		@SuppressLint("SimpleDateFormat")
		public void onReceive(Context context, Intent intent) {
			/** 取出action */
			String action = intent.getAction();
			/** 如果当前的aciton和更新界面进度的ation */
			if (BROD_ACTION_UPDATA.equals(action)) {
				/** 定义出时间格式对象 */
				SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
				/** 更新总时长 */
				/** 1 取出总时长 */
				int time = intent.getIntExtra(BROD_KEY_TIME, -1);
				String sTime = sdf.format(new Date(time));
				tvTime.setText(sTime);

				/** 2 取出当前播放时间 */
				int currTime = intent.getIntExtra(BROD_KEY_CURR_TIME, -1);
				String sCurrTime = sdf.format(new Date(currTime));
				tvCurrTime.setText(sCurrTime);
				/** 设置进度条最大进度 */
				sb.setMax(time);
				/** 设置进度条当前进度 */
				sb.setProgress(currTime);

			} else if (BROD_ACTION_TITLE.equals(action)) {
				/** 获取跟新标题内容 */
				String song = intent.getStringExtra(BROD_KEY_UPDTA_SONG);
				String singer = intent.getStringExtra(BROD_KEY_UPDTA_SINGER);
				/** 设置 */
				tvSong.setText(song);
				tvSinger.setText(singer);
				/** 处理按钮播放和暂停动作 */
				/** 取出播放状态 */

//				if (PlayMusicService.isPalyOrPause) {
//					/** 当播放按钮点击后修改当前为暂停按钮 */
//					ibPlay.setImageResource(R.drawable.pause_seleclor);
//					/** 判断是否在播放状态 */
//					isPlay = false;
//				} else {
//					/** 当暂停按钮点击后修改当前为播放按钮 */
//					ibPlay.setImageResource(R.drawable.paly_seleclor);
//					isPlay = true;
//				}
			}

		}
	}

}
