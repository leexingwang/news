package com.example.qianlong.view.activity;

import com.example.qianlong.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CBNBroadCastActivity extends Activity implements OnClickListener {

	private Button leftButton;
	private TextView textView_nowContent;
	private TextView textView_nextContent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cbn_broadcast_layout);
		init();
	}

	private void init() {
		leftButton = (Button) findViewById(R.id.leftButton);
		leftButton.setOnClickListener(this);
		textView_nowContent = (TextView) findViewById(R.id.text_now_content);
		textView_nextContent = (TextView) findViewById(R.id.text_next_content);

	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.leftButton:
			finish();
			break;

		default:
			break;
		}
	}

}
