package com.example.qianlong.view.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.qianlong.R;
import com.example.qianlong.application.AppManager;
import com.example.qianlong.base.BaseActivity;
import com.example.qianlong.modle.LoginModle.OnForgotResetPassWordListener;
import com.example.qianlong.modle.modleimpl.LoginModleImpl;
import com.example.qianlong.utils.StringUtils;

public class CBNLoginForgotPasswordSubmitActivity extends BaseActivity
		implements OnForgotResetPassWordListener {

	private String user_id;
	private String verification_code;
	private String password;
	private String password_angin;
	private EditText editText_New_passWord;
	private EditText editText_New_passWord_angin;
	private Button button_new_password_submit;
	private LoginModleImpl loginModleImpl;
	private final int SUCCESS_ = 1;
	private final int ERROE_ = 2;
	private Handler handler;

	@Override
	protected void initView() {
		setContentView(R.layout.cbn_login_forgot_new_password);
		initTitleBar();
		editText_New_passWord = (EditText) findViewById(R.id.edit_New_passWord);
		editText_New_passWord_angin = (EditText) findViewById(R.id.edit_New_passWord_angin);
		button_new_password_submit = (Button) findViewById(R.id.button_new_password_submit);
		button_new_password_submit.setOnClickListener(this);

	}

	@Override
	protected void initData() {
		loginModleImpl = new LoginModleImpl(ct);
		Intent intent = getIntent();
		user_id = (String) intent.getCharSequenceExtra("user_id");
		verification_code = (String) intent
				.getCharSequenceExtra("verification_code");
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				if (msg.what == SUCCESS_) {
					showToast(getResources().getString(
							R.string.cbn_password_fixed_success));
					Intent intent = new Intent();
					intent.putExtra("relogin", true);
					intent.setClass(CBNLoginForgotPasswordSubmitActivity.this, CBNLoginActivity.class);
					startActivity(intent);
					AppManager.getAppManager().finishActivity(
							CBNLoginForgotPasswordActivity.class);
					AppManager.getAppManager().finishActivity();
				} else {
					showToast(getResources().getString(
							R.string.cbn_password_fixed_error));
				}
				super.handleMessage(msg);
			}
		};

	}

	@Override
	protected void processClick(View v) {

		switch (v.getId()) {
		case R.id.button_new_password_submit:
			password = editText_New_passWord.getText().toString();
			password_angin = editText_New_passWord_angin.getText().toString();
			if (StringUtils.isBlank(password)) {
				showToast(getResources().getString(
						R.string.cbn_password_isblank));
				return;
			}
			if (!StringUtils.isEquals(password, password_angin)) {
				showToast(getResources().getString(
						R.string.cbn_password_angin_diff));
				return;
			}
			if (StringUtils.length(password.subSequence(0, password.length())) < 3) {
				showToast(getResources().getString(
						R.string.cbn_password_so_short));
				return;
			}
			loginModleImpl.userForgotResetPassWord(user_id, password,
					verification_code, this);
			break;

		default:
			break;
		}
	}

	@Override
	public void onForgotResetPassWordSuccess() {
		Message message = Message.obtain();
		message.what = SUCCESS_;
		handler.sendMessage(message);
	}

	@Override
	public void onForgotResetPassWordError() {
		// TODO Auto-generated method stub
		Message message = Message.obtain();
		message.what = ERROE_;
		handler.sendMessage(message);
	}

	@Override
	protected void finishChild() {
		// TODO Auto-generated method stub
		
	}

}
