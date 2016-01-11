package com.example.qianlong.view.popupwindow;

import com.example.qianlong.R;
import com.example.qianlong.utils.FastBlur;
import com.example.qianlong.view.activity.CBNLiveBroadCastActivity;
import com.example.qianlong.view.activity.CBNLiveActivity;
import com.example.qianlong.view.activity.CBNLiveTextActivity;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class LivePopupWindow extends PopupWindow implements OnClickListener {

	private String TAG = LivePopupWindow.class.getSimpleName();
	Activity mContext;
	private int mWidth;
	private int mHeight;
	private int statusBarHeight;
	private Bitmap mBitmap = null;
	private Bitmap overlay = null;

	private Handler mHandler = new Handler();

	public LivePopupWindow(Activity context) {
		mContext = context;
	}

	public void init() {
		Rect frame = new Rect();
		mContext.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		statusBarHeight = frame.top;
		DisplayMetrics metrics = new DisplayMetrics();
		mContext.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		mWidth = metrics.widthPixels;
		mHeight = metrics.heightPixels;

		setWidth(mWidth);
		setHeight(mHeight);
	}

	private Bitmap blur() {
		if (null != overlay) {
			return overlay;
		}
		long startMs = System.currentTimeMillis();

		View view = mContext.getWindow().getDecorView();
		view.setDrawingCacheEnabled(true);
		view.buildDrawingCache(true);
		mBitmap = view.getDrawingCache();

		float scaleFactor = 8;
		float radius = 40;
		int width = mBitmap.getWidth();
		int height = mBitmap.getHeight();

		overlay = Bitmap.createBitmap((int) (width / scaleFactor),
				(int) (height / scaleFactor), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(overlay);
		canvas.scale(1 / scaleFactor, 1 / scaleFactor);
		Paint paint = new Paint();
		paint.setFlags(Paint.FILTER_BITMAP_FLAG);
		canvas.drawBitmap(mBitmap, 0, 0, paint);

		overlay = FastBlur.doBlur(overlay, (int) radius, true);
		Log.i(TAG, "blur time is:" + (System.currentTimeMillis() - startMs));
		return overlay;
	}

	private Animation showAnimation1(final View view, int fromY, int toY) {
		AnimationSet set = new AnimationSet(true);
		TranslateAnimation go = new TranslateAnimation(0, 0, fromY, toY);
		go.setDuration(300);
		TranslateAnimation go1 = new TranslateAnimation(0, 0, -10, 2);
		go1.setDuration(100);
		go1.setStartOffset(250);
		set.addAnimation(go1);
		set.addAnimation(go);

		set.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationEnd(Animation animation) {
			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			@Override
			public void onAnimationStart(Animation animation) {

			}

		});
		return set;
	}

	public void showMoreWindow(View anchor, int bottomMargin) {
		final LinearLayout layout = (LinearLayout) LayoutInflater
				.from(mContext).inflate(R.layout.cbn_center_live_window, null);
		setContentView(layout);
		ImageView close = (ImageView) layout
				.findViewById(R.id.center_music_window_close);
		layout.findViewById(R.id.more_window_local).setOnClickListener(this);
		layout.findViewById(R.id.more_window_collect).setOnClickListener(this);
		layout.findViewById(R.id.more_window_delete).setOnClickListener(this);
		layout.findViewById(R.id.more_window_online).setOnClickListener(this);
		init();
		close.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (isShowing()) {

					closeAnimation(layout);
				}
			}

		});

		showAnimation(layout);
		setOutsideTouchable(true);
		setFocusable(true);
		showAtLocation(anchor, Gravity.BOTTOM, 0, statusBarHeight);
	}

	private void showAnimation(ViewGroup layout) {
		for (int i = 0; i < layout.getChildCount(); i++) {
			final View child = layout.getChildAt(i);
			if (child.getId() == R.id.center_music_window_close) {
				continue;
			}
			child.setOnClickListener(this);
			child.setVisibility(View.INVISIBLE);
			mHandler.postDelayed(new Runnable() {

				@Override
				public void run() {
					child.setVisibility(View.VISIBLE);
					ValueAnimator fadeAnim = ObjectAnimator.ofFloat(child,
							"translationY", 600, 0);
					fadeAnim.setDuration(300);
					PopupWindowAnimator kickAnimator = new PopupWindowAnimator();
					kickAnimator.setDuration(150);
					fadeAnim.setEvaluator(kickAnimator);
					fadeAnim.start();
				}
			}, i * 50);
		}

	}

	private void closeAnimation(ViewGroup layout) {
		for (int i = 0; i < layout.getChildCount(); i++) {
			final View child = layout.getChildAt(i);
			if (child.getId() == R.id.center_music_window_close) {
				continue;
			}
			child.setOnClickListener(this);
			mHandler.postDelayed(new Runnable() {
				public void run() {
					child.setVisibility(View.VISIBLE);
					ValueAnimator fadeAnim = ObjectAnimator.ofFloat(child,
							"translationY", 0, 600);
					fadeAnim.setDuration(200);
					PopupWindowAnimator kickAnimator = new PopupWindowAnimator();
					kickAnimator.setDuration(100);
					fadeAnim.setEvaluator(kickAnimator);
					fadeAnim.start();
					fadeAnim.addListener(new AnimatorListener() {

						@Override
						public void onAnimationStart(Animator animation) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onAnimationRepeat(Animator animation) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onAnimationEnd(Animator animation) {
							dismiss();
							child.setVisibility(View.INVISIBLE);
						}

						@Override
						public void onAnimationCancel(Animator animation) {
							// TODO Auto-generated method stub

						}
					});
				}
			}, (layout.getChildCount() - i - 1) * 30);

			if (child.getId() == R.id.more_window_local) {
				mHandler.postDelayed(new Runnable() {

					@Override
					public void run() {
						dismiss();
					}
				}, (layout.getChildCount() - i) * 30 + 80);
			}
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.more_window_local:
			mContext.startActivity(new Intent(mContext, CBNLiveActivity.class));
			dismiss();
			break;
		case R.id.more_window_online:
			mContext.startActivity(new Intent(mContext,
					CBNLiveTextActivity.class));
			dismiss();
			break;
		case R.id.more_window_delete:
			break;
		case R.id.more_window_collect:
			mContext.startActivity(new Intent(mContext,
					CBNLiveBroadCastActivity.class));
			dismiss();
			break;
		default:
			break;
		}
	}

	public void destroy() {
		if (null != overlay) {
			overlay.recycle();
			overlay = null;
			System.gc();
		}
		if (null != mBitmap) {
			mBitmap.recycle();
			mBitmap = null;
			System.gc();
		}
	}

}
