package com.example.playpic;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class MainActivity extends Activity {

	String str = "��ָ֤��  3671.12  2.26%   ��ָ֤��  12371.50  0.80%    ��ҵ��ָ��  1234.56  1.23%";

	String str11 = "�ٽ������ʦȫ�淢չ��������У�����ʦΪʵ���л�����ΰ���˵��й��ι�����";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		String str = "<font color='red'>�ٽ������ʦȫ�淢չ��������У�����ʦΪʵ���л�����ΰ���˵��й��ι�����</font>"
				+ "<font color= 'green'>�ٽ������ʦȫ�淢չ��������У�����ʦΪʵ���л�����ΰ���˵��й��ι�����</font>";
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