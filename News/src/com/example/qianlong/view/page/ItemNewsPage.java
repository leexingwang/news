package com.example.qianlong.view.page;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.base.common.ui.pullrefreshview.PullToRefreshBase;
import com.base.common.ui.pullrefreshview.PullToRefreshListView;
import com.base.common.ui.pullrefreshview.PullToRefreshBase.OnRefreshListener;
import com.example.qianlong.R;
import com.example.qianlong.base.BasePage;
import com.example.qianlong.utils.CommonUtil;
import com.example.qianlong.view.adpter.NewsAdapter;
import com.topnewgrid.bean.ChannelItem;

public class ItemNewsPage extends BasePage implements
		OnRefreshListener<ListView>, OnItemClickListener {
	private PullToRefreshListView ptrLv;
	private ChannelItem channelItem;
	private List<String> strings = new ArrayList<String>();
	private NewsAdapter adapter;

	public ItemNewsPage(Context context, ChannelItem channelItem) {
		super(context);
		this.channelItem = channelItem;
		channelItemName = channelItem.getName();
	}

	@Override
	protected View initView(LayoutInflater inflater) {
		View view = inflater.inflate(R.layout.frag_item_news, null);
		ptrLv = (PullToRefreshListView) view.findViewById(R.id.lv_item_news);
		// 上拉加载不可用
		ptrLv.setPullLoadEnabled(true);
		// 滚动到底自动加载可用
		ptrLv.setScrollLoadEnabled(true);
		// 得到实际的ListView 设置点击
		ptrLv.getRefreshableView().setOnItemClickListener(this);
		// 设置下拉刷新的listener
		ptrLv.setOnRefreshListener(this);
		setLastUpdateTime();
		return view;
	}

	@Override
	public void initData() {
		strings.clear();
		for (int i = 0; i < 40; i++) {
			strings.add(channelItem.name);
		}
		adapter = new NewsAdapter(ct, strings);
		ptrLv.getRefreshableView().setAdapter(adapter);
		isLoadSuccess = true;
		onLoaded();
	}

	@Override
	protected void processClick(View v) {

	}

	private void onLoaded() {
		dismissLoadingView();
		ptrLv.onPullDownRefreshComplete();
		ptrLv.onPullUpRefreshComplete();
		isLoadSuccess = true;
	}

	private void setLastUpdateTime() {
		String text = CommonUtil.getStringDate();
		ptrLv.setLastUpdatedLabel(text);
	}

	public void processData(final boolean isRefresh, String result) {
	}

	public void processDataFromCache(boolean isRefresh, String result) {
	}

	public ChannelItem getChannelItem() {
		return channelItem;
	}

	public void setChannelItem(ChannelItem channelItem) {
		this.channelItem = channelItem;
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		strings.add(0, "onPullDownToRefresh");
		adapter.notifyDataSetChanged();
		onLoaded();
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		strings.add("onPullUpToRefresh");
		adapter.notifyDataSetChanged();
		onLoaded();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

	}
}
