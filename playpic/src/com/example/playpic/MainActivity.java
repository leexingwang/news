package com.example.playpic;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class MainActivity extends Activity {

	String str = "上证指数  3671.12  2.26%   深证指数  12371.50  0.80%    创业板指数  1234.56  1.23%";

	String str11 = "促进青年教师全面发展引导广大高校青年教师为实现中华民族伟大复兴的中国梦贡献力";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		String str = "<font color='red'>促进青年教师全面发展引导广大高校青年教师为实现中华民族伟大复兴的中国梦贡献力</font>"
				+ "<font color= 'green'>促进青年教师全面发展引导广大高校青年教师为实现中华民族伟大复兴的中国梦贡献力</font>";
		TextView tv = (TextView) findViewById(R.id.tv_paomadeng);
		tv.setTextSize(20);
		tv.setText(Html.fromHtml(str));
		tv.requestFocus();
	}

	void scroll2() {
		ScrollText v = new ScrollText(getApplicationContext());
		setContentView(v);
		v.setTxtContent(str11);
		v.initDisplayMetrics(getWindowManager());
	}

	void scroll3(String str) {
		
	}

}