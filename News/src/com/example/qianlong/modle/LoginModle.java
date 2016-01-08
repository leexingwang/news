package com.example.qianlong.modle;

public interface LoginModle {

	// 用户注册
	public void userRegist(String phone_number, String username,
			String plain_password, OnRegistListener onRegistListener);

	// 注册码提交
	public void userRegistConfirm(String id, String confirmationToken,
			OnRegistListener onRegistListener);

	// 用户登录
	public void userLogin(String userName, String passWord,
			OnLoginListener onLoginListener);

	// 用户合法性Token检测
	public void userLoginAccessTokenValid(String accessToken,
			OnLoginTokenValidListener onLoginTokenValidListener);

	// 刷新token
	public void userLoginRefreshToken(String refreshToken,
			OnRefreshTokenListener onRefreshTokenListener);

	// 修改密码
	public void userFixPassWord(String id, String new_password,
			String old_password, OnFixPassWordListener onFixPassWordListener);

	// 忘记密码
	public void userForgotPassWord(String phoneNumber,
			OnForgotPassWordListener onForgotPassWordListener);

	// 忘记密码后重置密码
	public void userForgotResetPassWord(String id, String new_password,
			String validation_code,
			OnForgotResetPassWordListener onForgotResetPassWordListener);

	// 更改手机号
	public void userChangeContact(String id, String new_contact_info,
			String password, OnChangeContactListener onChangeContactListener);

	// 正式修改手机号
	public void userChangeContactValid(String id, String new_contact_info,
			String validation_code,
			OnChangeContactValidListener onChangeContactValidListener);

	public interface OnRegistListener {
		public void onRegistSuccess();

		public void onRegistError();
	}

	public interface OnLoginListener {
		public void onLoginSuccess();

		public void onLoginError();
	}

	public interface OnLoginTokenValidListener {
		public void onLoginTokenValidSuccess();

		public void onLoginTokenValidError();
	}

	public interface OnRefreshTokenListener {
		public void onRefreshTokenSuccess();

		public void onRefreshTokenError();
	}

	public interface OnFixPassWordListener {
		public void onFixPassWordSuccess();

		public void onFixPassWordError();
	}

	public interface OnForgotPassWordListener {
		public void onForgotPassWordSuccess();

		public void onForgotPassWordError();
	}

	public interface OnForgotResetPassWordListener {
		public void onForgotResetPassWordSuccess();

		public void onForgotResetPassWordError();
	}

	public interface OnChangeContactListener {
		public void onChangeContactSuccess();

		public void onChangeContactError();
	}

	public interface OnChangeContactValidListener {
		public void onChangeContactValidSuccess();

		public void onChangeContactValidError();
	}
}
