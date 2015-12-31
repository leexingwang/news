package com.example.qianlong.view.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.qianlong.R;
import com.example.qianlong.bean.Live;
import com.example.qianlong.view.adpter.TimelineAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class CBNLiveTextActivity extends Activity {

	List<Live> lives = new ArrayList<Live>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cbn_live7_24);
		init();
		ListView listView = (ListView) findViewById(R.id.listview_live);
		listView.setDividerHeight(0);
		TimelineAdapter adapter = new TimelineAdapter(this, lives);
		listView.setAdapter(adapter);

	}

	private void init() {
		for (int i = 0; i < 10; i++) {
			Live live = new Live();
			live.setAdminName("SXS");
			live.setLiveContent(i%2==0?"12345":"沪指收报3539.18点，下跌0.94%，成交额2540.3亿元。深成指收报12664.89点，下跌1.75%，成交额4325.7亿元。创业板收报2714.05点，下跌2.36%，成交额1118.9亿元。");
			live.setLiveDate("2015-12-29T15:08:0" + i);
			live.setLiveFrom("");
			live.setLiveID(52482);
			live.setLivePic("");
			live.setLiveType(4);
			lives.add(live);
		}

	}
}
