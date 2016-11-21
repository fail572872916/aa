package com.weili.wisdom.base.impl.nemudeatail;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.weili.wisdom.base.MenuDetailBasePager;

/**
 * ���˵����ŵ�����ҳ��
 * 
 * @author weli
 * 
 */
public class NewsMenuDetailPager extends MenuDetailBasePager {

	public NewsMenuDetailPager(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View initView() {
		TextView tv = new TextView(mActivity);
		tv.setText("��������ҳ��");
		tv.setTextSize(20);
		tv.setTextColor(Color.RED);
		tv.setGravity(Gravity.CENTER);
		return tv;
	}

	@Override
	public void initData() {
			System.out.println("���Ų˵���ҳ���ʼ����");
	}
}
