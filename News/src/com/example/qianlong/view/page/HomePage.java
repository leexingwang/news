package com.example.qianlong.view.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
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
import com.example.qianlong.bean.Live;
import com.example.qianlong.bean.LiveBean;
import com.example.qianlong.bean.News;
import com.example.qianlong.bean.NewsEntity;
import com.example.qianlong.constants.Constants;
import com.example.qianlong.modle.GetLiveTextModle;
import com.example.qianlong.modle.GetLiveTextModle.OnLiveListener;
import com.example.qianlong.modle.GetNewsByNidModle.OnGetNewsByNidListener;
import com.example.qianlong.modle.GetNewsListByChannelModle.OnNewsListByChannelListener;
import com.example.qianlong.modle.modleimpl.GetLiveTextModleImpl;
import com.example.qianlong.modle.modleimpl.GetNewsByNidImpl;
import com.example.qianlong.modle.modleimpl.GetNewsListByChannelImpl;
import com.example.qianlong.utils.ACache;
import com.example.qianlong.utils.CommonUtil;
import com.example.qianlong.utils.TLog;
import com.example.qianlong.view.adpter.HomeNewsItemAdapter;
import com.example.qianlong.view.adpter.TimelineAdapter;
import com.lidroid.xutils.exception.HttpException;
import com.squareup.picasso.Picasso;
import com.topnewgrid.bean.ChannelItem;

public class HomePage extends BasePage implements
		com.base.common.ui.banner.OnItemClickListener,
		OnRefreshListener<ListView>, OnItemClickListener,
		OnNewsListByChannelListener, OnGetNewsByNidListener, OnLiveListener {
	private final static int FOCUS_PIC = 3478;
	private final static int RECOMMEND_NEWS = 3479;
	private ConvenientBanner<News> convenientBanner;
	private PullToRefreshListView ptrLv;
	private ChannelItem channelItem;
	private GetNewsListByChannelImpl newsListByChannelImpl;
	private GetNewsByNidImpl newsByNidImpl;
	private GetLiveTextModleImpl getLiveTextModleImpl;
	private List<News> homesNews;
	private View view;
	private List<News> focus_news;
	private HomeNewsItemAdapter homeNewsItemAdapter;

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
		convenientBanner = (ConvenientBanner<News>) topNewsView
				.findViewById(R.id.convenientBanner);
		ptrLv.setPullLoadEnabled(true);
		// 滚动到底自动加载可用
		ptrLv.setScrollLoadEnabled(true);
		// 得到实际的ListView 设置点击
		ptrLv.getRefreshableView().setOnItemClickListener(this);
		// 设置下拉刷新的listener
		ptrLv.setOnRefreshListener(this);
		ptrLv.getRefreshableView().addHeaderView(topNewsView);
		newsListByChannelImpl = new GetNewsListByChannelImpl();
		newsByNidImpl = new GetNewsByNidImpl();
		getLiveTextModleImpl = new GetLiveTextModleImpl();
		return view;
	}

	@Override
	public void initData() {
		focus_news = new ArrayList<News>();
		homesNews = new ArrayList<News>();
		onLoaded();
		getFocusACacheInfo();
		getNewsACacheInfo();
		initBanner();
		setLastUpdateTime();
		homeNewsItemAdapter = new HomeNewsItemAdapter(ct, homesNews);
		ptrLv.getRefreshableView().setAdapter(homeNewsItemAdapter);
		newsListByChannelImpl.getNewsListByChannel(RECOMMEND_NEWS, 20, 1,
				RECOMMEND_NEWS, this);
		newsListByChannelImpl.getNewsListByChannel(FOCUS_PIC, 20, 1, FOCUS_PIC,
				this);
		isLoadSuccess = true;

	}

	private void getFocusACacheInfo() {
		try {
			String json = ACache.get().getAsString(
					Constants.GetNewsListByChannel_URL + FOCUS_PIC + 20 + 1
							+ FOCUS_PIC);
			List<News> news = newsListByChannelImpl.parseNews(json);
			if (news.size() > 0) {
				focus_news = news;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getNewsACacheInfo() {
		try {
			String json = ACache.get().getAsString(
					Constants.GetNewsListByChannel_URL + RECOMMEND_NEWS + 20
							+ 1 + RECOMMEND_NEWS);
			List<News> news = newsListByChannelImpl.parseNews(json);
			if (news.size() > 0) {
				homesNews = news;
				homesNews.addAll(news);
				homesNews.addAll(news);
				homesNews.addAll(news);
				homesNews.addAll(news);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initBanner() {
		convenientBanner
				.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
					@Override
					public NetworkImageHolderView createHolder() {
						return new NetworkImageHolderView();
					}
				}, focus_news)
				// 设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
				.setPageIndicator(
						new int[] { R.drawable.ic_page_indicator,
								R.drawable.ic_page_indicator_focused })
				.setPageIndicatorAlign(PageIndicatorAlign.ALIGN_PARENT_RIGHT)
				.setPageTransformer(new DefaultTransformer())
				.setOnItemClickListener(this);
		if (!convenientBanner.isTurning()) {
			convenientBanner.startTurning(Constants.LOOP_TIME);
		}
	}

	private void onLoaded() {
		dismissLoadingView();
		ptrLv.onPullDownRefreshComplete();
		ptrLv.onPullUpRefreshComplete();
		isLoadSuccess = true;
	}

	@Override
	public void onResume() {
		super.onResume();
		if (!convenientBanner.isTurning()) {
			convenientBanner.startTurning(Constants.LOOP_TIME);
		}
	}

	@Override
	public void onStop() {
		super.onStop();
		if (convenientBanner.isTurning()) {
			convenientBanner.stopTurning();
		}
	}

	@Override
	protected void processClick(View v) {
	}

	@Override
	public void onItemClick(int position) {
		showToast("点击了第" + (position + 1) + "图片");
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		newsListByChannelImpl.getNewsListByChannel(FOCUS_PIC, 20, 1, FOCUS_PIC,
				this);
		newsListByChannelImpl.getNewsListByChannel(RECOMMEND_NEWS, 20, 1,
				RECOMMEND_NEWS, this);
		onLoaded();
		TLog.log("onPullDownToRefresh");
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		onLoaded();
		TLog.log("onPullUpToRefresh");
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
	}

	private void setLastUpdateTime() {
		String text = CommonUtil.getStringDate();
		ptrLv.setLastUpdatedLabel(text);
	}

	public class NetworkImageHolderView implements Holder<News> {
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
		public void UpdateUI(Context context, final int position, News news) {
			if (news == null) {
				return;
			}
			Picasso.with(context)
					.load(Constants.IMGURL + Constants.IMGURL_ORIGIN
							+ news.getNewsThumbs())
					.error(R.drawable.cbn_default).into(imageView);
			textView.setText(news.getNewsTitle());
		}
	}

	@Override
	public void onSuccess(List<News> news, int loadType) {
		if (loadType == FOCUS_PIC) {
			focus_news = news;
			initBanner();
		} else if (loadType == RECOMMEND_NEWS) {
			homesNews = news;
			homeNewsItemAdapter.notifyDataSetChanged();
			ptrLv.getRefreshableView().setAdapter(homeNewsItemAdapter);
		}
	}

	@Override
	public void onError(int loadType) {

	}

	@SuppressWarnings("hiding")
	@Override
	public <NewsEntity> void onGetNewsSuccess(NewsEntity newsEntity,
			int loadType) {

	}

	@Override
	public void onGetNewsError(int loadType) {

	}

	@Override
	public void onLiveSuccess(List<Live> lives, int loadType) {

	}

	@Override
	public void onLiveError(int loadType) {

	}

}
