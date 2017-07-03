package com.tarena.music.mode;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.tarena.music.entity.Music;

/***
 * 用来查询音频数据
 * 
 * @author Administrator
 * 
 */
public class ScanMusics {
	/**
	 * 此方法实现查询音频数据库中所有的音频文件
	 * 
	 * @param context
	 *            用来获得ContentResolver
	 * @return 所有的音频文件集合对象
	 */
	public List<Music> query(Context context) {
		List<Music> musics = new ArrayList<Music>();
		/** 查询数据 */
		/** 1 获得Cursor对象 */
		Cursor cursor = context.getContentResolver().query(
				MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
				null);
		/** 2 判断cursor对象是否为空 */
		if (cursor != null) {
			Music m = null;
			/** 3 循环读取每条音乐数据 */
			while (cursor.moveToNext()) {
				m = new Music();
				/** 读取cursor中当前数据 */
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
