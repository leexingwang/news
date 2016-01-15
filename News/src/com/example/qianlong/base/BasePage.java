package com.example.qianlong.base;

import java.util.ArrayList;

import com.example.qianlong.R;
import com.example.qianlong.utils.CommonUtil;
import com.example.qianlong.utils.CustomToast;
import com.example.qianlong.view.activity.CBNHomeActivity;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.topnewgrid.ChannelActivity;
import com.topnewgrid.bean.ChannelItem;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public abstract class BasePage implements OnClickListener {
	protected Context ct;
	protected View contentView;
	protected Button leftBtn;
	protected ImageButton rightBtn;
	protected ImageButton leftImgBtn;
	protected ImageButton rightImgBtn;
	protected ImageButton isbackImgBtn;
	protected TextView titleTv;
	protected LinearLayout loadfailView;
	public boolean isLoadSuccess = false;
	protected String channelItemName;

	public BasePage() {
	}

	public BasePage(Context context) {
		ct = context;
		contentView = initView((LayoutInflater) ct
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
		loadingView = contentView.findViewById(R.id.loading_view);
		loadfailView = (LinearLayout) contentView
				.findViewById(R.id.ll_load_fail);
	}

	public String getChannelItemName() {
		return channelItemName;
	}

	public void setChannelItemName(String channelItemName) {
		this.channelItemName = channelItemName;
	}

	public <T> BasePage(Context context, ArrayList<T> t) {
		ct = context;
		contentView = initView((LayoutInflater) ct
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
		loadingView = contentView.findViewById(R.id.loading_view);
		loadfailView = (LinearLayout) contentView
				.findViewById(R.id.ll_load_fail);
	}

	protected void initTitleBar(View view) {
		leftBtn = (Button) view.findViewById(R.id.btn_left);
		rightBtn = (ImageButton) view.findViewById(R.id.btn_right);
		leftImgBtn = (ImageButton) view.findViewById(R.id.imgbtn_left);
		rightImgBtn = (ImageButton) view.findViewById(R.id.imgbtn_right);
		isbackImgBtn = (ImageButton) view.findViewById(R.id.imgbtn_channel);
		leftImgBtn.setImageResource(R.drawable.img_menu);
		rightImgBtn.setImageResource(R.drawable.cbn_search_icon);
		titleTv = (TextView) view.findViewById(R.id.txt_title);
		leftBtn.setVisibility(View.GONE);
		rightBtn.setVisibility(View.GONE);
		if (leftImgBtn != null)
			leftImgBtn.setOnClickListener(this);
		if (leftImgBtn != null)
			rightImgBtn.setOnClickListener(this);
		isbackImgBtn.setOnClickListener(this);

	}

	public View getContentView() {
		return contentView;
	}

	@ViewInject(R.id.loading_view)
	protected View loadingView;

	public void dismissLoadingView() {
		if (loadingView != null)
			loadingView.setVisibility(View.INVISIBLE);
	}

	protected abstract View initView(LayoutInflater inflater);

	public abstract void initData();

	protected abstract void processClick(View v);

	public void onResume() {

	}

	public void showToast(String msg) {
		showToast(msg, 0);
	}

	public void showToast(String msg, int time) {
		CustomToast customToast = new CustomToast(ct, msg, time);
		customToast.show();
	}

	protected void loadData(HttpRequest.HttpMethod method, String url,
			RequestParams params, RequestCallBack<String> callback) {
		HttpUtils http = new HttpUtils();
		http.configCurrentHttpCacheExpiry(1000 * 1);
		LogUtils.allowD = true;
		if (params != null) {
			if (params.getQueryStringParams() != null)
				LogUtils.d(url + params.getQueryStringParams().toString());
		} else {
			params = new RequestParams();
		}
		if (0 == CommonUtil.isNetworkAvailable(ct)) {
			showToast("无网络，请检查网络连接！");
		} else {
			http.send(method, url, params, callback);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imgbtn_channel:
			ct.startActivity(new Intent(ct, ChannelActivity.class));
			break;
		default:
			break;
		}
		processClick(v);
	}
}
