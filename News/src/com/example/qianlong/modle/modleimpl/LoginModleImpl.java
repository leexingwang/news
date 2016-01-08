package com.example.qianlong.modle.modleimpl;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.example.qianlong.bean.RegistEntity;
import com.example.qianlong.bean.RegistErrorEntity;
import com.example.qianlong.constants.LoginConstants;
import com.example.qianlong.modle.LoginModle;
import com.example.qianlong.utils.GsonTools;
import com.example.qianlong.utils.TLog;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class LoginModleImpl implements LoginModle {
	public static final MediaType JSON = MediaType
			.parse("application/json; charset=utf-8");
	OkHttpClient client = new OkHttpClient();

	public LoginModleImpl() {
	}

	@Override
	public void userRegist(String phone_number, String username,
			String plain_password, OnRegistListener onRegistListener) {
		String json = new Gson().toJson(new RegistEntity(phone_number,
				username, plain_password));
		postRegist(LoginConstants.OAUTH2_URL + "users", json, onRegistListener);

	}

	private void postRegist(String url, String json,
			final OnRegistListener onRegistListener) {
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder().url(url).post(body).build();
		client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Response response) throws IOException {
				String jsonString = response.body().string();
				TLog.log(jsonString);
				if (!response.isSuccessful()) {
					List<RegistErrorEntity> registErrorEntitys = null;
					try {
						registErrorEntitys = GsonTools.fromJsonArray(
								jsonString, RegistErrorEntity.class);
					} catch (Exception e) {
						e.printStackTrace();
					}
					RegistErrorEntity registErrorEntity = registErrorEntitys
							.get(0);
					if (LoginConstants.REGIST_ERROE_OLD_ALREADY
							.equals(registErrorEntity.getMessage())) {
						TLog.log("老用户");
					} else if (LoginConstants.REGIST_ERROE_PHONE_ALREADY
							.equals(registErrorEntity.getMessage())) {
						TLog.log("电话号码已存在");
					} else if (LoginConstants.REGIST_ERROE_USERNAME_ALREADY
							.equals(registErrorEntity.getMessage())) {
						TLog.log("用户名已存在");
					}
				}
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
		Request request = new Request.Builder()
				.url(LoginConstants.OAUTH2_URL + "users/" + id
						+ LoginConstants.CONFIRM + confirmationToken).put(body)
				.build();
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
	public void userLogin(String userName, String passWord,
			final OnLoginListener onLoginListener) {
		String loginUrl = LoginConstants.OAUTH2_URL
				+ LoginConstants.OAUTH2_URL_USER_LOGIN + "username=" + userName
				+ "&password=" + passWord;
		Request request = new Request.Builder().url(loginUrl).get().build();
		client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Response response) throws IOException {
				TLog.log(response.body().string());
				if (response.isSuccessful()) {
					onLoginListener.onLoginSuccess();
				}

			}

			@Override
			public void onFailure(Request arg0, IOException arg1) {
				onLoginListener.onLoginError();
			}
		});
	}

	@Override
	public void userLoginAccessTokenValid(String accessToken,
			final OnLoginTokenValidListener loginTokenValidListener) {
		String loginAccessTokenValid = LoginConstants.OAUTH2_URL
				+ LoginConstants.ACCESS_TOKEN_VALID + accessToken;
		Request request = new Request.Builder().url(loginAccessTokenValid)
				.get().build();
		client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Response response) throws IOException {
				TLog.log(response.body().string());
				if (response.isSuccessful()) {
					loginTokenValidListener.onLoginTokenValidSuccess();
				}

			}

			@Override
			public void onFailure(Request arg0, IOException arg1) {
				loginTokenValidListener.onLoginTokenValidError();
			}
		});

	}

	@Override
	public void userLoginRefreshToken(String refreshToken,
			final OnRefreshTokenListener refreshTokenListener) {
		String loginRefreshToken = LoginConstants.OAUTH2_URL
				+ LoginConstants.REFRESH_TOKEN + refreshToken;
		Request request = new Request.Builder().url(loginRefreshToken).get()
				.build();
		client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Response response) throws IOException {
				TLog.log(response.body().string());
				if (response.isSuccessful()) {
					refreshTokenListener.onRefreshTokenSuccess();
				}

			}

			@Override
			public void onFailure(Request arg0, IOException arg1) {
				refreshTokenListener.onRefreshTokenError();
			}
		});
	}

	@Override
	public void userFixPassWord(String id, String new_password,
			String old_password,
			final OnFixPassWordListener onFixPassWordListener) {
		String loginFixPassWord = LoginConstants.OAUTH2_URL + "users/" + id
				+ "/password";
		JSONObject json = null;
		try {
			json = new JSONObject().put("new_password", new_password).put(
					"old_password", old_password);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		RequestBody body = RequestBody.create(JSON, json.toString());
		Request request = new Request.Builder().url(loginFixPassWord)
				.patch(body).build();
		client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Response response) throws IOException {
				TLog.log(response.body().string());
				if (response.isSuccessful()) {
					onFixPassWordListener.onFixPassWordSuccess();
				}

			}

			@Override
			public void onFailure(Request arg0, IOException arg1) {
				onFixPassWordListener.onFixPassWordError();
			}
		});

	}

	@Override
	public void userForgotPassWord(String phoneNumber,
			final OnForgotPassWordListener onForgotPassWordListener) {
		String loginFixPassWord = LoginConstants.OAUTH2_URL
				+ "users/forgot-password";
		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj.put("contact_info", phoneNumber);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		RequestBody body = RequestBody.create(JSON, jsonObj.toString());
		Request request = new Request.Builder().url(loginFixPassWord)
				.post(body).build();
		client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Response response) throws IOException {
				TLog.log(response.body().string());
				if (response.isSuccessful()) {
					onForgotPassWordListener.onForgotPassWordSuccess();
				}

			}

			@Override
			public void onFailure(Request arg0, IOException arg1) {
				onForgotPassWordListener.onForgotPassWordError();
			}
		});

	}

	@Override
	public void userForgotResetPassWord(String id, String new_password,
			String validation_code,
			final OnForgotResetPassWordListener onForgotResetPassWordListener) {
		String loginForgotResetPassWord = LoginConstants.OAUTH2_URL + "users/"
				+ id + "/reset-password";
		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj.put("new_password", new_password);
			jsonObj.put("validation_code", validation_code);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		RequestBody body = RequestBody.create(JSON, jsonObj.toString());
		Request request = new Request.Builder().url(loginForgotResetPassWord)
				.patch(body).build();
		client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Response response) throws IOException {
				TLog.log(response.body().string());
				if (response.isSuccessful()) {
					onForgotResetPassWordListener
							.onForgotResetPassWordSuccess();
				}

			}

			@Override
			public void onFailure(Request arg0, IOException arg1) {
				onForgotResetPassWordListener.onForgotResetPassWordError();
			}
		});
	}

	@Override
	public void userChangeContact(String id, String new_contact_info,
			String password,
			final OnChangeContactListener onChangeContactListener) {
		String loginChangeContact = LoginConstants.OAUTH2_URL + "users/" + id
				+ "/request-change-contact-info";
		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj.put("new_contact_info", new_contact_info);
			jsonObj.put("password", password);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		RequestBody body = RequestBody.create(JSON, jsonObj.toString());
		Request request = new Request.Builder().url(loginChangeContact)
				.patch(body).build();
		client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Response response) throws IOException {
				TLog.log(response.body().string());
				if (response.isSuccessful()) {
					onChangeContactListener.onChangeContactSuccess();
				}

			}

			@Override
			public void onFailure(Request arg0, IOException arg1) {
				onChangeContactListener.onChangeContactError();
			}
		});

	}

	@Override
	public void userChangeContactValid(String id, String new_contact_info,
			String validation_code,
			final OnChangeContactValidListener onChangeContactValidListener) {
		String loginChangeContact = LoginConstants.OAUTH2_URL + "users/" + id
				+ "/contact-info";
		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj.put("new_contact_info", new_contact_info);
			jsonObj.put("validation_code", validation_code);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		RequestBody body = RequestBody.create(JSON, jsonObj.toString());
		Request request = new Request.Builder().url(loginChangeContact)
				.patch(body).build();
		client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Response response) throws IOException {
				TLog.log(response.body().string());
				if (response.isSuccessful()) {
					onChangeContactValidListener.onChangeContactValidSuccess();
				}

			}

			@Override
			public void onFailure(Request arg0, IOException arg1) {
				onChangeContactValidListener.onChangeContactValidError();
			}
		});

	}
}
