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

	/* 通过构造方法创建出MusicDBHelper的对象 */
	public CollectMusicSQLUitls(Context context) {
		mdb = new MusicDBHelper(context,// 上下文对象
				"tarena.db",// 数据库名称
				null,// 一般都为null
				1);// 数据库版本
	}

	/** 实现插入数据功能 */
	public long insert(String table, ContentValues values) {
		/** 获得sqlitedatebase */
		SQLiteDatabase sdb = mdb.getWritableDatabase();
		long index = sdb.insert(table, null, values);
		sdb.close();// 操作数据库对象记得关闭
		return index;
	}

	/**
	 * 实现更新界面
	 * 
	 * @param table
	 *            表名
	 * @param values
	 *            更新的内容
	 * @param whereClause
	 *            位置条件
	 * @param whereArgs
	 *            更新位置条件值
	 * @return 更新多少条
	 */
	public long update(String table, ContentValues values, String whereClause,
			String[] whereArgs) {
		SQLiteDatabase sdb = mdb.getWritableDatabase();
		long index = sdb.update(table, values, whereClause, whereArgs);
		sdb.close();//
		return index;

	}

	/**
	 * 实现删除功能
	 * 
	 * @param table
	 *            表名
	 * @param whereClause
	 *            删除条件
	 * @param whereArgs
	 *            删除条件值
	 * @return 删除的条数 如果是返回0 代表没有数据产出
	 */
	public long delete(String table, String whereClause, String[] whereArgs) {
		SQLiteDatabase sdb = mdb.getWritableDatabase();
		int index = sdb.delete(table, whereClause, whereArgs);
		sdb.close();//
		return index;
	}

	/**
	 * 实现查询功能
	 * 
	 * @param table
	 *            表名
	 * @param selection
	 *            查询条件
	 * @param selectionArgs
	 *            查询条件值
	 * @param orderBy
	 *            排序
	 * @return 游标Cursor 保存所有查询到的数据
	 */
	public Cursor query(String table, String selection, String[] selectionArgs,
			String orderBy) {
		/** 获取读取数据库的对象 */
		SQLiteDatabase sdb = mdb.getReadableDatabase();
		return sdb.query(table, null, selection, selectionArgs, null, null,
				orderBy);
	}

	/**
	 * 操作数据库对象的一个工具类
	 */
	class MusicDBHelper extends SQLiteOpenHelper {

		public MusicDBHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
		}

		/**
		 * 用来创建数据库中的一个表 smusic
		 */
		@Override
		public void onCreate(SQLiteDatabase db) {
			String sql = "create table if not exists smusic("
					+ "_id Integer primary key autoincrement,"
					+ "title text(50) not null,"
					+ "songname text(50) not null,"
					+ "songid text(30) not null," + "pic text(200) not null)";
			db.execSQL(sql);
			Log.i("TAG", "创建数据表smusic成功");
		}

		/** 数据库版本更新时执行 */
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			// 一般情况是固定的 但不一定 要看业务需求
			String sql = "drop table if exists smusic";
			db.execSQL(sql);
			onCreate(db);
		}

	}

}
