package com.example.qianlong.view.page;

import java.util.ArrayList;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.example.qianlong.R;
import com.example.qianlong.base.BasePage;
import com.example.qianlong.bean.NewsCenterCategories;
import com.example.qianlong.bean.NewsCenterCategories.NewsCategory;
import com.example.qianlong.utils.GsonTools;
import com.example.qianlong.utils.QLApi;
import com.example.qianlong.utils.QLParser;
import com.example.qianlong.utils.SharePrefUtil;
import com.example.qianlong.utils.SharePrefUtil.KEY;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class NewsCenterPage extends BasePage {

	public NewsCenterPage(Context context) {
		super(context);
	}

	public void onResume() {
		super.onResume();
	}

	@Override
	protected View initView(LayoutInflater inflater) {
		View view = inflater.inflate(R.layout.news_center_frame, null);
		ViewUtils.inject(this, view);
		initTitleBar(view);
		return view;
	}

	private ArrayList<BasePage> pageList;
	public ArrayList<String> newsCenterMenuList = new ArrayList<String>();

	@Override
	public void initData() {
		pageList = new ArrayList<BasePage>();
		if (newsCenterMenuList.size() == 0) {
			String result = SharePrefUtil.getString(ct,
					QLApi.NEWS_CENTER_CATEGORIES, "");
			if (!TextUtils.isEmpty(result)) {
				processData(result);
			}
			getNewsCenterCategories();

		}
	}

	public ArrayList<NewsCategory> categorieList;

	private void processData(String result) {
		NewsCenterCategories categories = QLParser.parse(result,
				NewsCenterCategories.class);
		if (categories.retcode != 200) {
			return;
		}
		categorieList = categories.data;
		newsCenterMenuList.clear();
		for (NewsCategory cate : categories.data) {
			newsCenterMenuList.add(cate.title);
		}
		
		
		NewsCategory newsCategory = categorieList.get(0);
		SharePrefUtil.saveString(ct, KEY.CATE_ALL_JSON,
				GsonTools.createGsonString(newsCategory.children));
		SharePrefUtil.saveString(ct, KEY.CATE_EXTEND_ID,
				GsonTools.createGsonString(categories.extend));
		pageList.clear();
		BasePage newsPage = new NewsPage(ct, newsCategory);
		pageList.add(newsPage);
		switchFragment(0);
	}

	@ViewInject(R.id.news_center_fl)
	private FrameLayout news_center_fl;

	public void switchFragment(int newsCenterPosition) {
		BasePage page = pageList.get(newsCenterPosition);
		switch (newsCenterPosition) {
		case 0:
			news_center_fl.removeAllViews();
			news_center_fl.addView(page.getContentView());
			break;
		case 1:
			news_center_fl.removeAllViews();
			news_center_fl.addView(page.getContentView());
			break;
		case 2:
			news_center_fl.removeAllViews();
			news_center_fl.addView(page.getContentView());
			break;
		case 3:
			news_center_fl.removeAllViews();
			news_center_fl.addView(page.getContentView());
			break;
		case 4:
			news_center_fl.removeAllViews();
			news_center_fl.addView(page.getContentView());
			break;
		}
		page.initData();
	}

	private void getNewsCenterCategories() {
		loadData(HttpMethod.GET, QLApi.NEWS_CENTER_CATEGORIES, null,
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> info) {
						LogUtils.d("response_json---" + info.result);
						 SharePrefUtil.saveString(ct,
						 QLApi.NEWS_CENTER_CATEGORIES, info.result);
						processData(info.result);

					}

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						LogUtils.d("response_fail---" + arg1);

					}
				});
	}

	@Override
	protected void processClick(View v) {
		// TODO Auto-generated method stub

	}

}
