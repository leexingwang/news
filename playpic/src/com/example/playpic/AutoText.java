package com.example.playpic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.Paint.FontMetrics;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.view.View.BaseSavedState;
import android.widget.TextView;

public class AutoText extends TextView {

	private int width, height;
	private Paint paintText;
	private float posx, posy;
	private float speed = 0.0f;
	private String text = "hello haha";
	private float textWidth = 0;
	private float moveDistance = 0.0f;
	private boolean isStarting = false;

	public AutoText(Context context) {
		super(context);

	}

	public AutoText(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	private void initView() {
		paintText = new Paint();
		paintText.setTypeface(Typeface.DEFAULT_BOLD);
		paintText.setAntiAlias(true);
		text = getText().toString();
		textWidth = paintText.measureText(text);
		Log.e("msg", "textWidth= " + textWidth);
		this.speed = textWidth;
		moveDistance = textWidth * 2 + width;
	}

	public void initDisplayMetrics(WindowManager windowManager) {
		/* 取得屏幕分辨率大小 */
		DisplayMetrics dm = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(dm);
		this.width = dm.widthPixels;
		this.height = dm.heightPixels;

		initView();
		this.posx = width + textWidth;
		FontMetrics fm = paintText.getFontMetrics();
		float baseline = fm.descent - fm.ascent;
		this.posy = height / 2 - baseline;
	}

	public void startScroll() {
		isStarting = true;
		invalidate();
	}

	public void stopScroll() {
		isStarting = false;
		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawText(text, posx - speed, posy, paintText);
		if (!isStarting) {
			return;
		}
		speed += 2.0f;
		if (speed > moveDistance)
			speed = textWidth;
		invalidate();
	}

}