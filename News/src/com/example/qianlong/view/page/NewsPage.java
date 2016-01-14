package com.example.qianlong.view.page;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import com.base.common.ui.pagerindicator.TabPageIndicator;
import com.example.qianlong.R;
import com.example.qianlong.base.BasePage;
import com.example.qianlong.utils.QLApi;
import com.example.qianlong.utils.SharePrefUtil;
import com.example.qianlong.view.activity.CBNBannerActivity;
import com.example.qianlong.view.activity.CBNLiveTextActivity;
import com.example.qianlong.view.adpter.NewsPagerAdapter;

public class NewsPage extends BasePage implements
		ViewPager.OnPageChangeListener {

	private ArrayList<BasePage> itemNewsPages = new ArrayList<BasePage>();

	private ArrayList<BasePage> basePages = new ArrayList<BasePage>();
	private TabPageIndicator indicator;
	private NewsPagerAdapter newsPagerAdapter;
	private ViewPager pagerItemNews;
	private int curIndex = 0;

	public NewsPage(Context context) {
		super(context);
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
		for (int i = 0; i <= 2; i++) {
			itemNewsPages.add(new ItemNewsPage(ct, ""));
		}
		itemNewsPages.add(0, new HomePage(ct));
		newsPagerAdapter = new NewsPagerAdapter(ct, itemNewsPages);
		pagerItemNews.removeAllViews();
		pagerItemNews.setAdapter(newsPagerAdapter);
		indicator.setOnPageChangeListener(this);
		itemNewsPages.get(0).initData();
		indicator.setViewPager(pagerItemNews);
		indicator.setCurrentItem(curIndex);
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

	}

}
