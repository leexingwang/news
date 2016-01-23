package com.example.qianlong.modle.modleimpl;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

import com.alibaba.fastjson.JSON;
import com.example.qianlong.bean.Live;
import com.example.qianlong.constants.Constants;
import com.example.qianlong.modle.GetLiveTextModle;
import com.example.qianlong.utils.ACache;
import com.example.qianlong.utils.MD5;
import com.example.qianlong.utils.TLog;
import com.network.http.okhttp.OkHttpUtils;
import com.network.http.okhttp.callback.Callback;

public class GetLiveTextModleImpl implements GetLiveTextModle {
	private List<Live> lives = new ArrayList<Live>();
	public static int LIVE_LOAD_MORE = 1;
	public static int LIVE_LOAD_REFRESH = 2;
	private String cacheKey;

	public void getLiveInfo(final int pagesize, final int page,
			final int loadType, final OnLiveListener onLiveListener) {
		cacheKey = Constants.GetLives_URL + pagesize + page + loadType;
		String checkString = "" + pagesize + page + Constants.GetNewsALL_KEY;
		OkHttpUtils.post().url(Constants.GetLives_URL)
				.addParams("pagesize", pagesize + "")
				.addParams("page", page + "")
				.addParams("check", MD5.getMD5(checkString)).build()
				.execute(new Callback<List<Live>>() {
					@Override
					public List<Live> parseNetworkResponse(Response response)
							throws Exception {
						String json = response.body().string();
						if (response.isSuccessful()) {
							ACache.get().remove(cacheKey);
							ACache.get().put(cacheKey, json, ACache.TIME_YEAR);
						}
						lives = parseNews(json);
						return lives;
					}

					@Override
					public void onError(Request request, Exception e) {
						onLiveListener.onLiveError(loadType);
					}

					@Override
					public void onResponse(List<Live> lives) {

					}

					@Override
					public void onAfter(int isSuccess) {
						if (isSuccess == Callback.SUCCESS) {
							onLiveListener.onLiveSuccess(lives, loadType);
						} else {
							onLiveListener.onLiveError(loadType);
						}
					}
				});
	}

	public List<Live> parseNews(String json) throws Exception {
		List<Live> lives = JSON.parseArray(json, Live.class);
		return lives;
	}
}
