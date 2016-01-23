package com.example.playpic;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.playpic.netbase.NetworkConstant;
import com.example.playpic.netbase.StockGetInfo;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.TextUtils.TruncateAt;
import android.util.Log;
import android.widget.TextView;

@SuppressLint("DefaultLocale")
public class MainActivity extends Activity {

	String str1 = "上证指数&3671.12&0.00%&深证指数&12371.50&0.80%&创业板指数&1234.56&-1.23%";
	String str2 = "";

	TextView tv;
	TextView tv1;
	ScrollTextView marqueeTextView;

	final Handler handler = new Handler();
	Runnable runnable = new Runnable() {
		@Override
		public void run() {
			updateStockinfo();
			handler.postDelayed(this, (long) (mTextWidth));// 50是延时时长
		}
	};
	public float mTextWidth;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		String buffer = getHTML(str1);
		tv = (TextView) findViewById(R.id.tv_paomadeng);
		tv1 = (TextView) findViewById(R.id.textView1);
		marqueeTextView = (ScrollTextView) findViewById(R.id.textView2);
		marqueeTextView.setText(Html.fromHtml(buffer.toString()));
		marqueeTextView.setTextSize(20);
		marqueeTextView.startScroll();
		tv.setTextSize(20);
		tv1.setTextSize(20);
		tv.setText(Html.fromHtml(buffer.toString()));
		tv1.setText(Html.fromHtml(buffer.toString()));
		tv.requestFocus();
		updateStockinfo();
		mTextWidth = tv.getPaint().measureText(buffer);
		setMarqueeSpeed(tv, 500, false);
		handler.postDelayed(runnable, (long) (mTextWidth * 5));// 打开定时器，执行操作
		marqueeTextView.setRndDuration((int) (mTextWidth * 5));

	}

	protected void setMarqueeSpeed(TextView tv, float speed,
			boolean speedIsMultiplier) {

		try {
			Field f = tv.getClass().getDeclaredField("mMarquee");
			f.setAccessible(true);

			Object marquee = f.get(tv);
			if (marquee != null) {

				String scrollSpeedFieldName = "mScrollUnit";
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
					scrollSpeedFieldName = "mPixelsPerSecond";

				Field mf = marquee.getClass().getDeclaredField(
						scrollSpeedFieldName);
				mf.setAccessible(true);

				float newSpeed = speed;
				if (speedIsMultiplier)
					newSpeed = mf.getFloat(marquee) * speed;

				mf.setFloat(marquee, newSpeed);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateStockinfo() {
		List<StockGetInfo> stockGetInfos = new ArrayList<StockGetInfo>();
		stockGetInfos.add(new StockGetInfo("getQuoteSnap",
				NetworkConstant.Shanghai_Composite_IndexStock, "1"));
		stockGetInfos.add(new StockGetInfo("getQuoteSnap",
				NetworkConstant.Shenzhen_Stock_Index, "2"));
		stockGetInfos.add(new StockGetInfo("getQuoteSnap",
				NetworkConstant.GEM_Index_Stock, "2"));
		stockGetInfos.add(new StockGetInfo("getQuoteSnap", "300379", "2"));
		getStockInfo(stockGetInfos);
	}

	private String getHTML(String mk) {
		String[] strings = mk.split("&");
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < strings.length; i++) {
			if ((i + 1) % 3 == 0) {
				String p = strings[i];
				float f = Float.parseFloat(p.subSequence(0, p.length() - 1)
						.toString());
				if (f > 0.00) {
					buffer.append("<font color='red'>"
							+ strings[i]
							+ "</font>"
							+ (i == strings.length - 1 ? ""
									: " &nbsp &nbsp &nbsp &nbsp "));
					continue;
				} else if (f < 0.00) {
					buffer.append("<font color='green'>"
							+ strings[i]
							+ "</font>"
							+ (i == strings.length - 1 ? ""
									: " &nbsp &nbsp &nbsp &nbsp "));
					continue;
				} else {
					buffer.append("<font color='black'>"
							+ strings[i]
							+ "</font>"
							+ (i == strings.length - 1 ? ""
									: " &nbsp &nbsp &nbsp &nbsp "));
					continue;
				}

			}
			buffer.append(strings[i] + "      ");
		}
		return buffer.toString();
	}

	@SuppressLint("DefaultLocale")
	public void getStockInfo(List<StockGetInfo> getInfos) {
		str2 = "";
		HttpUtils httpUtils = new HttpUtils();
		String actions = "getQuoteSnap";
		String stockcodes = "";
		String markets = "";
		String encrypt = "";
		for (int j = 0; j < getInfos.size(); j++) {
			markets += getInfos.get(j).getMarket()
					+ (j != getInfos.size() - 1 ? "," : "");
			stockcodes += getInfos.get(j).getStockcode()
					+ (j != getInfos.size() - 1 ? "," : "");
		}
		encrypt = actions + "_" + NetworkConstant.STOCK_HOME_URL_Column + "_"
				+ markets + "_" + stockcodes;
		encrypt = encrypt + "_" + NetworkConstant.STOCKKEY;
		encrypt = encrypt.toLowerCase();
		String url = NetworkConstant.STOCK_HOME_URL + "getQuoteSnap&stockcode="
				+ stockcodes + "&market=" + markets + "&column="
				+ NetworkConstant.STOCK_HOME_URL_Column + "&source="
				+ "android" + "&encrypt=" + Util.SHA1(encrypt);
		httpUtils.send(HttpRequest.HttpMethod.POST, url,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
					}

					@Override
					public void onSuccess(
							ResponseInfo<String> successResponseInfo) {
						Log.i("onSuccess", successResponseInfo.result);
						JsonUtils jsonUtils = new JsonUtils();
						if (!jsonUtils.validate(successResponseInfo.result)) {
							return;
						}
						;
						List<StockReport> stockReports = null;
						try {
							stockReports = GsonTools.fromJsonArray(
									successResponseInfo.result,
									StockReport.class);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						for (int i = 0; i < stockReports.size(); i++) {
							try {
								float upDownPer = (float) (Math
										.round(stockReports.get(i)
												.getUpDownPer() * 10000)) / 100;
								float close = (float) (Math.round(stockReports
										.get(i).getClose() * 100)) / 100;
								str2 += stockReports.get(i).getSecuname()
										+ "&"
										+ close
										+ "&"
										+ upDownPer
										+ "%"
										+ (i == stockReports.size() - 1 ? ""
												: "&");
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						try {
							String html = getHTML(str2);
							tv.setText(Html.fromHtml(html));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				});

	}

	/**
	 * 请求熊胜华股票接口 参数按字母排序
	 * 
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
}