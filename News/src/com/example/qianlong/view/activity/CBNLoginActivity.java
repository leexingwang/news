package com.example.qianlong.view.activity;

import com.example.qianlong.R;
import com.example.qianlong.base.BaseActivity;
import com.example.qianlong.constants.LoginConstants;
import com.example.qianlong.modle.LoginModle.OnLoginListener;
import com.example.qianlong.modle.LoginModle.OnLoginTokenValidListener;
import com.example.qianlong.modle.modleimpl.LoginModleImpl;
import com.example.qianlong.utils.SharePrefUtil;
import com.example.qianlong.utils.StringUtils;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class CBNLoginActivity extends BaseActivity implements OnLoginListener,
		OnLoginTokenValidListener {

	private EditText editTextUsername;
	private EditText editTextPassword;
	private TextView textViewLogin;
	private TextView textViewForget;
	private TextView textViewRegist;
	private ImageView imageViewByQQ;
	private ImageView imageViewBySina;
	private String userName;
	private String passWord;
	private LoginModleImpl loginModleImpl;

	private Handler handler;

	private boolean relogin = false;

	@SuppressLint("HandlerLeak")
	@Override
	protected void initData() {
		loginModleImpl = new LoginModleImpl(this);
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				if (msg.what == LoginConstants.LOGIN_SUCCESS) {
					SharePrefUtil.saveString(ct, SharePrefUtil.KEY.USER_NAME,
							userName);
					SharePrefUtil.saveString(ct,
							SharePrefUtil.KEY.USER_PASSWORD, passWord);
					finish();
					showToast("登陆成功！");
				} else {
					showToast("用户名或密码错误！");
				}
				super.handleMessage(msg);
			}
		};

	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		relogin = intent.getBooleanExtra("relogin", false);
		if (relogin) {
			editTextPassword.setText("");
			SharePrefUtil.getString(this, SharePrefUtil.KEY.USER_PASSWORD, "");
		}
		super.onNewIntent(intent);
	}

	@Override
	protected void initView() {
		setContentView(R.layout.cbn_login);
		initTitleBar();
		editTextUsername = (EditText) findViewById(R.id.editUserName);
		editTextPassword = (EditText) findViewById(R.id.editPassword);
		editTextUsername.setText(SharePrefUtil.getString(ct,
				SharePrefUtil.KEY.USER_PHONENUMBER, ""));
		if (!relogin) {
			editTextPassword.setText(SharePrefUtil.getString(ct,
					SharePrefUtil.KEY.USER_PASSWORD, ""));
		}
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
	public void onLoginSuccess(String accessToken) {
		loginModleImpl.userLoginAccessTokenValid(accessToken, this);
	}

	@Override
	public void onLoginError() {
		Message message = Message.obtain();
		message.what = LoginConstants.LOGIN_ERROR;
		handler.sendMessage(message);
	}

	@Override
	protected void processClick(View v) {
		switch (v.getId()) {
		case R.id.textview_login:
			userName = editTextUsername.getText().toString();
			passWord = editTextPassword.getText().toString();
			if (StringUtils.isBlank(userName)) {
				showToast("用户名或电话号码不能为空");
				return;
			}
			if (StringUtils.isBlank(passWord)) {
				showToast("密码不能为空");
				return;
			}
			loginModleImpl.userLogin(userName, passWord, this);
			break;
		case R.id.textview_forget:
			startActivity(new Intent(this, CBNLoginForgotPasswordActivity.class));
			break;
		case R.id.textview_regist:
			startActivity(new Intent(this, CBNLoginRegistActivity.class));
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
	public void onLoginTokenValidSuccess() {
		Message message = Message.obtain();
		message.what = LoginConstants.LOGIN_SUCCESS;
		handler.sendMessage(message);
	}

	@Override
	public void onLoginTokenValidError() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void finishChild() {
		// TODO Auto-generated method stub

	}

}
