package com.example.qianlong.view.activity;

import com.base.common.ui.ToggleButton;
import com.base.common.ui.ToggleButton.OnToggleChanged;
import com.example.qianlong.R;
import com.example.qianlong.base.BaseActivity;
import com.example.qianlong.utils.SharePrefUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CBNSettingsPushActivity extends BaseActivity implements
		OnClickListener {
	private ToggleButton toggleButtonHongguan;
	private ToggleButton toggleButtonShangye;
	private ToggleButton toggleButtonTouzi;

	@Override
	protected void initView() {
		setContentView(R.layout.cbn_settings_push);
		initTitleBar();
		toggleButtonHongguan = (ToggleButton) findViewById(R.id.togglebut_hongguan);
		toggleButtonHongguan.setOnToggleChanged(new OnToggleChanged() {
			@Override
			public void onToggle(boolean on) {
				SharePrefUtil.saveBoolean(CBNSettingsPushActivity.this,
						SharePrefUtil.KEY.SETTINGS_PUSH_HONGGUAN, on);
			}
		});
		toggleButtonShangye = (ToggleButton) findViewById(R.id.togglebut_shangye);
		toggleButtonShangye.setOnToggleChanged(new OnToggleChanged() {

			@Override
			public void onToggle(boolean on) {
				SharePrefUtil.saveBoolean(CBNSettingsPushActivity.this,
						SharePrefUtil.KEY.SETTINGS_PUSH_SHANGYE, on);
			}
		});
		toggleButtonTouzi = (ToggleButton) findViewById(R.id.togglebut_touzi);
		toggleButtonTouzi.setOnToggleChanged(new OnToggleChanged() {

			@Override
			public void onToggle(boolean on) {
				SharePrefUtil.saveBoolean(CBNSettingsPushActivity.this,
						SharePrefUtil.KEY.SETTINGS_PUSH_TOUZI, on);
			}
		});
		initToggleButtons();
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub

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

	}

	@Override
	protected void processClick(View view) {
		switch (view.getId()) {
		case R.id.togglebut_hongguan:
			break;
		case R.id.togglebut_shangye:
			break;
		case R.id.togglebut_touzi:
			break;
		}

	}

}
