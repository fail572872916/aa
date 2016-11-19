package com.weili.wisdom.base.impl;

import com.weili.wisdom.base.BasePager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Gallery;
import android.widget.TextView;



public class HomePage extends BasePager {

	public HomePage(Activity activity) {
		super(activity);
	}
	@Override
	public void initData() {
		tvTitle.setText("ÖÇ»Û±±¾©");
		ibMenu.setVisibility(View.GONE);
		
		TextView  tv=new TextView(mActivity)
;
		tv.setText("Ê×Ò³");
		tv.setTextColor(Color.RED);
		tv.setTextSize(24);
		tv.setGravity(Gravity.CENTER);
		flCountent.addView(tv);
	}
	
}
