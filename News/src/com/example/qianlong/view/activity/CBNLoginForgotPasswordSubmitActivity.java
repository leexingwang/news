package com.example.qianlong.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.qianlong.R;
import com.example.qianlong.base.BaseActivity;
import com.example.qianlong.modle.LoginModle.OnForgotResetPassWordListener;
import com.example.qianlong.modle.modleimpl.LoginModleImpl;

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

	}

	@Override
	protected void processClick(View v) {

		switch (v.getId()) {
		case R.id.button_new_password_submit:
			password = editText_New_passWord.getText().toString();
			password_angin = editText_New_passWord_angin.getText().toString();
			loginModleImpl.userForgotResetPassWord(user_id, password,
					verification_code, this);
			break;

		default:
			break;
		}
	}

	@Override
	public void onForgotResetPassWordSuccess() {

	}

	@Override
	public void onForgotResetPassWordError() {
		// TODO Auto-generated method stub

	}

}
