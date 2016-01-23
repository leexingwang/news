package com.example.playpic;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.TimeZone;

import org.apache.http.conn.util.InetAddressUtils;

import android.R.integer;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;


public final class Util {

	// 时间转成String
	public static String time2String(int time) {
		String s;
		if (time != 0) {
			int sec = time / 1000;
			int min = (sec / 60) % 60;
			sec %= 60;
			StringBuilder sb = new StringBuilder();
			String s2;
			if (min <= 9)
				s2 = (new StringBuilder("0")).append(min).toString();
			else
				s2 = min + "";
			sb = sb.append(s2).append(":");
			if (sec <= 9)
				s2 = (new StringBuilder("0")).append(sec).toString();
			else
				s2 = sec + "";
			s = sb.append(s2).toString();
		} else {
			s = "00:00";
		}
		return s;
	}
	public static String time2String(long time) {
		String s;
		if (time != 0) {
			long sec = (int)(time / 1000);
			long min = (sec / 60) % 60;
			sec %= 60;
			StringBuilder sb = new StringBuilder();
			String s2;
			if (min <= 9)
				s2 = (new StringBuilder("0")).append(min).toString();
			else
				s2 = min + "";
			sb = sb.append(s2).append(":");
			if (sec <= 9)
				s2 = (new StringBuilder("0")).append(sec).toString();
			else
				s2 = sec + "";
			s = sb.append(s2).toString();
		} else {
			s = "00:00";
		}
		return s;
	}

	// 转换信息时间
	public static String getInfoTime(int time) {
		int month = time / 1000000;
		int day = (time / 10000) % 100;
		int hour = (time / 100) % 100;
		int minute = time % 100;
		// String stime = (month<10?"0":"")+Integer.toString(month) + "月" +
		// (day<10?"0":"")+Integer.toString(day) + "日 "
		// + (hour<10?"0":"")+Integer.toString(hour) + ":" +
		// (minute<10?"0":"")+Integer.toString(minute);
		String stime = (month < 10 ? "0" : "") + Integer.toString(month) + "-"
				+ (day < 10 ? "0" : "") + Integer.toString(day) + " "
				+ (hour < 10 ? "0" : "") + Integer.toString(hour) + ":"
				+ (minute < 10 ? "0" : "") + Integer.toString(minute);
		return stime;
	}

	/**
	 * 检测是否有可用网络
	 * 
	 * @param context
	 * @return 网络连接状态
	 */
	public static boolean isNetworkAvailable(Context context) {
		try {
			ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			// 获取网络信息
			NetworkInfo info = cm.getActiveNetworkInfo();
			// 返回检测的网络状态
			return (info != null && info.isConnected());
		} catch (Exception e) {
			return false;
		}

	}

	public static int getNetworkType(Context context) {
		int networkType = -1;
		String typeName = "";
		if (isNetworkAvailable(context)) {
			NetworkInfo info = ((ConnectivityManager) context
					.getSystemService("connectivity")).getActiveNetworkInfo();
			if (info == null)
				return -1;
			if (info.getTypeName().equals("WIFI")) {
				typeName = "wifi";
			} else {
				try {
					typeName = info.getExtraInfo().toLowerCase();
				} catch (Exception localException) {
					localException.printStackTrace();
					typeName = "未知";
				}
			}

			String str = typeName.toLowerCase().trim();
			// Toast.makeText(context,
			// "NetWork TypeName:"+str, Toast.LENGTH_SHORT)
			// .show();
			// System.out.println("NetWork TypeName:"+str);
			if (str.length() == 0)
				return -1;
			if (!(str.equals("wifi"))) {
				if (str.equals("cmnet")) {
					networkType = 2;
				} else if (str.equals("cmwap")) {
					networkType = 3;
				} else if (str.equals("uninet")) {
					networkType = 4;
				} else if (str.equals("uniwap")) {
					networkType = 5;
				} else if (str.equals("3gnet")) {
					networkType = 6;
				} else if (str.equals("3gwap")) {
					networkType = 7;
				} else if (str.equals("ctnet")) {
					networkType = 8;
				} else if (str.equals("ctwap")) {
					networkType = 9;
				} else if (str.equals("internet")) {
					networkType = 10;
				}
				return networkType;
			} else {
				return 1;
			}
		}
		return -1;
	}

	// 检查SD卡是否安装
	public static boolean checkSDCard() {
		boolean isMounted;
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED))
			isMounted = true;
		else
			isMounted = false;
		return isMounted;
	}

	// 获取SD卡路径
	public static String getSDCardPath() {
		String sdpath = "null";
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			sdpath = Environment.getExternalStorageDirectory().getPath();
		}
		return sdpath;
	}

	public static String getCachePath(Context context) {
		String sdpath = "null";
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			sdpath = Environment.getExternalStorageDirectory().getPath();
		} else {
			sdpath = context.getCacheDir().getPath();
		}
		File dir = new File(sdpath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return sdpath;
	}

	// 获取SD卡剩余容量
	public static long getSDCardCapacity() {
		StatFs statFs = new StatFs(new File(Util.getSDCardPath()).getPath());
		return statFs.getBlockSize() * (statFs.getAvailableBlocks() - 4L);
	}

	/**
	 * 获得下载文件的类型
	 * 
	 * @param file
	 *            文件名称
	 * @return 文件类型
	 */
	public static String getMIMEType(File file) {
		String type = "";
		// 获得文件名称
		String fName = file.getName();
		// 获得文件扩展名
		String end = fName
				.substring(fName.lastIndexOf(".") + 1, fName.length())
				.toLowerCase();
		if (end.equals("m4a") || end.equals("mp3") || end.equals("mid")
				|| end.equals("xmf") || end.equals("ogg") || end.equals("wav")) {
			type = "audio";
		} else if (end.equals("3gp") || end.equals("mp4")) {
			type = "video";
		} else if (end.equals("jpg") || end.equals("gif") || end.equals("png")
				|| end.equals("jpeg") || end.equals("bmp")) {
			type = "image";
		} else if (end.equals("apk")) {
			type = "application/vnd.android.package-archive";
		} else {
			type = "*";
		}

		if (end.equals("apk")) {

		} else {
			type += "/*";
		}
		return type;
	}

	public static int computeSampleSize(BitmapFactory.Options options,
			int minSideLength, int maxNumOfPixels) {

		int initialSize = computeInitialSampleSize(options, minSideLength,
				maxNumOfPixels);

		int roundedSize;
		if (initialSize <= 8) {
			roundedSize = 1;
			while (roundedSize < initialSize) {
				roundedSize <<= 1;
			}
		} else {
			roundedSize = (initialSize + 7) / 8 * 8;
		}

		return roundedSize;
	}

	private static int computeInitialSampleSize(BitmapFactory.Options options,
			int minSideLength, int maxNumOfPixels) {

		double w = options.outWidth;
		double h = options.outHeight;

		int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math
				.sqrt(w * h / maxNumOfPixels));

		int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(
				Math.floor(w / minSideLength), Math.floor(h / minSideLength));

		if (upperBound < lowerBound) {
			// return the larger one when there is no overlapping zone.
			return lowerBound;
		}
		if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
			return 1;
		} else if (minSideLength == -1) {
			return lowerBound;
		} else {
			return upperBound;
		}
	}

	public static Bitmap getDrawable(String path, int width, int height) {
		Bitmap bitmap = null;
		try {
			BitmapFactory.Options opts = new BitmapFactory.Options();
			opts.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(path, opts);
			int width_tmp = opts.outWidth, height_tmp = opts.outHeight;
			int new_height = height;
			if (height == 0) {
				float ratio = (float) width_tmp / (float) height_tmp;
				new_height = (int) (width / ratio);
			}
			int scale = 1;
			while (true) {
				if (width_tmp / 2 < width || height_tmp / 2 < new_height)
					break;
				width_tmp /= 2;
				height_tmp /= 2;
				scale *= 2;
			}
			// opts.inSampleSize = Util.computeSampleSize(opts, -1, width *
			// new_height);
			opts.inSampleSize = scale;
			// System.out.println("inSampleSize="+opts.inSampleSize+","+width+"*"+new_height);

			opts.inJustDecodeBounds = false;
			opts.inDither = false;
			opts.inPreferredConfig = Bitmap.Config.RGB_565;
			try {
				bitmap = BitmapFactory.decodeFile(path, opts);
				if (bitmap == null)
					return null;
				return resizeImage(bitmap, width, new_height);
			} catch (OutOfMemoryError e) {
				System.gc();
				return null;
			}
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	public static Bitmap getDrawable(String url, String cacheDir, int width,
			int height) {
		try {
			String filename = String.valueOf(url.hashCode());
			File file = new File(cacheDir, filename);
			// decode image size
			BitmapFactory.Options opts = new BitmapFactory.Options();
			opts.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(new FileInputStream(file), null, opts);

			// Find the correct scale value. It should be the power of 2.
			int width_tmp = opts.outWidth, height_tmp = opts.outHeight;
			int new_height = height;
			if (height == 0) {
				float ratio = (float) width_tmp / (float) height_tmp;
				new_height = (int) (width / ratio);
			}
			int scale = 1;
			while (true) {
				if (width_tmp / 2 < width || height_tmp / 2 < new_height)
					break;
				width_tmp /= 2;
				height_tmp /= 2;
				scale *= 2;
			}
			// decode with inSampleSize
			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = scale;
			o2.inJustDecodeBounds = false;
			o2.inDither = false;
			o2.inPreferredConfig = Bitmap.Config.RGB_565;
			o2.inPurgeable = true;
			o2.inInputShareable = true;
			try {
				Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(
						file), null, o2);
				if (bitmap == null)
					return null;
				return resizeImage(bitmap, width, new_height);
			} catch (OutOfMemoryError e) {
				System.gc();
				return null;
			}
		} catch (FileNotFoundException e) {
		}
		return null;
	}

	public static Bitmap getDrawable(Resources res, int id, int width,
			int height) {
		Bitmap bitmap = null;
		try {
			BitmapFactory.Options opts = new BitmapFactory.Options();
			opts.inJustDecodeBounds = true;
			BitmapFactory.decodeResource(res, id, opts);
			int width_tmp = opts.outWidth, height_tmp = opts.outHeight;
			int new_height = height;
			if (height == 0) {
				float ratio = (float) width_tmp / (float) height_tmp;
				new_height = (int) (width / ratio);
			}
			int scale = 1;
			while (true) {
				if (width_tmp / 2 < width || height_tmp / 2 < new_height)
					break;
				width_tmp /= 2;
				height_tmp /= 2;
				scale *= 2;
			}
			// opts.inSampleSize = computeSampleSize(opts, -1, width *
			// new_height);
			opts.inSampleSize = scale;
			// System.out.println("inSampleSize="+opts.inSampleSize+","+width+"*"+new_height);

			opts.inJustDecodeBounds = false;
			opts.inDither = false;
			opts.inPreferredConfig = Bitmap.Config.RGB_565;
			try {
				bitmap = BitmapFactory.decodeResource(res, id, opts);
				if (bitmap == null)
					return null;
				return resizeImage(bitmap, width, new_height);
			} catch (OutOfMemoryError e) {
				System.gc();
				return null;
			}
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	public static boolean isAssetsFile(Resources res, String assetName) {
		try {
			String[] names = res.getAssets().list("");
			for (int i = 0; i < names.length; i++) {
				// Log.d("FILES",names[i]);
				if (names[i].equals(assetName.trim())) {
					// System.out.println(assetName+"文件存在！！！！");
					return true;
				} else {
					// System.out.println(assetName+"不存在啦！！！！");
				}
			}
		} catch (IOException e) {
		}
		// System.out.println(assetName+"不存在！");
		return false;
	}

	public static Bitmap getDrawableFromAsserts(Resources res,
			String assetName, int width, int height) {
		Bitmap bitmap = null;
		try {
			BitmapFactory.Options opts = new BitmapFactory.Options();
			opts.inJustDecodeBounds = true;
			InputStream is = res.getAssets().open(assetName);
			BitmapFactory.decodeStream(is, null, opts);
			int width_tmp = opts.outWidth, height_tmp = opts.outHeight;
			int new_height = height;
			if (height == 0) {
				float ratio = (float) width_tmp / (float) height_tmp;
				new_height = (int) (width / ratio);
			}
			int scale = 1;
			while (true) {
				if (width_tmp / 2 < width || height_tmp / 2 < new_height)
					break;
				width_tmp /= 2;
				height_tmp /= 2;
				scale *= 2;
			}
			// opts.inSampleSize = computeSampleSize(opts, -1, width *
			// new_height);
			opts.inSampleSize = scale;
			// System.out.println("inSampleSize="+opts.inSampleSize+","+width+"*"+new_height);

			opts.inJustDecodeBounds = false;
			opts.inDither = false;
			opts.inPreferredConfig = Bitmap.Config.RGB_565;
			try {
				bitmap = BitmapFactory.decodeStream(is, null, opts);
				is.close();
				if (bitmap == null)
					return null;
				return resizeImage(bitmap, width, new_height);
			} catch (OutOfMemoryError e) {
				System.gc();
				return null;
			}
		} catch (IOException e) {
			return null;
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	public static void CopyStream(InputStream is, OutputStream os) {
		final int buffer_size = 1024;
		try {
			byte[] bytes = new byte[buffer_size];
			for (;;) {
				int count = is.read(bytes, 0, buffer_size);
				if (count == -1)
					break;
				os.write(bytes, 0, count);
			}
		} catch (Exception ex) {
		}
	}

	// decode这个图片并且按比例缩放以减少内存消??，虚拟机对每张图片的缓存大小也是有限制的
	public static Bitmap decodeFile(File f, int width, int height) {
		try {
			// decode image size
			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(new FileInputStream(f), null, o);

			// Find the correct scale value. It should be the power of 2.
			int width_tmp = o.outWidth, height_tmp = o.outHeight;
			int new_height = height;
			if (height == 0) {
				float ratio = (float) width_tmp / (float) height_tmp;
				new_height = (int) (width / ratio);
			}
			int scale = 1;
			while (true) {
				if (width_tmp / 2 < width || height_tmp / 2 < new_height)
					break;
				width_tmp /= 2;
				height_tmp /= 2;
				scale *= 2;
			}
			// decode with inSampleSize
			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = scale;
			o2.inJustDecodeBounds = false;
			o2.inDither = false;
			o2.inPreferredConfig = Bitmap.Config.RGB_565;
			o2.inPurgeable = true;
			o2.inInputShareable = true;
			try {
				Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(
						f), null, o2);
				if (bitmap == null)
					return null;
				if (bitmap.getWidth() > width
						|| bitmap.getHeight() > new_height) {
					return resizeImage(bitmap, width, new_height);
				} else {
					return bitmap;
				}
			} catch (OutOfMemoryError e) {
				System.gc();
				return null;
			}
		} catch (FileNotFoundException e) {
		}
		return null;
	}

	public static Bitmap resizeImage(Bitmap bitmap, int w, int h) {

		// load the origial Bitmap
		Bitmap BitmapOrg = bitmap;

		try {
			int width = BitmapOrg.getWidth();
			int height = BitmapOrg.getHeight();
			int newWidth = w;
			int newHeight = h;

			// calculate the scale
			float scaleWidth = ((float) newWidth) / width;
			float scaleHeight = ((float) newHeight) / height;

			// create a matrix for the manipulation
			Matrix matrix = new Matrix();
			// resize the Bitmap
			matrix.postScale(scaleWidth, scaleHeight);

			// recreate the new Bitmap
			Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0, width,
					height, matrix, true);

			if (Build.VERSION.SDK_INT < 10) {
				if (!BitmapOrg.isRecycled()) {
					// BitmapOrg.recycle();
					BitmapOrg = null;
				}
			}
			return resizedBitmap;
		} catch (OutOfMemoryError e) {
			System.gc();
		}
		return bitmap;

	}

	// dip转pixel
	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	// 绘制渐变色
	public static void DrawList(Canvas canvas, Paint paint, int x, int y,
			int w, int h, int colorBegin, int colorEnd) {

		int r0 = (colorBegin >> 16) & 0xff;
		int r1 = (colorEnd >> 16) & 0xff;
		int g0 = (colorBegin >> 8) & 0xff;
		int g1 = (colorEnd >> 8) & 0xff;
		int b0 = (colorBegin) & 0xff;
		int b1 = (colorEnd) & 0xff;
		int F, r, g, b;
		for (int i = 0; i < h; ++i) {
			F = (i << 16) / h;
			r = r0 + ((F * (r1 - r0)) >> 16);
			g = g0 + ((F * (g1 - g0)) >> 16);
			b = b0 + ((F * (b1 - b0)) >> 16);
			paint.setARGB(0xff, r, g, b);
			canvas.drawRect(x, y + i, 320, y + i + 1, paint);
		}
	}

	// 图片平铺
	public static Bitmap createRepeater(int width, Bitmap src) {
		int count = (width + src.getWidth() - 1) / src.getWidth();

		Bitmap bitmap = Bitmap.createBitmap(width, src.getHeight(),
				Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);

		for (int idx = 0; idx < count; ++idx) {
			canvas.drawBitmap(src, idx * src.getWidth(), 0, null);
		}

		return bitmap;
	}

	// 设置对象的背景图片,图片采用平铺方式
	public static void setBackgroundDrawable(Context context, View v,
			int width, int resid) {
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),
				resid);
		if (bitmap == null)
			return;
		bitmap = Util.createRepeater(width, bitmap);
		Drawable drawable = new BitmapDrawable(bitmap);
		v.setBackgroundDrawable(drawable);
	}

	public static void setAlphaAnimation(ImageView ivImage) {
		AnimationSet animationSet = new AnimationSet(true);
		// 创建AlphaAnimation对象,第一个参数为从透明度为0(完全透明)到1(不透明)渐变
		AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
		// 设置动画执行的时间
		alphaAnimation.setDuration(500);
		// 将ScaleAnimation对象添加到AnimationSet
		animationSet.addAnimation(alphaAnimation);
		// 将动画使用到imageview
		ivImage.startAnimation(animationSet);
	}

	// 获取文字宽度
	public static int getTextWidth(String str, int textSize) {
		int width = 0;
		if (str != null && str.length() != 0) {
			Paint paint = new Paint(1);
			paint.setTextSize(textSize);
			Rect rect = new Rect();
			paint.getTextBounds(str, 0, str.length(), rect);
			width = rect.width();
		}
		return width;
	}

	public static String getDateTime(long timestamp, String pattern) {
		DateFormat formatter = new SimpleDateFormat(pattern);
		TimeZone timezone = TimeZone.getTimeZone("GMT+8:00");
		formatter.setTimeZone(timezone);
		String stime = formatter.format(new Date(timestamp));
		return stime;
	}

	public static String getDurationTime(int time) {
		String s;
		time /= 1000;
		int minute = time / 60;
		int hour = minute / 60;
		int second = time % 60;
		minute %= 60;
		if (hour > 0)
			s = String.format("%02d:%02d:%02d", hour, minute, second);
		else
			s = String.format("%02d:%02d", minute, second);
		return s;
	}

	public static String getDurationTime2(int time) {
		String s;
		time /= 1000;
		int minute = time / 60;
		int hour = minute / 60;
		int second = time % 60;
		minute %= 60;
		if (hour > 0)
			s = String.format("%02d时%02d分%02d秒", hour, minute, second);
		else
			s = String.format("%02d分%02d秒", minute, second);
		return s;
	}

	public static String getVersion(Context context) {
		PackageManager manager = context.getPackageManager();
		String versionName = "1.0";
		try {
			PackageInfo info = manager.getPackageInfo(context.getPackageName(),
					0);

			versionName = info.versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
		}
		return versionName;
	}


	public static void saveFile(Context context, Bitmap bitmap, String dir,
			String fileName) {
		try {
			File dirFile = new File(getCachePath(context) + dir);
			if (!dirFile.exists()) {
				dirFile.mkdir();
			}
			File myCaptureFile = new File(getCachePath(context) + dir
					+ fileName);
			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream(myCaptureFile));
			bitmap.compress(Bitmap.CompressFormat.PNG, 50, bos);
			bos.flush();
			bos.close();
		} catch (IOException e) {
		}
	}

	public static void delFile(String fileName) {
		try {
			File file = new File(fileName);
			if (file != null) {
				file.delete();
			}
		} catch (Exception e) {
		}
	}

	public static Bitmap getShareImage(Bitmap bitmap) {
		if (bitmap != null) {
			if (bitmap.getHeight() * bitmap.getRowBytes() >= 32000) {
				float ratio = (float) bitmap.getHeight()
						/ (float) bitmap.getWidth();
				int width = 90;
				int height = (int) (90 * ratio);
				if (ratio <= 1.0f) {
					height = 90;
					width = (int) (90 / ratio);
				}
				Bitmap bm = Util.resizeImage(bitmap, width, height);
				return bm;
			} else {
				return bitmap;
			}
		}
		return null;
	}

	private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	};
	private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};

	/**
	 * 以友好的方式显示时间
	 * 
	 * @param sdate
	 * @return
	 */
	public static String friendly_time(String sdate) {
		Date time = toDate(sdate);
		if (time == null) {
			return "刚刚";
		}
		String ftime = "";
		Calendar cal = Calendar.getInstance();

		// 判断是否是同一天
		String curDate = dateFormater2.get().format(cal.getTime());
		String paramDate = dateFormater2.get().format(time);
		if (curDate.equals(paramDate)) {
			int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
			if (hour == 0) {
				ftime = Math.max(
						(cal.getTimeInMillis() - time.getTime()) / 60000, 1)
						+ "分钟前";
			} else if (hour > 0) {
				ftime = hour + "小时前";
			} else {
				ftime = "刚刚";
			}
			if (ftime.length() > 0 && ftime.contains("-")) {
				ftime = ftime.substring(1);
			}
			return ftime;
		}

		long lt = time.getTime() / 86400000;
		long ct = cal.getTimeInMillis() / 86400000;
		int days = (int) (ct - lt);
		if (days == 0) {
			int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
			if (hour == 0)
				ftime = Math.max(
						(cal.getTimeInMillis() - time.getTime()) / 60000, 1)
						+ "分钟前";
			else
				ftime = hour + "小时前";
		} else if (days == 1) {
			ftime = "昨天";
		} else if (days == 2) {
			ftime = "前天";
		} else if (days > 2 && days <= 30) {
			ftime = days + "天前";
		} else if (days > 30) {
//			ftime = dateFormater2.get().format(time);
			ftime = days + "天前";
		} else {
			ftime = "刚刚";
		}
		if (ftime.length() > 0 && ftime.contains("-")) {
			ftime = ftime.substring(1);
		}
		return ftime;
	}

	/**
	 * 将字符串转位日期类型
	 * 
	 * @param sdate
	 * @return
	 */
	public static Date toDate(String sdate) {
		try {
			return dateFormater.get().parse(sdate);
		} catch (ParseException e) {
			return null;
		}
	}


	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 将px值转换为sp值，保证文字大小不变
	 * 
	 * @param pxValue
	 * @param fontScale
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int px2sp(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 将sp值转换为px值，保证文字大小不变
	 * 
	 * @param spValue
	 * @param fontScale
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int sp2px(Context context, float spValue) {
		final float scale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * scale + 0.5f);
	}

	/**
	 * 获取ip6地址
	 * 
	 * @return
	 */
	public static String getLocalIp6Address() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException ex) {
		}
		return null;
	}

	/**
	 * 获取ip4地址
	 * 
	 * @return
	 */
	public static String getLocalIp4Address() {

		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()
							&& InetAddressUtils.isIPv4Address(inetAddress
									.getHostAddress())) {

						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException e) {
			// TODO: handle exception
		}

		return null;
	}

	/**
	 * DecimalFormat转换最简便
	 */
	public static String format2doc(Double f) {
		DecimalFormat df = new DecimalFormat("#.00");
		if (f < 1 && f >= 0) {
			return "0" + df.format(f);
		}
		return df.format(f);
	}
	

	/**
	 * DecimalFormat转换最简便
	 */
	public static String format2doc(Float f) {
		DecimalFormat df = new DecimalFormat("#.00");
		if (f < 1) {
			return "0" + df.format(f);
		}
		return df.format(f);
	}

	public static String getSystemProperty(String propName) {
		String line;
		BufferedReader input = null;
		try {
			Process p = Runtime.getRuntime().exec("getprop " + propName);
			input = new BufferedReader(
					new InputStreamReader(p.getInputStream()), 1024);
			line = input.readLine();
			input.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return line;
	}
	
	/**
	 * 删除第一个小数点后面的所有点
	 * @param version
	 * @return
	 */
	public static String formatVersionName(String version){
		int count = 0 ;
		String[] array = version.split("");
		String newVersion = "";
		for (int i = 0; i < array.length; i++) {
			if(array[i].equals(".")){
				if(count < 1){
					newVersion += array[i];
				}
				count++;
			}else{
				newVersion += array[i];
			}
		}
		return newVersion;
	}
	
	/**
	 * 请求熊胜华股票接口 参数按字母排序
	 * @param target
	 * @return
	 */
	public static ArrayList<String> mySort(String target) {
		ArrayList<String> list = new ArrayList<String>();
		for (String temp : target.split("_")) {
			list.add(temp);
		}
		Collections.sort(list);
		return list;
	}
	
	/**
	 * 熊胜华股票参数sha1加密
	 * @param decript
	 * @return
	 */
	public static String SHA1(String decript) {
		decript = decript.toLowerCase();
        try {
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
 
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
	
	/**
	 * 
	 * @return
	 */
	@SuppressLint("SimpleDateFormat") public static boolean stockTime(){
		DateFormat dfDay = new SimpleDateFormat("yyyy-MM-dd");
		String day = dfDay.format(new Date()); 
		Calendar cal = Calendar.getInstance();
		Date bdate;
		try {
			bdate = dfDay.parse(day);
			cal.setTime(bdate);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY||cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
			return false;
		}else{
			DateFormat dfTime = new SimpleDateFormat("HHmm");
			int time = Integer.parseInt(dfTime.format(new Date()));
			if(time > 900 && time < 1600){
				return true;
			}else{
				return false;
			}
		}
	}
}
