package com.tarena.music.mode;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.tarena.music.entity.Music;

/***
 * ������ѯ��Ƶ����
 * 
 * @author Administrator
 * 
 */
public class ScanMusics {
	/**
	 * �˷���ʵ�ֲ�ѯ��Ƶ���ݿ������е���Ƶ�ļ�
	 * 
	 * @param context
	 *            �������ContentResolver
	 * @return ���е���Ƶ�ļ����϶���
	 */
	public List<Music> query(Context context) {
		List<Music> musics = new ArrayList<Music>();
		/** ��ѯ���� */
		/** 1 ���Cursor���� */
		Cursor cursor = context.getContentResolver().query(
				MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
				null);
		/** 2 �ж�cursor�����Ƿ�Ϊ�� */
		if (cursor != null) {
			Music m = null;
			/** 3 ѭ����ȡÿ���������� */
			while (cursor.moveToNext()) {
				m = new Music();
				/** ��ȡcursor�е�ǰ���� */
				String song = cursor.getString(cursor
						.getColumnIndex(MediaStore.Audio.Media.TITLE));
				String singer = cursor.getString(cursor
						.getColumnIndex(MediaStore.Audio.Media.ARTIST));
				String path = cursor.getString(cursor
						.getColumnIndex(MediaStore.Audio.Media.DATA));
				m.setSong(song);
				m.setSinger(singer);
				m.setPath(path);
				musics.add(m);
			}
		}
		return musics;
	}
}
