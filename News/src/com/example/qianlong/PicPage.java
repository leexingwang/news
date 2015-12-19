package com.example.qianlong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.example.qianlong.base.BasePage;
import com.example.qianlong.bean.NewsCenterCategories.NewsCategory;
import com.lidroid.xutils.ViewUtils;

public class PicPage extends BasePage {


	private NewsCategory category;
	public PicPage(Context ct, NewsCategory newsCategory) {
		super(ct);
		category = newsCategory;
	}

	@Override
	protected View initView(LayoutInflater inflater) {
		View view = inflater.inflate(R.layout.frag_news, null);
		ViewUtils.inject(this, view);
		return view;
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void processClick(View v) {
		// TODO Auto-generated method stub

	}

}
