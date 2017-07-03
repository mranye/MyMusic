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

	/** ����boolean������¼��ǰ�Ƿ�Ϊ���Ű�ť */
	private boolean isPlay = true;

	/** ����boolean������¼�Ƿ�Ϊ����״̬ */
	private boolean isPlaying = false;

	private MyMusicUpDataBrodcase receiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_paly);
		
		
		/** ȡ����DFragment�д��ݹ�����Musicʵ���� */
		/** ���������ǰActivity��intent���� */
		Intent intent = getIntent();
		/** ��intent��ȡ��ʵ����music */
		music = (Music) intent.getSerializableExtra(ENTITY_KEY);
		/**��Ϳ���ֱ���������񲥷�����*/
		playService();
		/**���ò��Ű�ť��״̬  ����ǰ����ͣ��ť*/
		isPlay=false;
		// Log.i("tag", music.toString());
		/** ע��㲥������ */
		/** �����㲥���������� */
		receiver = new MyMusicUpDataBrodcase();
		/** �����㲥������������ */
		IntentFilter filter = new IntentFilter();
		filter.addAction(BROD_ACTION_UPDATA);
		filter.addAction(BROD_ACTION_TITLE);
		/** ע�� */
		registerReceiver(receiver, filter);

		initViews();
		initListeners();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		/** ע���㲥������ */
		unregisterReceiver(receiver);
	}

	/** ��ʼ���ؼ� */
	private void initViews() {
		tvSong = (TextView) findViewById(R.id.paly_tv_song);
		tvSinger = (TextView) findViewById(R.id.paly_tv_singer);
		tvTime = (TextView) findViewById(R.id.paly_tv_time);
		tvCurrTime = (TextView) findViewById(R.id.paly_tv_curr_time);
		/** ���ø������ƺ͸������� */
		tvSong.setText(music.getSong());
		tvSinger.setText(music.getSinger());

		sb = (SeekBar) findViewById(R.id.paly_sb);

		ibPro = (ImageButton) findViewById(R.id.paly_ib_pro);
		ibNext = (ImageButton) findViewById(R.id.paly_ib_next);
		ibPlay = (ImageButton) findViewById(R.id.paly_ib_paly);
        ibPlay.setImageResource(R.drawable.pause_seleclor);
	}

	/** ����ʼ���Ÿ������� */
	private void playService() {
		/** ����service���Ÿ��� */
		Intent intent = new Intent(PlayMusicActivity.this,
				PlayMusicService.class);
		/** ��װ���� */
		intent.putExtra(ENTITY_KEY, music);
		/*** ��װ��ǰ��ͼ */
		intent.putExtra(PLAY_ACTION, MUSIC_PLAY);
		startService(intent);
	}

	/** ������ͣ���Ÿ������� */
	private void puaseService() {
		/** ����service���Ÿ��� */
		Intent intent = new Intent(PlayMusicActivity.this,
				PlayMusicService.class);
		/*** ��װ��ǰ��ͼ */
		intent.putExtra(PLAY_ACTION, MUSIC_PUASE);
		startService(intent);
	}

	/** ����������Ÿ������� */
	private void playingService() {
		/** ����service���Ÿ��� */
		Intent intent = new Intent(PlayMusicActivity.this,
				PlayMusicService.class);
		/*** ��װ��ǰ��ͼ */
		intent.putExtra(PLAY_ACTION, MUSIC_PLAYING);
		startService(intent);
	}

	/** ��ʼ���¼����� */
	private void initListeners() {
		/** ��һ�׸�����ť */
		ibNext.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				nextMusicToServicer();
			}
		});
		/** ��һ�׸�����ť */
		ibPro.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				proMusicToServicer();

			}
		});
		ibPlay.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (isPlay) {
					/** �����Ű�ť������޸ĵ�ǰΪ��ͣ��ť */
					ibPlay.setImageResource(R.drawable.pause_seleclor);
					/** �ж��Ƿ��ڲ���״̬ */
					if (isPlaying) {
						playingService();
					} else {
						playService();
						isPlaying = true;
					}
					isPlay = false;
				} else {
					/** ����ͣ��ť������޸ĵ�ǰΪ���Ű�ť */
					ibPlay.setImageResource(R.drawable.paly_seleclor);
					puaseService();
					isPlay = true;
					/***��Ϊ������濪ʼ����״̬*/
					isPlaying = true;
				}
			}

		});

		sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			/** �϶�ֹͣ��ִ�д˷��� */
			public void onStopTrackingTouch(SeekBar seekBar) {
			}

			/** �϶���ʼʱִ�д˷��� */
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			/**
			 * �϶�����ִ�д˷��� formUser ��ʾ���϶������û����� progress �϶�������λ��
			 */
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if (fromUser) {
					seekProgessToPlay(progress);
				}
			}
		});

	}

	/** ʵ��֪ͨ���ŷ����л�����һ�� */
	protected void nextMusicToServicer() {
		/** ����service���Ÿ��� */
		Intent intent = new Intent(PlayMusicActivity.this,
				PlayMusicService.class);
		/*** ��װ��ǰ��ͼ ��һ�� */
		intent.putExtra(PLAY_ACTION, MUSIC_NEXT);
		startService(intent);

	}

	/** ʵ��֪ͨ���ŷ����л�����һ�� */
	protected void proMusicToServicer() {
		/** ����service���Ÿ��� */
		Intent intent = new Intent(PlayMusicActivity.this,
				PlayMusicService.class);
		/*** ��װ��ǰ��ͼ ��һ�� */
		intent.putExtra(PLAY_ACTION, MUSIC_PRO);
		startService(intent);
	}

	/**
	 * ����������µ�ǰ���Ÿ���λ��
	 * 
	 * @param progress
	 *            ����λ��
	 */
	/**
	 * @param progress
	 */
	protected void seekProgessToPlay(int progress) {
		/** ��ͼ */
		Intent intent = new Intent(this, PlayMusicService.class);
		/** ��ͼ���� */
		intent.putExtra(PLAY_ACTION, MUSIC_PROGESS);
		/** ��װ������ */
		intent.putExtra(PLAY_PROGESS_KEY, progress);
		startService(intent);

	}

	/** �㲥��������Ĵ��� */
	class MyMusicUpDataBrodcase extends BroadcastReceiver {
		@SuppressLint("SimpleDateFormat")
		public void onReceive(Context context, Intent intent) {
			/** ȡ��action */
			String action = intent.getAction();
			/** �����ǰ��aciton�͸��½�����ȵ�ation */
			if (BROD_ACTION_UPDATA.equals(action)) {
				/** �����ʱ���ʽ���� */
				SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
				/** ������ʱ�� */
				/** 1 ȡ����ʱ�� */
				int time = intent.getIntExtra(BROD_KEY_TIME, -1);
				String sTime = sdf.format(new Date(time));
				tvTime.setText(sTime);

				/** 2 ȡ����ǰ����ʱ�� */
				int currTime = intent.getIntExtra(BROD_KEY_CURR_TIME, -1);
				String sCurrTime = sdf.format(new Date(currTime));
				tvCurrTime.setText(sCurrTime);
				/** ���ý����������� */
				sb.setMax(time);
				/** ���ý�������ǰ���� */
				sb.setProgress(currTime);

			} else if (BROD_ACTION_TITLE.equals(action)) {
				/** ��ȡ���±������� */
				String song = intent.getStringExtra(BROD_KEY_UPDTA_SONG);
				String singer = intent.getStringExtra(BROD_KEY_UPDTA_SINGER);
				/** ���� */
				tvSong.setText(song);
				tvSinger.setText(singer);
				/** ����ť���ź���ͣ���� */
				/** ȡ������״̬ */

//				if (PlayMusicService.isPalyOrPause) {
//					/** �����Ű�ť������޸ĵ�ǰΪ��ͣ��ť */
//					ibPlay.setImageResource(R.drawable.pause_seleclor);
//					/** �ж��Ƿ��ڲ���״̬ */
//					isPlay = false;
//				} else {
//					/** ����ͣ��ť������޸ĵ�ǰΪ���Ű�ť */
//					ibPlay.setImageResource(R.drawable.paly_seleclor);
//					isPlay = true;
//				}
			}

		}
	}

}
