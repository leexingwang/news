package com.example.qianlong.view.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.qianlong.R;
import com.example.qianlong.application.AppManager;
import com.example.qianlong.base.BaseActivity;
import com.example.qianlong.constants.LoginConstants;
import com.example.qianlong.modle.LoginModle.OnLoginListener;
import com.example.qianlong.modle.LoginModle.OnLoginTokenValidListener;
import com.example.qianlong.modle.LoginModle.OnRegistConfirmListener;
import com.example.qianlong.modle.LoginModle.OnRegistListener;
import com.example.qianlong.modle.modleimpl.LoginModleImpl;
import com.example.qianlong.utils.StringUtils;

public class CBNLoginRegistSubmitActivity extends BaseActivity implements
		OnRegistConfirmListener, OnLoginListener, OnLoginTokenValidListener,
		OnRegistListener {

	private String user_id = "";
	private String user_name = "";
	private String phone_number = "";
	private String plain_password = "";
	private EditText editText_Regist_identifying_code;
	private TextView textView_Regist_code_submit;
	private TextView textView_Regist_code_submit_angin;
	private LoginModleImpl loginModleImpl;
	private String identifying_code;
	private Handler handler;
	private final int RegistSubmit_SUCCESS = 1;
	private final int RegistSubmit_ERROR = 2;
	private final int Identifying_Code_SUCCESS = 3;
	private final int Identifying_Code_ERROR = 4;
	private final int Login_SUCCESS = 5;
	private final int Login_ERROR = 6;

	@Override
	protected void initView() {
		setContentView(R.layout.cbn_login_regist_02);
		initTitleBar();
		editText_Regist_identifying_code = (EditText) findViewById(R.id.edit_Regist_regist_identifying_code);
		textView_Regist_code_submit = (TextView) findViewById(R.id.textview_regist_code_submit);
		textView_Regist_code_submit_angin = (TextView) findViewById(R.id.textview_regist_code_submit_angin);
		textView_Regist_code_submit_angin.setOnClickListener(this);
		textView_Regist_code_submit.setOnClickListener(this);
	}

	@Override
	protected void initData() {
		Intent intent = getIntent();
		user_id = (String) intent.getCharSequenceExtra(LoginConstants.USER_ID);
		user_name = (String) intent
				.getCharSequenceExtra(LoginConstants.USER_NAME);
		phone_number = (String) intent
				.getCharSequenceExtra(LoginConstants.PHONE_NUMBER);
		plain_password = (String) intent
				.getCharSequenceExtra(LoginConstants.PASSWORD);
		loginModleImpl = new LoginModleImpl(this);
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				switch (msg.what) {
				case RegistSubmit_SUCCESS:

					break;
				case RegistSubmit_ERROR:
					showToast(getResources().getString(
							R.string.cbn_verification_code_error));
					break;
				case Identifying_Code_SUCCESS:
					showToast(getResources().getString(
							R.string.cbn_verification_code_send_success));
					break;
				case Identifying_Code_ERROR:
					showToast(getResources().getString(
							R.string.cbn_verification_code_send_error));
					break;
				case Login_SUCCESS:
					AppManager.getAppManager().finishActivity();
					AppManager.getAppManager().finishActivity(
							CBNLoginRegistActivity.class);
					AppManager.getAppManager().finishActivity(
							CBNLoginActivity.class);
					break;
				case Login_ERROR:

					break;
				default:
					break;
				}
				super.handleMessage(msg);
			}
		};

	}

	@Override
	protected void processClick(View v) {
		switch (v.getId()) {
		case R.id.textview_regist_code_submit:
			identifying_code = editText_Regist_identifying_code.getText()
					.toString();
			if (StringUtils.isBlank(identifying_code)) {
				showToast(getResources().getString(
						R.string.cbn_verification_code_isblank));
				return;
			}
			loginModleImpl.userRegistConfirm(user_id, identifying_code, this);
			break;
		case R.id.textview_regist_code_submit_angin:
			loginModleImpl.userRegist(phone_number, user_name, plain_password,
					this);
			break;

		default:
			break;
		}
	}

	@Override
	public void onRegistConfirmSuccess() {
		Message message = Message.obtain();
		message.what = RegistSubmit_SUCCESS;
		handler.sendMessage(message);
		loginModleImpl.userLogin(phone_number, plain_password, this);
	}

	@Override
	public void onRegistConfirmError(String errorInfo) {
		Message message = Message.obtain();
		message.what = RegistSubmit_ERROR;
		handler.sendMessage(message);
	}

	@Override
	public void onLoginSuccess(String accessToken) {
		loginModleImpl.userLoginAccessTokenValid(accessToken, this);
	}

	@Override
	public void onLoginError() {

	}

	@Override
	public void onLoginTokenValidSuccess() {
		Message message = Message.obtain();
		message.what = Login_SUCCESS;
		handler.sendMessage(message);
	}

	@Override
	public void onLoginTokenValidError() {
		Message message = Message.obtain();
		message.what = Login_ERROR;
		handler.sendMessage(message);
	}

	@Override
	public void onRegistSuccess(String id) {
		this.user_id = id;
		Message message = Message.obtain();
		message.what = Identifying_Code_SUCCESS;
		handler.sendMessage(message);

	}

	@Override
	public void onRegistError(String errorInfo) {
		Message message = Message.obtain();
		message.what = Identifying_Code_ERROR;
		handler.sendMessage(message);
	}

	@Override
	protected void finishChild() {
		// TODO Auto-generated method stub
		
	}

}