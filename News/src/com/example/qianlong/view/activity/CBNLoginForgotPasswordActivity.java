package com.example.qianlong.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.qianlong.R;
import com.example.qianlong.base.BaseActivity;
import com.example.qianlong.modle.LoginModle.OnForgotPassWordListener;
import com.example.qianlong.modle.modleimpl.LoginModleImpl;

public class CBNLoginForgotPasswordActivity extends BaseActivity implements
		OnForgotPassWordListener {

	private EditText editText_Forgot_password_phonenumber;
	private EditText editText_Forgot_password_verification_code;
	private Button button_Forgot_password_verification_code_submit;
	private Button button_Forgot_password_submit;
	private LoginModleImpl loginModleImpl;
	private String phone_number = "";
	private String verification_code = "";
	private String user_id = "";

	@Override
	protected void initView() {
		setContentView(R.layout.cbn_login_forgot_password);
		initTitleBar();
		editText_Forgot_password_phonenumber = (EditText) findViewById(R.id.edit_Forgot_password_phonenumber);
		editText_Forgot_password_verification_code = (EditText) findViewById(R.id.edit_Forgot_password_verification_code);
		button_Forgot_password_submit = (Button) findViewById(R.id.button_Forgot_password_submit);
		button_Forgot_password_verification_code_submit = (Button) findViewById(R.id.button_Forgot_password_verification_code_submit);
		button_Forgot_password_submit.setOnClickListener(this);
		button_Forgot_password_verification_code_submit
				.setOnClickListener(this);

	}

	@Override
	protected void initData() {
		loginModleImpl = new LoginModleImpl(ct);
	}

	@Override
	protected void processClick(View v) {

		switch (v.getId()) {
		case R.id.button_Forgot_password_submit:
			phone_number = editText_Forgot_password_phonenumber.getText()
					.toString();
			verification_code = editText_Forgot_password_verification_code
					.getText().toString();
			Intent intent = new Intent();
			intent.putExtra("user_id", user_id);
			intent.putExtra("verification_code", verification_code);
			intent.setClass(CBNLoginForgotPasswordActivity.this,
					CBNLoginForgotPasswordSubmitActivity.class);
			startActivity(intent);
			break;
		case R.id.button_Forgot_password_verification_code_submit:
			phone_number = editText_Forgot_password_phonenumber.getText()
					.toString();
			loginModleImpl.userForgotPassWord(phone_number, this);
			break;

		default:
			break;
		}
	}

	@Override
	public void onForgotPassWordSuccess(String id) {
		user_id = id;

	}

	@Override
	public void onForgotPassWordError() {
		// TODO Auto-generated method stub

	}

}
