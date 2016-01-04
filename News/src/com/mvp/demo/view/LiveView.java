package com.mvp.demo.view;

import java.util.List;

import com.example.qianlong.bean.Live;
import com.lidroid.xutils.exception.HttpException;

public interface LiveView {
	public void setLiveInfo(List<Live> lives);
	public void setLiveErrorInfo(HttpException arg0, String arg1);
}
