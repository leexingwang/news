package com.example.qianlong.view.adpter;

import java.util.ArrayList;

import com.example.qianlong.bean.NewsCenterCategories.NewsCategory;
import com.example.qianlong.view.page.ItemNewsPage;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

public class NewsPagerAdapter extends PagerAdapter {
	private ArrayList<ItemNewsPage> pages;

	public NewsPagerAdapter(Context ct, ArrayList<ItemNewsPage> pages) {
		this.pages = pages;
	}

	@Override
	public void destroyItem(View container, int position, Object object) {
		// TODO Auto-generated method stub
		if (position >= pages.size())
			return;
		((ViewPager) container)
				.removeView(pages.get(position).getContentView());
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return "123";
	}

	@Override
	public Object instantiateItem(View arg0, int arg1) {
		// TODO Auto-generated method stub
		((ViewPager) arg0).addView(pages.get(arg1).getContentView(), 0);
		return pages.get(arg1).getContentView();
	}

	@Override
	public int getCount() {
		return pages.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg1 == arg0;
	}

}
