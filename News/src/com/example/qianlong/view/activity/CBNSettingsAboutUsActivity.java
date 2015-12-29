package com.example.qianlong.view.activity;

import com.example.qianlong.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CBNSettingsAboutUsActivity extends Activity implements OnClickListener {
	private Button buttBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cbn_settings_about_us);
		((TextView) findViewById(R.id.tvTitle)).setText(getResources()
				.getString(R.string.cbn_settings_about_us));
		buttBack = (Button) findViewById(R.id.leftButton);
		buttBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.leftButton:
			finish();
			break;
		}
	}
}
