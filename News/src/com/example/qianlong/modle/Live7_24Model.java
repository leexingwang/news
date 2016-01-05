package com.example.qianlong.modle;

import java.util.List;

import com.example.qianlong.bean.Live;
import com.lidroid.xutils.exception.HttpException;

public interface Live7_24Model {

	public void getLiveInfo(String command, String pagesize, String page,
			String type, OnLiveListener onLiveListener, int loadType);

	public interface OnLiveListener {
		public void onSuccess(List<Live> lives,int loadType);

		public void onError(HttpException arg0, String arg1);
	}
}
