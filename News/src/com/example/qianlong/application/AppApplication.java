package com.example.qianlong.application;


import okhttp3.OkHttpClient;

import com.network.http.okhttp.OkHttpUtils;
import com.topnewgrid.db.ChannelSQLHelper;

import android.app.Application;

public class AppApplication extends Application {

	private static AppApplication mAppApplication;
	private ChannelSQLHelper sqlHelper;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mAppApplication = this;
		OkHttpClient client = OkHttpUtils.getInstance().getOkHttpClient();
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
