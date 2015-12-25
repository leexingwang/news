package com.example.qianlong.ui.page;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.qianlong.R;
import com.example.qianlong.R.layout;
import com.example.qianlong.base.BasePage;
import com.example.qianlong.ui.activity.CBNSettingsActivity;
import com.lidroid.xutils.ViewUtils;

public class SettingPage extends BasePage {

	private ImageButton imageButSettings;

	public SettingPage(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected View initView(LayoutInflater inflater) {
		View view = inflater.inflate(R.layout.settings_frame, null);
		imageButSettings = (ImageButton) view
				.findViewById(R.id.imgbut_settings);
		imageButSettings.setOnClickListener(this);
		return view;
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.imgbut_settings:
			ct.startActivity(new Intent(ct, CBNSettingsActivity.class));
			break;

		default:
			break;
		}
	}

	@Override
	protected void processClick(View v) {

	}

}
