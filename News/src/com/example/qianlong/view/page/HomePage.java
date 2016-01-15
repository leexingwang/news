package com.example.qianlong.view.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.base.common.ui.banner.CBViewHolderCreator;
import com.base.common.ui.banner.ConvenientBanner;
import com.base.common.ui.banner.DefaultTransformer;
import com.base.common.ui.banner.Holder;
import com.base.common.ui.banner.ConvenientBanner.PageIndicatorAlign;
import com.base.common.ui.pullrefreshview.PullToRefreshBase;
import com.base.common.ui.pullrefreshview.PullToRefreshListView;
import com.base.common.ui.pullrefreshview.PullToRefreshBase.OnRefreshListener;
import com.example.qianlong.R;
import com.example.qianlong.base.BasePage;
import com.example.qianlong.bean.LiveBean;
import com.example.qianlong.utils.CommonUtil;
import com.example.qianlong.view.activity.CBNBannerActivity;
import com.example.qianlong.view.activity.CBNBannerActivity.NetworkImageHolderView;
import com.example.qianlong.view.adpter.TimelineAdapter;
import com.squareup.picasso.Picasso;
import com.topnewgrid.bean.ChannelItem;

public class HomePage extends BasePage implements
		com.base.common.ui.banner.OnItemClickListener {
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
	private PullToRefreshListView ptrLv;
	private ChannelItem channelItem;
	View view;

	public HomePage(Context context, ChannelItem channelItem) {
		super(context);
		this.channelItem = channelItem;
		channelItemName = channelItem.getName();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected View initView(LayoutInflater inflater) {
		view = inflater.inflate(R.layout.frag_item_news, null);
		ptrLv = (PullToRefreshListView) view.findViewById(R.id.lv_item_news);
		View topNewsView = inflater.inflate(R.layout.banner, null);
		convenientBanner = (ConvenientBanner<String>) topNewsView
				.findViewById(R.id.convenientBanner);
		ptrLv.setPullLoadEnabled(true);
		// 滚动到底自动加载可用
		ptrLv.setScrollLoadEnabled(true);
		// 得到实际的ListView 设置点击
		ptrLv.getRefreshableView().setOnItemClickListener(
				new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {

					}
				});
		setLastUpdateTime();
		// 设置下拉刷新的listener
		ptrLv.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				onLoaded();
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				onLoaded();
			}
		});
		ptrLv.getRefreshableView().addHeaderView(topNewsView);
		return view;
	}

	private void setLastUpdateTime() {
		String text = CommonUtil.getStringDate();
		ptrLv.setLastUpdatedLabel(text);
	}

	@Override
	public void initData() {
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
				.setOnItemClickListener(this);
		List<LiveBean> lives = new ArrayList<LiveBean>();
		for (int i = 0; i < 50; i++) {
			lives.add(new LiveBean("123", "233444", 1, "1234" + i, "123",
					"2016-01-14T12:46:00", 1));
		}
		TimelineAdapter adapter = new TimelineAdapter(ct, lives);
		ptrLv.getRefreshableView().setAdapter(adapter);
		if (!convenientBanner.isTurning()) {
			convenientBanner.startTurning(3000);
		}
		onLoaded();

	}

	private void onLoaded() {
		dismissLoadingView();
		ptrLv.onPullDownRefreshComplete();
		ptrLv.onPullUpRefreshComplete();
	}

	@Override
	protected void processClick(View v) {
		// TODO Auto-generated method stub

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
			Picasso.with(context).load(data).resize(700, 350).centerCrop()
					.into(imageView);
			textView.setText("qqqqqqq" + position);
		}

	}

	@Override
	public void onItemClick(int position) {
		showToast("点击了第" + (position + 1) + "图片");
	}
}
