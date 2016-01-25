package com.example.qianlong.modle.modleimpl;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

import com.alibaba.fastjson.JSON;
import com.example.qianlong.application.AppApplication;
import com.example.qianlong.bean.News;
import com.example.qianlong.constants.Constants;
import com.example.qianlong.modle.GetNewsListByChannelModle;
import com.example.qianlong.utils.ACache;
import com.example.qianlong.utils.MD5;
import com.example.qianlong.utils.TLog;
import com.network.http.okhttp.OkHttpUtils;
import com.network.http.okhttp.callback.Callback;

public class GetNewsListByChannelImpl implements GetNewsListByChannelModle {
	private List<News> listNews;
	private String cacheKey;

	@Override
	public void getNewsListByChannel(final int cid, final int pageSize,
			final int page, final int loadType,
			final OnNewsListByChannelListener newsListByChannelListener) {
		String checkString = "" + cid + pageSize + page
				+ Constants.GetNewsALL_KEY;
		cacheKey = Constants.GetNewsListByChannel_URL + cid + pageSize + page
				+ loadType;
		OkHttpUtils.post().url(Constants.GetNewsListByChannel_URL)
				.addParams("cid", cid + "")
				.addParams("pageSize", pageSize + "")
				.addParams("page", page + "")
				.addParams("check", MD5.getMD5(checkString)).build()
				.execute(new Callback<List<News>>() {
					@Override
					public List<News> parseNetworkResponse(Response response)
							throws Exception {
						listNews = new ArrayList<News>();
						String json = response.body().string();
						if (response.isSuccessful()) {
							ACache.get().remove(cacheKey);
							ACache.get().put(cacheKey, json, ACache.TIME_YEAR);
						}
						listNews = parseNews(json);
						return listNews;
					}

					@Override
					public void onError(Request request, Exception e) {
					}

					@Override
					public void onResponse(List<News> response) {

					}

					@Override
					public void onAfter(int isSuccess) {
						if (isSuccess == Callback.SUCCESS) {
							newsListByChannelListener.onSuccess(listNews,
									loadType);
						} else {
							newsListByChannelListener.onError(loadType);
						}
					}

				});
	}

	public synchronized List<News> parseNews(String json) throws Exception {
		List<News> listNews = new ArrayList<News>();
		listNews.addAll(JSON.parseArray(json, News.class));
		return listNews;
	}
}
