package com.example.qianlong.view.activity;

import com.example.qianlong.R;
import com.example.qianlong.view.fragment.HomeFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

public class MainActivity extends FragmentActivity {
	private HomeFragment mHomeFragment;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.content_frame);
		if (savedInstanceState == null) {
			mHomeFragment = new HomeFragment();
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.content_frame, mHomeFragment, "Home")
					.commit();

		}
	}

}
