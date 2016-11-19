package com.weili.wisdom.base.impl;



import java.util.List;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.weili.wisdom.MainActivity;
import com.weili.wisdom.base.BasePager;
import com.weili.wisdom.daomain.NewBean;
import com.weili.wisdom.daomain.NewBean.NewsCenterMenu;
import com.weili.wisdom.daomain.News;
import com.weili.wisdom.fragment.LeftMenuFragment;
import com.weili.wisdom.utils.Constants;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

import android.widget.TextView;



public class NewCenterPage extends BasePager {

	public NewCenterPage(Activity activity) {
		super(activity);
	}
	@Override
	public void initData() {
		tvTitle.setText("新闻");
		ibMenu.setVisibility(View.VISIBLE);
		
		TextView  tv=new TextView(mActivity)
;
		tv.setText("新闻");
		tv.setTextColor(Color.RED);
		tv.setTextSize(24);
		tv.setGravity(Gravity.CENTER);
		flCountent.addView(tv);
		getDataFromNet();
	}
	/**
	 * 获取网络数据
	 */
	private  void getDataFromNet(){
		
		String url=Constants.CATEGORIES_URL;
		HttpUtils httpUtils=new HttpUtils();
//		RequestCallBack<T>() 中的泛型是定义请求网络成功后，返回拉的数据类型
		httpUtils.send(HttpMethod.GET, url,new  RequestCallBack<String>() {

			/**
			 * 当请求成功的时候回调此方法
			 * resqpnseIfno 请求结果信息
			 */
	
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// TODO Auto-generated method stub
//System.out.println("新闻请求成功"+responseInfo.result);
		Log.d("data","新闻请求成功"+responseInfo.result);
				String json=responseInfo.result;
		processData(json);
			}

			/**
			 * 当请求失败时回调此函数
			 * msg错误信息
			 */
			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub
				System.out.println("新闻请求成功"+msg);
				Log.d("data","新闻请求成功"+msg);
			}
		});

			

		
	}
	/*
	 * 处理和解析数据
	 */
	protected void processData(String json) {
	Gson gosn=new Gson();
	 NewBean bean=gosn.fromJson(json, NewBean.class);
	System.out.println( bean.data.get(0).children.get(0).title);
	//初始化左侧菜单订单数据
	List<NewsCenterMenu> leftMenuDataList=bean.data; //左侧菜单的数据
	//把左侧菜单的数据传递给LeftMenuFragment处理
MainActivity mainUI=	((MainActivity)mActivity);
		LeftMenuFragment leftMenuFragment=mainUI.getLeftFragment();
	
	leftMenuFragment.SetMenuDatalist(leftMenuDataList);
	}

	
}
