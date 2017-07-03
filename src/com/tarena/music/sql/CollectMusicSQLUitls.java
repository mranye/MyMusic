package com.tarena.music.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CollectMusicSQLUitls {
	private MusicDBHelper mdb;

	/* ͨ�����췽��������MusicDBHelper�Ķ��� */
	public CollectMusicSQLUitls(Context context) {
		mdb = new MusicDBHelper(context,// �����Ķ���
				"tarena.db",// ���ݿ�����
				null,// һ�㶼Ϊnull
				1);// ���ݿ�汾
	}

	/** ʵ�ֲ������ݹ��� */
	public long insert(String table, ContentValues values) {
		/** ���sqlitedatebase */
		SQLiteDatabase sdb = mdb.getWritableDatabase();
		long index = sdb.insert(table, null, values);
		sdb.close();// �������ݿ����ǵùر�
		return index;
	}

	/**
	 * ʵ�ָ��½���
	 * 
	 * @param table
	 *            ����
	 * @param values
	 *            ���µ�����
	 * @param whereClause
	 *            λ������
	 * @param whereArgs
	 *            ����λ������ֵ
	 * @return ���¶�����
	 */
	public long update(String table, ContentValues values, String whereClause,
			String[] whereArgs) {
		SQLiteDatabase sdb = mdb.getWritableDatabase();
		long index = sdb.update(table, values, whereClause, whereArgs);
		sdb.close();//
		return index;

	}

	/**
	 * ʵ��ɾ������
	 * 
	 * @param table
	 *            ����
	 * @param whereClause
	 *            ɾ������
	 * @param whereArgs
	 *            ɾ������ֵ
	 * @return ɾ�������� ����Ƿ���0 ����û�����ݲ���
	 */
	public long delete(String table, String whereClause, String[] whereArgs) {
		SQLiteDatabase sdb = mdb.getWritableDatabase();
		int index = sdb.delete(table, whereClause, whereArgs);
		sdb.close();//
		return index;
	}

	/**
	 * ʵ�ֲ�ѯ����
	 * 
	 * @param table
	 *            ����
	 * @param selection
	 *            ��ѯ����
	 * @param selectionArgs
	 *            ��ѯ����ֵ
	 * @param orderBy
	 *            ����
	 * @return �α�Cursor �������в�ѯ��������
	 */
	public Cursor query(String table, String selection, String[] selectionArgs,
			String orderBy) {
		/** ��ȡ��ȡ���ݿ�Ķ��� */
		SQLiteDatabase sdb = mdb.getReadableDatabase();
		return sdb.query(table, null, selection, selectionArgs, null, null,
				orderBy);
	}

	/**
	 * �������ݿ�����һ��������
	 */
	class MusicDBHelper extends SQLiteOpenHelper {

		public MusicDBHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
		}

		/**
		 * �����������ݿ��е�һ���� smusic
		 */
		@Override
		public void onCreate(SQLiteDatabase db) {
			String sql = "create table if not exists smusic("
					+ "_id Integer primary key autoincrement,"
					+ "title text(50) not null,"
					+ "songname text(50) not null,"
					+ "songid text(30) not null," + "pic text(200) not null)";
			db.execSQL(sql);
			Log.i("TAG", "�������ݱ�smusic�ɹ�");
		}

		/** ���ݿ�汾����ʱִ�� */
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			// һ������ǹ̶��� ����һ�� Ҫ��ҵ������
			String sql = "drop table if exists smusic";
			db.execSQL(sql);
			onCreate(db);
		}

	}

}
