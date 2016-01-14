package com.example.qianlong.view.page;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

import com.base.common.ui.CircleImageView;
import com.example.qianlong.R;
import com.example.qianlong.base.BasePage;
import com.example.qianlong.view.activity.CBNLoginActivity;
import com.example.qianlong.view.activity.CBNSettingsActivity;

public class SettingPage extends BasePage {

	private ImageButton imageButSettings;
	private CircleImageView circleImageViewProfile;
	private Handler handler;

	public SettingPage(Context context, Handler handler) {
		super(context);
		this.handler = handler;
	}

	@Override
	protected View initView(LayoutInflater inflater) {
		View view = inflater.inflate(R.layout.settings_frame, null);
		imageButSettings = (ImageButton) view
				.findViewById(R.id.imgbut_settings);
		circleImageViewProfile = (CircleImageView) view
				.findViewById(R.id.circleimage_profile_image);
		circleImageViewProfile.setOnClickListener(this);
		imageButSettings.setOnClickListener(this);
		return view;
	}

	@Override
	public void initData() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imgbut_settings:
			ct.startActivity(new Intent(ct, CBNSettingsActivity.class));
			break;
		case R.id.circleimage_profile_image:
			ct.startActivity(new Intent(ct, CBNLoginActivity.class));
			break;

		default:
			break;
		}
	}

	@Override
	protected void processClick(View v) {

	}

}
