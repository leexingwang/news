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

	/** 获取Application */
	public static AppApplication getApp() {
		return mAppApplication;
	}

	/** 获取数据库Helper */
	public ChannelSQLHelper getSQLHelper() {
		if (sqlHelper == null)
			sqlHelper = new ChannelSQLHelper(mAppApplication);
		return sqlHelper;
	}

	/** 摧毁应用进程时候调用 */
	public void onTerminate() {
		if (sqlHelper != null)
			sqlHelper.close();
		super.onTerminate();
	}

	public void clearAppCache() {
	}

}
