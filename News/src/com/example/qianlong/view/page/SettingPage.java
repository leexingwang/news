package com.example.qianlong.view.page;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.base.common.ui.CircleImageView;
import com.example.qianlong.R;
import com.example.qianlong.R.layout;
import com.example.qianlong.base.BasePage;
import com.example.qianlong.view.activity.CBNLoginActivity;
import com.example.qianlong.view.activity.CBNSettingsActivity;
import com.lidroid.xutils.ViewUtils;

public class SettingPage extends BasePage {

	private ImageButton imageButSettings;
	private CircleImageView circleImageViewProfile;

	public SettingPage(Context context) {
		super(context);
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
