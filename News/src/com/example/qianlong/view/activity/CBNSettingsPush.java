package com.example.qianlong.view.activity;

import com.base.common.ui.ToggleButton;
import com.base.common.ui.ToggleButton.OnToggleChanged;
import com.example.qianlong.R;
import com.example.qianlong.utils.SharePrefUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CBNSettingsPush extends Activity implements OnClickListener {
	private Button buttBack;
	private ToggleButton toggleButtonHongguan;
	private ToggleButton toggleButtonShangye;
	private ToggleButton toggleButtonTouzi;
	private ToggleButton toggleButtonYejian;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cbn_settings_push);
		((TextView)findViewById(R.id.tvTitle)).setText(getResources().getString(R.string.cbn_settings_push));
		buttBack = (Button) findViewById(R.id.leftButton);
		buttBack.setOnClickListener(this);
		toggleButtonHongguan = (ToggleButton) findViewById(R.id.togglebut_hongguan);
		toggleButtonHongguan.setOnToggleChanged(new OnToggleChanged() {
			@Override
			public void onToggle(boolean on) {
				SharePrefUtil.saveBoolean(CBNSettingsPush.this,
						SharePrefUtil.KEY.SETTINGS_PUSH_HONGGUAN, on);
			}
		});
		toggleButtonShangye = (ToggleButton) findViewById(R.id.togglebut_shangye);
		toggleButtonShangye.setOnToggleChanged(new OnToggleChanged() {

			@Override
			public void onToggle(boolean on) {
				SharePrefUtil.saveBoolean(CBNSettingsPush.this,
						SharePrefUtil.KEY.SETTINGS_PUSH_SHANGYE, on);
			}
		});
		toggleButtonTouzi = (ToggleButton) findViewById(R.id.togglebut_touzi);
		toggleButtonTouzi.setOnToggleChanged(new OnToggleChanged() {

			@Override
			public void onToggle(boolean on) {
				SharePrefUtil.saveBoolean(CBNSettingsPush.this,
						SharePrefUtil.KEY.SETTINGS_PUSH_TOUZI, on);
			}
		});
		toggleButtonYejian = (ToggleButton) findViewById(R.id.togglebut_yejian);
		toggleButtonYejian.setOnToggleChanged(new OnToggleChanged() {

			@Override
			public void onToggle(boolean on) {
				SharePrefUtil.saveBoolean(CBNSettingsPush.this,
						SharePrefUtil.KEY.SETTINGS_PUSH_YEJIAN, on);

			}
		});
		initToggleButtons();

	}

	private void initToggleButtons() {
		boolean isOpenHongguan = SharePrefUtil.getBoolean(this,
				SharePrefUtil.KEY.SETTINGS_PUSH_HONGGUAN,
				SharePrefUtil.KEY.SETTINGS_PUSH_DEFAULT);
		boolean isOpenShangye = SharePrefUtil.getBoolean(this,
				SharePrefUtil.KEY.SETTINGS_PUSH_SHANGYE,
				SharePrefUtil.KEY.SETTINGS_PUSH_DEFAULT);
		boolean isOpenTouzi = SharePrefUtil.getBoolean(this,
				SharePrefUtil.KEY.SETTINGS_PUSH_TOUZI,
				SharePrefUtil.KEY.SETTINGS_PUSH_DEFAULT);
		boolean isOpenYejian = SharePrefUtil.getBoolean(this,
				SharePrefUtil.KEY.SETTINGS_PUSH_YEJIAN,
				SharePrefUtil.KEY.SETTINGS_PUSH_DEFAULT);
		if (isOpenHongguan) {
			toggleButtonHongguan.setToggleOn();
		} else {
			toggleButtonHongguan.setToggleOff();
		}
		if (isOpenShangye) {
			toggleButtonShangye.setToggleOn();
		} else {
			toggleButtonShangye.setToggleOff();
		}
		if (isOpenTouzi) {
			toggleButtonTouzi.setToggleOn();
		} else {
			toggleButtonTouzi.setToggleOff();
		}
		if (isOpenYejian) {
			toggleButtonYejian.setToggleOn();
		} else {
			toggleButtonYejian.setToggleOff();
		}

	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.leftButton:
			finish();
			break;
		case R.id.togglebut_hongguan:
			break;
		case R.id.togglebut_shangye:
			break;
		case R.id.togglebut_touzi:
			break;
		case R.id.togglebut_yejian:
			break;
		}
	}

}
