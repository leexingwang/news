package com.example.qianlong.utils;

import android.app.Dialog;
import android.content.Context;

/**
 * �Ի��򴴽�������
 * 
 */
public class DialogUtil {

	/**
	 * �������ȶԻ���
	 * 
	 * @param context
	 * @param content
	 * @return
	 */
	public static Dialog createProgressDialog(Context context, String content) {
		return new CustomProgressDialog(context, content);
	}

}
