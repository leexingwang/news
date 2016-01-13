package com.base.common.ui.banner;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.PageTransformer;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.example.qianlong.R;

/**
 * ҳ�淭ת�ؼ���������Ĺ���� ֧������ѭ�����Զ���ҳ����ҳ��Ч
 * 
 * @author Sai ֧���Զ���ҳ
 */
public class ConvenientBanner<T> extends LinearLayout {
	private List<T> mDatas;
	private int[] page_indicatorId;
	private ArrayList<ImageView> mPointViews = new ArrayList<ImageView>();
	private CBPageChangeListener pageChangeListener;
	private ViewPager.OnPageChangeListener onPageChangeListener;
	private CBPageAdapter pageAdapter;
	private CBLoopViewPager viewPager;
	private ViewPagerScroller scroller;
	private ViewGroup loPageTurningPoint;
	private long autoTurningTime;
	private boolean turning;
	private boolean canTurn = false;
	private boolean manualPageable = true;
	private boolean canLoop = true;

	public enum PageIndicatorAlign {
		ALIGN_PARENT_LEFT, ALIGN_PARENT_RIGHT, CENTER_HORIZONTAL
	}

	private Runnable adSwitchTask = new Runnable() {
		@Override
		public void run() {
			if (viewPager != null && turning) {
				int page = viewPager.getCurrentItem() + 1;
				viewPager.setCurrentItem(page);
				postDelayed(adSwitchTask, autoTurningTime);
			}
		}
	};

	public ConvenientBanner(Context context, boolean canLoop) {
		this(context, null);
		this.canLoop = canLoop;
	}

	public ConvenientBanner(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.ConvenientBanner);
		canLoop = a.getBoolean(R.styleable.ConvenientBanner_canLoop, true);
		init(context);
	}

	private void init(Context context) {
		View hView = LayoutInflater.from(context).inflate(
				R.layout.include_viewpager, this, true);
		viewPager = (CBLoopViewPager) hView.findViewById(R.id.cbLoopViewPager);
		loPageTurningPoint = (ViewGroup) hView
				.findViewById(R.id.loPageTurningPoint);
		initViewPagerScroll();
	}

	public ConvenientBanner setPages(CBViewHolderCreator holderCreator,
			List<T> datas) {
		this.mDatas = datas;
		pageAdapter = new CBPageAdapter(holderCreator, mDatas);
		viewPager.setAdapter(pageAdapter, canLoop);

		if (page_indicatorId != null)
			setPageIndicator(page_indicatorId);
		return this;
	}

	/**
	 * ֪ͨ���ݱ仯 ���ֻ���������ݽ���ʹ�� notifyDataSetAdd()
	 */
	public void notifyDataSetChanged() {
		viewPager.getAdapter().notifyDataSetChanged();
		if (page_indicatorId != null)
			setPageIndicator(page_indicatorId);
	}

	/**
	 * ���õײ�ָʾ���Ƿ�ɼ�
	 * 
	 * @param visible
	 */
	public ConvenientBanner setPointViewVisible(boolean visible) {
		loPageTurningPoint.setVisibility(visible ? View.VISIBLE : View.GONE);
		return this;
	}

	/**
	 * �ײ�ָʾ����ԴͼƬ
	 * 
	 * @param page_indicatorId
	 */
	public ConvenientBanner setPageIndicator(int[] page_indicatorId) {
		loPageTurningPoint.removeAllViews();
		mPointViews.clear();
		this.page_indicatorId = page_indicatorId;
		if (mDatas == null)
			return this;
		for (int count = 0; count < mDatas.size(); count++) {
			// ��ҳָʾ�ĵ�
			ImageView pointView = new ImageView(getContext());
			pointView.setPadding(5, 0, 5, 0);
			if (mPointViews.isEmpty())
				pointView.setImageResource(page_indicatorId[1]);
			else
				pointView.setImageResource(page_indicatorId[0]);
			mPointViews.add(pointView);
			loPageTurningPoint.addView(pointView);
		}
		pageChangeListener = new CBPageChangeListener(mPointViews,
				page_indicatorId);
		viewPager.setOnPageChangeListener(pageChangeListener);
		pageChangeListener.onPageSelected(viewPager.getRealItem());
		if (onPageChangeListener != null)
			pageChangeListener.setOnPageChangeListener(onPageChangeListener);

		return this;
	}

	/**
	 * ָʾ���ķ���
	 * 
	 * @param align
	 *            �������򣺾��� ��RelativeLayout.ALIGN_PARENT_LEFT��������
	 *            ��RelativeLayout.CENTER_HORIZONTAL��������
	 *            ��RelativeLayout.ALIGN_PARENT_RIGHT��
	 * @return
	 */
	public ConvenientBanner setPageIndicatorAlign(PageIndicatorAlign align) {
		RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) loPageTurningPoint
				.getLayoutParams();
		layoutParams
				.addRule(
						RelativeLayout.ALIGN_PARENT_LEFT,
						align == PageIndicatorAlign.ALIGN_PARENT_LEFT ? RelativeLayout.TRUE
								: 0);
		layoutParams
				.addRule(
						RelativeLayout.ALIGN_PARENT_RIGHT,
						align == PageIndicatorAlign.ALIGN_PARENT_RIGHT ? RelativeLayout.TRUE
								: 0);
		layoutParams
				.addRule(
						RelativeLayout.CENTER_HORIZONTAL,
						align == PageIndicatorAlign.CENTER_HORIZONTAL ? RelativeLayout.TRUE
								: 0);
		loPageTurningPoint.setLayoutParams(layoutParams);
		return this;
	}

	/***
	 * �Ƿ����˷�ҳ
	 * 
	 * @return
	 */
	public boolean isTurning() {
		return turning;
	}

	/***
	 * ��ʼ��ҳ
	 * 
	 * @param autoTurningTime
	 *            �Զ���ҳʱ��
	 * @return
	 */
	public ConvenientBanner startTurning(long autoTurningTime) {
		// ��������ڷ�ҳ�Ļ���ͣ��
		if (turning) {
			stopTurning();
		}
		// ���ÿ��Է�ҳ��������ҳ
		canTurn = true;
		this.autoTurningTime = autoTurningTime;
		turning = true;
		postDelayed(adSwitchTask, autoTurningTime);
		return this;
	}

	public void stopTurning() {
		turning = false;
		removeCallbacks(adSwitchTask);
	}

	/**
	 * �Զ��巭ҳ����Ч��
	 * 
	 * @param transformer
	 * @return
	 */
	public ConvenientBanner setPageTransformer(PageTransformer transformer) {
		viewPager.setPageTransformer(true, transformer);
		return this;
	}

	/**
	 * ����ViewPager�Ļ����ٶ�
	 * */
	private void initViewPagerScroll() {
		try {
			Field mScroller = null;
			mScroller = ViewPager.class.getDeclaredField("mScroller");
			mScroller.setAccessible(true);
			scroller = new ViewPagerScroller(viewPager.getContext());
			mScroller.set(viewPager, scroller);

		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public boolean isManualPageable() {
		return viewPager.isCanScroll();
	}

	public void setManualPageable(boolean manualPageable) {
		viewPager.setCanScroll(manualPageable);
	}

	// �����ؼ���ʱ�򣬷�ҳӦ��ֹͣ���뿪��ʱ�����֮ǰ�ǿ����˷�ҳ�Ļ�������������ҳ
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {

		int action = ev.getAction();
		if (action == MotionEvent.ACTION_UP
				|| action == MotionEvent.ACTION_CANCEL
				|| action == MotionEvent.ACTION_OUTSIDE) {
			// ��ʼ��ҳ
			if (canTurn)
				startTurning(autoTurningTime);
		} else if (action == MotionEvent.ACTION_DOWN) {
			// ֹͣ��ҳ
			if (canTurn)
				stopTurning();
		}
		return super.dispatchTouchEvent(ev);
	}

	// ��ȡ��ǰ��ҳ��index
	public int getCurrentItem() {
		if (viewPager != null) {
			return viewPager.getRealItem();
		}
		return -1;
	}

	// ���õ�ǰ��ҳ��index
	public void setcurrentitem(int index) {
		if (viewPager != null) {
			viewPager.setCurrentItem(index);
		}
	}

	public ViewPager.OnPageChangeListener getOnPageChangeListener() {
		return onPageChangeListener;
	}

	/**
	 * ���÷�ҳ������
	 * 
	 * @param onPageChangeListener
	 * @return
	 */
	public ConvenientBanner setOnPageChangeListener(
			ViewPager.OnPageChangeListener onPageChangeListener) {
		this.onPageChangeListener = onPageChangeListener;
		// �����Ĭ�ϵļ�����������ʹ����Ĭ�ϵķ�ҳָʾ��������û����õ�������Ĭ�ϵ����棬�����ֱ������
		if (pageChangeListener != null)
			pageChangeListener.setOnPageChangeListener(onPageChangeListener);
		else
			viewPager.setOnPageChangeListener(onPageChangeListener);
		return this;
	}

	public boolean isCanLoop() {
		return viewPager.isCanLoop();
	}

	/**
	 * ����item���
	 * 
	 * @param onItemClickListener
	 */
	public ConvenientBanner setOnItemClickListener(
			OnItemClickListener onItemClickListener) {
		if (onItemClickListener == null) {
			viewPager.setOnItemClickListener(null);
			return this;
		}
		viewPager.setOnItemClickListener(onItemClickListener);
		return this;
	}

	/**
	 * ����ViewPager�Ĺ����ٶ�
	 * 
	 * @param scrollDuration
	 */
	public void setScrollDuration(int scrollDuration) {
		scroller.setScrollDuration(scrollDuration);
	}

	public int getScrollDuration() {
		return scroller.getScrollDuration();
	}

	public CBLoopViewPager getViewPager() {
		return viewPager;
	}

	public void setCanLoop(boolean canLoop) {
		this.canLoop = canLoop;
		viewPager.setCanLoop(canLoop);
	}
}
