package com.weili.wisdom.base;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.weili.wisdom.MainActivity;
import com.weili.wisdom.R;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;


/*
 * ��ҳ�����ҳ��Ļ���
 */
public class BasePager implements OnClickListener {
	public Activity mActivity;
	public TextView tvTitle; //����
	public ImageButton ibMenu;//�˵�
	public FrameLayout flCountent;//����
	public View rootView;//��ǰҳ��Ĳ���

	public BasePager(Activity activity) {
		this.mActivity = activity;
		rootView = initView();
	}
/**
 * ��ʼ�������ļ�
 * @return
 */
	public View initView() {
		View view = View.inflate(mActivity, R.layout.base_pager, null);
		tvTitle = (TextView) view.findViewById(R.id.tv_title_bar_title);
		ibMenu = (ImageButton) view.findViewById(R.id.ib_title_bar_menu);
		flCountent = (FrameLayout) view.findViewById(R.id.fl_base_pager_cotent);
	ibMenu.setOnClickListener(this);
		
		return view;
	}
	/**
	 * ��ʼ�����ݣ����า�Ǵ˷�����ʵ���Լ������ݳ�ʼ��
	 */
	public void initData(){}
	@Override
	public void onClick(View v) {
		MainActivity mainUI=(MainActivity) mActivity;
		SlidingMenu slidingMenu=mainUI.getSlidingMenu();
		slidingMenu.toggle(); //���Ʋ˵�����������˵���ʾ�������Σ�
		
		// TOODO ѡ�в˵��ľ���ҳ��
		
	}
	
}
