package com.example.qianlong.view.activity;

import com.example.qianlong.R;
import com.example.qianlong.modle.LoginModle;
import com.example.qianlong.modle.LoginModle.OnRegistListener;
import com.example.qianlong.modle.modleimpl.LoginModleImpl;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class CBNLoginActivity extends Activity implements OnClickListener,
		OnRegistListener {

	private EditText editTextUsername;
	private EditText editTextPassword;
	private TextView textViewLogin;
	private TextView textViewForget;
	private TextView textViewRegist;
	private ImageView imageViewByQQ;
	private ImageView imageViewBySina;
	private String userName;
	private String password;
	private LoginModleImpl loginModleImpl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cbn_login);
		initView();
		initData();
	}

	private void initData() {
		userName = editTextUsername.getText().toString();
		password = editTextPassword.getText().toString();
		loginModleImpl = new LoginModleImpl();
		loginModleImpl.userRegist("", "1314815112", "lixingwang123456", "123456",
				"", this);

	}

	private void initView() {
		editTextPassword = (EditText) findViewById(R.id.editPassword);
		editTextUsername = (EditText) findViewById(R.id.editUserName);
		textViewLogin = (TextView) findViewById(R.id.textview_login);
		textViewForget = (TextView) findViewById(R.id.textview_forget);
		textViewRegist = (TextView) findViewById(R.id.textview_regist);
		imageViewByQQ = (ImageView) findViewById(R.id.imageView_login_by_qq);
		imageViewBySina = (ImageView) findViewById(R.id.imageView_login_by_sina);
		textViewLogin.setOnClickListener(this);
		textViewForget.setOnClickListener(this);
		textViewRegist.setOnClickListener(this);
		imageViewByQQ.setOnClickListener(this);
		imageViewBySina.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.textview_login:

			break;
		case R.id.textview_forget:

			break;
		case R.id.textview_regist:

			break;
		case R.id.imageView_login_by_qq:

			break;
		case R.id.imageView_login_by_sina:

			break;
		default:
			break;
		}

	}

	@Override
	public void onRegistSuccess() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRegistError() {
		// TODO Auto-generated method stub

	}

}
