package com.example.qianlong.view.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.qianlong.R;
import com.example.qianlong.application.AppManager;
import com.example.qianlong.base.BaseActivity;
import com.example.qianlong.bean.Live;
import com.example.qianlong.constants.Constants;
import com.example.qianlong.utils.StringUtils;
import com.example.qianlong.view.adpter.NewsTextLiveImageAdapter;

public class CBNLiveTextDetailActivity extends BaseActivity {

	private TextView textView_live_date;
	private TextView textView_live_content;
	private ListView listView_live_images;
	private Live live;
	private List<String> images_urls;
	private ScrollView scrollView;

	@Override
	protected void initView() {
		setContentView(R.layout.cbn_live7_24_detail);
		initTitleBar();
		textView_live_date = (TextView) findViewById(R.id.textview_live_7_24_date);
		textView_live_content = (TextView) findViewById(R.id.textview_live_7_24_content);
		listView_live_images = (ListView) findViewById(R.id.listview_live_7_24_images);
		scrollView = (ScrollView) findViewById(R.id.scrollview_live_7_24);
	}

	@Override
	protected void initData() {
		images_urls = new ArrayList<String>();
		Intent intent = getIntent();
		live = (Live) intent.getSerializableExtra(Constants.TEXT_LIVE);
		if (!StringUtils.isEmpty(live.getCreateDate())) {
			textView_live_date.setText(live.getCreateDate());
		}
		if (!StringUtils.isEmpty(live.getLiveContent())) {
			textView_live_content.setText(Html.fromHtml(live.getLiveContent()));
		}
		if (!StringUtils.isEmpty(live.getLiveImages())) {
			for (int i = 0; i < live.getLiveImages().split(";").length; i++) {
				if (!(live.getLiveImages().split(";"))[i].startsWith("http://"))
					images_urls.add(Constants.IMGURL + Constants.IMGURL_ORIGIN
							+ (live.getLiveImages().split(";"))[i]);
				else {
					images_urls.add((live.getLiveImages().split(";"))[i]);
				}
			}
		}
		if (images_urls.size() != 0) {
			NewsTextLiveImageAdapter newsTextLiveImageAdapter = new NewsTextLiveImageAdapter(
					this, images_urls);
			listView_live_images.setAdapter(newsTextLiveImageAdapter);
			setListViewHeightBasedOnChildren(listView_live_images);
			scrollView.scrollTo(0, 0);
		}

	}

	@Override
	protected void processClick(View v) {
	}

	@Override
	protected void finishChild() {
		AppManager.getAppManager().finishActivity();
	}

	/*
	 * 动态设置ListView组建的高度
	 */
	private void setListViewHeightBasedOnChildren(ListView listView) {

		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			return;
		}
		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}

}
