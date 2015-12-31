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
			live.setLiveContent(i%2==0?"12345":"��ָ�ձ�3539.18�㣬�µ�0.94%���ɽ���2540.3��Ԫ�����ָ�ձ�12664.89�㣬�µ�1.75%���ɽ���4325.7��Ԫ����ҵ���ձ�2714.05�㣬�µ�2.36%���ɽ���1118.9��Ԫ��");
			live.setLiveDate("2015-12-29T15:08:0" + i);
			live.setLiveFrom("");
			live.setLiveID(52482);
			live.setLivePic("");
			live.setLiveType(4);
			lives.add(live);
		}

	}
}
