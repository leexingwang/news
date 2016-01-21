package com.example.qianlong.constants;

public class LoginConstants {

	public final static String OAUTH2_URL = "http://test.oauth2.yicai.com/";

	public final static String OAUTH2_URL_REGIST = "http://test.oauth2.yicai.com/users";

	public final static String OAUTH2_URL_REGIST_CONFIRM = "http://test.oauth2.yicai.com/users/";

	public final static String CONFIRM = "/confirmation-token/";

	/**
	 * 注册信息返回
	 */
	public static String REGIST_ERROE_USERNAME_ALREADY = "fos_user.username.already_used";
	public static String REGIST_ERROE_OLD_USERNAME_ALREADY = "fos_user.oldsystem.username_already_used";
	public static String REGIST_ERROE_PHONE_ALREADY = "bst.phonenumber.already_used";
	public static String REGIST_ERROE_OLD_PHONE_ALREADY = "fos_user.oldsystem.mobile_already_used";

	/**
	 * login
	 */
	public final static String CLIENT_ID = "1_3w04orxypacksowww0sc84s88k8gcw8o4co40c4sk0cwgwsoco";
	public final static String CLIENT_SECRET = "38egiuwv6beow0c0kwsc44w8sck4wo4wocskc48ooosssw8oc8";
	public final static String OAUTH2_URL_USER_LOGIN = "yicailogin?client_id="
			+ CLIENT_ID + "&client_secret=" + CLIENT_SECRET
			+ "&grant_type=password&";
	/**
	 * 合法性
	 */
	public final static String ACCESS_TOKEN_VALID = "oauth/access_token_valid/";

	/**
	 * 刷新token
	 */

	public final static String REFRESH_TOKEN = "yicailogin?client_id="
			+ CLIENT_ID + "&client_secret=" + CLIENT_SECRET
			+ "&grant_type=refresh_token&refresh_token=";

	public final static String USER_ID = "id";
	public final static String PHONE_NUMBER = "phone_number";
	public final static String USER_NAME = "username";
	public final static String PASSWORD = "plain_password";

	public final static int LOGIN_SUCCESS = 1;

	public final static int LOGIN_ERROR = 2;

	public final static int LOGIN_REGIST_SUCCESS = 3;

	public final static int LOGIN_REGIST_ERROR = 4;

}
