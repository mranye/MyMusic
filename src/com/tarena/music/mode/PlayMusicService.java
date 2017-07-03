package com.tarena.music.mode;

import java.io.IOException;

import com.tarena.music.MusicApplication;
import com.tarena.music.entity.Music;
import com.tarena.music.notification.MusicNotification;
import com.tarena.music.util.ConfigUitl;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

public class PlayMusicService extends Service implements ConfigUitl {
	/** MediaPlayer负责播放歌曲 */
	MediaPlayer player;

	public static boolean isPalyOrPause = true;

	private MusicNotification mn;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	/** 第一次执行一次 ,以后不再执行 */
	@Override
	public void onCreate() {
		super.onCreate();
		/** 初始化palyer对象 */
		player = new MediaPlayer();
		mn = new MusicNotification(getApplicationContext());

	}

	/** 每次启动service都会执行此方法 */
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		/** 处理歌曲播放动作 */
		Music m = (Music) intent.getSerializableExtra(ENTITY_KEY);
		int action = intent.getIntExtra(PLAY_ACTION, -1);
		if (action != -1) {
			switch (action) {
			case MUSIC_PLAY:
				/** 处理播放动作 */
				try {
					//
					player.reset();
					/** 设置播放资源路径 */
					player.setDataSource(m.getPath());
					/** 设置播放器准备动作 */
					player.prepare();
					/** 播放 */
					player.start();
					sendMessageToActivity();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case MUSIC_PUASE:
				player.pause();
				break;
			case MUSIC_PRO:
				proPlayMusic();
				break;
			case MUSIC_NEXT:
				nextPlayMusic();
				break;
			case MUSIC_PROGESS:
				/** 取出播放位置 */
				int progress = intent.getIntExtra(PLAY_PROGESS_KEY, -1);
				/** 跟新播放器位置 */
				player.seekTo(progress);
				break;
			case MUSIC_PLAYING:
				player.start();
				sendMessageToActivity();
				break;
			case MUSIC_EXIT:
				/** 关闭通知栏 */
				mn.eixt();
				/** 推出程序 */
				System.exit(0);
				break;
			case MUSIC_NOTIFT_PALY_OR_PAUSE:
				if (isPalyOrPause) {
					player.pause();
					isPalyOrPause = false;
					Log.i("TAG", "NOT PAUSE");
				} else {
					player.start();
					isPalyOrPause = true;
					sendMessageToActivity();
					Log.i("TAG", "NOT START");
				}
				break;
			}
		}

		return super.onStartCommand(intent, flags, startId);
	}

	private void nextPlayMusic() {
		/** 确定播放位置 */
		/** 判断是否为最后一首 size-1 */
		if (MusicApplication.index >= MusicApplication.data.size() - 1) {
			/** 如果是最后一首歌曲,切到第一首 */
			MusicApplication.index = 0;
		} else {
			MusicApplication.index++;
		}
		nextOrProPlay();
	}

	/** 播放上一首歌曲 */
	private void proPlayMusic() {
		/** 确定播放位置 */
		/** 判断是否为第一首 0 */
		if (MusicApplication.index <= 0) {
			/** 如果是第一首歌曲,则切换到最后一首 */
			MusicApplication.index = MusicApplication.data.size() - 1;
		} else {
			MusicApplication.index--;
		}
		nextOrProPlay();
	}

	/** 不管是上一首还是下一首都要执行此方法 */
	private void nextOrProPlay() {
		/** 取出播放位置的音乐对象 */
		Music m = MusicApplication.data.get(MusicApplication.index);
		/** 重置播放器状态 */
		player.reset();
		/** 取出播放路径 */
		String path = m.getPath();
		/** 开始播放 */
		try {
			player.setDataSource(path);
			player.prepare();
			player.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/** 发送更新歌曲名称和歌手名称广播 */
		sendMuicMessge(m.getSong(), m.getSinger());
		sendMessageToActivity();

		sendNotification();
	}

	private void sendMuicMessge(String song, String singer) {
		Intent intent = new Intent();
		/** 封装数据 歌手名称 */
		intent.putExtra(BROD_KEY_UPDTA_SONG, song);
		intent.putExtra(BROD_KEY_UPDTA_SINGER, singer);
		/** 设置广播接受频道 action */
		intent.setAction(BROD_ACTION_TITLE);
		/** 发送广播 */
		sendBroadcast(intent);
	}

	/** 负责发送更新播放界面的广播功能 */
	private void sendMessageToActivity() {
		sendNotification();
		/** 启动线程 */
		new Thread() {
			public void run() {
				/** 循环每个一秒钟发送一次广播 */
				while (player.isPlaying()) {
					
					SystemClock.sleep(1000);
					/** 发送广播 */
					/** 广播意图 */
					Intent intent = new Intent();
					/** 封装数据 */
					intent.putExtra(BROD_KEY_TIME, player.getDuration());
					intent.putExtra(BROD_KEY_CURR_TIME,
							player.getCurrentPosition());
					/** 设置广播接受频道 action */
					intent.setAction(BROD_ACTION_UPDATA);
					/** 发送广播 */
					sendBroadcast(intent);
				}

			}
		}.start();

	}

	/** 发送通知 */
	private void sendNotification() {
		/** 获得歌手名称和歌曲名称 */
		Music m = MusicApplication.data.get(MusicApplication.index);
		/** 发送通知 */
		mn.sendNotification(m.getSong(), m.getSinger());
	}

}
