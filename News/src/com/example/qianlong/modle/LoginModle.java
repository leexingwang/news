package com.example.qianlong.modle;

public interface LoginModle {
	/**
	 * { "email": "TEST@EXAMPLE.COM" , "phone_number": "1234567" , "username" :
	 * "USER_NAME", "plain_password" : "PLAIN_TEXT_PASSWORD", "source":"tlf" }
	 */

	public void userRegist(String email, String phone_number, String username,
			String plain_password, String source, OnRegistListener onRegistListener);

	public interface OnRegistListener {
		public void onRegistSuccess();

		public void onRegistError();
	}
}
