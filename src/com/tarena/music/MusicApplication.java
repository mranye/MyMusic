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
	/** ���徲̬�����������е��������� */
	public static List<Music> data = new ArrayList<Music>();
	/** ��ǰ���Ÿ�����λ�� */
	public static int index = -1;
	/** ���ݿ�������� */
	public static CollectMusicSQLUitls smsu;;

	/** ��дonCreate���� */
	@Override
	public void onCreate() {
		super.onCreate();
		/** ��ʼ���������� */
		getMusics();
		initXUtils();
	}
	private void initXUtils() {
		smsu = new CollectMusicSQLUitls(this);
	}

	/***
	 * ��ȡ��Ƶ�ļ�����
	 */
	private void getMusics() {
		ScanMusics scanMusics = new ScanMusics();
		data = scanMusics.query(this);
	}
}
