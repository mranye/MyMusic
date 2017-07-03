package com.tarena.music.notification;

import com.tarena.music.MusicApplication;
import com.tarena.music.R;
import com.tarena.music.activity.PlayMusicActivity;
import com.tarena.music.entity.Music;
import com.tarena.music.mode.PlayMusicService;
import com.tarena.music.util.ConfigUitl;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViews.RemoteView;

/**
 * ���ֲ���֪ͨ����ʾ
 * */
public class MusicNotification implements ConfigUitl {
	/** ��ʶ�Ƿ��������Ű�ť */
	private Context mContext;
	/** �����֪ͨ�������� */
	private NotificationManager manager;

	/** ͨ�����췽����������Ķ��� */
	public MusicNotification(Context context) {
		mContext = context;
		/** 1 ���֪ͨϵͳ������� */
		manager = (NotificationManager) mContext
				.getSystemService(Context.NOTIFICATION_SERVICE);
	}

	/**
	 * �˷���ʵ���˷���֪ͨ����
	 * 
	 * @param song
	 *            ��������
	 * @param singer
	 *            ��������
	 */
	public void sendNotification(String song, String singer) {
		/** ֪ͨ ����ϵͳ���,��Ҫ���ϵͳ��Ӧ�ķ������ */
		/** 2����֪ͨ���� **/
		/** ������Notificationʵ������ **/
		Notification notification = new Notification();
		/*** ����֪ͨ��ʾͼ����ʽ */
		notification.icon = R.drawable.a;
		/** �����û����֪ͨ���Ƿ����� */
		notification.flags = Notification.FLAG_NO_CLEAR;

		/** ��layout�ļ��������ý����ļ� notification_title.xml */
		/** ���֪ͨ�����ļ����Ķ��� */
		RemoteViews rv = new RemoteViews(mContext.getPackageName(),// ����
				R.layout.notification_title);// ��Ӧ�Ĳ����ļ�
		/** ���ø������� */
		rv.setTextViewText(R.id.notf_song, song);
		/** ���ø������� */
		rv.setTextViewText(R.id.notf_singer, singer);
		/** ��rv���õ�֪ͨ�� */
		notification.contentView = rv;

		/** ���õ��֪ͨ(�������֪ͨ�ϵİ�ťͼƬ) */
		/** ���֪ͨ�������Ž��� */
		/** ��������Activity����ͼ */
		Intent intentStartActivity = new Intent(mContext,
				PlayMusicActivity.class);
		Music m = MusicApplication.data.get(MusicApplication.index);
		intentStartActivity.putExtra(ENTITY_KEY, m);
		/** �ӳ���ͼ */
		PendingIntent pIntent = PendingIntent.getActivity(mContext, 0,
				intentStartActivity, PendingIntent.FLAG_UPDATE_CURRENT);

		/** �����Ż���ͣ��ť�ĵ���¼� */
		/** ���һ����ͼ����,�������ŷ��� */
		Intent intentPlayOrPause = new Intent(mContext, PlayMusicService.class);
		/** ֪ͨ���񲥷Ż�����ͣ */
		intentPlayOrPause.putExtra(PLAY_ACTION, MUSIC_NOTIFT_PALY_OR_PAUSE);
		/** ʹ��PendingIntent�����ӳ���ͼ */
		PendingIntent pIntentPlayOrPause = PendingIntent.getService(mContext,
				1, intentPlayOrPause, PendingIntent.FLAG_UPDATE_CURRENT);
		rv.setOnClickPendingIntent(R.id.notf_paly, pIntentPlayOrPause);

		/** ������һ�װ�ť�ĵ���¼� */
		/** ���һ����ͼ����,�������ŷ��� */
		Intent intentNext = new Intent(mContext, PlayMusicService.class);
		/** ֪ͨ�����л�����һ�� */
		intentNext.putExtra(PLAY_ACTION, MUSIC_NEXT);
		/** ʹ��PendingIntent�����ӳ���ͼ */
		PendingIntent pIntentNext = PendingIntent.getService(mContext, 2,
				intentNext, PendingIntent.FLAG_UPDATE_CURRENT);
		rv.setOnClickPendingIntent(R.id.notf_next, pIntentNext);

		/** �����Ƴ���ť�ĵ���¼� */
		/** ���һ����ͼ����,�������ŷ��� */
		Intent intentExit = new Intent(mContext, PlayMusicService.class);
		/** ֪ͨ�����л�����һ�� */
		intentExit.putExtra(PLAY_ACTION, MUSIC_EXIT);
		/** ʹ��PendingIntent�����ӳ���ͼ */
		PendingIntent pIntentExit = PendingIntent.getService(mContext, 3,
				intentExit, PendingIntent.FLAG_UPDATE_CURRENT);
		rv.setOnClickPendingIntent(R.id.notf_exit, pIntentExit);

		/** ���������Ž����pIntent������뵽notification�� */
		notification.contentIntent = pIntent;

		/** ��manager��ʾ֪ͨ */
		manager.notify(0, notification);

	}

	/** �Ƴ�֪ͨ�� */
	public void eixt() {
		/** ȡ������֪ͨ���������е�֪ͨ */
		manager.cancelAll();
	}

}
