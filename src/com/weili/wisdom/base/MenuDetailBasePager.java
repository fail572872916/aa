package com.weili.wisdom.base;

import android.app.Activity;
import android.view.View;
/**
 * �Ӳ˵�����ҳ�Ļ���
 * @author weli
 *
 */
public abstract class MenuDetailBasePager {
	public Activity mActivity;
	public View root;//��ǰҳ��Ĳ���
	public MenuDetailBasePager(Activity activity){
		this.mActivity=activity;
		root = initView();
	}
	/**
	 * ��ǰҳ��Ĳ��֡����ڳ�ȡ���ֲ�һ�����������ʵ�ִη���,�����Լ��Ĳ��ֲ�����
	 * 
	 * @return
	 */
	public  abstract View initView();
		
	/**
	 * ���า�Ǵ˷�����ʵ���Լ������ݳ�ʼ��
	 */
	public void initData(){
		
	}
	
}
