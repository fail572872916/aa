package com.weili.wisdom.fragment;

import java.util.List;

import com.weili.wisdom.base.BaseFragment;
import com.weili.wisdom.daomain.NewBean.NewsCenterMenu;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class LeftMenuFragment extends BaseFragment {

	private  List<NewsCenterMenu> mDataList;
	private ListView 		mListview;
	@Override
	public View initView() {
		mListview = new ListView(mActivity);
			return 		mListview;
	}

	/**
	 * 设置菜单数据
	 * @param dataList
	 */
	public void SetMenuDatalist(List<NewsCenterMenu> dataList){
		
	this.mDataList=dataList;
	LeftMenuAdapter mAdapter=new LeftMenuAdapter();
	mListview.setAdapter(mAdapter);
	}
	class LeftMenuAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mDataList.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			return null;
		}
		
		
	}
}
