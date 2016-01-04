package com.example.qianlong.view.activity;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.base.common.ui.pullrefreshview.PullToRefreshBase;
import com.base.common.ui.pullrefreshview.PullToRefreshBase.OnRefreshListener;
import com.base.common.ui.pullrefreshview.PullToRefreshListView;
import com.example.qianlong.R;
import com.example.qianlong.bean.Live;
import com.example.qianlong.utils.MD5;
import com.example.qianlong.view.adpter.TimelineAdapter;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * 
 * @author lixingwang
 * 
 */
public class CBNLiveTextActivity extends Activity {

	List<Live> lives = new ArrayList<Live>();
	PullToRefreshListView listView;
	TimelineAdapter adapter;
	String HOSTFORTUNE = "http://app.yicai.com/srv/app.ashx";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cbn_live7_24);
		init();
		listView = (PullToRefreshListView) findViewById(R.id.listview_live);
		// 上拉加载不可用
		listView.setPullLoadEnabled(true);
		// 滚动到底自动加载可用
		listView.setScrollLoadEnabled(true);
		listView.setDividerDrawable(null);
		adapter = new TimelineAdapter(this, lives);
		listView.getRefreshableView().setAdapter(adapter);
		listView.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				init();
				HttpUtils http = new HttpUtils();
				http.configCurrentHttpCacheExpiry(1000 * 1);
				RequestParams params = new RequestParams();
				params.addBodyParameter("command", "stringlist");
				params.addBodyParameter("pagesize", "20");
				params.addBodyParameter("page", "1");
				params.addBodyParameter("type", "2");
				params.addBodyParameter("check",

				MD5.MD5("stringlist" + 2 + 20 + 1 + "IGUadi9SuFix"));
				http.send(HttpMethod.POST, HOSTFORTUNE, params,
						new RequestCallBack() {
							@Override
							public void onFailure(HttpException arg0,
									String arg1) {
								Log.e("", arg1);
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
									lives.clear();
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
									adapter.notifyDataSetChanged();
									listView.onPullDownRefreshComplete();
									listView.onPullUpRefreshComplete();
								} catch (JSONException e) {
									e.printStackTrace();
								}
							};
						});
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				add();
				adapter.notifyDataSetChanged();
				listView.onPullDownRefreshComplete();
				listView.onPullUpRefreshComplete();
			}
		});
		listView.getRefreshableView().setOnItemClickListener(
				new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {

					}
				});
	}

	Live live = new Live();

	private void init() {
		for (int i = 0; i < 10; i++) {
			live.setAdminName("SXS");
			live.setLiveContent(i % 2 == 0 ? "12345"
					: "沪指收报3539.18点，下跌0.94%，成交额2540.3亿元。深成指收报12664.89点，下跌1.75%，成交额4325.7亿元。创业板收报2714.05点，下跌2.36%，成交额1118.9亿元。");
			live.setLiveDate("2015-12-29T15:08:0" + i);
			live.setLiveFrom("");
			live.setLiveID(52482);
			live.setLivePic("");
			live.setLiveType(4);
			lives.add(live);
		}

	}

	private void add() {
		for (int i = 0; i < 5; i++) {
			live.setAdminName("SXS");
			live.setLiveContent(i % 2 == 0 ? "12345"
					: "沪指收报3539.18点，下跌0.94%，成交额2540.3亿元。深成指收报12664.89点，下跌1.75%，成交额4325.7亿元。创业板收报2714.05点，下跌2.36%，成交额1118.9亿元。");
			live.setLiveDate("2015-12-29T15:08:0" + i);
			live.setLiveFrom("");
			live.setLiveID(52482);
			live.setLivePic("");
			live.setLiveType(4);
			lives.add(live);
		}

	}
}
