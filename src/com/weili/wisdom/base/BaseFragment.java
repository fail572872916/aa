package com.weili.wisdom.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/*
 * Fragment�Ļ���
 *    �����ĳ�ȡ
 *    ��ʼ�����ֳ�ȡ ������
 *    ��ʼ�����ݷ�����ȡ����ѡ
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
		//Activity�Ѿ���ʼ����ϣ���ǰ��Ҫ��ʼ��Fragment��������
		initData();
	}
	
	/**
	 * ��ʼ��Fragment�Ĳ���
	 * @return
	 */
	public abstract  View initView();

	/**
	 * ��ʼ�����ݣ����า�Ǵη�����ʵ���Լ��� �����ݳ�ʼ������
	 */
	public void initData() {
		
	}

}
