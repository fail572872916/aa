package com.weili.wisdom.base.impl.nemudeatail;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.viewpagerindicator.TabPageIndicator;
import com.weili.wisdom.R;
import com.weili.wisdom.base.MenuDetailBasePager;
import com.weili.wisdom.daomain.NewBean.NewMenuTab;
import com.weili.wisdom.daomain.NewBean.NewsCenterMenu;

/**
 * ���˵����ŵ�����ҳ��
 * 
 * @author weli
 * 
 */
public class NewsMenuDetailPager extends MenuDetailBasePager {

	
	@ViewInject(R.id.tpi_news_nemu_detail)
	private TabPageIndicator mIndicator;
	@ViewInject(R.id.vp_news_menu_detail)
	private ViewPager mVipewPager;
	private List<NewMenuTab> newsMenuList;//ҳǩ��Ӧ ������
	private List<TabDetailPage> mTabDetailPagerList;//ҳǩ��Ӧ��ҳ��
	public NewsMenuDetailPager(Activity activity, NewsCenterMenu menuItemBean) {
		super(activity);
	newsMenuList = menuItemBean.children;
	}

	@Override
	public View initView() {
		// TextView tv = new TextView(mActivity);
		// tv.setText("��������ҳ��");
		// tv.setTextSize(20);
		// tv.setTextColor(Color.RED);
		// tv.setGravity(Gravity.CENTER);

		View view = View.inflate(mActivity, R.layout.new_menu_detail, null);
		ViewUtils.inject(this, view);//�ѵ�ǰ��View ����ע�뵽xUtils��
		return view;
	}

	@Override
	public void initData() {
		System.out.println("���Ų˵���ҳ���ʼ����");
		//׼������
	mTabDetailPagerList=new ArrayList<TabDetailPage>();
		
		for (int i = 0; i < newsMenuList.size(); i++) {
			 mTabDetailPagerList.add(new TabDetailPage(mActivity,newsMenuList.get(i)));
		}
		NewsMenuAdapter menuAdapter=new NewsMenuAdapter();//�õ�����������
		mVipewPager.setAdapter(menuAdapter);
		//��TabPageIdicator����ViewPager
		//��mIndicator��ViewPage��������֮��,mIndicator�����ݹ������ɹ����ϵ�ViewPage��adapter���ṩ
		mIndicator.setViewPager(mVipewPager);
	}
	
	
	/**
	 * ������
	 * @author weli
	 *
	 */
	class NewsMenuAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mTabDetailPagerList.size();
		}

		@Override
		public CharSequence getPageTitle(int position) {
			
			return newsMenuList.get(position).title;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			// TODO Auto-generated method stub
			return view==object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			TabDetailPage pager=mTabDetailPagerList.get(position);
			container.addView(pager.root);
			pager.initData();//��������
			return pager.root;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
		}

		
	}
}
