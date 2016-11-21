package com.weili.wisdom.base.impl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.weili.wisdom.MainActivity;
import com.weili.wisdom.base.BasePager;
import com.weili.wisdom.base.MenuDetailBasePager;
import com.weili.wisdom.base.impl.nemudeatail.InteractMenuDetailPager;
import com.weili.wisdom.base.impl.nemudeatail.NewsMenuDetailPager;
import com.weili.wisdom.base.impl.nemudeatail.PhotoMenuDetailPager;
import com.weili.wisdom.base.impl.nemudeatail.TopicMenuDetailPager;

import com.weili.wisdom.daomain.NewBean;
import com.weili.wisdom.daomain.NewBean.NewsCenterMenu;

import com.weili.wisdom.fragment.LeftMenuFragment;
import com.weili.wisdom.utils.Constants;

import android.app.Activity;

import android.graphics.Color;

import android.util.Log;
import android.view.Gravity;
import android.view.View;

import android.widget.TextView;

public class NewCenterPage extends BasePager {

	private List<MenuDetailBasePager> pagerList;
	public NewCenterPage(Activity activity) {
		super(activity);
	}

	@Override
	public void initData() {
		tvTitle.setText("����");
		ibMenu.setVisibility(View.VISIBLE);

		TextView tv = new TextView(mActivity);
		tv.setText("����");
		tv.setTextColor(Color.RED);
		tv.setTextSize(24);
		tv.setGravity(Gravity.CENTER);
		flCountent.addView(tv);
		getDataFromNet();
	}

	/**
	 * ��ȡ��������
	 */
	private void getDataFromNet() {

		String url = Constants.CATEGORIES_URL;
		HttpUtils httpUtils = new HttpUtils();
		// RequestCallBack<T>() �еķ����Ƕ�����������ɹ��󣬷���������������
		httpUtils.send(HttpMethod.GET, url, new RequestCallBack<String>() {

			/**
			 * ������ɹ���ʱ��ص��˷��� resqpnseIfno ��������Ϣ
			 */

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// TODO Auto-generated method stub
				// System.out.println("��������ɹ�"+responseInfo.result);
				Log.d("data", "��������ɹ�" + responseInfo.result);
				String json = responseInfo.result;
				processData(json);
			}

			/**
			 * ������ʧ��ʱ�ص��˺��� msg������Ϣ
			 */
			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub
				System.out.println("��������ʧ��" + msg);
				
			}
		});

	}

	/*
	 * �����ͽ�������
	 */
	protected void processData(String json) {
		Gson gosn = new Gson();
		NewBean bean = gosn.fromJson(json, NewBean.class);
		System.out.println(bean.data.get(0).children.get(0).title);
		// ��ʼ�����˵���������
		List<NewsCenterMenu> leftMenuDataList = bean.data; // ���˵�������
		// �����˵������ݴ��ݸ�LeftMenuFragment����
		MainActivity mainUI = ((MainActivity) mActivity);
		LeftMenuFragment leftMenuFragment = mainUI.getLeftFragment();
		leftMenuFragment.SetMenuDatalist(leftMenuDataList);

		// ��ʼ�������˵���Ӧҳ�棺���ţ�ר�⣬��ͼ������
	pagerList= new ArrayList<MenuDetailBasePager>();
		pagerList.add(new NewsMenuDetailPager(mActivity));
		pagerList.add(new TopicMenuDetailPager(mActivity));
		pagerList.add(new PhotoMenuDetailPager(mActivity));
		pagerList.add(new InteractMenuDetailPager(mActivity));
	}

	/**
	 * ��ȡ���˵���Fragment�Ķ���ʵ��
	 * 
	 * @param pposition
	 */
	public void switchCurrentPager(int position){
		MenuDetailBasePager pager=		pagerList.get(position);
		flCountent.removeAllViews();//��������Ӳ���
		flCountent.addView(pager.root);
		pager.initData();
	}
}