package com.mvc.demo.view;

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
import com.mvc.demo.model.Live7_24Model.OnLiveListener;
import com.mvc.demo.model.modelimpl.Live7_24ModelImpl;

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
public class CBNLiveTextMVCDemoActivity extends Activity implements OnLiveListener {

	List<Live> lives = new ArrayList<Live>();
	PullToRefreshListView listView;
	TimelineAdapter adapter;
	Live7_24ModelImpl live7_24ModelImpl;

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

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				live7_24ModelImpl.getLiveInfo("stringlist", "20", "1", "2", CBNLiveTextMVCDemoActivity.this);
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				live7_24ModelImpl.getLiveInfo("stringlist", "20", "2", "2", CBNLiveTextMVCDemoActivity.this);
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
		live7_24ModelImpl=new Live7_24ModelImpl();
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


	private void loadedCompleted(){
		listView.onPullDownRefreshComplete();
	    listView.onPullUpRefreshComplete();
	}
	@Override
	public void onSuccess(List<Live> lives) {
		loadedCompleted();
		lives.addAll(lives);
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onError(HttpException arg0, String arg1) {
		loadedCompleted();
	}
}
