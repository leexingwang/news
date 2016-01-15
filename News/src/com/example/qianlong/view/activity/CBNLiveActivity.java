package com.example.qianlong.view.activity;

import com.example.qianlong.R;
import com.example.qianlong.base.BaseActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CBNLiveActivity extends BaseActivity {
	private WebView webView;

	@SuppressLint("SetJavaScriptEnabled")
	private void init() {
		webView = (WebView) findViewById(R.id.web_live);
		webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		// 启用支持javascript
		webView.getSettings().setJavaScriptEnabled(true);
		// WebView加载web资源
		webView.loadUrl("http://weixin.yicai.com/AppNewsList/index.php?nid=4735971&pagesize=20");
		// 覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
				view.loadUrl(url);
				return true;
			}
		});
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		webView.reload();
	}

	@Override
	protected void onStop() {
		super.onStop();
		webView.destroy();
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.cbn_pop_live);
		initTitleBar();
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		init();
	}

	@Override
	protected void processClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void finishChild() {
		// TODO Auto-generated method stub
		
	}

}
