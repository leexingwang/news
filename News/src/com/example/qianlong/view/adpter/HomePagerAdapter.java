package com.example.qianlong.view.adpter;

import java.util.ArrayList;

import com.base.common.ui.CustomViewPager;
import com.example.qianlong.base.BasePage;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;

public class HomePagerAdapter extends PagerAdapter {
	private Context mContext;
	private ArrayList<BasePage> pages;

	public HomePagerAdapter(Context ct, ArrayList<BasePage> pages) {
		this.mContext = ct;
		this.pages = pages;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return pages.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg1 == arg0;
	}

	@Override
	public void destroyItem(View container, int position, Object object) {
		// TODO Auto-generated method stub
		((CustomViewPager) container).removeView(pages.get(position)
				.getContentView());
	}

	@Override
	public Object instantiateItem(View arg0, int arg1) {
		// TODO Auto-generated method stub
		((CustomViewPager) arg0).addView(pages.get(arg1).getContentView(), 0);
		return pages.get(arg1).getContentView();
	}

}
