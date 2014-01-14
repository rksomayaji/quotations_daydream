package com.rksomayaji.quotationsdaydream;

import java.util.Random;

import android.os.Handler;
import android.os.Looper;
import android.service.dreams.DreamService;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class DreamActivity extends DreamService {
	private TextView dreamQuote;
	private Animation alphaAnimation;
	private static Looper loopie;
	private boolean runDream;
	@Override
	
	public void onAttachedToWindow  () {
		try {
			super.onAttachedToWindow();
			setInteractive(false);
			setFullscreen(true);
			
			setContentView(R.layout.activity_dream);
			dreamQuote = (TextView)findViewById(R.id.dream_quotes);
			alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
			runDream = true;
		}catch (Exception e) {
			Log.e("QuotationsDaydream", e.toString());
		}
	}
	
	public void onDreamingStarted () {
		super.onDreamingStarted();
		try{
			loopie = Looper.myLooper();
			Handler myHandler = new Handler();
			displayQuotes();
			myHandler.postDelayed(new Runnable () {
				public void run () {
					displayQuotes();
				}
			}, 7000);
			
			//displayQuotes();
			Looper.loop();
			
		}catch (Exception e) {
			Log.e("QuotationsDaydream", e.toString());
		}
		
	}
	
	private void displayQuotes() {
		Random rn = new Random();
		int nextQuote = rn.nextInt(4);
		switch (nextQuote){
		case 0:
			dreamQuote.setText(R.string.first);
			dreamQuote.clearAnimation();
			dreamQuote.startAnimation(alphaAnimation);
			break;
		case 1:
			dreamQuote.setText(R.string.second);
			dreamQuote.clearAnimation();
			dreamQuote.startAnimation(alphaAnimation);
			break;
		case 2:
			dreamQuote.setText(R.string.third);
			dreamQuote.clearAnimation();
			dreamQuote.startAnimation(alphaAnimation);
			break;
		case 3:
			dreamQuote.setText(R.string.fourth);
			dreamQuote.clearAnimation();
			dreamQuote.startAnimation(alphaAnimation);
			break;
		default:
			dreamQuote.setText(R.string.first);
			dreamQuote.clearAnimation();
			dreamQuote.startAnimation(alphaAnimation);
				
		}
	}
	
	public void onDreamingStopped () {
		super.onDreamingStopped();
		//loopie.quitSafely();
		runDream = false;
		Log.d("QuotationsDaydream", "Daydream ended not with a bang but with an encore...");
	}
}
