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
	/** MediaPlayer���𲥷Ÿ��� */
	MediaPlayer player;

	public static boolean isPalyOrPause = true;

	private MusicNotification mn;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	/** ��һ��ִ��һ�� ,�Ժ���ִ�� */
	@Override
	public void onCreate() {
		super.onCreate();
		/** ��ʼ��palyer���� */
		player = new MediaPlayer();
		mn = new MusicNotification(getApplicationContext());

	}

	/** ÿ������service����ִ�д˷��� */
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		/** ����������Ŷ��� */
		Music m = (Music) intent.getSerializableExtra(ENTITY_KEY);
		int action = intent.getIntExtra(PLAY_ACTION, -1);
		if (action != -1) {
			switch (action) {
			case MUSIC_PLAY:
				/** �����Ŷ��� */
				try {
					//
					player.reset();
					/** ���ò�����Դ·�� */
					player.setDataSource(m.getPath());
					/** ���ò�����׼������ */
					player.prepare();
					/** ���� */
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
				/** ȡ������λ�� */
				int progress = intent.getIntExtra(PLAY_PROGESS_KEY, -1);
				/** ���²�����λ�� */
				player.seekTo(progress);
				break;
			case MUSIC_PLAYING:
				player.start();
				sendMessageToActivity();
				break;
			case MUSIC_EXIT:
				/** �ر�֪ͨ�� */
				mn.eixt();
				/** �Ƴ����� */
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
		/** ȷ������λ�� */
		/** �ж��Ƿ�Ϊ���һ�� size-1 */
		if (MusicApplication.index >= MusicApplication.data.size() - 1) {
			/** ��������һ�׸���,�е���һ�� */
			MusicApplication.index = 0;
		} else {
			MusicApplication.index++;
		}
		nextOrProPlay();
	}

	/** ������һ�׸��� */
	private void proPlayMusic() {
		/** ȷ������λ�� */
		/** �ж��Ƿ�Ϊ��һ�� 0 */
		if (MusicApplication.index <= 0) {
			/** ����ǵ�һ�׸���,���л������һ�� */
			MusicApplication.index = MusicApplication.data.size() - 1;
		} else {
			MusicApplication.index--;
		}
		nextOrProPlay();
	}

	/** ��������һ�׻�����һ�׶�Ҫִ�д˷��� */
	private void nextOrProPlay() {
		/** ȡ������λ�õ����ֶ��� */
		Music m = MusicApplication.data.get(MusicApplication.index);
		/** ���ò�����״̬ */
		player.reset();
		/** ȡ������·�� */
		String path = m.getPath();
		/** ��ʼ���� */
		try {
			player.setDataSource(path);
			player.prepare();
			player.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/** ���͸��¸������ƺ͸������ƹ㲥 */
		sendMuicMessge(m.getSong(), m.getSinger());
		sendMessageToActivity();

		sendNotification();
	}

	private void sendMuicMessge(String song, String singer) {
		Intent intent = new Intent();
		/** ��װ���� �������� */
		intent.putExtra(BROD_KEY_UPDTA_SONG, song);
		intent.putExtra(BROD_KEY_UPDTA_SINGER, singer);
		/** ���ù㲥����Ƶ�� action */
		intent.setAction(BROD_ACTION_TITLE);
		/** ���͹㲥 */
		sendBroadcast(intent);
	}

	/** �����͸��²��Ž���Ĺ㲥���� */
	private void sendMessageToActivity() {
		sendNotification();
		/** �����߳� */
		new Thread() {
			public void run() {
				/** ѭ��ÿ��һ���ӷ���һ�ι㲥 */
				while (player.isPlaying()) {
					
					SystemClock.sleep(1000);
					/** ���͹㲥 */
					/** �㲥��ͼ */
					Intent intent = new Intent();
					/** ��װ���� */
					intent.putExtra(BROD_KEY_TIME, player.getDuration());
					intent.putExtra(BROD_KEY_CURR_TIME,
							player.getCurrentPosition());
					/** ���ù㲥����Ƶ�� action */
					intent.setAction(BROD_ACTION_UPDATA);
					/** ���͹㲥 */
					sendBroadcast(intent);
				}

			}
		}.start();

	}

	/** ����֪ͨ */
	private void sendNotification() {
		/** ��ø������ƺ͸������� */
		Music m = MusicApplication.data.get(MusicApplication.index);
		/** ����֪ͨ */
		mn.sendNotification(m.getSong(), m.getSinger());
	}

}
