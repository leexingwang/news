package com.example.qianlong.modle.modleimpl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

import com.alibaba.fastjson.JSON;
import com.example.qianlong.bean.NewsEntity;
import com.example.qianlong.constants.Constants;
import com.example.qianlong.modle.GetNewsByNidModle;
import com.example.qianlong.utils.MD5;
import com.example.qianlong.utils.TLog;
import com.network.http.okhttp.OkHttpUtils;
import com.network.http.okhttp.callback.Callback;

public class GetNewsByNidImpl implements GetNewsByNidModle {
	private NewsEntity newsEntity = new NewsEntity();

	@Override
	public void getNewsByNid(int nid, final int type,
			final OnGetNewsByNidListener onGetNewsByNidListener) {
		String checkString = "" + nid + Constants.GetNewsALL_KEY;
		OkHttpUtils.post().url(Constants.GetNews_URL)
				.addParams("nid", nid + "")
				.addParams("check", MD5.getMD5(checkString)).build()
				.execute(new Callback<NewsEntity>() {

					@Override
					public NewsEntity parseNetworkResponse(Response response)
							throws Exception {
						String json = response.body().string();
						TLog.log(json);
						newsEntity = parseNews(json);
						return newsEntity;
					}

					@Override
					public void onError(Request request, Exception e) {
						onGetNewsByNidListener.onGetNewsError(type);
					}

					@Override
					public void onResponse(NewsEntity news) {

					}

					@Override
					public void onAfter(int isSuccess) {
						if (isSuccess == Callback.SUCCESS) {
							onGetNewsByNidListener.onGetNewsSuccess(newsEntity,
									type);
						} else {
							onGetNewsByNidListener.onGetNewsError(type);
						}
					}

				});
	}

	public NewsEntity parseNews(String json) throws Exception {
		NewsEntity news = JSON.parseObject(json, NewsEntity.class);
		return news;
	}
}
