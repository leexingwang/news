package com.example.qianlong.view.activity;

import com.example.qianlong.R;
import com.example.qianlong.modle.LoginModle.OnChangeContactListener;
import com.example.qianlong.modle.LoginModle.OnChangeContactValidListener;
import com.example.qianlong.modle.LoginModle.OnFixPassWordListener;
import com.example.qianlong.modle.LoginModle.OnForgotPassWordListener;
import com.example.qianlong.modle.LoginModle.OnForgotResetPassWordListener;
import com.example.qianlong.modle.LoginModle.OnLoginListener;
import com.example.qianlong.modle.LoginModle.OnLoginTokenValidListener;
import com.example.qianlong.modle.LoginModle.OnRefreshTokenListener;
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
		OnRegistListener, OnLoginListener, OnLoginTokenValidListener,
		OnRefreshTokenListener, OnFixPassWordListener,
		OnForgotPassWordListener, OnForgotResetPassWordListener,
		OnChangeContactListener, OnChangeContactValidListener {

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
		// loginModleImpl.userRegist("13148151103", "lixing", "123456", this);
		// loginModleImpl.userRegistConfirm("40000030", "123456", this);
		//loginModleImpl.userLogin("13148151103", "123456", this);
		// loginModleImpl
		// .userLoginAccessTokenValid(
		// "NjJlNDNlOTIyMTFjZjRmZWU1YmZlNzJmNWY0NmU2MWM1NmIxNmU0MDA1ZGE1ZjFkMjU0N2ZiZmUwMWIxMGEwOQ",
		// this);

		// loginModleImpl
		// .userLoginRefreshToken(
		// "MTUyZjM2NmI5YzgxNmE2ZGFmYjNkNTkzYzAyNjFhMGExNWJiMWVhZmUwZmY2NTAwNzY3ZjU5NDRhMGNjYmViNw",
		// this);
		// loginModleImpl.userFixPassWord("40000004", "1234567", "1234567",
		// this);
		// loginModleImpl.userForgotPassWord("13148151103", this);
		// loginModleImpl.userForgotResetPassWord("40000043", "123456",
		// "489403",
		// this);
		// loginModleImpl.userChangeContact("40000004", "18121453675", "123456",
		// this);
		// loginModleImpl.userChangeContactValid("40000004", "18121453675",
		// "698852",
		// this);

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

	@Override
	public void onLoginSuccess() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLoginError() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLoginTokenValidSuccess() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLoginTokenValidError() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onForgotResetPassWordSuccess() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onForgotResetPassWordError() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onForgotPassWordSuccess() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onForgotPassWordError() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFixPassWordSuccess() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFixPassWordError() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRefreshTokenSuccess() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRefreshTokenError() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onChangeContactSuccess() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onChangeContactError() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onChangeContactValidSuccess() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onChangeContactValidError() {
		// TODO Auto-generated method stub

	}

}
