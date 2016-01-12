package com.example.qianlong.view.activity;

import java.util.ArrayList;

import com.base.common.ui.CustomViewPager;
import com.base.common.ui.LazyViewPager.OnPageChangeListener;
import com.example.qianlong.R;
import com.example.qianlong.base.BaseActivity;
import com.example.qianlong.base.BasePage;
import com.example.qianlong.view.adpter.HomePagerAdapter;
import com.example.qianlong.view.page.NewsCenterPage;
import com.example.qianlong.view.page.SettingPage;
import com.example.qianlong.view.popupwindow.LivePopupWindow;
import com.lidroid.xutils.util.LogUtils;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class CBNHomeActivity extends BaseActivity {
	private HomePagerAdapter adapter;
	private ArrayList<BasePage> pages = new ArrayList<BasePage>();
	private CustomViewPager viewPager;
	public RadioGroup mainRg;
	private int curCheckId = R.id.rb_news_center;
	private LivePopupWindow mMoreWindow;
	public RadioButton radioButton;

	@Override
	protected void initView() {
		setContentView(R.layout.content_frame);
		viewPager = (CustomViewPager) findViewById(R.id.viewpager);
		mainRg = (RadioGroup) findViewById(R.id.main_radio);
		radioButton = (RadioButton) findViewById(R.id.rb_popupwindow);
	}

	@Override
	protected void initData() {
		pages.add(new NewsCenterPage(ct));
		pages.add(new SettingPage(ct));
		adapter = new HomePagerAdapter(ct, pages);
		viewPager.setAdapter(adapter);
		viewPager.setScrollable(false);
		// 不进行预加载
		viewPager.setOffscreenPageLimit(0);
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				BasePage page = pages.get(position);
				LogUtils.i(page + "---------------------------------"
						+ position);
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
		mainRg.check(curCheckId);
		radioButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				showMoreWindow(viewPager);
			}
		});
		mainRg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				LogUtils.d(checkedId + "");

				switch (checkedId) {
				case R.id.rb_news_center:
					NewsCenterPage page = (NewsCenterPage) pages.get(0);
					page.onResume();
					viewPager.setCurrentItem(0, false);
					break;
				case R.id.rb_setting:
					viewPager.setCurrentItem(1, false);
					break;
				default:
					break;
				}
				curCheckId = checkedId;
			}
		});

	}

	@Override
	protected void processClick(View v) {
		// TODO Auto-generated method stub

	}

	private void showMoreWindow(View view) {
		if (null == mMoreWindow) {
			mMoreWindow = new LivePopupWindow(this);
			mMoreWindow.init();
		}
		mMoreWindow.showMoreWindow(view, 100);
	}
}
