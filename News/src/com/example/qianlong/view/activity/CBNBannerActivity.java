package com.example.qianlong.view.activity;

import java.util.Arrays;
import java.util.List;

import com.base.common.ui.banner.CBViewHolderCreator;
import com.base.common.ui.banner.ConvenientBanner;
import com.base.common.ui.banner.ConvenientBanner.PageIndicatorAlign;
import com.base.common.ui.banner.DefaultTransformer;
import com.base.common.ui.banner.Holder;
import com.base.common.ui.banner.OnItemClickListener;
import com.example.qianlong.R;
import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CBNBannerActivity extends Activity {
	private ConvenientBanner<String> convenientBanner;
	private List<String> networkImages;
	private String[] images = {
			"http://img2.imgtn.bdimg.com/it/u=3093785514,1341050958&fm=21&gp=0.jpg",
			"http://img2.3lian.com/2014/f2/37/d/40.jpg",
			"http://d.3987.com/sqmy_131219/001.jpg",
			"http://img2.3lian.com/2014/f2/37/d/39.jpg",
			"http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg",
			"http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg",
			"http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg" };

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.banner);
		convenientBanner = (ConvenientBanner<String>) findViewById(R.id.convenientBanner);
		// 网络加载例子
		networkImages = Arrays.asList(images);
		convenientBanner
				.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
					@Override
					public NetworkImageHolderView createHolder() {
						return new NetworkImageHolderView();
					}
				}, networkImages)
				// 设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
				.setPageIndicator(
						new int[] { R.drawable.ic_page_indicator,
								R.drawable.ic_page_indicator_focused })
				.setPageIndicatorAlign(PageIndicatorAlign.ALIGN_PARENT_RIGHT)
				.setPageTransformer(new DefaultTransformer())
				.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(int position) {
						// TODO Auto-generated method stub
						Toast.makeText(CBNBannerActivity.this,
								"点击了第" + (position + 1) + "图片",
								Toast.LENGTH_SHORT).show();
					}
				});
	}

	// 开始自动翻页
	@Override
	protected void onResume() {
		super.onResume();
		// 开始自动翻页
		convenientBanner.startTurning(5000);
	}

	// 停止自动翻页
	@Override
	protected void onPause() {
		super.onPause();
		// 停止翻页
		convenientBanner.stopTurning();
	}

	public class NetworkImageHolderView implements Holder<String> {
		private ImageView imageView;
		private TextView textView;

		@Override
		public View createView(Context context) {
			View view = LayoutInflater.from(context).inflate(
					R.layout.cbn_header_loop_banner, null);
			imageView = (ImageView) view
					.findViewById(R.id.imageView_head_loop_banner);
			textView = (TextView) view
					.findViewById(R.id.textView_head_loop_content);
			imageView.setScaleType(ImageView.ScaleType.FIT_XY);
			return view;
		}

		@Override
		public void UpdateUI(Context context, final int position, String data) {
			Picasso.with(context).load(data).resize(500, 200).centerCrop()
					.into(imageView);
			textView.setText("qqqqqqq" + position);
		}

	}

}
