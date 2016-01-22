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
import com.example.qianlong.view.adpter.HomePagerAdapter;
import com.example.qianlong.view.page.NewsPage;
import com.example.qianlong.view.page.SettingPage;
import com.example.qianlong.view.popupwindow.LivePopupWindow;
import com.topnewgrid.bean.ChannelItem;
import com.topnewgrid.bean.ChannelManage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class CBNHomeActivity extends BaseActivity implements
		OnPageChangeListener {
	private HomePagerAdapter adapter;
	private ArrayList<BasePage> pages = new ArrayList<BasePage>();
	private CustomViewPager viewPager;
	private LivePopupWindow mMoreWindow;
	private Handler handler;
	private NewsPage newsPage;
	private LinearLayout mTabZiXun;
	private LinearLayout mTabZhiBo;
	private LinearLayout mTabWode;
	private ImageButton mImgZiXun;
	private ImageButton mImgZhiBo;
	private ImageButton mImgWode;
	/** 用户栏目列表 */
	private ArrayList<ChannelItem> userChannelList = new ArrayList<ChannelItem>();
	private final int initPageNumber = 0;

	@Override
	protected void initView() {
		setContentView(R.layout.content_frame);
		viewPager = (CustomViewPager) findViewById(R.id.viewpager);
		mTabZiXun = (LinearLayout) findViewById(R.id.id_tab_zixun);
		mTabZhiBo = (LinearLayout) findViewById(R.id.id_tab_zhibo);
		mTabWode = (LinearLayout) findViewById(R.id.id_tab_wode);
		mImgZiXun = (ImageButton) findViewById(R.id.id_tab_zixun_img);
		mImgZhiBo = (ImageButton) findViewById(R.id.id_tab_zhibo_img);
		mImgWode = (ImageButton) findViewById(R.id.id_tab_wode_img);
		mTabZiXun.setOnClickListener(this);
		mTabZhiBo.setOnClickListener(this);
		mTabWode.setOnClickListener(this);
		viewPager.setOnPageChangeListener(this);
		setSelect(0);
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
		case R.id.id_tab_zixun:
			viewPager.setCurrentItem(0, false);
			setSelect(0);
			break;
		case R.id.id_tab_zhibo:
			showMoreWindow(viewPager);
			break;
		case R.id.id_tab_wode:
			viewPager.setCurrentItem(1, false);
			setSelect(1);
			break;
		default:
			break;
		}

	}

	private void setSelect(int i) {
		// 把图片设置为亮的
		resetImgs();
		switch (i) {
		case 0:
			mImgZiXun.setImageResource(R.drawable.tabbar_home_highlighted);
			break;
		case 1:
			mImgWode.setImageResource(R.drawable.tabbar_profile_highlighted);
			break;
		default:
			break;
		}
	}

	/**
	 * 切换图片至暗色
	 */
	private void resetImgs() {
		mImgZiXun.setImageResource(R.drawable.tabbar_home);
		mImgWode.setImageResource(R.drawable.tabbar_profile);
	}

	private void showMoreWindow(View view) {
		if (null == mMoreWindow) {
			mMoreWindow = new LivePopupWindow(this);
			mMoreWindow.init();
		}
		mMoreWindow.showMoreWindow(view, 100);
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
