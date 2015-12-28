package com.example.qianlong.view.activity;

import com.base.common.ui.ToggleButton;
import com.base.common.ui.ToggleButton.OnToggleChanged;
import com.example.qianlong.R;
import com.example.qianlong.utils.SharePrefUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class CBNSettingsActivity extends Activity implements OnClickListener {
	private Button buttBack;
	private ImageButton imageButtonAboutUs;
	private ImageButton imageButtonPush;
	private ToggleButton toggleButtonGuankan;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cbn_settings_activity);
		((TextView)findViewById(R.id.tvTitle)).setText(getResources().getString(R.string.cbn_settings_));
		buttBack = (Button) findViewById(R.id.leftButton);
		imageButtonAboutUs = (ImageButton) findViewById(R.id.imgbtn_about_us);
		imageButtonPush = (ImageButton) findViewById(R.id.imgbtn_push);
		toggleButtonGuankan = (ToggleButton) findViewById(R.id.togglebut_guankan);
		toggleButtonGuankan.setOnToggleChanged(new OnToggleChanged() {

			@Override
			public void onToggle(boolean on) {
				SharePrefUtil.saveBoolean(CBNSettingsActivity.this,
						SharePrefUtil.KEY.SETTINGS_3G_4G_GUANKAN, on);

			}
		});
		boolean is3G_4G = SharePrefUtil.getBoolean(this,
				SharePrefUtil.KEY.SETTINGS_3G_4G_GUANKAN,
				SharePrefUtil.KEY.SETTINGS_3G_4G_DEFAULT);
		if (is3G_4G) {
			toggleButtonGuankan.setToggleOn();
		} else {
			toggleButtonGuankan.setToggleOff();
		}
		buttBack.setOnClickListener(this);
		imageButtonAboutUs.setOnClickListener(this);
		imageButtonPush.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.leftButton:
			finish();
			break;
		case R.id.imgbtn_about_us:
			startActivity(new Intent(this, CBNSettingsAboutUsAcitivty.class));
			break;
		case R.id.imgbtn_push:
			startActivity(new Intent(this, CBNSettingsPush.class));
			break;
		default:
			break;
		}
	}
}
