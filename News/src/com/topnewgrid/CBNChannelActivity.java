package com.topnewgrid;

import java.util.ArrayList;

import com.example.qianlong.R;
import com.example.qianlong.application.AppApplication;
import com.example.qianlong.base.BaseActivity;
import com.example.qianlong.constants.Constants;
import com.topnewgrid.adapter.ChannelDragAdapter;
import com.topnewgrid.adapter.ChannelOtherAdapter;
import com.topnewgrid.bean.ChannelItem;
import com.topnewgrid.bean.ChannelManage;
import com.topnewgrid.view.ChannelDragGrid;
import com.topnewgrid.view.ChannelOtherGridView;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @Title: ChannelActivity.java
 * 
 * @Package com.example.topnewgrid
 * 
 * @Description:
 * 
 *               Copyright: Copyright (c) 2015
 * 
 *               Company:第一财经传媒有限公司
 * 
 * @author yicai-lixingwang
 * 
 * @date 2015-12-15 下午4:33:46
 * 
 * @version V1.0
 */
public class CBNChannelActivity extends BaseActivity implements
		OnItemClickListener, OnClickListener {
	/** 用户栏目的GRIDVIEW */
	private ChannelDragGrid userGridView;
	/** 其它栏目的GRIDVIEW */
	private ChannelOtherGridView otherGridView;
	/** 用户栏目的编辑按钮 */
	private TextView userTextView;
	/** 用户栏目对应的适配器，可以拖动 */
	ChannelDragAdapter userAdapter;
	/** 其它栏目对应的适配器 */
	ChannelOtherAdapter otherAdapter;
	/** 其它栏目列表 */
	ArrayList<ChannelItem> otherChannelList = new ArrayList<ChannelItem>();
	/** 用户栏目列表 */
	ArrayList<ChannelItem> userChannelList = new ArrayList<ChannelItem>();
	/** 是否在移动，由于这边是动画结束后才进行的数据更替，设置这个限制为了避免操作太频繁造成的数据错乱。 */
	boolean isMove = false;
	/** 是否在编辑状态 */
	boolean isEdit = false;

	@Override
	protected void initView() {
		setContentView(R.layout.channel_subscribe_activity);
		initTitleBar();
		userGridView = (ChannelDragGrid) findViewById(R.id.userGridView);
		otherGridView = (ChannelOtherGridView) findViewById(R.id.otherGridView);
		userTextView = (TextView) findViewById(R.id.my_category_text_ok);
		// 设置GRIDVIEW的ITEM的点击监听
		otherGridView.setOnItemClickListener(this);
		userGridView.setOnItemClickListener(this);
		userTextView.setOnClickListener(this);
	}

	@Override
	protected void initData() {
		userChannelList = ((ArrayList<ChannelItem>) ChannelManage.getManage(
				AppApplication.getApp().getSQLHelper()).getUserChannel());
		otherChannelList = ((ArrayList<ChannelItem>) ChannelManage.getManage(
				AppApplication.getApp().getSQLHelper()).getOtherChannel());
		userAdapter = new ChannelDragAdapter(this, userChannelList, isEdit);
		userGridView.setAdapter(userAdapter);
		otherAdapter = new ChannelOtherAdapter(this, otherChannelList);
		otherGridView.setAdapter(this.otherAdapter);
	}

	@Override
	public void onBackPressed() {
		saveChannel();
		Intent intent = getIntent();
		intent.putExtra(Constants.CHANGE_BACK_TYPE,
				Constants.CHANGE_BACK_TYPE_BACKKEY);
		this.setResult(Constants.CHANGE_CHANNEL, intent);
		super.onBackPressed();
		((Activity) ct).overridePendingTransition(android.R.anim.fade_in,
				R.anim.fade_form_down_to_up);
	}

	@Override
	protected void processClick(View v) {
		switch (v.getId()) {
		case R.id.my_category_text_ok:
			if (isEdit) {
				isEdit = false;
				userAdapter = new ChannelDragAdapter(this, userChannelList,
						isEdit);
				userAdapter.notifyDataSetChanged();
				userGridView.setAdapter(userAdapter);
				userGridView.invalidateViews();
				userTextView.setText(getResources().getString(
						R.string.subscribe_my_category_edit));

			} else {
				isEdit = true;
				userAdapter = new ChannelDragAdapter(this, userChannelList,
						isEdit);
				userAdapter.notifyDataSetChanged();
				userGridView.setAdapter(userAdapter);
				userGridView.invalidateViews();
				userTextView.setText(getResources().getString(
						R.string.subscribe_my_category_ok));
			}
			break;
		default:
			break;
		}

	}

	/** GRIDVIEW对应的ITEM点击监听接口 */
	@Override
	public void onItemClick(AdapterView<?> parent, final View view,
			final int position, long id) {
		// 如果点击的时候，之前动画还没结束，那么就让点击事件无效
		if (isMove) {
			return;
		}
		switch (parent.getId()) {
		case R.id.userGridView:
			// position为 0，1 的不可以进行任何操作
			if (position != 0 && isEdit) {
				final ImageView moveImageView = getView(view);
				if (moveImageView != null) {
					TextView newTextView = (TextView) view
							.findViewById(R.id.text_item);
					final int[] startLocation = new int[2];
					newTextView.getLocationInWindow(startLocation);
					final ChannelItem channel = ((ChannelDragAdapter) parent
							.getAdapter()).getItem(position);// 获取点击的频道内容
					otherAdapter.setVisible(false);
					// 添加到最后一个
					otherAdapter.addItem(channel);
					new Handler().postDelayed(new Runnable() {
						public void run() {
							try {
								int[] endLocation = new int[2];
								// 获取终点的坐标
								otherGridView.getChildAt(
										otherGridView.getLastVisiblePosition())
										.getLocationInWindow(endLocation);
								MoveAnim(moveImageView, startLocation,
										endLocation, channel, userGridView);
								userAdapter.setRemove(position);
							} catch (Exception localException) {
							}
						}
					}, 50L);
				}
			} else {
				final ImageView moveImageView = getView(view);
				if (moveImageView != null) {
					final ChannelItem channel = ((ChannelDragAdapter) parent
							.getAdapter()).getItem(position);// 获取点击的频道内容
					Toast.makeText(this, channel.getName(), Toast.LENGTH_SHORT)
							.show();
					saveChannel();
					Intent intent = getIntent();
					intent.putExtra(Constants.CHANGE_CLICK_NUMBER, position);
					intent.putExtra(Constants.CHANGE_BACK_TYPE,
							Constants.CHANGE_BACK_TYPE_CLICK);
					this.setResult(Constants.CHANGE_CHANNEL, intent);
					finish();
					((Activity) ct)
							.overridePendingTransition(android.R.anim.fade_in,
									R.anim.fade_form_down_to_up);

				}
			}
			break;
		case R.id.otherGridView:
			final ImageView moveImageView = getView(view);
			if (moveImageView != null) {
				TextView newTextView = (TextView) view
						.findViewById(R.id.text_item);
				final int[] startLocation = new int[2];
				newTextView.getLocationInWindow(startLocation);
				final ChannelItem channel = ((ChannelOtherAdapter) parent
						.getAdapter()).getItem(position);
				userAdapter.setVisible(false);
				// 添加到最后一个
				userAdapter.addItem(channel);
				new Handler().postDelayed(new Runnable() {
					public void run() {
						try {
							int[] endLocation = new int[2];
							// 获取终点的坐标
							userGridView.getChildAt(
									userGridView.getLastVisiblePosition())
									.getLocationInWindow(endLocation);
							MoveAnim(moveImageView, startLocation, endLocation,
									channel, otherGridView);
							otherAdapter.setRemove(position);
						} catch (Exception localException) {
						}
					}
				}, 50L);
			}
			break;
		default:
			break;
		}
	}

	/**
	 * 点击ITEM移动动画
	 * 
	 * @param moveView
	 * @param startLocation
	 * @param endLocation
	 * @param moveChannel
	 * @param clickGridView
	 */
	private void MoveAnim(View moveView, int[] startLocation,
			int[] endLocation, final ChannelItem moveChannel,
			final GridView clickGridView) {
		int[] initLocation = new int[2];
		// 获取传递过来的VIEW的坐标
		moveView.getLocationInWindow(initLocation);
		// 得到要移动的VIEW,并放入对应的容器中
		final ViewGroup moveViewGroup = getMoveViewGroup();
		final View mMoveView = getMoveView(moveViewGroup, moveView,
				initLocation);
		// 创建移动动画
		TranslateAnimation moveAnimation = new TranslateAnimation(
				startLocation[0], endLocation[0], startLocation[1],
				endLocation[1]);
		moveAnimation.setDuration(300L);// 动画时间
		// 动画配置
		AnimationSet moveAnimationSet = new AnimationSet(true);
		moveAnimationSet.setFillAfter(false);// 动画效果执行完毕后，View对象不保留在终止的位置
		moveAnimationSet.addAnimation(moveAnimation);
		mMoveView.startAnimation(moveAnimationSet);
		moveAnimationSet.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				isMove = true;
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				moveViewGroup.removeView(mMoveView);
				// instanceof 方法判断2边实例是不是一样，判断点击的是DragGrid还是OtherGridView
				if (clickGridView instanceof ChannelDragGrid) {
					otherAdapter.setVisible(true);
					otherAdapter.notifyDataSetChanged();
					userAdapter.remove();
				} else {
					userAdapter.setVisible(true);
					userAdapter.notifyDataSetChanged();
					otherAdapter.remove();
				}
				isMove = false;
			}
		});
	}

	/**
	 * 获取移动的VIEW，放入对应ViewGroup布局容器
	 * 
	 * @param viewGroup
	 * @param view
	 * @param initLocation
	 * @return
	 */
	private View getMoveView(ViewGroup viewGroup, View view, int[] initLocation) {
		int x = initLocation[0];
		int y = initLocation[1];
		viewGroup.addView(view);
		LinearLayout.LayoutParams mLayoutParams = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		mLayoutParams.leftMargin = x;
		mLayoutParams.topMargin = y;
		view.setLayoutParams(mLayoutParams);
		return view;
	}

	/**
	 * 创建移动的ITEM对应的ViewGroup布局容器
	 */
	private ViewGroup getMoveViewGroup() {
		ViewGroup moveViewGroup = (ViewGroup) getWindow().getDecorView();
		LinearLayout moveLinearLayout = new LinearLayout(this);
		moveLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		moveViewGroup.addView(moveLinearLayout);
		return moveLinearLayout;
	}

	/**
	 * 获取点击的Item的对应View，
	 * 
	 * @param view
	 * @return
	 */
	private ImageView getView(View view) {
		view.destroyDrawingCache();
		view.setDrawingCacheEnabled(true);
		Bitmap cache = Bitmap.createBitmap(view.getDrawingCache());
		view.setDrawingCacheEnabled(false);
		ImageView iv = new ImageView(this);
		iv.setImageBitmap(cache);
		return iv;
	}

	/** 退出时候保存选择后数据库的设置 */
	private void saveChannel() {
		ChannelManage.getManage(AppApplication.getApp().getSQLHelper())
				.deleteAllChannel();
		ChannelManage.getManage(AppApplication.getApp().getSQLHelper())
				.saveUserChannel(userAdapter.getChannnelLst());
		ChannelManage.getManage(AppApplication.getApp().getSQLHelper())
				.saveOtherChannel(otherAdapter.getChannnelLst());
	}

	@Override
	public void finishBefore() {
		saveChannel();
		Intent intent = getIntent();
		intent.putExtra(Constants.CHANGE_BACK_TYPE,
				Constants.CHANGE_BACK_TYPE_BACKKEY);
		this.setResult(Constants.CHANGE_CHANNEL, intent);

	}

	@Override
	protected void finishChild() {
		((Activity) ct).overridePendingTransition(android.R.anim.fade_in,
				R.anim.fade_form_down_to_up);

	}

}
