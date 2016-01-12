package com.example.qianlong.view.page;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
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
import com.example.qianlong.view.adpter.NewsPagerAdapter;

public class NewsPage extends BasePage {

	private ArrayList<ItemNewsPage> pages = new ArrayList<ItemNewsPage>();
	private TabPageIndicator indicator;
	private NewsPagerAdapter adapter;
	private ViewPager pager;
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
		pager = (ViewPager) view.findViewById(R.id.pager);
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
		pages.clear();
		for (int i = 0; i <= 9; i++) {
			pages.add(new ItemNewsPage(ct, ""));
		}
		adapter = new NewsPagerAdapter(ct, pages);
		pager.removeAllViews();
		pager.setAdapter(adapter);

		indicator.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				ItemNewsPage page = pages.get(arg0);
				if (!page.isLoadSuccess) {
					page.initData();
				}
				curIndex = arg0;

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
		pages.get(0).initData();
		indicator.setViewPager(pager);
		indicator.setCurrentItem(curIndex);
		isLoadSuccess = true;
	}

	private void processData(String result) {
	}

	@Override
	protected void processClick(View v) {

	}

}
