package com.weili.wisdom.fragment;

import java.util.List;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.weili.wisdom.MainActivity;
import com.weili.wisdom.R;
import com.weili.wisdom.base.BaseFragment;
import com.weili.wisdom.base.impl.NewCenterPage;
import com.weili.wisdom.daomain.NewBean.NewsCenterMenu;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class LeftMenuFragment extends BaseFragment implements
		OnItemClickListener {

	private List<NewsCenterMenu> mDataList;
	private ListView mListview;
	private int currentSelectItem;// 当前被选中菜单的变量
	private LeftMenuAdapter mAdapter;

	@Override
	public View initView() {
		mListview = new ListView(mActivity);
		mListview.setBackgroundColor(Color.BLACK);
		mListview.setCacheColorHint(Color.TRANSPARENT);
		mListview.setDividerHeight(0);
		mListview.setPadding(0, 40, 0, 0);
		mListview.setSelector(android.R.color.transparent);// 给ListView的item的设置
															// 透明
		return mListview;
	}

	/**
	 * 设置菜单数据
	 * 
	 * @param dataList
	 */
	public void SetMenuDatalist(List<NewsCenterMenu> dataList) {

		this.mDataList = dataList;
		mAdapter = new LeftMenuAdapter();
		// 当前被选中菜单的变量

		currentSelectItem = 0;
		// 初始化默认选中菜单对应的页面
		switchMenuDetailPager(0);
		mListview.setAdapter(mAdapter);
		// Listview的点击事件
		mListview.setOnItemClickListener(this);

	}

	class LeftMenuAdapter extends BaseAdapter {

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
			TextView tv = (TextView) View.inflate(mActivity,
					R.layout.item_left_menu, null);

			NewsCenterMenu menu = mDataList.get(arg0);
			tv.setText(menu.title);
			if (arg0 == currentSelectItem) {// 当前获取的条目和被选择的条目位置一样。应该把当前的条目设置为红色

				tv.setEnabled(true);

			} else {

				tv.setEnabled(false);
			}
			return tv;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		currentSelectItem = position;
		mAdapter.notifyDataSetChanged();
		// 点击之后收回侧边菜单
		MainActivity mainUI = (MainActivity) mActivity;
		SlidingMenu slidingMenu = mainUI.getSlidingMenu();
		slidingMenu.toggle(); // 控制菜单显隐，如果菜单显示，就屏蔽，
		// 把当前选中的对应的页面展示出来

		CountentFragment countentFragment = mainUI.getCountentFragment();
		NewCenterPage page = countentFragment.getNewCenterPagerInstance();
		page.switchCurrentPager(position);
		switchMenuDetailPager(position);
	}

	/*
	 * 根据索引来切换菜单对应详情页页面
	 */
	private void switchMenuDetailPager(int position) {
		MainActivity mainUI = (MainActivity) mActivity;
		CountentFragment countentFragment = mainUI.getCountentFragment();
		NewCenterPage page = countentFragment.getNewCenterPagerInstance();
		page.switchCurrentPager(position);

	}
}
