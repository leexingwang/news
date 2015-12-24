package com.topnewgrid.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ChannelDBUtil {
	private static ChannelDBUtil mInstance;
	private Context mContext;
	private ChannelSQLHelper mSQLHelp;
	private SQLiteDatabase mSQLiteDatabase;

	private ChannelDBUtil(Context context) {
		mContext = context;
		mSQLHelp = new ChannelSQLHelper(context);
		mSQLiteDatabase = mSQLHelp.getWritableDatabase();
	}
	/**
	 * ��ʼ�����ݿ����DBUtil��
	 */
	public static ChannelDBUtil getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new ChannelDBUtil(context);
		}
		return mInstance;
	}
	/**
	 * �ر����ݿ�
	 */
	public void close() {
		mSQLHelp.close();
		mSQLHelp = null;
		mSQLiteDatabase.close();
		mSQLiteDatabase = null;
		mInstance = null;
	}

	/**
	 * �������
	 */
	public void insertData(ContentValues values) {
		mSQLiteDatabase.insert(ChannelSQLHelper.TABLE_CHANNEL, null, values);
	}

	/**
	 * ��������
	 * 
	 * @param values
	 * @param whereClause
	 * @param whereArgs
	 */
	public void updateData(ContentValues values, String whereClause,
			String[] whereArgs) {
		mSQLiteDatabase.update(ChannelSQLHelper.TABLE_CHANNEL, values, whereClause,
				whereArgs);
	}

	/**
	 * ɾ������
	 * 
	 * @param whereClause
	 * @param whereArgs
	 */
	public void deleteData(String whereClause, String[] whereArgs) {
		mSQLiteDatabase
				.delete(ChannelSQLHelper.TABLE_CHANNEL, whereClause, whereArgs);
	}

	/**
	 * ��ѯ����
	 * 
	 * @param columns
	 * @param selection
	 * @param selectionArgs
	 * @param groupBy
	 * @param having
	 * @param orderBy
	 * @return
	 */
	public Cursor selectData(String[] columns, String selection,
			String[] selectionArgs, String groupBy, String having,
			String orderBy) {
		Cursor cursor = mSQLiteDatabase.query(ChannelSQLHelper.TABLE_CHANNEL,columns, selection, selectionArgs, groupBy, having, orderBy);
		return cursor;
	}
}