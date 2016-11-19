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
	//�Ƿ�򿪹���ҳ��
	public static final String IS_OPEN_MAIN_KEY="is_open_main_key";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_welcome);

		// init��ʼ��
		init();
	}

	private void init() {
		RelativeLayout rl_welcome_root = (RelativeLayout) findViewById(R.id.rl_welcome_root);
		AnimationSet animationSet=new AnimationSet(false);
		// ��ת���� ,���ĵ������ת
		RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		// ����ʱ��
		rotateAnimation.setDuration(1000);// ����ʱ��
		rotateAnimation.setFillAfter(true);// ���ֶ�������״̬

		ScaleAnimation sAnimation = new ScaleAnimation(0, 1, 0, 1,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		sAnimation.setDuration(1000);// ����ʱ��
		sAnimation.setFillAfter(true);// ���ֶ�������״̬
		// ���䶯��
		AlphaAnimation apAnimation = new AlphaAnimation(0, 1);
		apAnimation.setDuration(2000);// ����ʱ��
		apAnimation.setFillAfter(true);// ���ֶ�������״̬

		
		animationSet.addAnimation(rotateAnimation);
		animationSet.addAnimation(sAnimation);
		animationSet.addAnimation(apAnimation);

		animationSet.setAnimationListener(new MyAnimationListener());
		// ��������
		rl_welcome_root.startAnimation(animationSet);

	}
 class MyAnimationListener implements AnimationListener{

	@Override
	public void onAnimationEnd(Animation arg0) {
		//�жϵ�ǰ������ҳ�滹�� ��������
		
		
	boolean isOpenMainPager=	CacheUtils.getBoolean(ActivityWelcome.this, IS_OPEN_MAIN_KEY, false);
		if(isOpenMainPager){
			//��ת����ҳ��
			startActivity(new Intent(ActivityWelcome.this,MainActivity.class));
			}
		else{
			Toast.makeText(ActivityWelcome.this, "����", Toast.LENGTH_SHORT).show();
			//��ת������
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
