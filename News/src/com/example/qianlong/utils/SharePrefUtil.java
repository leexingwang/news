package com.example.qianlong.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharePreferences����������
 */
public class SharePrefUtil {
	@SuppressWarnings("unused")
	private static String tag = SharePrefUtil.class.getSimpleName();
	private final static String SP_NAME = "config";
	private static SharedPreferences sp;

	public interface KEY {

		String FUNCTION_ALL_JSON = "all_function_json";// ���е�Funcation Json
		String FUNCTION_SELECTED_ID = "selcted_function_ids";// ѡ�е�function ids

		String CATE_ALL_JSON = "all_cate_json";// ���е�����Ŀ¼ Json
		String CATE_SELECTED_JSON = "selcted_cate_json";// ѡ�е�����Ŀ¼ids
		String CATE_EXTEND_ID = "extend_cate_ids";// �Ƽ������� Ŀ¼ids

		String VOTE_SELECTED_ID = "selcted_vote_ids";// ѡ�е�function ids

		// settings_push

		String SETTINGS_PUSH_HONGGUAN = "settings_push_hongguan";
		String SETTINGS_PUSH_SHANGYE = "settings_push_shangye";
		String SETTINGS_PUSH_TOUZI = "settings_push_touzi";
		String SETTINGS_PUSH_YEJIAN = "settings_push_yejian";
		boolean SETTINGS_PUSH_DEFAULT = true;

		// 3G/4G״�������㲥����Ƶ
		String SETTINGS_3G_4G_GUANKAN = "settings_3G_4G_guankan";

		boolean SETTINGS_3G_4G_DEFAULT = true;

		// 7*24��Ϣ�Ƿ��ɫ״̬
		String IS_7_24_RED = "7_24_red";

		boolean IS_7_24_RED_DEFAULT = false;

		String USER_NAME = "user_name";
		String USER_PASSWORD = "user_password";
		String USER_PHONENUMBER = "user_phonenumber";
		String USER_ID = "user_id";
		String USER_EMAIL = "user_email";
	}

	/**
	 * ���沼��ֵ
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void saveBoolean(Context context, String key, boolean value) {
		if (sp == null)
			sp = context.getSharedPreferences(SP_NAME, 0);
		sp.edit().putBoolean(key, value).commit();
	}

	/**
	 * �����ַ���
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void saveString(Context context, String key, String value) {
		if (sp == null)
			sp = context.getSharedPreferences(SP_NAME, 0);
		sp.edit().putString(key, value).commit();

	}

	public static void clear(Context context) {
		if (sp == null)
			sp = context.getSharedPreferences(SP_NAME, 0);
		sp.edit().clear().commit();
	}

	/**
	 * ����long��
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void saveLong(Context context, String key, long value) {
		if (sp == null)
			sp = context.getSharedPreferences(SP_NAME, 0);
		sp.edit().putLong(key, value).commit();
	}

	/**
	 * ����int��
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void saveInt(Context context, String key, int value) {
		if (sp == null)
			sp = context.getSharedPreferences(SP_NAME, 0);
		sp.edit().putInt(key, value).commit();
	}

	/**
	 * ����float��
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void saveFloat(Context context, String key, float value) {
		if (sp == null)
			sp = context.getSharedPreferences(SP_NAME, 0);
		sp.edit().putFloat(key, value).commit();
	}

	/**
	 * ��ȡ�ַ�ֵ
	 * 
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static String getString(Context context, String key, String defValue) {
		if (sp == null)
			sp = context.getSharedPreferences(SP_NAME, 0);
		return sp.getString(key, defValue);
	}

	/**
	 * ��ȡintֵ
	 * 
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static int getInt(Context context, String key, int defValue) {
		if (sp == null)
			sp = context.getSharedPreferences(SP_NAME, 0);
		return sp.getInt(key, defValue);
	}

	/**
	 * ��ȡlongֵ
	 * 
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static long getLong(Context context, String key, long defValue) {
		if (sp == null)
			sp = context.getSharedPreferences(SP_NAME, 0);
		return sp.getLong(key, defValue);
	}

	/**
	 * ��ȡfloatֵ
	 * 
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static float getFloat(Context context, String key, float defValue) {
		if (sp == null)
			sp = context.getSharedPreferences(SP_NAME, 0);
		return sp.getFloat(key, defValue);
	}

	/**
	 * ��ȡ����ֵ
	 * 
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static boolean getBoolean(Context context, String key,
			boolean defValue) {
		if (sp == null)
			sp = context.getSharedPreferences(SP_NAME, 0);
		return sp.getBoolean(key, defValue);
	}

}
