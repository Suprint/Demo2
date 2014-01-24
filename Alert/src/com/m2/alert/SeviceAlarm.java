package com.m2.alert;

import java.util.Calendar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SeviceAlarm extends BroadcastReceiver {
	
	void log(String message){
		Log.i("Log : ", message);
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {

		Calendar calender = Calendar.getInstance();
		String day = MainActivity.setting.getDay().toString();
		if(!"".equals(day)){
			String []arrDay = MainActivity.setting.getDay().split(",", -1);
			if(arrDay.length > 0){
				for (int i = 0; i < arrDay.length; i++) {
					if(Integer.parseInt(arrDay[i]) == calender.get(Calendar.DAY_OF_WEEK)){
						inits();
						break;
					}
				}
			}
		}
		
	}
	
	
	private void inits() {
		MainActivity.beep.updatePrefs();
		MainActivity.beep.playBeepSoundAndVibrate();
	}
}
