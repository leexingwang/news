package com.example.topnewgrid.app;

import com.example.topnewgrid.db.ChannelSQLHelper;

import android.app.Application;

public class AppApplication extends Application {
	private static AppApplication mAppApplication;
	private ChannelSQLHelper sqlHelper;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mAppApplication = this;
	}

	/** ��ȡApplication */
	public static AppApplication getApp() {
		return mAppApplication;
	}
	
	/** ��ȡ���ݿ�Helper */
	public ChannelSQLHelper getSQLHelper() {
		if (sqlHelper == null)
			sqlHelper = new ChannelSQLHelper(mAppApplication);
		return sqlHelper;
	}
	
	/** �ݻ�Ӧ�ý���ʱ����� */
	public void onTerminate() {
		if (sqlHelper != null)
			sqlHelper.close();
		super.onTerminate();
	}

	public void clearAppCache() {
	}
}
