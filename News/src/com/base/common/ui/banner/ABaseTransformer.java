package com.base.common.ui.banner;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;

public abstract class ABaseTransformer implements PageTransformer {
    public ABaseTransformer() {
    }

    protected abstract void onTransform(View var1, float var2);

    public void transformPage(View page, float position) {
        this.onPreTransform(page, position);
        this.onTransform(page, position);
        this.onPostTransform(page, position);
    }

    protected boolean hideOffscreenPages() {
        return true;
    }

    protected boolean isPagingEnabled() {
        return false;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
	protected void onPreTransform(View page, float position) {
        float width = (float)page.getWidth();
        page.setRotationX(0.0F);
        page.setRotationY(0.0F);
        page.setRotation(0.0F);
        page.setScaleX(1.0F);
        page.setScaleY(1.0F);
        page.setPivotX(0.0F);
        page.setPivotY(0.0F);
        page.setTranslationY(0.0F);
        page.setTranslationX(this.isPagingEnabled()?0.0F:-width * position);
        if(this.hideOffscreenPages()) {
            page.setAlpha(position > -1.0F && position < 1.0F?1.0F:0.0F);
            page.setEnabled(false);
        } else {
            page.setEnabled(true);
            page.setAlpha(1.0F);
        }

    }

    protected void onPostTransform(View page, float position) {
    }

    protected static final float min(float val, float min) {
        return val < min?min:val;
    }
}
