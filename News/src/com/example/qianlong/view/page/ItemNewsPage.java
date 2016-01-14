package com.example.qianlong.view.page;

import java.util.ArrayList;
import java.util.HashSet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.base.common.ui.RollViewPager;
import com.base.common.ui.pullrefreshview.PullToRefreshBase;
import com.base.common.ui.pullrefreshview.PullToRefreshListView;
import com.base.common.ui.pullrefreshview.PullToRefreshBase.OnRefreshListener;
import com.example.qianlong.R;
import com.example.qianlong.base.BasePage;
import com.example.qianlong.bean.NewsListBean;
import com.example.qianlong.bean.NewsListBean.News;
import com.example.qianlong.bean.NewsListBean.TopNews;
import com.example.qianlong.utils.CommonUtil;
import com.example.qianlong.view.adpter.NewsAdapter;

public class ItemNewsPage extends BasePage {
	private PullToRefreshListView ptrLv;
	private TextView topNewsTitle;
	private LinearLayout mViewPagerLay;
	private LinearLayout dotLl;
	private View topNewsView;
	private String url;
	private String moreUrl;
	private ArrayList<News> news = new ArrayList<NewsListBean.News>();
	private ArrayList<TopNews> topNews;
	private NewsAdapter adapter;
	private ArrayList<View> dotList;
	private ArrayList<String> titleList, urlList;
	private HashSet<String> readSet = new HashSet<String>();
	private String hasReadIds;
	private RollViewPager mViewPager;
	public boolean isLoadSuccess;

	public ItemNewsPage(Context context, String url) {
		super(context);
		this.url = url;
	}

	@Override
	protected View initView(LayoutInflater inflater) {
		View view = inflater.inflate(R.layout.frag_item_news, null);
		topNewsView = inflater.inflate(R.layout.layout_roll_view, null);
		ptrLv = (PullToRefreshListView) view.findViewById(R.id.lv_item_news);
		topNewsTitle = (TextView) topNewsView.findViewById(R.id.top_news_title);
		mViewPagerLay = (LinearLayout) topNewsView
				.findViewById(R.id.top_news_viewpager);
		dotLl = (LinearLayout) topNewsView.findViewById(R.id.dots_ll);
		// 上拉加载不可用
		ptrLv.setPullLoadEnabled(false);
		// 滚动到底自动加载可用
		ptrLv.setScrollLoadEnabled(true);
		// 得到实际的ListView 设置点击
		ptrLv.getRefreshableView().setOnItemClickListener(
				new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
					}
				});
		setLastUpdateTime();
		// 设置下拉刷新的listener
		ptrLv.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				getNewsList(url, true);
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				getNewsList(moreUrl, false);

			}
		});
		return view;
	}

	@Override
	public void initData() {
		// hasReadIds = SharePrefUtil.getString(ct, Constants.READ_NEWS_IDS,
		// "");
		// String[] ids = hasReadIds.split(",");
		// for (String id : ids) {
		// readSet.add(id);
		// }
		// if (!TextUtils.isEmpty(url)) {
		// String result = SharePrefUtil.getString(ct, url, "");
		// if (!TextUtils.isEmpty(result)) {
		// processDataFromCache(true, result);
		// }
		// getNewsList(url, true);
		// }

	}

	private void getNewsList(final String loadUrl, final boolean isRefresh) {
	}

	@SuppressWarnings("unused")
	private void getNewsCommentCount(String countcommenturl,
			final ArrayList<News> newsList, final boolean isRefresh) {
	}

	@Override
	protected void processClick(View v) {

	}

	private void onLoaded() {
		dismissLoadingView();
		ptrLv.onPullDownRefreshComplete();
		ptrLv.onPullUpRefreshComplete();
	}

	private void initDot(int size) {
		dotList = new ArrayList<View>();
		dotLl.removeAllViews();
		for (int i = 0; i < size; i++) {
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					CommonUtil.dip2px(ct, 6), CommonUtil.dip2px(ct, 6));
			params.setMargins(5, 0, 5, 0);
			View m = new View(ct);
			if (i == 0) {
				m.setBackgroundResource(R.drawable.dot_focus);
			} else {
				m.setBackgroundResource(R.drawable.dot_normal);
			}
			m.setLayoutParams(params);
			dotLl.addView(m);
			dotList.add(m);
		}
	}

	private void setLastUpdateTime() {
		String text = CommonUtil.getStringDate();
		ptrLv.setLastUpdatedLabel(text);
	}

	private String countCommentUrl;

	public void processData(final boolean isRefresh, String result) {
	}

	public void processDataFromCache(boolean isRefresh, String result) {
	}

}
