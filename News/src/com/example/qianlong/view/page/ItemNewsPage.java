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
import com.example.qianlong.bean.News;
import com.example.qianlong.constants.Constants;
import com.example.qianlong.modle.GetNewsListByChannelModle.OnNewsListByChannelListener;
import com.example.qianlong.modle.modleimpl.GetNewsListByChannelImpl;
import com.example.qianlong.utils.ACache;
import com.example.qianlong.utils.CommonUtil;
import com.example.qianlong.view.adpter.NewsItemAdapter;
import com.topnewgrid.bean.ChannelItem;

public class ItemNewsPage extends BasePage implements
		OnRefreshListener<ListView>, OnItemClickListener,
		OnNewsListByChannelListener {
	private PullToRefreshListView ptrLv;
	private ChannelItem channelItem;
	private int pageSize;
	private int page;
	private List<News> news;
	private GetNewsListByChannelImpl newsListByChannelImpl;
	private int NEWS_ID = 0;
	private NewsItemAdapter itemAdapter;

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
		newsListByChannelImpl = new GetNewsListByChannelImpl();
		setLastUpdateTime();
		page = 1;
		pageSize = 20;
		return view;
	}

	@Override
	public void initData() {
		news = new ArrayList<News>();
		NEWS_ID = channelItem.getId();
		getNewsACacheInfo();
		itemAdapter = new NewsItemAdapter(ct, news);
		ptrLv.getRefreshableView().setAdapter(itemAdapter);
		newsListByChannelImpl.getNewsListByChannel(NEWS_ID, pageSize, page,
				NEWS_ID, this);
		onLoaded();
	}

	@Override
	protected void processClick(View v) {

	}

	private void onLoaded() {
		dismissLoadingView();
		ptrLv.onPullDownRefreshComplete();
		ptrLv.onPullUpRefreshComplete();
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
		onLoaded();
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		onLoaded();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

	}

	private synchronized void getNewsACacheInfo() {
		try {
			String json = ACache.get().getAsString(
					Constants.GetNewsListByChannel_URL + NEWS_ID + pageSize
							+ page + NEWS_ID);
			List<News> news = newsListByChannelImpl.parseNews(json);
			for (int i = 0; i < news.size(); i++) {
				this.news.add(news.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onSuccess(List<News> news, int loadType) {
		// TODO Auto-generated method stub
		if (loadType == NEWS_ID) {
			this.news.clear();
			for (int i = 0; i < news.size(); i++) {
				this.news.add(news.get(i));
			}
			itemAdapter = new NewsItemAdapter(ct, this.news);
			ptrLv.getRefreshableView().setAdapter(itemAdapter);
			isLoadSuccess = true;
			onLoaded();
		}
	}

	@Override
	public void onError(int loadType) {
		// TODO Auto-generated method stub

	}
}
