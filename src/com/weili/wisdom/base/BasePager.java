package com.weili.wisdom.base;

import com.weili.wisdom.R;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;


/*
 * 主页的五个页面的基类
 */
public class BasePager {
	public Activity mActivity;
	public TextView tvTitle; //标题
	public ImageButton ibMenu;//菜单
	public FrameLayout flCountent;//正文
	public View rootView;//当前页面的布局

	public BasePager(Activity activity) {
		this.mActivity = activity;
		rootView = initView();
	}
/**
 * 初始化布局文件
 * @return
 */
	public View initView() {
		View view = View.inflate(mActivity, R.layout.base_pager, null);
		tvTitle = (TextView) view.findViewById(R.id.tv_title_bar_title);
		ibMenu = (ImageButton) view.findViewById(R.id.ib_title_bar_menu);
		flCountent = (FrameLayout) view.findViewById(R.id.fl_base_pager_cotent);
		return view;
	}
	/**
	 * 初始化数据，子类覆盖此方法，实现自己的数据初始化
	 */
	public void initData(){}
	
}
