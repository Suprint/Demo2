package com.m2.alert;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static AutoCompleteTextView editText1 = null;
	private String sThongBao = "";
	public static SettingSharePreference setting = null;
	private static AlarmManager alarm = null;
	private static PendingIntent intentPD = null;
	public static BeepManager beep = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setControls();
	}
	
	private void setControls() {
		editText1 = (AutoCompleteTextView) findViewById(R.id.editText1);
		if(setting == null)
			setting = new SettingSharePreference(this);
		
		editText1.requestFocus();
		
		if(alarm == null)
			alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
		
		Intent intent = new Intent(this, SeviceAlarm.class);
		
		if(intentPD == null)
			intentPD = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
		

		if(beep == null)
			beep = new BeepManager(MainActivity.this);
		
		// Gia lap
		setting.setSound(false);
		setting.setVibrate(true);
		setting.setDay("2,3,5,6,7,8,4");
		
		setEvent();
	}

	private void setEvent() {
		
		((Button) findViewById(R.id.button1))
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						setData();
					}
				});
		((Button) findViewById(R.id.button2))
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						if(alarm != null && intentPD != null)
							alarm.cancel(intentPD);
					}
				});
		
	}
	
	public static void initData() {
		Calendar calender = Calendar.getInstance();
		Date date = new Date();
		date.setHours(13);
		date.setMinutes(5);
		
		if(alarm != null && intentPD != null)
			alarm.cancel(intentPD);
		
		alarm.setTimeZone(TimeZone.getDefault().getID());
		alarm.setRepeating(AlarmManager.RTC_WAKEUP, date.getTime() , parseMinute(Integer.parseInt(setting.getParameter().toString())), intentPD);
	}
	
	private void setData() {
		if(checkData()){
			setting.setParameter(String.valueOf(getPatameter()));
			initData();
		}else {
			Toast.makeText(this, sThongBao, Toast.LENGTH_LONG).show();
		}
	}
	
	private boolean checkData() {
		if("".equals(getPatameter())){
			sThongBao = "Tham số cài đặt không được để trống";
			editText1.requestFocus();
			return false;
		}
		try {
			getPatameter();
		} catch (NumberFormatException e) {
			sThongBao = "Tham số cài đặt phải nhập số";
			editText1.requestFocus();
			return false;
		}
		
		return true;
	}
	private static int getPatameter() {
		return Integer.parseInt(editText1.getText().toString());
	}
	
	private static int parseMinute(int data) {

		return data *  1000;
//		return data * (60 * 1000);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
