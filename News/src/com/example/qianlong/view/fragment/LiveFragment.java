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
		// ����֧��javascript
		webView.getSettings().setJavaScriptEnabled(true);
		// WebView����web��Դ
		webView.loadUrl("http://www.yicai.com/video/");
		// ����WebViewĬ��ʹ�õ�������ϵͳĬ�����������ҳ����Ϊ��ʹ��ҳ��WebView��
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				// ����ֵ��true��ʱ�����ȥWebView�򿪣�Ϊfalse����ϵͳ�����������������
				view.loadUrl(url);
				return true;
			}
		});
	}
}
