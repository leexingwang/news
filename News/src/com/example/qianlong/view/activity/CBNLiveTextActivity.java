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
import com.example.qianlong.bean.Live;
import com.example.qianlong.constants.Constants;
import com.example.qianlong.modle.GetLiveTextModle;
import com.example.qianlong.modle.modleimpl.GetLiveTextModleImpl;
import com.example.qianlong.utils.ACache;
import com.example.qianlong.utils.CommonUtil;
import com.example.qianlong.utils.SharePrefUtil;
import com.example.qianlong.view.adpter.NewsTextLiveTimelineAdapter;

import android.content.Intent;
import android.os.Bundle;
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
		GetLiveTextModle.OnLiveListener, OnToggleChanged, OnClickListener,
		OnRefreshListener<ListView>, OnItemClickListener {

	private List<Live> lives = new ArrayList<Live>();
	private PullToRefreshListView listView;
	private NewsTextLiveTimelineAdapter adapter;
	private GetLiveTextModleImpl live7_24ModelImpl;
	private ToggleButton toggleButton;
	private int pageNumber = 1;
	private int newsType = 0;

	@Override
	protected void initView() {
		setContentView(R.layout.cbn_live7_24);
		initTitleBar();
		init();
		getAcacheInfo();
		listView = (PullToRefreshListView) findViewById(R.id.listview_live);
		// 上拉加载不可用
		listView.setPullLoadEnabled(true);
		// 滚动到底自动加载可用
		listView.setScrollLoadEnabled(true);
		listView.setDividerDrawable(null);
		adapter = new NewsTextLiveTimelineAdapter(this, lives);
		listView.getRefreshableView().setAdapter(adapter);
		listView.setOnRefreshListener(this);
		listView.getRefreshableView().setOnItemClickListener(this);

	}

	private void getAcacheInfo() {
		try {
			String json = ACache.get().getAsString(
					Constants.GetLives_URL + 20 + 1
							+ GetLiveTextModleImpl.LIVE_LOAD_REFRESH);
			List<Live> lives = live7_24ModelImpl.parseNews(json);
			if (lives.size() > 0) {
				this.lives = lives;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void initData() {
		live7_24ModelImpl.getLiveInfo(20, 1,
				GetLiveTextModleImpl.LIVE_LOAD_REFRESH,
				CBNLiveTextActivity.this);
		setLastUpdateTime();
	}

	private void init() {
		live7_24ModelImpl = new GetLiveTextModleImpl();
		toggleButton = (ToggleButton) findViewById(R.id.toggle_live);
		toggleButton.setOnToggleChanged(this);
		initToggle();
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
	public void onLiveSuccess(List<Live> lives, int loadType) {
		loadedCompleted();
		if (lives.size() == 0) {
			return;
		}
		if (loadType == GetLiveTextModleImpl.LIVE_LOAD_MORE) {
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

	@Override
	public void onLiveError(int loadType) {
		loadedCompleted();
	}

	private void setLastUpdateTime() {
		String text = CommonUtil.getStringDate();
		listView.setLastUpdatedLabel(text);
	}

	@Override
	public void onToggle(boolean on) {
		SharePrefUtil.saveBoolean(this, SharePrefUtil.KEY.IS_7_24_RED, on);
		if (on) {
			newsType = 2;
		} else {
			newsType = 0;
		}
		live7_24ModelImpl.getLiveInfo(20, 1,
				GetLiveTextModleImpl.LIVE_LOAD_REFRESH,
				CBNLiveTextActivity.this);
	}

	@Override
	protected void processClick(View view) {
		switch (view.getId()) {
		case R.id.imgbtn_right:
			pageNumber = 1;
			live7_24ModelImpl.getLiveInfo(20, 1,
					GetLiveTextModleImpl.LIVE_LOAD_REFRESH,
					CBNLiveTextActivity.this);
			break;
		default:
			break;
		}
	}

	@Override
	protected void finishChild() {

	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		live7_24ModelImpl.getLiveInfo(20, 1,
				GetLiveTextModleImpl.LIVE_LOAD_REFRESH,
				CBNLiveTextActivity.this);
		setLastUpdateTime();

	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		live7_24ModelImpl.getLiveInfo(20, (pageNumber + 1),
				GetLiveTextModleImpl.LIVE_LOAD_REFRESH,
				CBNLiveTextActivity.this);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// showToast(lives.get(position).getLiveContent());
		Intent intent = new Intent();
		intent.setClass(this, CBNLiveTextDetailActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable(Constants.TEXT_LIVE, lives.get(position));
		intent.putExtras(bundle);
		startActivity(intent);
	}
}
