package com.mvp.demo.model.modelimpl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.example.qianlong.bean.Live;
import com.example.qianlong.utils.MD5;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class Live7_24ModelImpl implements Live7_24Model{
	String HOSTFORTUNE = "http://app.yicai.com/srv/app.ashx";
	List<Live> lives=new ArrayList<Live>();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getLiveInfo(String command, String pagesize, String page,
			String type, final OnLiveListener onLiveListener) {
		HttpUtils http = new HttpUtils();
		http.configCurrentHttpCacheExpiry(1000 * 1);
		RequestParams params = new RequestParams();
		params.addBodyParameter("command", command);
		params.addBodyParameter("pagesize", pagesize);
		params.addBodyParameter("page", page);
		params.addBodyParameter("type", type);
		params.addBodyParameter("check",
		MD5.MD5("stringlist" + page + pagesize + type + "IGUadi9SuFix"));
		http.send(HttpMethod.POST, HOSTFORTUNE, params,
				new RequestCallBack() {
					@Override
					public void onFailure(HttpException arg0,
							String arg1) {
						onLiveListener.onError(arg0,arg1);
					}

					@Override
					public void onSuccess(ResponseInfo arg0) {
						String strNewsList = "";
						try {
							JSONObject jsonObject = new JSONObject(
									arg0.result.toString());
							strNewsList = jsonObject.getString("Lives");

							JSONArray jsonArray = new JSONArray(
									strNewsList);
							for (int i = 0; i < jsonArray.length(); i++) {
								JSONObject jsonObject1 = jsonArray
										.getJSONObject(i);
								Live liveTxt = new Live();
								liveTxt.setLiveFrom(jsonObject1
										.getString("LiveFrom"));
								liveTxt.setLivePic(jsonObject1
										.getString("LivePic"));
								liveTxt.setLiveContent(jsonObject1
										.getString("LiveContent"));
								liveTxt.setLiveDate(jsonObject1
										.getString("LiveDate"));
								liveTxt.setAdminName(jsonObject1
										.getString("AdminName"));
								lives.add(liveTxt);
							}
							onLiveListener.onSuccess(lives);
						} catch (JSONException e) {
							e.printStackTrace();
						}
					};
				});
	}

}
