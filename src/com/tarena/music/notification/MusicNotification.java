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
 * 音乐播放通知栏显示
 * */
public class MusicNotification implements ConfigUitl {
	/** 标识是否点击过播放按钮 */
	private Context mContext;
	/** 定义出通知变量对象 */
	private NotificationManager manager;

	/** 通过构造方法获得上下文对象 */
	public MusicNotification(Context context) {
		mContext = context;
		/** 1 获得通知系统服务对象 */
		manager = (NotificationManager) mContext
				.getSystemService(Context.NOTIFICATION_SERVICE);
	}

	/**
	 * 此方法实现了发送通知功能
	 * 
	 * @param song
	 *            歌曲名称
	 * @param singer
	 *            歌手名称
	 */
	public void sendNotification(String song, String singer) {
		/** 通知 属于系统组件,需要获得系统对应的服务对象 */
		/** 2创建通知对象 **/
		/** 创建出Notification实例对象 **/
		Notification notification = new Notification();
		/*** 设置通知显示图标样式 */
		notification.icon = R.drawable.a;
		/** 设置用户点击通知后是否销毁 */
		notification.flags = Notification.FLAG_NO_CLEAR;

		/** 在layout文件夹下设置界面文件 notification_title.xml */
		/** 获得通知布局文件按的对象 */
		RemoteViews rv = new RemoteViews(mContext.getPackageName(),// 包名
				R.layout.notification_title);// 对应的布局文件
		/** 设置歌曲名称 */
		rv.setTextViewText(R.id.notf_song, song);
		/** 设置歌手名称 */
		rv.setTextViewText(R.id.notf_singer, singer);
		/** 将rv设置到通知上 */
		notification.contentView = rv;

		/** 设置点击通知(不含点击通知上的按钮图片) */
		/** 点击通知启动播放界面 */
		/** 创建启动Activity的意图 */
		Intent intentStartActivity = new Intent(mContext,
				PlayMusicActivity.class);
		Music m = MusicApplication.data.get(MusicApplication.index);
		intentStartActivity.putExtra(ENTITY_KEY, m);
		/** 延迟意图 */
		PendingIntent pIntent = PendingIntent.getActivity(mContext, 0,
				intentStartActivity, PendingIntent.FLAG_UPDATE_CURRENT);

		/** 处理播放或暂停按钮的点击事件 */
		/** 获得一个意图对象,启动播放服务 */
		Intent intentPlayOrPause = new Intent(mContext, PlayMusicService.class);
		/** 通知服务播放或者暂停 */
		intentPlayOrPause.putExtra(PLAY_ACTION, MUSIC_NOTIFT_PALY_OR_PAUSE);
		/** 使用PendingIntent发送延迟意图 */
		PendingIntent pIntentPlayOrPause = PendingIntent.getService(mContext,
				1, intentPlayOrPause, PendingIntent.FLAG_UPDATE_CURRENT);
		rv.setOnClickPendingIntent(R.id.notf_paly, pIntentPlayOrPause);

		/** 处理下一首按钮的点击事件 */
		/** 获得一个意图对象,启动播放服务 */
		Intent intentNext = new Intent(mContext, PlayMusicService.class);
		/** 通知服务切换到下一首 */
		intentNext.putExtra(PLAY_ACTION, MUSIC_NEXT);
		/** 使用PendingIntent发送延迟意图 */
		PendingIntent pIntentNext = PendingIntent.getService(mContext, 2,
				intentNext, PendingIntent.FLAG_UPDATE_CURRENT);
		rv.setOnClickPendingIntent(R.id.notf_next, pIntentNext);

		/** 处理推出按钮的点击事件 */
		/** 获得一个意图对象,启动播放服务 */
		Intent intentExit = new Intent(mContext, PlayMusicService.class);
		/** 通知服务切换到下一首 */
		intentExit.putExtra(PLAY_ACTION, MUSIC_EXIT);
		/** 使用PendingIntent发送延迟意图 */
		PendingIntent pIntentExit = PendingIntent.getService(mContext, 3,
				intentExit, PendingIntent.FLAG_UPDATE_CURRENT);
		rv.setOnClickPendingIntent(R.id.notf_exit, pIntentExit);

		/** 将启动播放界面的pIntent对象放入到notification中 */
		notification.contentIntent = pIntent;

		/** 让manager显示通知 */
		manager.notify(0, notification);

	}

	/** 推出通知栏 */
	public void eixt() {
		/** 取消本次通知栏对象所有的通知 */
		manager.cancelAll();
	}

}
