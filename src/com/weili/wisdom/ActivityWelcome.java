package com.weili.wisdom;

import com.weili.wisdom.utils.CacheUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class ActivityWelcome extends Activity {
	//是否打开过主页面
	public static final String IS_OPEN_MAIN_KEY="is_open_main_key";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_welcome);

		// init初始化
		init();
	}

	private void init() {
		RelativeLayout rl_welcome_root = (RelativeLayout) findViewById(R.id.rl_welcome_root);
		AnimationSet animationSet=new AnimationSet(false);
		// 旋转动画 ,中心点进行旋转
		RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		// 动画时间
		rotateAnimation.setDuration(1000);// 动画时间
		rotateAnimation.setFillAfter(true);// 保持动画结束状态

		ScaleAnimation sAnimation = new ScaleAnimation(0, 1, 0, 1,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		sAnimation.setDuration(1000);// 动画时间
		sAnimation.setFillAfter(true);// 保持动画结束状态
		// 渐变动画
		AlphaAnimation apAnimation = new AlphaAnimation(0, 1);
		apAnimation.setDuration(2000);// 动画时间
		apAnimation.setFillAfter(true);// 保持动画结束状态

		
		animationSet.addAnimation(rotateAnimation);
		animationSet.addAnimation(sAnimation);
		animationSet.addAnimation(apAnimation);

		animationSet.setAnimationListener(new MyAnimationListener());
		// 启动动画
		rl_welcome_root.startAnimation(animationSet);

	}
 class MyAnimationListener implements AnimationListener{

	@Override
	public void onAnimationEnd(Animation arg0) {
		//判断当前跳到主页面还是 引导界面
		
		
	boolean isOpenMainPager=	CacheUtils.getBoolean(ActivityWelcome.this, IS_OPEN_MAIN_KEY, false);
		if(isOpenMainPager){
			//跳转到主页面
			startActivity(new Intent(ActivityWelcome.this,MainActivity.class));
			}
		else{
			Toast.makeText(ActivityWelcome.this, "错误", Toast.LENGTH_SHORT).show();
			//跳转到引导
			startActivity(new Intent(ActivityWelcome.this,GildeActivity.class));
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
