package com.example.qianlong.activity;
import com.example.qianlong.HomeFragment2;
import com.example.qianlong.MenuFragment;
import com.example.qianlong.R;
import com.example.qianlong.R.dimen;
import com.example.qianlong.R.drawable;
import com.example.qianlong.R.id;
import com.example.qianlong.R.layout;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import android.os.Bundle;
import android.view.Window;

public class MainActivity extends SlidingFragmentActivity {
	private MenuFragment mMenuFragment;
	private HomeFragment2 mHomeFragment;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setBehindContentView(R.layout.menu_frame);
		setContentView(R.layout.content_frame);
		
		SlidingMenu sm = getSlidingMenu();
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.35f);
		getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		if(savedInstanceState==null){
			mMenuFragment = new MenuFragment();
			mHomeFragment =new HomeFragment2();
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.menu_frame, mMenuFragment,"Menu").commit();
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.content_frame,mHomeFragment ,"Home").commit();
			
		}
		sm.setMode(SlidingMenu.LEFT);
	}


	public MenuFragment getMenuFragment(){
		mMenuFragment = (MenuFragment) getSupportFragmentManager().findFragmentByTag("Menu");
		return mMenuFragment;
		
	}
}
