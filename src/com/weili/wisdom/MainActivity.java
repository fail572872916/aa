package com.weili.wisdom;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.weili.wisdom.fragment.CountentFragment;
import com.weili.wisdom.fragment.LeftMenuFragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

public class MainActivity extends SlidingFragmentActivity {
	private  final String LEFT_MENU_TAG = "left_menu_tag"; //���˵�Fragment�ı�ǣ��൱�ڿؼ���Id,���ڿ�ʹ��tag���
	private  final String MAIN_CONTENT_TAG = "main_content_tag";//������Fragment�ı�ǣ��൱�ڿؼ���Id,���ڿ�ʹ��tag���
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//�����沼��
		setContentView(R.layout.activity_main);
		//�������˵�
		setBehindContentView(R.layout.left_main);
		//�˵�����ģʽ�����˵�
		SlidingMenu slidingMenu=getSlidingMenu();
		slidingMenu.setMode(slidingMenu.LEFT);
	//���ò˵�����ק����������Ļ
		slidingMenu.setTouchModeAbove(slidingMenu.TOUCHMODE_FULLSCREEN);
		//������Ļ�ϵ�λ��
		slidingMenu.setBehindOffset(200);
		
		initFragment();
	}
	
	/**
	 * ��ʼ��Fragment����
	 */
	private void initFragment() {
		//��ȡFragmentManager����������
	FragmentManager fm	=getSupportFragmentManager();
	//��������
	FragmentTransaction ft=fm.beginTransaction();//��ȡ�����������
	//�滻Fragment

	ft.replace(R.id.fl_left_menu, new LeftMenuFragment(), LEFT_MENU_TAG);//���沼���滻�����Fragment����

	ft.replace(R.id.fl_main, new CountentFragment(), MAIN_CONTENT_TAG);
	//�ύ����
	ft.commit();

	}
/**
 * ��ȡ���˵���Fragment�Ķ���ʵ��
 * @return
 */

	public LeftMenuFragment getLeftFragment(){
		
		FragmentManager fm=getSupportFragmentManager();
		LeftMenuFragment fragment=(LeftMenuFragment) fm.findFragmentByTag(LEFT_MENU_TAG);
		return fragment;
	}
	/**
	 * ��ȡ�Ҳ�˵���Fragment�Ķ���ʵ��
	 * @return
	 */

public CountentFragment getCountentFragment(){
		
		FragmentManager fm=getSupportFragmentManager();
		CountentFragment fragment=(CountentFragment) fm.findFragmentByTag(MAIN_CONTENT_TAG);
		return fragment;
	}

	
}
