package com.example.qianlong.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qianlong.R;
import com.example.qianlong.base.BaseActivity;
import com.example.qianlong.constants.LoginConstants;
import com.example.qianlong.modle.LoginModle.OnRegistListener;
import com.example.qianlong.modle.modleimpl.LoginModleImpl;
import com.example.qianlong.utils.StringUtils;

public class CBNLoginRegistActivity extends BaseActivity implements
		OnRegistListener {

	private EditText editText_Regist_phonenumber;
	private EditText editText_Regist_username;
	private EditText editText_Regist_password;
	private EditText editText_Regist_password_angin;
	private TextView textView_Regist_submit;
	private LoginModleImpl loginModleImpl;
	private String phone_number;
	private String username;
	private String plain_password;
	private String plain_password_angin;
	private Handler handler;

	@Override
	protected void initView() {
		setContentView(R.layout.cbn_login_regist_01);
		editText_Regist_phonenumber = (EditText) findViewById(R.id.edit_Regist_phonenumber);
		editText_Regist_username = (EditText) findViewById(R.id.edit_Regist_username);
		editText_Regist_password = (EditText) findViewById(R.id.edit_Regist_password);
		editText_Regist_password_angin = (EditText) findViewById(R.id.edit_Regist_password_angin);
		textView_Regist_submit = (TextView) findViewById(R.id.textview_Regist_submit);
		textView_Regist_submit.setOnClickListener(this);
		initTitleBar();
	}

	@SuppressLint("HandlerLeak")
	@Override
	protected void initData() {
		loginModleImpl = new LoginModleImpl(this);
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == LoginConstants.LOGIN_REGIST_SUCCESS) {
					showToast(getResources().getString(
							R.string.cbn_verification_code_send_success));
					String id = (String) msg.obj;
					Intent intent = new Intent();
					intent.putExtra(LoginConstants.USER_ID, id);
					intent.putExtra(LoginConstants.PHONE_NUMBER, phone_number);
					intent.putExtra(LoginConstants.USER_NAME, username);
					intent.putExtra(LoginConstants.PASSWORD, plain_password);
					intent.setClass(CBNLoginRegistActivity.this,
							CBNLoginRegistSubmitActivity.class);
					startActivity(intent);
				} else {
					showToast((String) msg.obj);

				}
				super.handleMessage(msg);
			}
		};

	}

	@Override
	protected void processClick(View v) {
		switch (v.getId()) {
		case R.id.textview_Regist_submit:
			phone_number = editText_Regist_phonenumber.getText().toString();
			username = editText_Regist_username.getText().toString();
			plain_password = editText_Regist_password.getText().toString();
			plain_password_angin = editText_Regist_password_angin.getText()
					.toString();
			if (StringUtils.isBlank(phone_number)) {
				showToast(getResources().getString(
						R.string.cbn_phonenumber_isblank));
				return;
			}
			if (StringUtils.isBlank(username)) {
				showToast(getResources().getString(
						R.string.cbn_username_isblank));
				return;
			}
			if (StringUtils.isBlank(plain_password)) {
				showToast(getResources().getString(
						R.string.cbn_password_isblank));
				return;
			}
			if (StringUtils.isBlank(plain_password_angin)) {
				showToast(getResources().getString(
						R.string.cbn_password_isblank));
				return;
			}
			if (!StringUtils.isEquals(plain_password, plain_password_angin)) {
				showToast(getResources().getString(
						R.string.cbn_password_angin_diff));
				return;
			}
			if (!StringUtils.isPhoneNumberValid(phone_number)) {
				showToast(getResources().getString(
						R.string.cbn_phonenumber_error));
				return;
			}
			if (StringUtils.length(username.subSequence(0, username.length())) < 3) {
				showToast(getResources().getString(
						R.string.cbn_username_so_short));
				return;
			}
			loginModleImpl.userRegist(phone_number, username, plain_password,
					this);
			break;
		default:
			break;
		}

	}

	@Override
	public void onRegistSuccess(String id) {
		Message message = Message.obtain();
		message.what = LoginConstants.LOGIN_REGIST_SUCCESS;
		message.obj = id;
		handler.sendMessage(message);
	}

	@Override
	public void onRegistError(String errorInfo) {
		Message message = Message.obtain();
		message.what = LoginConstants.LOGIN_REGIST_ERROR;
		message.obj = errorInfo;
		handler.sendMessage(message);
	}

}
