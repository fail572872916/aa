package com.weili.wisdom.base.impl.nemudeatail;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.weili.wisdom.base.MenuDetailBasePager;

/**
 * 左侧专题的详情页面
 * 
 * @author weli
 * 
 */
public class TopicMenuDetailPager extends MenuDetailBasePager {

	public TopicMenuDetailPager(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View initView() {
		TextView tv = new TextView(mActivity);
		tv.setText("专题详情页面");
		tv.setTextSize(20);
		tv.setTextColor(Color.RED);
		tv.setGravity(Gravity.CENTER);
		return tv;
	}

	@Override
	public void initData() {
			System.out.println("专题菜单的页面初始化了");
	}
}
