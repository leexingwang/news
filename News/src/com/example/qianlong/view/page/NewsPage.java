package com.example.qianlong.view.page;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import com.base.common.ui.pagerindicator.TabPageIndicator;
import com.example.qianlong.R;
import com.example.qianlong.base.BasePage;
import com.example.qianlong.constants.Constants;
import com.example.qianlong.utils.QLApi;
import com.example.qianlong.utils.SharePrefUtil;
import com.example.qianlong.view.activity.CBNBannerActivity;
import com.example.qianlong.view.activity.CBNLiveTextActivity;
import com.example.qianlong.view.adpter.NewsPagerAdapter;
import com.topnewgrid.ChannelActivity;
import com.topnewgrid.bean.ChannelItem;

public class NewsPage extends BasePage implements
		ViewPager.OnPageChangeListener {

	private ArrayList<BasePage> itemNewsPages = new ArrayList<BasePage>();
	private TabPageIndicator indicator;
	private NewsPagerAdapter newsPagerAdapter;

	private ViewPager pagerItemNews;
	private int curIndex = 0;
	private List<ChannelItem> userChannelList = new ArrayList<ChannelItem>();

	public NewsPage(Context context) {
		super(context);
	}

	public NewsPage(Context context, ArrayList<ChannelItem> userChannelList) {
		super(context);
		this.userChannelList.clear();
		this.userChannelList = new ArrayList<ChannelItem>();
		this.userChannelList.addAll(userChannelList);

	}

	public void onResume() {
		super.onResume();
	}

	@Override
	protected View initView(LayoutInflater inflater) {
		View view = inflater.inflate(R.layout.news_center_frame, null);
		initTitleBar(view);
		indicator = (TabPageIndicator) view.findViewById(R.id.indicator);
		pagerItemNews = (ViewPager) view.findViewById(R.id.pager);
		titleTv.setOnClickListener(this);
		return view;
	}

	@Override
	public void initData() {
		String result = SharePrefUtil.getString(ct,
				QLApi.NEWS_CENTER_CATEGORIES, "");
		if (!TextUtils.isEmpty(result)) {
			processData(result);
		}
		initIndicator();
	}

	private void initIndicator() {
		itemNewsPages.clear();
		for (int i = 0; i < userChannelList.size(); i++) {
			if (i == 0)
				itemNewsPages.add(new HomePage(ct, userChannelList.get(i)));
			else {
				itemNewsPages.add(new ItemNewsPage(ct, userChannelList.get(i)));
			}
		}
		newsPagerAdapter = new NewsPagerAdapter(ct, itemNewsPages);
		pagerItemNews.removeAllViews();
		pagerItemNews.setAdapter(newsPagerAdapter);
		indicator.setOnPageChangeListener(this);
		itemNewsPages.get(0).initData();
		titleTv.setVisibility(View.VISIBLE);
		titleTv.setText(userChannelList.get(0).getName());
		indicator.setViewPager(pagerItemNews);
		indicator.setCurrentItem(0);
		isLoadSuccess = true;
	}

	private void processData(String result) {
	}

	@Override
	protected void processClick(View view) {
		switch (view.getId()) {
		case R.id.imgbtn_left:
			ct.startActivity(new Intent(ct, CBNLiveTextActivity.class));
			break;
		case R.id.imgbtn_right:
			ct.startActivity(new Intent(ct, CBNBannerActivity.class));
			break;
		case R.id.txt_title:
			((Activity) ct).startActivityForResult(new Intent(ct,
					ChannelActivity.class), Constants.CHANGE_CHANNEL);
			((Activity) ct).overridePendingTransition(
					R.anim.fade_form_up_to_down, android.R.anim.fade_out);
			break;
		default:
			break;
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		BasePage page;
		if (arg0 != 0) {
			page = (ItemNewsPage) itemNewsPages.get(arg0);
		} else {
			page = (HomePage) itemNewsPages.get(arg0);
		}
		if (!page.isLoadSuccess) {
			page.initData();

		}
		curIndex = arg0;
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int arg0) {
		titleTv.setVisibility(View.VISIBLE);
		titleTv.setText(userChannelList.get(arg0).getName());
	}

	public NewsPagerAdapter getNewsPagerAdapter() {
		return newsPagerAdapter;
	}

	public void setNewsPagerAdapter(NewsPagerAdapter newsPagerAdapter) {
		this.newsPagerAdapter = newsPagerAdapter;
	}

	public List<ChannelItem> getUserChannelList() {
		return userChannelList;
	}

	public void setUserChannelList(List<ChannelItem> userChannelList) {
		this.userChannelList = userChannelList;
	}

	public ViewPager getPagerItemNews() {
		return pagerItemNews;
	}

	public void setPagerItemNews(ViewPager pagerItemNews) {
		this.pagerItemNews = pagerItemNews;
	}

	public void updateUI(ArrayList<ChannelItem> list) {
//		itemNewsPages.clear();
//		for (int i = 0; i < list.size(); i++) {
//			if (i == 0)
//				itemNewsPages.add(new HomePage(ct, list.get(i)));
//			else {
//				itemNewsPages.add(new ItemNewsPage(ct, list.get(i)));
//			}
//		}
//		newsPagerAdapter = new NewsPagerAdapter(ct, itemNewsPages);
//		pagerItemNews.removeAllViews();
//		pagerItemNews.setAdapter(newsPagerAdapter);
	}
}
