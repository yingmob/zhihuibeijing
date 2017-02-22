package com.example.zhbj;

import com.example.zhbj.utils.ContentValue;
import com.example.zhbj.utils.SpUtil;

import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

public class WelcomeActivity extends Activity {
	private RelativeLayout mrl_welcome;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_welcome);
		initview();
	}

	private void initview() {
		mrl_welcome = (RelativeLayout) findViewById(R.id.rl_welcome);
		//设置动画
		//1、旋转
		RotateAnimation ra = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		ra.setDuration(1000);
		ra.setFillAfter(true);
		//2、缩放
		ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF,Animation.RELATIVE_TO_SELF);
		sa.setDuration(1000);
		sa.setFillAfter(true);
		//3、渐变
		AlphaAnimation aa = new AlphaAnimation(0, 1);
		aa.setDuration(2000);
		aa.setFillAfter(true);
		//4、设置属性集合
		AnimationSet as = new AnimationSet(false);
		   as.addAnimation(ra);
		   as.addAnimation(sa);
		   as.addAnimation(aa);
		//5、开启动画
		mrl_welcome.startAnimation(as);
		as.setAnimationListener(new welcomeAnimationListener());
		
	}
	private class welcomeAnimationListener implements AnimationListener{

		@Override
		public void onAnimationEnd(Animation arg0) {
			//监听动画结束后的动作
			boolean is_guide = SpUtil.getBoolean(getApplicationContext(), ContentValue.WELCOME_IS_GUIDE, false);
			if(is_guide){
				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(intent);
			}else{
				Intent intent = new Intent(getApplicationContext(), GuideActivity.class);
				startActivity(intent);
			}
			finish();
		}

		@Override
		public void onAnimationRepeat(Animation arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onAnimationStart(Animation arg0) {
			// TODO Auto-generated method stub
			
		}
	}
}
