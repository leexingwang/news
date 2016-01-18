package com.example.qianlong.view.activity;

import java.util.ArrayList;

import com.base.common.ui.CustomViewPager;
import com.base.common.ui.LazyViewPager.OnPageChangeListener;
import com.example.qianlong.R;
import com.example.qianlong.application.AppApplication;
import com.example.qianlong.application.AppManager;
import com.example.qianlong.base.BaseActivity;
import com.example.qianlong.base.BasePage;
import com.example.qianlong.constants.Constants;
import com.example.qianlong.utils.TLog;
import com.example.qianlong.view.adpter.HomePagerAdapter;
import com.example.qianlong.view.page.NewsPage;
import com.example.qianlong.view.page.SettingPage;
import com.example.qianlong.view.popupwindow.LivePopupWindow;
import com.topnewgrid.bean.ChannelItem;
import com.topnewgrid.bean.ChannelManage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
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
	private NewsPage newsPage;
	/** 用户栏目列表 */
	private ArrayList<ChannelItem> userChannelList = new ArrayList<ChannelItem>();
	private final int initPageNumber = 0;

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
		userChannelList = (ArrayList<ChannelItem>) ChannelManage.getManage(
				AppApplication.getApp().getSQLHelper()).getUserChannel();
		newsPage = new NewsPage(ct, userChannelList);
		pages.add(newsPage);
		pages.add(new SettingPage(ct, handler));
		adapter = new HomePagerAdapter(ct, pages);
		viewPager.setAdapter(adapter);
		viewPager.setScrollable(false);
		viewPager.setOffscreenPageLimit(0); // 不进行预加载
		// 初始化新闻页
		pages.get(initPageNumber).initData();
		viewPager.setCurrentItem(initPageNumber);
		mainRg.check(curCheckId);
		radioButton.setOnClickListener(this);
		mainRg.setOnCheckedChangeListener(this);
		viewPager.setOnPageChangeListener(this);

	}

	@SuppressLint("HandlerLeak")
	private void initHandler() {
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				switch (msg.what) {

				default:
					break;
				}
			}

		};
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		if (requestCode == Constants.CHANGE_CHANNEL) {
			int clickChannelNum = 1000;
			if (intent.getIntExtra(Constants.CHANGE_BACK_TYPE, 1000) == Constants.CHANGE_BACK_TYPE_CLICK) {
				clickChannelNum = intent.getIntExtra(
						Constants.CHANGE_CLICK_NUMBER, 1000);
			}
			upDataChannel(clickChannelNum);
		}
	}

	private void upDataChannel(int clickChannelNum) {
		newsPage.updateUI(
				(ArrayList<ChannelItem>) ChannelManage.getManage(
						AppApplication.getApp().getSQLHelper())
						.getUserChannel(), clickChannelNum);
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
		if (!page.isLoadSuccess) {
			page.initData();
		}

	}

	@Override
	public void onPageScrollStateChanged(int state) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		AppManager.getAppManager().AppExit(this);
	}

	@Override
	protected void finishChild() {
	}
}
