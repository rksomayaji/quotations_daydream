package com.rksomayaji.quotationsdaydream;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Handler;
import android.os.Looper;
import android.service.dreams.DreamService;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class DreamActivity extends DreamService {
	private TextView dreamQuote;
	//private Timer displayQuotes = new Timer();
	private boolean contToDream = true;
	//private Looper loopy = new Looper ();
	@Override
	
	public void onAttachedToWindow  () {
		try {
			super.onAttachedToWindow();
			setInteractive(false);
			setFullscreen(true);
			
			setContentView(R.layout.activity_dream);
			dreamQuote = (TextView)findViewById(R.id.dream_quotes);
		}catch (Exception e) {
			Log.e("QuotationsDaydream", e.toString());
		}
	}
	
	public void onDreamingStarted () {
		super.onDreamingStarted();
		final Animation alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
		
		while (contToDream){
			displayQuotes();
		}
		/*try {
			displayQuotes.scheduleAtFixedRate(new TimerTask (){
				int iter = 0;
				
				public void run () {
					runOnUiThread(new Runnable() {
						public void run () {
							++iter;
							switch (iter)
							{
							case 1:
								dreamQuote.setText(R.string.first);
								dreamQuote.clearAnimation();
								dreamQuote.startAnimation(alphaAnimation);
								break;
							case 2:
								dreamQuote.setText(R.string.second);
								dreamQuote.clearAnimation();
								dreamQuote.startAnimation(alphaAnimation);
								break;
							case 3:
								dreamQuote.setText(R.string.third);
								dreamQuote.clearAnimation();
								dreamQuote.startAnimation(alphaAnimation);
								break;
							case 4:
								dreamQuote.setText(R.string.fourth);
								dreamQuote.clearAnimation();
								dreamQuote.startAnimation(alphaAnimation);
								break;
							default:
								iter = 0;	
							}
						}
					});
				}
				
				
			}, 0, 7000);
		}
		catch (Exception e) {
			Log.e("Dream",e.toString());
		}*/
				
		/*Looper.prepare();
		Handler handler = new Handler (); 
		handler.postDelayed(new Runnable() {
			int iter = 0;
			public void run () {
				++iter;
				switch (iter)
				{
				case 1:
					dreamQuote.setText(R.string.first);
					dreamQuote.clearAnimation();
					dreamQuote.startAnimation(alphaAnimation);
					break;
				case 2:
					dreamQuote.setText(R.string.second);
					dreamQuote.clearAnimation();
					dreamQuote.startAnimation(alphaAnimation);
					break;
				case 3:
					dreamQuote.setText(R.string.third);
					dreamQuote.clearAnimation();
					dreamQuote.startAnimation(alphaAnimation);
					break;
				case 4:
					dreamQuote.setText(R.string.fourth);
					dreamQuote.clearAnimation();
					dreamQuote.startAnimation(alphaAnimation);
					break;
				default:
					iter = 0;	
				}
			}			
		}, 7000);
		
		Looper.loop();*/
	}
	
	private void displayQuotes() {
		Random rn = new Random();
		switch (rn.nextInt(4)){
		case 0:
			dreamQuote.setText(R.string.first);
			break;
		case 1:
			dreamQuote.setText(R.string.second);
			break;
		case 2:
			dreamQuote.setText(R.string.third);
			break;
		case 3:
			dreamQuote.setText(R.string.fourth);
			break;
			default:
				dreamQuote.setText(R.string.first);
		}
	}
	
	public void onDreamingStopped () {
		super.onDreamingStopped();
		contToDream = false;
	}
}
