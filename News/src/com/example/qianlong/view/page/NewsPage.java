package com.example.qianlong.view.page;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;

import com.base.common.ui.pagerindicator.TabPageIndicator;
import com.example.qianlong.R;
import com.example.qianlong.R.id;
import com.example.qianlong.R.layout;
import com.example.qianlong.base.BasePage;
import com.example.qianlong.bean.NewsCenterCategories.ChildNewsCate;
import com.example.qianlong.bean.NewsCenterCategories.NewsCategory;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class NewsPage extends BasePage {

	private NewsCategory category;

	public NewsPage(Context ct, NewsCategory newsCategory) {
		super(ct);
		category = newsCategory;
	}

	@Override
	protected View initView(LayoutInflater inflater) {
		View view = inflater.inflate(R.layout.frag_news, null);
		ViewUtils.inject(this, view);
		return view;
	}

	@Override
	public void initData() {
		initIndicator();

	}

	private ArrayList<ItemNewsPage> pages = new ArrayList<ItemNewsPage>();
	@ViewInject(R.id.indicator)
	private TabPageIndicator indicator;
	private NewsPagerAdapter adapter;
	@ViewInject(R.id.pager)
	private ViewPager pager;
	private int curIndex = 0;

	private void initIndicator() {
		pages.clear();
		for (ChildNewsCate cate : category.children) {
			pages.add(new ItemNewsPage(ct, cate.url));
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

	class NewsPagerAdapter extends PagerAdapter {
		private ArrayList<ItemNewsPage> pages;

		public NewsPagerAdapter(Context ct, ArrayList<ItemNewsPage> pages) {
			this.pages = pages;
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			// TODO Auto-generated method stub
			if (position >= pages.size())
				return;
			((ViewPager) container).removeView(pages.get(position)
					.getContentView());
		}

		@Override
		public CharSequence getPageTitle(int position) {
			int size = category.children.size();
			return category.children.get(position % size).title;
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			// TODO Auto-generated method stub
			((ViewPager) arg0).addView(pages.get(arg1).getContentView(), 0);
			return pages.get(arg1).getContentView();
		}

		@Override
		public int getCount() {
			return category.children.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg1 == arg0;
		}

	}

	@Override
	protected void processClick(View v) {

	}

}
