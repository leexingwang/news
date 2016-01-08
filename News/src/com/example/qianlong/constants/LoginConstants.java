package com.example.qianlong.constants;

public class LoginConstants {

	public final static String OAUTH2_URL = "http://test.oauth2.yicai.com/";

	public final static String OAUTH2_URL_REGIST = "http://test.oauth2.yicai.com/users";

	public final static String OAUTH2_URL_REGIST_CONFIRM = "http://test.oauth2.yicai.com/users/";

	public final static String CONFIRM = "/confirmation-token/";

	/**
	 * ������Ϣԭ�� ������Ϣ���� bst.phone_and_email.missing �绰�����䶼Ϊ��
	 * fos_user.email.already_used �����Ѿ����� fos_user.username.already_used �û����Ѿ�����
	 * bst.phonenumber.already_used �绰�Ѿ����� fos_user.email.short �ʼ���ַ̫��
	 * fos_user.email.long �ʼ���ַ̫�� fos_user.username.short �û���̫��(��������2���ַ�)
	 * fos_user.username.long �û���̫��(���ܴ���255���ַ�) bst.phonenumber.short
	 * �绰̫��(��������3λ) bst.phonenumber.long �绰̫��(���ܴ���20λ) bst.phonenumber.space
	 * �绰���пո� bst.phonenumber.format �绰��ʽ���� fos_user.password.blank ���������пո�
	 * fos_user.password.short ����̫��(����С��3���ַ�) fos_user.oldsystem.already_used
	 * ԭʼϵͳ�Ѿ����ڸ��û�
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
	 * �Ϸ���
	 */
	public final static String ACCESS_TOKEN_VALID = "oauth/access_token_valid/";

	/**
	 * ˢ��token
	 */

	public final static String REFRESH_TOKEN = "yicailogin?client_id="
			+ CLIENT_ID + "&client_secret=" + CLIENT_SECRET
			+ "&grant_type=refresh_token&refresh_token=";

}
