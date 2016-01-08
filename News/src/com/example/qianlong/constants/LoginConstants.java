package com.example.qianlong.constants;

public class LoginConstants {

	public final static String OAUTH2_URL = "http://test.oauth2.yicai.com/";

	public final static String OAUTH2_URL_REGIST = "http://test.oauth2.yicai.com/users";

	public final static String OAUTH2_URL_REGIST_CONFIRM = "http://test.oauth2.yicai.com/users/";

	public final static String CONFIRM = "/confirmation-token/";

	/**
	 * 错误信息原文 错误信息内容 bst.phone_and_email.missing 电话和邮箱都为空
	 * fos_user.email.already_used 邮箱已经存在 fos_user.username.already_used 用户名已经存在
	 * bst.phonenumber.already_used 电话已经存在 fos_user.email.short 邮件地址太短
	 * fos_user.email.long 邮件地址太长 fos_user.username.short 用户名太短(不能少于2个字符)
	 * fos_user.username.long 用户名太长(不能大于255个字符) bst.phonenumber.short
	 * 电话太短(不能少于3位) bst.phonenumber.long 电话太长(不能大于20位) bst.phonenumber.space
	 * 电话里有空格 bst.phonenumber.format 电话格式有误 fos_user.password.blank 密码里面有空格
	 * fos_user.password.short 密码太短(不能小于3个字符) fos_user.oldsystem.already_used
	 * 原始系统已经存在该用户
	 */
	public static String REGIST_ERROE_USERNAME_ALREADY = "fos_user.username.already_used";
	public static String REGIST_ERROE_PHONE_ALREADY = "bst.phonenumber.already_used";
	public static String REGIST_ERROE_OLD_ALREADY = "fos_user.oldsystem.already_used";

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

}
