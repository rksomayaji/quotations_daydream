package com.rksomayaji.quotationsdaydream;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class QDSettingsActivity extends Activity {
	public void onCreate (final Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_qdsettings);
		}
		catch (Exception e){
			Log.e("QuotationsDaydream", e.toString());
		}
	}
}