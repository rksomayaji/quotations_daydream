package com.rksomayaji.quotationsdaydream;

import java.util.Random;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.service.dreams.DreamService;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class DreamActivity extends DreamService {
	private TextView dreamQuote;
	private Animation alphaAnimation;
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
			final AnimatorSet quotes = new AnimatorSet();
			Animator anim = null;
			quotes.play(anim);
			quotes.addListener(new AnimatorListener () {
				@Override
				public void onAnimationEnd(Animator animation)
				{
					quotes.start();
				}
				
				@Override
				public void onAnimationCancel(Animator arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onAnimationRepeat(Animator arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onAnimationStart(Animator arg0) {
					// TODO Auto-generated method stub
					
				}
			});
			quotes.start();

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
		Log.d("QuotationsDaydream", "Daydream ended not with a bang but with an encore...");
	}
}
