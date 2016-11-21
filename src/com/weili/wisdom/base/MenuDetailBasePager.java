package com.weili.wisdom.base;

import android.app.Activity;
import android.view.View;
/**
 * 子菜单详情页的基类
 * @author weli
 *
 */
public abstract class MenuDetailBasePager {
	public Activity mActivity;
	public View root;//当前页面的布局
	public MenuDetailBasePager(Activity activity){
		this.mActivity=activity;
		root = initView();
	}
	/**
	 * 当前页面的布局。由于抽取布局不一样，子类必须实现次方法,创建自己的布局并返回
	 * 
	 * @return
	 */
	public  abstract View initView();
		
	/**
	 * 子类覆盖此方法，实现自己的数据初始化
	 */
	public void initData(){
		
	}
	
}
