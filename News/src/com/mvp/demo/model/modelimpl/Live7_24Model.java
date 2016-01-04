package com.mvp.demo.model.modelimpl;

import java.util.List;

import com.example.qianlong.bean.Live;
import com.lidroid.xutils.exception.HttpException;

public interface Live7_24Model {

	public void getLiveInfo(String command, String pagesize, String page,
			String type, OnLiveListener onLiveListener);

	public interface OnLiveListener {
		public void onSuccess(List<Live> lives);

		public void onError(HttpException arg0, String arg1);
	}
}
