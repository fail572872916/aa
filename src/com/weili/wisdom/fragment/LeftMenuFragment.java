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
	private int currentSelectItem;// ��ǰ��ѡ�в˵��ı���
	private LeftMenuAdapter mAdapter;

	@Override
	public View initView() {
		mListview = new ListView(mActivity);
		mListview.setBackgroundColor(Color.BLACK);
		mListview.setCacheColorHint(Color.TRANSPARENT);
		mListview.setDividerHeight(0);
		mListview.setPadding(0, 40, 0, 0);
		mListview.setSelector(android.R.color.transparent);// ��ListView��item������
															// ͸��
		return mListview;
	}

	/**
	 * ���ò˵�����
	 * 
	 * @param dataList
	 */
	public void SetMenuDatalist(List<NewsCenterMenu> dataList) {

		this.mDataList = dataList;
		mAdapter = new LeftMenuAdapter();
		// ��ǰ��ѡ�в˵��ı���

		currentSelectItem = 0;
		// ��ʼ��Ĭ��ѡ�в˵���Ӧ��ҳ��
		switchMenuDetailPager(0);
		mListview.setAdapter(mAdapter);
		// Listview�ĵ���¼�
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
			if (arg0 == currentSelectItem) {// ��ǰ��ȡ����Ŀ�ͱ�ѡ�����Ŀλ��һ����Ӧ�ðѵ�ǰ����Ŀ����Ϊ��ɫ

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
		// ���֮���ջز�߲˵�
		MainActivity mainUI = (MainActivity) mActivity;
		SlidingMenu slidingMenu = mainUI.getSlidingMenu();
		slidingMenu.toggle(); // ���Ʋ˵�����������˵���ʾ�������Σ�
		// �ѵ�ǰѡ�еĶ�Ӧ��ҳ��չʾ����

		CountentFragment countentFragment = mainUI.getCountentFragment();
		NewCenterPage page = countentFragment.getNewCenterPagerInstance();
		page.switchCurrentPager(position);
		switchMenuDetailPager(position);
	}

	/*
	 * �����������л��˵���Ӧ����ҳҳ��
	 */
	private void switchMenuDetailPager(int position) {
		MainActivity mainUI = (MainActivity) mActivity;
		CountentFragment countentFragment = mainUI.getCountentFragment();
		NewCenterPage page = countentFragment.getNewCenterPagerInstance();
		page.switchCurrentPager(position);

	}
}
