package com.example.qianlong.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.qianlong.R;
import com.example.qianlong.base.BaseFragment;

public class LiveFragment extends BaseFragment {
	private WebView webView;
	@Override
	protected View initView(LayoutInflater inflater) {
		View view = inflater.inflate(R.layout.cbn_pop_live, null);
		webView = (WebView) view.findViewById(R.id.web_live);
		return view;
	}

	@Override
	protected void initData(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		init();
	}

	@Override
	protected void processClick(View v) {
		// TODO Auto-generated method stub

	}

	@SuppressLint("SetJavaScriptEnabled")
	private void init() {
		
		webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		// 启用支持javascript
		webView.getSettings().setJavaScriptEnabled(true);
		// WebView加载web资源
		webView.loadUrl("http://www.yicai.com/video/");
		// 覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				// 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
				view.loadUrl(url);
				return true;
			}
		});
	}
}
