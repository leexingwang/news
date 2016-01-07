package com.example.qianlong.modle.modleimpl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.entity.StringEntity;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.example.qianlong.bean.RegistEntiy;
import com.example.qianlong.constants.LoginConstants;
import com.example.qianlong.modle.LoginModle;
import com.example.qianlong.utils.TLog;
import com.google.gson.Gson;

public class LoginModleImpl implements LoginModle {
	public static final MediaType JSON = MediaType
			.parse("application/json; charset=utf-8");
	OkHttpClient client = new OkHttpClient();

	public LoginModleImpl() {
	}

	@Override
	public void userRegist(String email, String phone_number, String username,
			String plain_password, String source,
			OnRegistListener onRegistListener) {
		String json = new Gson().toJson(new RegistEntiy(phone_number, username,
				plain_password));
		postRegist(LoginConstants.OAUTH2_URL+"users", json, onRegistListener);

	}

	void postRegist(String url, String json,
			final OnRegistListener onRegistListener) {
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder().url(url).post(body).build();
		client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Response response) throws IOException {
				TLog.log(response.body().string());
				onRegistListener.onRegistSuccess();

			}

			@Override
			public void onFailure(Request arg0, IOException arg1) {
				TLog.log(arg0.toString());
				onRegistListener.onRegistError();

			}
		});
	}

	@Override
	public void userRegistConfirm(String id, String confirmationToken,
			final OnRegistListener onRegistListener) {
		RequestBody body = RequestBody.create(JSON, "");
		Request request = new Request.Builder().url(LoginConstants.OAUTH2_URL+"users/"+id+LoginConstants.CONFIRM+confirmationToken).post(body).build();
		client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Response response) throws IOException {
				TLog.log(response.body().string());
				onRegistListener.onRegistSuccess(); 

			}

			@Override
			public void onFailure(Request arg0, IOException arg1) {
				TLog.log(arg0.toString());
				onRegistListener.onRegistError();

			}
		});
	}

}
