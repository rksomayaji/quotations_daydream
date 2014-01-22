package com.rksomayaji.quotationsdaydream;

import java.util.Random;

import android.service.dreams.DreamService;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class DreamActivity extends DreamService {
	private TextView dreamQuote;
	private Animation alphaAnimIn, alphaAnimOut;
	private boolean runDream;
	private Thread myThread;
	@Override
	
	public void onAttachedToWindow  () {
		try {
			super.onAttachedToWindow();
			setInteractive(false);
			setFullscreen(true);
			
			setContentView(R.layout.activity_dream);
			dreamQuote = (TextView)findViewById(R.id.dream_quotes);
			alphaAnimIn = AnimationUtils.loadAnimation(this, R.anim.anim_alpha_in);
			alphaAnimOut = AnimationUtils.loadAnimation(this, R.anim.anim_alpha_out);
			runDream = true;
		}catch (Exception e) {
			Log.e("QuotationsDaydream", e.toString());
		}
	}
	
	public void onDreamingStarted () {
		super.onDreamingStarted();
		try{
			Runnable myRun = new Runnable() {

				@Override
				public void run()
				{
					while(runDream)
					{
						try{
							Thread.sleep(1000);
							
							final String quote = displayQuotes();
							dreamQuote.post(new Runnable() {
								
								@Override
								public void run(){
									dreamQuote.setText(quote);
								}
							});
							
						}catch(InterruptedException ie){
							Log.e("Quoatations",ie.toString());
						}
					}
				}
			};
			myThread = new Thread(myRun);
			myThread.start();
		}catch (Exception e) {
			Log.e("QuotationsDaydream", e.toString());
		}
		
	}
	
	private String displayQuotes() {
		Random rn = new Random();
		int nextQuote = rn.nextInt(4);
		switch (nextQuote){
		case 0:
			return("The first quote");
		case 1:
			return("the second quote");
		case 2:
			return("The third quote");
		case 3:
			return("The fourth quote");
		default:
			return("The defaulte quote");
		}
	}
	
	public void onDreamingStopped () {
		super.onDreamingStopped();
		runDream = false;
		myThread.stop();
		Log.d("QuotationsDaydream", "Daydream ended not with a bang but with an encore...");
	}
}
