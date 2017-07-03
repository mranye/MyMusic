package com.tarena.music;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.xutils.DbManager;
import org.xutils.x;

import com.tarena.music.entity.Music;
import com.tarena.music.mode.ScanMusics;
import com.tarena.music.sql.CollectMusicSQLUitls;

import android.app.Application;

public class MusicApplication extends Application {
	/** 定义静态变量保存所有的音乐数据 */
	public static List<Music> data = new ArrayList<Music>();
	/** 当前播放歌曲的位置 */
	public static int index = -1;
	/** 数据库操作对象 */
	public static CollectMusicSQLUitls smsu;;

	/** 重写onCreate方法 */
	@Override
	public void onCreate() {
		super.onCreate();
		/** 初始化音乐数据 */
		getMusics();
		initXUtils();
	}
	private void initXUtils() {
		smsu = new CollectMusicSQLUitls(this);
	}

	/***
	 * 获取音频文件数据
	 */
	private void getMusics() {
		ScanMusics scanMusics = new ScanMusics();
		data = scanMusics.query(this);
	}
}
