package com.mvp.demo.view;

import java.util.ArrayList;
import java.util.List;

import com.base.common.ui.pullrefreshview.PullToRefreshBase;
import com.base.common.ui.pullrefreshview.PullToRefreshBase.OnRefreshListener;
import com.base.common.ui.pullrefreshview.PullToRefreshListView;
import com.example.qianlong.R;
import com.example.qianlong.bean.Live;
import com.example.qianlong.view.adpter.TimelineAdapter;
import com.lidroid.xutils.exception.HttpException;
import com.mvp.demo.presenter.presenterimpl.LivePresenterImpl;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * 
 * @author lixingwang
 * 
 */
public class CBNLiveTextMVCDemoActivity extends Activity implements LiveView {

	List<Live> lives = new ArrayList<Live>();
	PullToRefreshListView listView;
	TimelineAdapter adapter;
	LivePresenterImpl livePresenterImpl = new LivePresenterImpl(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cbn_live7_24);
		init();
		listView = (PullToRefreshListView) findViewById(R.id.listview_live);
		// �������ز�����
		listView.setPullLoadEnabled(true);
		// ���������Զ����ؿ���
		listView.setScrollLoadEnabled(true);
		listView.setDividerDrawable(null);
		adapter = new TimelineAdapter(this, lives);
		listView.getRefreshableView().setAdapter(adapter);
		listView.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				livePresenterImpl.getLiveInfo("stringlist", "20", "1", "2");
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				livePresenterImpl.getLiveInfo("stringlist", "20", "2", "2");
			}
		});
		listView.getRefreshableView().setOnItemClickListener(
				new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {

					}
				});
	}

	Live live = new Live();

	private void init() {
		for (int i = 0; i < 10; i++) {
			live.setAdminName("SXS");
			live.setLiveContent(i % 2 == 0 ? "12345"
					: "��ָ�ձ�3539.18�㣬�µ�0.94%���ɽ���2540.3��Ԫ�����ָ�ձ�12664.89�㣬�µ�1.75%���ɽ���4325.7��Ԫ����ҵ���ձ�2714.05�㣬�µ�2.36%���ɽ���1118.9��Ԫ��");
			live.setLiveDate("2015-12-29T15:08:0" + i);
			live.setLiveFrom("");
			live.setLiveID(52482);
			live.setLivePic("");
			live.setLiveType(4);
			lives.add(live);
		}

	}


	private void loadedCompleted(){
		listView.onPullDownRefreshComplete();
	    listView.onPullUpRefreshComplete();
	}


	@Override
	public void setLiveInfo(List<Live> lives) {
		// TODO Auto-generated method stub
		loadedCompleted();
		lives.addAll(lives);
		adapter.notifyDataSetChanged();
		
	}


	@Override
	public void setLiveErrorInfo(HttpException arg0, String arg1) {
		// TODO Auto-generated method stub
		loadedCompleted();
	}
}
