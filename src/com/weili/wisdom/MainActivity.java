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
	private  final String LEFT_MENU_TAG = "left_menu_tag"; //左侧菜单Fragment的标记，相当于控件的Id,后期可使用tag标记
	private  final String MAIN_CONTENT_TAG = "main_content_tag";//主界面Fragment的标记，相当于控件的Id,后期可使用tag标记
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//主界面布局
		setContentView(R.layout.activity_main);
		//配置左侧菜单
		setBehindContentView(R.layout.left_main);
		//菜单可用模式，做菜单
		SlidingMenu slidingMenu=getSlidingMenu();
		slidingMenu.setMode(slidingMenu.LEFT);
	//配置菜单可拖拽区域，整个屏幕
		slidingMenu.setTouchModeAbove(slidingMenu.TOUCHMODE_FULLSCREEN);
		//留在屏幕上的位置
		slidingMenu.setBehindOffset(200);
		
		initFragment();
	}
	
	/**
	 * 初始化Fragment对象
	 */
	private void initFragment() {
		//获取FragmentManager管理器对象
	FragmentManager fm	=getSupportFragmentManager();
	//开启事务
	FragmentTransaction ft=fm.beginTransaction();//获取事务操作对象
	//替换Fragment

	ft.replace(R.id.fl_left_menu, new LeftMenuFragment(), LEFT_MENU_TAG);//把真布局替换成左侧Fragment对象

	ft.replace(R.id.fl_main, new CountentFragment(), MAIN_CONTENT_TAG);
	//提交事务
	ft.commit();

	}
/**
 * 获取左侧菜单的Fragment的对象实例
 * @return
 */

	public LeftMenuFragment getLeftFragment(){
		
		FragmentManager fm=getSupportFragmentManager();
		LeftMenuFragment fragment=(LeftMenuFragment) fm.findFragmentByTag(LEFT_MENU_TAG);
		return fragment;
	}
	/**
	 * 获取右侧菜单的Fragment的对象实例
	 * @return
	 */

public CountentFragment getCountentFragment(){
		
		FragmentManager fm=getSupportFragmentManager();
		CountentFragment fragment=(CountentFragment) fm.findFragmentByTag(MAIN_CONTENT_TAG);
		return fragment;
	}

	
}
