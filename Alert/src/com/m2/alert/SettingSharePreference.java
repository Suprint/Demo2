package com.m2.alert;

import android.content.Context;
import android.content.SharedPreferences;

public class SettingSharePreference {
	private final String PPREFER_NAME = "iShare";
	private final String KEY_PARAMETER = "parameter";
	private final String KEY_DAYS = "day_of_week";
	private final String KEY_SOUND = "sound";
	private final String KEY_VIBRATE = "vibrate";
	
	private SharedPreferences iShare = null;

	public SettingSharePreference(Context context) {
		if (iShare == null)
			iShare = (SharedPreferences) context.getSharedPreferences(
					PPREFER_NAME, Context.MODE_PRIVATE);
	}

	public void setParameter(String parameter) {
		iShare.edit().putString(KEY_PARAMETER, parameter).commit();
	}

	public String getParameter() {
		return iShare.getString(KEY_PARAMETER, "");
	}
	
	public void setDay(String day) {
		iShare.edit().putString(KEY_DAYS, day).commit();
	}

	public String getDay() {
		return iShare.getString(KEY_DAYS, "");
	}

	public void setSound(boolean status) {
		iShare.edit().putBoolean(KEY_SOUND, status).commit();
	}

	public boolean getSound() {
		return iShare.getBoolean(KEY_SOUND, false);
	}

	public void setVibrate(boolean status) {
		iShare.edit().putBoolean(KEY_VIBRATE, status).commit();
	}

	public boolean getVibrate() {
		return iShare.getBoolean(KEY_VIBRATE, false);
	}
}
