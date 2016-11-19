package com.weili.wisdom.base.impl;

import com.weili.wisdom.base.BasePager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Gallery;
import android.widget.TextView;



public class GovaffiairsPage extends BasePager {

	public GovaffiairsPage(Activity activity) {
		super(activity);
	}
	@Override
	public void initData() {
		tvTitle.setText("政务");
		ibMenu.setVisibility(View.VISIBLE);
		
		TextView  tv=new TextView(mActivity)
;
		tv.setText("政务");
		tv.setTextColor(Color.RED);
		tv.setTextSize(24);
		tv.setGravity(Gravity.CENTER);
		flCountent.addView(tv);
	}
	
}
