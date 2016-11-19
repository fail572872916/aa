package com.weili.wisdom.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/*
 * Fragment的基类
 *    上下文抽取
 *    初始化布局抽取 ：覆盖
 *    初始化数据方法抽取：可选
 */
public  abstract class BaseFragment extends Fragment {
	
	public Activity mActivity;
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity=getActivity();
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container,  Bundle savedInstanceState) {
		View view=initView();
		return view ;
	}

	@Override
	public void onActivityCreated( Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		//Activity已经初始化完毕，当前需要初始化Fragment的数据了
		initData();
	}
	
	/**
	 * 初始化Fragment的布局
	 * @return
	 */
	public abstract  View initView();

	/**
	 * 初始化数据，子类覆盖次方法，实现自己的 的数据初始化操作
	 */
	public void initData() {
		
	}

}
