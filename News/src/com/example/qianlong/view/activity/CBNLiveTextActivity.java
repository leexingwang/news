package com.example.qianlong.view.activity;

import java.util.ArrayList;
import java.util.List;

import com.base.common.ui.ToggleButton;
import com.base.common.ui.ToggleButton.OnToggleChanged;
import com.base.common.ui.pullrefreshview.PullToRefreshBase;
import com.base.common.ui.pullrefreshview.PullToRefreshBase.OnRefreshListener;
import com.base.common.ui.pullrefreshview.PullToRefreshListView;
import com.example.qianlong.R;
import com.example.qianlong.base.BaseActivity;
import com.example.qianlong.bean.LiveBean;
import com.example.qianlong.modle.LiveTextModle.OnLiveListener;
import com.example.qianlong.modle.modleimpl.LiveTextModleImpl;
import com.example.qianlong.utils.CommonUtil;
import com.example.qianlong.utils.SharePrefUtil;
import com.example.qianlong.view.adpter.TimelineAdapter;
import com.lidroid.xutils.exception.HttpException;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * 
 * @author lixingwang
 * 
 */
public class CBNLiveTextActivity extends BaseActivity implements
		OnLiveListener, OnToggleChanged, OnClickListener {

	private List<LiveBean> lives = new ArrayList<LiveBean>();
	private PullToRefreshListView listView;
	private TimelineAdapter adapter;
	private LiveTextModleImpl live7_24ModelImpl;
	private ToggleButton toggleButton;
	private int pageNumber = 1;
	private int newsType = 0;

	@Override
	protected void initView() {
		setContentView(R.layout.cbn_live7_24);
		initTitleBar();
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
				live7_24ModelImpl.getLiveInfo("stringlist", "20", "1", newsType
						+ "", CBNLiveTextActivity.this,
						LiveTextModleImpl.LIVE_LOAD_REFRESH);
				setLastUpdateTime();
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				live7_24ModelImpl.getLiveInfo("stringlist", "20",
						(pageNumber + 1) + "", newsType + "",
						CBNLiveTextActivity.this,
						LiveTextModleImpl.LIVE_LOAD_MORE);
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

	@Override
	protected void initData() {

	}

	private void init() {
		live7_24ModelImpl = new LiveTextModleImpl();
		toggleButton = (ToggleButton) findViewById(R.id.toggle_live);
		toggleButton.setOnToggleChanged(this);
		initToggle();
		live7_24ModelImpl.getLiveInfo("stringlist", "20", "1", newsType + "",
				CBNLiveTextActivity.this, LiveTextModleImpl.LIVE_LOAD_REFRESH);
		rightImgBtn.setOnClickListener(this);
		rightImgBtn.setVisibility(View.VISIBLE);
		rightImgBtn
				.setBackgroundResource(R.drawable.base_action_bar_action_refresh);
	}

	private void initToggle() {
		boolean isRed = SharePrefUtil.getBoolean(this,
				SharePrefUtil.KEY.IS_7_24_RED,
				SharePrefUtil.KEY.IS_7_24_RED_DEFAULT);
		if (isRed) {
			toggleButton.setToggleOn();
			newsType = 2;
		} else {
			toggleButton.setToggleOff();
			newsType = 0;
		}
	}

	private void loadedCompleted() {
		listView.onPullDownRefreshComplete();
		listView.onPullUpRefreshComplete();
	}

	@Override
	public void onSuccess(List<LiveBean> lives, int loadType) {
		loadedCompleted();
		if (loadType == LiveTextModleImpl.LIVE_LOAD_MORE) {
			this.lives.addAll(lives);
			if (lives.size() != 20)
				pageNumber++;
		} else {
			this.lives.clear();
			this.lives.addAll(lives);
			pageNumber = 1;
		}
		adapter.notifyDataSetChanged();
	}

	private void setLastUpdateTime() {
		String text = CommonUtil.getStringDate();
		listView.setLastUpdatedLabel(text);
	}

	@Override
	public void onError(HttpException arg0, String arg1) {
		loadedCompleted();
	}

	@Override
	public void onToggle(boolean on) {
		SharePrefUtil.saveBoolean(this, SharePrefUtil.KEY.IS_7_24_RED, on);
		if (on) {
			newsType = 2;
		} else {
			newsType = 0;
		}
		live7_24ModelImpl.getLiveInfo("stringlist", "20", "1", newsType + "",
				CBNLiveTextActivity.this, LiveTextModleImpl.LIVE_LOAD_REFRESH);
	}

	@Override
	protected void processClick(View view) {
		switch (view.getId()) {
		case R.id.imgbtn_right:
			pageNumber = 1;
			live7_24ModelImpl.getLiveInfo("stringlist", "20", "1", newsType
					+ "", CBNLiveTextActivity.this,
					LiveTextModleImpl.LIVE_LOAD_REFRESH);
			break;
		default:
			break;
		}
	}

	@Override
	protected void finishChild() {
		// TODO Auto-generated method stub
		
	}
}
