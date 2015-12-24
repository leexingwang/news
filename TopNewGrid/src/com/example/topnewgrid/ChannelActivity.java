package com.example.topnewgrid;

import java.util.ArrayList;
import com.example.topnewgrid.adapter.ChannelOtherAdapter;
import com.example.topnewgrid.adapter.ChannelDragAdapter;
import com.example.topnewgrid.app.AppApplication;
import com.example.topnewgrid.bean.ChannelManage;
import com.example.topnewgrid.bean.ChannelItem;
import com.example.topnewgrid.view.ChannelDragGrid;
import com.example.topnewgrid.view.ChannelOtherGridView;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.graphics.Bitmap;
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
 *               Company:��һ�ƾ���ý���޹�˾
 * 
 * @author yicai-lixingwang
 * 
 * @date 2015-12-15 ����4:33:46
 * 
 * @version V1.0
 */
public class ChannelActivity extends Activity implements OnItemClickListener,
		OnClickListener {
	/** �û���Ŀ��GRIDVIEW */
	private ChannelDragGrid userGridView;
	/** ������Ŀ��GRIDVIEW */
	private ChannelOtherGridView otherGridView;
	/** �û���Ŀ�ı༭��ť */
	private TextView userTextView;
	/** �û���Ŀ��Ӧ���������������϶� */
	ChannelDragAdapter userAdapter;
	/** ������Ŀ��Ӧ�������� */
	ChannelOtherAdapter otherAdapter;
	/** ������Ŀ�б� */
	ArrayList<ChannelItem> otherChannelList = new ArrayList<ChannelItem>();
	/** �û���Ŀ�б� */
	ArrayList<ChannelItem> userChannelList = new ArrayList<ChannelItem>();
	/** �Ƿ����ƶ�����������Ƕ���������Ž��е����ݸ��棬�����������Ϊ�˱������̫Ƶ����ɵ����ݴ��ҡ� */
	boolean isMove = false;
	/** �Ƿ��ڱ༭״̬ */
	boolean isEdit = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.channel_subscribe_activity);
		initView();
		initData();
	}

	/** ��ʼ������ */
	private void initData() {
		userChannelList = ((ArrayList<ChannelItem>) ChannelManage.getManage(
				AppApplication.getApp().getSQLHelper()).getUserChannel());
		otherChannelList = ((ArrayList<ChannelItem>) ChannelManage.getManage(
				AppApplication.getApp().getSQLHelper()).getOtherChannel());
		userAdapter = new ChannelDragAdapter(this, userChannelList, isEdit);
		userGridView.setAdapter(userAdapter);
		otherAdapter = new ChannelOtherAdapter(this, otherChannelList);
		otherGridView.setAdapter(this.otherAdapter);
	}

	/** ��ʼ������ */
	private void initView() {
		userGridView = (ChannelDragGrid) findViewById(R.id.userGridView);
		otherGridView = (ChannelOtherGridView) findViewById(R.id.otherGridView);
		userTextView = (TextView) findViewById(R.id.my_category_text_ok);
		// ����GRIDVIEW��ITEM�ĵ������
		otherGridView.setOnItemClickListener(this);
		userGridView.setOnItemClickListener(this);
		userTextView.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/** GRIDVIEW��Ӧ��ITEM��������ӿ� */
	@Override
	public void onItemClick(AdapterView<?> parent, final View view,
			final int position, long id) {
		// ��������ʱ��֮ǰ������û��������ô���õ���¼���Ч
		if (isMove) {
			return;
		}
		switch (parent.getId()) {
		case R.id.userGridView:
			// positionΪ 0��1 �Ĳ����Խ����κβ���
			if (position != 0 && isEdit) {
				final ImageView moveImageView = getView(view);
				if (moveImageView != null) {
					TextView newTextView = (TextView) view
							.findViewById(R.id.text_item);
					final int[] startLocation = new int[2];
					newTextView.getLocationInWindow(startLocation);
					final ChannelItem channel = ((ChannelDragAdapter) parent
							.getAdapter()).getItem(position);// ��ȡ�����Ƶ������
					otherAdapter.setVisible(false);
					// ��ӵ����һ��
					otherAdapter.addItem(channel);
					new Handler().postDelayed(new Runnable() {
						public void run() {
							try {
								int[] endLocation = new int[2];
								// ��ȡ�յ������
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
							.getAdapter()).getItem(position);// ��ȡ�����Ƶ������
					Toast.makeText(this, channel.getName(), Toast.LENGTH_SHORT).show();
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
				final ChannelItem channel = ((ChannelOtherAdapter) parent.getAdapter())
						.getItem(position);
				userAdapter.setVisible(false);
				// ��ӵ����һ��
				userAdapter.addItem(channel);
				new Handler().postDelayed(new Runnable() {
					public void run() {
						try {
							int[] endLocation = new int[2];
							// ��ȡ�յ������
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.my_category_text_ok:
			if (isEdit) {
				isEdit = false;
				userAdapter = new ChannelDragAdapter(this, userChannelList, isEdit);
				userAdapter.notifyDataSetChanged();
				userGridView.setAdapter(userAdapter);
				userGridView.invalidateViews();
				userTextView.setText(getResources().getString(
						R.string.subscribe_my_category_edit));

			} else {
				isEdit = true;
				userAdapter = new ChannelDragAdapter(this, userChannelList, isEdit);
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

	/**
	 * ���ITEM�ƶ�����
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
		// ��ȡ���ݹ�����VIEW������
		moveView.getLocationInWindow(initLocation);
		// �õ�Ҫ�ƶ���VIEW,�������Ӧ��������
		final ViewGroup moveViewGroup = getMoveViewGroup();
		final View mMoveView = getMoveView(moveViewGroup, moveView,
				initLocation);
		// �����ƶ�����
		TranslateAnimation moveAnimation = new TranslateAnimation(
				startLocation[0], endLocation[0], startLocation[1],
				endLocation[1]);
		moveAnimation.setDuration(300L);// ����ʱ��
		// ��������
		AnimationSet moveAnimationSet = new AnimationSet(true);
		moveAnimationSet.setFillAfter(false);// ����Ч��ִ����Ϻ�View���󲻱�������ֹ��λ��
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
				// instanceof �����ж�2��ʵ���ǲ���һ�����жϵ������DragGrid����OtherGridView
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
	 * ��ȡ�ƶ���VIEW�������ӦViewGroup��������
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
	 * �����ƶ���ITEM��Ӧ��ViewGroup��������
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
	 * ��ȡ�����Item�Ķ�ӦView��
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

	/** �˳�ʱ�򱣴�ѡ������ݿ������ */
	private void saveChannel() {
		ChannelManage.getManage(AppApplication.getApp().getSQLHelper())
				.deleteAllChannel();
		ChannelManage.getManage(AppApplication.getApp().getSQLHelper())
				.saveUserChannel(userAdapter.getChannnelLst());
		ChannelManage.getManage(AppApplication.getApp().getSQLHelper())
				.saveOtherChannel(otherAdapter.getChannnelLst());
	}

	@Override
	public void onBackPressed() {
		saveChannel();
		super.onBackPressed();
	}
}
