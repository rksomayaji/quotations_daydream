package com.rksomayaji.quotationsdaydream;

import java.util.Random;
import android.service.dreams.DreamService;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class DreamActivity extends DreamService {
	private TextView dreamQuote;
	private Animation alphaAnimation;
	private boolean displayQ = true;
	private Thread myThread;
	@Override
	
	public void onAttachedToWindow  () {
		try {
			super.onAttachedToWindow();
			setInteractive(false);
			setFullscreen(true);
			
			setContentView(R.layout.activity_dream);
			dreamQuote = (TextView)findViewById(R.id.dream_quotes);
			alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
		}catch (Exception e) {
			Log.e("QuotationsDaydream", e.toString());
		}
	}
	
	public void onDreamingStarted () {
		super.onDreamingStarted();
		try{
			Runnable myRun = new Runnable() {
				
				@Override
				public void run() {
					while (displayQ) {
						try {
							Thread.sleep(1000);
							final String quotes = displayQuotes();
							dreamQuote.post(new Runnable(){
									@Override
									public void run() {
										dreamQuote.setText(quotes);
									}
								});
						}catch (InterruptedException ie){
							Log.e("Quotations", ie.toString());
						}
					}
				}
			};
			//displayQuotes();
			myThread = new Thread(myRun);
			myThread.start();
		}catch (Exception ie) {
			Log.e("QuotationsDaydream", ie.toString());
			
		}
		
	}
	
	private String displayQuotes() {
		Random rn = new Random();
		//String text;
		int nextQuote = rn.nextInt(4);
		switch (nextQuote){
		case 0:
			return("The first quote");
			//break;
		case 1:
			return("the second quote");
			//break;
		case 2:
			return("the third quote");
			//break;
		case 3:
			return("the fourth quote");
			//break;
		default:
			return("the default quote");
				
		}
	}
	
	public void onDreamingStopped () {
		super.onDreamingStopped();
		displayQ = false;
		myThread.stop();
		Log.d("QuotationsDaydream", "Daydream ended not with a bang but with an encore...");
	}
}
