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
import com.weili.wisdom.utils.CacheUtils;
import com.weili.wisdom.utils.Constants;

import android.app.Activity;

import android.graphics.Color;

import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

import android.widget.TextView;

public class NewCenterPage extends BasePager {

	private List<MenuDetailBasePager> pagerList;//左侧菜单独对应详情页面
	private List<NewsCenterMenu> leftMenuDataList;//左侧菜单的数据
	public NewCenterPage(Activity activity) {
		super(activity);
	}

	@Override
	public void initData() {
		tvTitle.setText("新闻");
		ibMenu.setVisibility(View.VISIBLE);

//		TextView tv = new TextView(mActivity);
//		tv.setText("新闻");
//		tv.setTextColor(Color.RED);
//		tv.setTextSize(24);
//		tv.setGravity(Gravity.CENTER);
//		flCountent.addView(tv);
		
		//在请求网络之前，把以前默认的数据取出来，展示在界面上
	String json=	CacheUtils.getString(mActivity, Constants.CATEGORIES_URL, null);
		if(!TextUtils.isEmpty(json)){
			//本地数据不为null，展示到界面上
			processData(json);
		}
		getDataFromNet();
	}

	/**
	 * 获取网络数据
	 */
	private void getDataFromNet() {


		HttpUtils httpUtils = new HttpUtils();
		// RequestCallBack<T>() 中的泛型是定义请求网络成功后，返回拉的数据类型
		httpUtils.send(HttpMethod.GET, Constants.CATEGORIES_URL, new RequestCallBack<String>() {

			/**
			 * 当请求成功的时候回调此方法 resqpnseIfno 请求结果信息
			 */

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// TODO Auto-generated method stub
				// System.out.println("新闻请求成功"+responseInfo.result);
				Log.d("data", "新闻请求成功" + responseInfo.result);
				String json = responseInfo.result;
				
				//把数据缓存到本地，为了下次打开程序显示历史数据
			CacheUtils.putString(mActivity, Constants.CATEGORIES_URL, json);
			
			
			processData(json);
			}

			/**
			 * 当请求失败时回调此函数 msg错误信息
			 */
			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub
				System.out.println("新闻请求失败" + msg);
				
			}
		});

	}

	/*
	 * 处理和解析数据
	 */
	protected void processData(String json) {
		Gson gosn = new Gson();
		NewBean bean = gosn.fromJson(json, NewBean.class);
		
		leftMenuDataList = bean.data;
		// 初始化化左侧菜单对应页面：新闻，专题，组图，互动
	pagerList= new ArrayList<MenuDetailBasePager>();
		pagerList.add(new NewsMenuDetailPager(mActivity,leftMenuDataList.get(0)));
		pagerList.add(new TopicMenuDetailPager(mActivity));
		pagerList.add(new PhotoMenuDetailPager(mActivity));
		pagerList.add(new InteractMenuDetailPager(mActivity));
		
		System.out.println(bean.data.get(0).children.get(0).title);
		
		// 把左侧菜单的数据传递给LeftMenuFragment处理
		MainActivity mainUI = ((MainActivity) mActivity);
		LeftMenuFragment leftMenuFragment = mainUI.getLeftFragment();
		leftMenuFragment.SetMenuDatalist(leftMenuDataList);


	}

	/**
	 * 获取左侧菜单的Fragment的对象实例
	 * 
	 * @param pposition
	 */
	public void switchCurrentPager(int position){
		//设置当前的标题
		String title=leftMenuDataList.get(position).title;
		tvTitle.setText(title);
		
		MenuDetailBasePager pager=		pagerList.get(position);
		flCountent.removeAllViews();//清楚所有子布局
		flCountent.addView(pager.root);
		pager.initData();
	}
}
