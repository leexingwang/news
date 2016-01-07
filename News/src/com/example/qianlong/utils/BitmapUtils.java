package com.example.qianlong.utils;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;

public final class BitmapUtils {

	/**
	 * 取得指定区域的图形
	 * 
	 * @param source
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public static Bitmap getBitmap(Bitmap source, int x, int y, int width,
			int height) {
		Bitmap bitmap = Bitmap.createBitmap(source, x, y, width, height);
		return bitmap;
	}

	/**
	 * 从大图中截取小图
	 * 
	 * @param r
	 * @param resourseId
	 * @param row
	 * @param col
	 * @param rowTotal
	 * @param colTotal
	 * @return
	 */
	public static Bitmap getImage(Context context, Bitmap source, int row,
			int col, int rowTotal, int colTotal, float multiple,
			boolean isRecycle) {
		Bitmap temp = getBitmap(source, (col - 1) * source.getWidth()
				/ colTotal, (row - 1) * source.getHeight() / rowTotal,
				source.getWidth() / colTotal, source.getHeight() / rowTotal);

		if (isRecycle) {
			recycleBitmap(source);
		}
		if (multiple != 1.0) {
			Matrix matrix = new Matrix();
			matrix.postScale(multiple, multiple);
			temp = Bitmap.createBitmap(temp, 0, 0, temp.getWidth(),
					temp.getHeight(), matrix, true);
		}
		return temp;
	}

	/**
	 * 从大图中截取小图
	 * 
	 * @param r
	 * @param resourseId
	 * @param row
	 * @param col
	 * @param rowTotal
	 * @param colTotal
	 * @return
	 */
	public static Drawable getDrawableImage(Context context, Bitmap source,
			int row, int col, int rowTotal, int colTotal, float multiple) {

		Bitmap temp = getBitmap(source, (col - 1) * source.getWidth()
				/ colTotal, (row - 1) * source.getHeight() / rowTotal,
				source.getWidth() / colTotal, source.getHeight() / rowTotal);
		if (multiple != 1.0) {
			Matrix matrix = new Matrix();
			matrix.postScale(multiple, multiple);
			temp = Bitmap.createBitmap(temp, 0, 0, temp.getWidth(),
					temp.getHeight(), matrix, true);
		}
		Drawable d = new BitmapDrawable(context.getResources(), temp);
		return d;
	}

	public static Drawable[] getDrawables(Context context, int resourseId,
			int row, int col, float multiple) {
		Drawable drawables[] = new Drawable[row * col];
		Bitmap source = decodeResource(context, resourseId);
		int temp = 0;
		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= col; j++) {
				drawables[temp] = getDrawableImage(context, source, i, j, row,
						col, multiple);
				temp++;
			}
		}
		if (source != null && !source.isRecycled()) {
			source.recycle();
			source = null;
		}
		return drawables;
	}

	public static Drawable[] getDrawables(Context context, String resName,
			int row, int col, float multiple) {
		Drawable drawables[] = new Drawable[row * col];
		Bitmap source = decodeBitmapFromAssets(context, resName);
		int temp = 0;
		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= col; j++) {
				drawables[temp] = getDrawableImage(context, source, i, j, row,
						col, multiple);
				temp++;
			}
		}
		if (source != null && !source.isRecycled()) {
			source.recycle();
			source = null;
		}
		return drawables;
	}

	/**
	 * 根据一张大图，返回切割后的图元数组
	 * 
	 * @param resourseId
	 *            :资源id
	 * @param row
	 *            ：总行数
	 * @param col
	 *            ：总列数 multiple:图片缩放的倍数1:表示不变，2表示放大为原来的2倍
	 * @return
	 */
	public static Bitmap[] getBitmaps(Context context, int resourseId, int row,
			int col, float multiple) {
		Bitmap bitmaps[] = new Bitmap[row * col];
		Bitmap source = decodeResource(context, resourseId);
		int temp = 0;
		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= col; j++) {
				bitmaps[temp] = getImage(context, source, i, j, row, col,
						multiple, false);
				temp++;
			}
		}
		if (source != null && !source.isRecycled()) {
			source.recycle();
			source = null;
		}
		return bitmaps;
	}

	public static Bitmap[] getBitmaps(Context context, String resName, int row,
			int col, float multiple) {
		Bitmap bitmaps[] = new Bitmap[row * col];
		Bitmap source = decodeBitmapFromAssets(context, resName);
		int temp = 0;
		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= col; j++) {
				bitmaps[temp] = getImage(context, source, i, j, row, col,
						multiple, false);
				temp++;
			}
		}
		if (source != null && !source.isRecycled()) {
			source.recycle();
			source = null;
		}
		return bitmaps;
	}

	public static Bitmap[] getBitmapsByBitmap(Context context, Bitmap source,
			int row, int col, float multiple) {
		Bitmap bitmaps[] = new Bitmap[row * col];
		int temp = 0;
		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= col; j++) {
				bitmaps[temp] = getImage(context, source, i, j, row, col,
						multiple, false);
				temp++;
			}
		}
		return bitmaps;
	}

	public static Bitmap decodeResource(Context context, int resourseId) {
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Bitmap.Config.ARGB_8888;
		opt.inPurgeable = true;
		opt.inInputShareable = true; // 需把 inPurgeable设置为true，否则被忽略
		// 获取资源图片
		InputStream is = context.getResources().openRawResource(resourseId);
		return BitmapFactory.decodeStream(is, null, opt); // decodeStream直接调用JNI>>nativeDecodeAsset()来完成decode，无需再使用java层的createBitmap，从而节省了java层的空间
	}

	/**
	 * 从assets文件下解析图片
	 * 
	 * @param resName
	 * @return
	 */
	public static Bitmap decodeBitmapFromAssets(Context context, String resName) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		options.inPurgeable = true;
		options.inInputShareable = true;
		InputStream in = null;
		try {
			// in = AssetsResourcesUtil.openResource(resName);
			in = context.getAssets().open(resName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return BitmapFactory.decodeStream(in, null, options);
	}

	/**
	 * 回收不用的bitmap
	 * 
	 * @param b
	 */
	public static void recycleBitmap(Bitmap b) {
		if (b != null && !b.isRecycled()) {
			b.recycle();
			b = null;
		}
	}

	/**
	 * 获取某些连在一起的图片的某一个画面（图片为横着排的情况）
	 * 
	 * @param source
	 * @param frameIndex
	 *            从1开始
	 * @param totalCount
	 * @return
	 */
	public static Bitmap getOneFrameImg(Bitmap source, int frameIndex,
			int totalCount) {
		int singleW = source.getWidth() / totalCount;
		return Bitmap.createBitmap(source, (frameIndex - 1) * singleW, 0,
				singleW, source.getHeight());
	}

	public static void recycleBitmaps(Bitmap bitmaps[]) {
		if (bitmaps != null) {
			for (Bitmap b : bitmaps) {
				recycleBitmap(b);
			}
			bitmaps = null;
		}
	}

	/**
	 * drawable转换成bitmap
	 * 
	 * @param drawable
	 * @return
	 */
	public static Bitmap drawableToBitmap(Drawable drawable) {
		if (drawable instanceof BitmapDrawable) {
			return ((BitmapDrawable) drawable).getBitmap();
		} else if (drawable instanceof NinePatchDrawable) {
			Bitmap bitmap = Bitmap
					.createBitmap(
							drawable.getIntrinsicWidth(),
							drawable.getIntrinsicHeight(),
							drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
									: Bitmap.Config.ARGB_8888);
			Canvas canvas = new Canvas(bitmap);
			drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
					drawable.getIntrinsicHeight());

			drawable.draw(canvas);
			return bitmap;
		} else {
			throw new IllegalArgumentException(
					"can not support this drawable to bitmap now!!!");
		}
	}

}
