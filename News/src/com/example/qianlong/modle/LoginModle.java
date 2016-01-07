package com.example.qianlong.modle;

public interface LoginModle {

	public void userRegist(String email, String phone_number, String username,
			String plain_password, String source, OnRegistListener onRegistListener);
	
	public void userRegistConfirm(String id,String confirmationToken,OnRegistListener onRegistListener);

	public interface OnRegistListener {
		public void onRegistSuccess();

		public void onRegistError();
	}
}
