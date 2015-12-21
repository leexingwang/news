package com.example.qianlong;

import java.util.ArrayList;

import com.example.qianlong.base.BaseFragment;
import com.example.qianlong.base.BasePage;
import com.example.qianlong.page.NewsCenterPage;
import com.example.qianlong.page.SettingPage;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.qianlong.android.view.CustomViewPager;
import com.qianlong.android.view.LazyViewPager.OnPageChangeListener;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class HomeFragment extends BaseFragment {

	@Override
	protected View initView(LayoutInflater inflater) {
		View view = inflater.inflate(R.layout.frag_home2, null);
		ViewUtils.inject(this, view);
		return view;
	}

	private HomePagerAdapter adapter;
	private ArrayList<BasePage> pages = new ArrayList<BasePage>();
	@ViewInject(R.id.viewpager)
	private CustomViewPager viewPager;
	@ViewInject(R.id.main_radio)
	public RadioGroup mainRg;
	private int curCheckId = R.id.rb_news_center;
	@SuppressWarnings("unused")
	private int curIndex;
	@Override
	protected void initData(Bundle savedInstanceState) {
		pages.add(new NewsCenterPage(ct));
		pages.add(new SettingPage(ct));
		adapter = new HomePagerAdapter(ct, pages);
		viewPager.setAdapter(adapter);
		viewPager.setScrollable(false);
		//不进行预加载
		viewPager.setOffscreenPageLimit(0);
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				BasePage page = pages.get(position);
 				LogUtils.i(page+"---------------------------------"+position);
				if (!page.isLoadSuccess) {
					page.initData();
				}

			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {

			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});
		pages.get(0).initData();
		viewPager.setCurrentItem(0);
		mainRg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				LogUtils.d(checkedId + "");

				switch (checkedId) {
				case R.id.rb_news_center:
					NewsCenterPage page = (NewsCenterPage) pages.get(0);
					page.onResume();
					curIndex=0;
					viewPager.setCurrentItem(0, false);
					break;
				case R.id.rb_setting:
					curIndex=1;
					viewPager.setCurrentItem(1, false);
					break;
				default:
					break;
				}
				curCheckId = checkedId;

			}
		});
		mainRg.check(curCheckId);
	}
	public NewsCenterPage getNewsCenterPage() {
		NewsCenterPage page =(NewsCenterPage) pages.get(1);
		return page;

	}
	class HomePagerAdapter extends PagerAdapter {
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

	@Override
	protected void processClick(View v) {
		// TODO Auto-generated method stub

	}

}
