package com.example.qianlong.modle;

public interface LoginModle {

	// �û�ע��
	public void userRegist(String phone_number, String username,
			String plain_password, OnRegistListener onRegistListener);

	// ע�����ύ
	public void userRegistConfirm(String id, String confirmationToken,
			OnRegistConfirmListener onRegistConfirmListener);

	// �û���¼
	public void userLogin(String userName, String passWord,
			OnLoginListener onLoginListener);

	// �û��Ϸ���Token���
	public void userLoginAccessTokenValid(String accessToken,
			OnLoginTokenValidListener onLoginTokenValidListener);

	// ˢ��token
	public void userLoginRefreshToken(String refreshToken,
			OnRefreshTokenListener onRefreshTokenListener);

	// �޸�����
	public void userFixPassWord(String id, String new_password,
			String old_password, OnFixPassWordListener onFixPassWordListener);

	// ��������
	public void userForgotPassWord(String phoneNumber,
			OnForgotPassWordListener onForgotPassWordListener);

	// �����������������
	public void userForgotResetPassWord(String id, String new_password,
			String validation_code,
			OnForgotResetPassWordListener onForgotResetPassWordListener);

	// �����ֻ���
	public void userChangeContact(String id, String new_contact_info,
			String password, OnChangeContactListener onChangeContactListener);

	// ��ʽ�޸��ֻ���
	public void userChangeContactValid(String id, String new_contact_info,
			String validation_code,
			OnChangeContactValidListener onChangeContactValidListener);

	public interface OnRegistListener {
		public void onRegistSuccess(String id);

		public void onRegistError(String errorInfo);
	}

	public interface OnRegistConfirmListener {
		public void onRegistConfirmSuccess();

		public void onRegistConfirmError(String errorInfo);
	}

	public interface OnLoginListener {
		public void onLoginSuccess(String accessToken);

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
		public void onForgotPassWordSuccess(String id);

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
