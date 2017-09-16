package com.example.kaiyuan.mydictionarydemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

public class StartActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
//		final View view=View.inflate(this, R.layout.startactivity, null);
		setContentView(R.layout.startactivity);
		
		//渐变动画
		RelativeLayout relative=(RelativeLayout) findViewById(R.id.relative);
		Animation alphaAnimation=AnimationUtils.loadAnimation(this, R.anim.alpha);
		
		relative.startAnimation(alphaAnimation);
		alphaAnimation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(StartActivity.this,MainActivity.class);
				StartActivity.this.startActivity(intent);
				StartActivity.this.finish();
			}
		});
		
	

		
	}
	//跳转到
//	private void changeto(){
//		Intent intent=new Intent(this,MainActivity.class);
//	startActivity(intent);
//		this.finish();
//	}

}
