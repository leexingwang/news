package com.example.qianlong.view.activity;

import java.util.ArrayList;

import com.base.common.ui.CustomViewPager;
import com.base.common.ui.LazyViewPager.OnPageChangeListener;
import com.example.qianlong.R;
import com.example.qianlong.base.BaseActivity;
import com.example.qianlong.base.BasePage;
import com.example.qianlong.utils.TLog;
import com.example.qianlong.view.adpter.HomePagerAdapter;
import com.example.qianlong.view.page.NewsPage;
import com.example.qianlong.view.page.SettingPage;
import com.example.qianlong.view.popupwindow.LivePopupWindow;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class CBNHomeActivity extends BaseActivity implements
		OnCheckedChangeListener, OnPageChangeListener {
	private HomePagerAdapter adapter;
	private ArrayList<BasePage> pages = new ArrayList<BasePage>();
	private CustomViewPager viewPager;
	private RadioGroup mainRg;
	private int curCheckId = R.id.rb_news_center;
	private LivePopupWindow mMoreWindow;
	private RadioButton radioButton;
	private Handler handler;

	@Override
	protected void initView() {
		setContentView(R.layout.content_frame);
		viewPager = (CustomViewPager) findViewById(R.id.viewpager);
		mainRg = (RadioGroup) findViewById(R.id.main_radio);
		radioButton = (RadioButton) findViewById(R.id.rb_popupwindow);
	}

	@Override
	protected void initData() {
		initHandler();
		pages.add(new NewsPage(ct));
		pages.add(new SettingPage(ct, handler));
		adapter = new HomePagerAdapter(ct, pages);
		viewPager.setAdapter(adapter);
		viewPager.setScrollable(false);
		viewPager.setOffscreenPageLimit(0); // 不进行预加载
		pages.get(0).initData();
		viewPager.setCurrentItem(0);
		mainRg.check(curCheckId);
		radioButton.setOnClickListener(this);
		mainRg.setOnCheckedChangeListener(this);
		viewPager.setOnPageChangeListener(this);

	}

	private void initHandler() {
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
			}
		};
	}

	@Override
	protected void processClick(View v) {
		switch (v.getId()) {
		case R.id.rb_popupwindow:
			showMoreWindow(viewPager);
			break;

		default:
			break;
		}

	}

	private void showMoreWindow(View view) {
		if (null == mMoreWindow) {
			mMoreWindow = new LivePopupWindow(this);
			mMoreWindow.init();
		}
		mMoreWindow.showMoreWindow(view, 100);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		TLog.log(checkedId + "");
		switch (checkedId) {
		case R.id.rb_news_center:
			NewsPage page = (NewsPage) pages.get(0);
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

	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {

	}

	@Override
	public void onPageSelected(int position) {
		BasePage page = pages.get(position);
		TLog.log(page + "---------------------------------" + position);
		if (!page.isLoadSuccess) {
			page.initData();
		}

	}

	@Override
	public void onPageScrollStateChanged(int state) {
		// TODO Auto-generated method stub

	}
}
