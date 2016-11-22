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
 * 左侧菜单新闻的详情页面
 * 
 * @author weli
 * 
 */
public class NewsMenuDetailPager extends MenuDetailBasePager {

	
	@ViewInject(R.id.tpi_news_nemu_detail)
	private TabPageIndicator mIndicator;
	@ViewInject(R.id.vp_news_menu_detail)
	private ViewPager mVipewPager;
	private List<NewMenuTab> newsMenuList;//页签对应 的数据
	private List<TabDetailPage> mTabDetailPagerList;//页签对应的页面
	public NewsMenuDetailPager(Activity activity, NewsCenterMenu menuItemBean) {
		super(activity);
	newsMenuList = menuItemBean.children;
	}

	@Override
	public View initView() {
		// TextView tv = new TextView(mActivity);
		// tv.setText("新闻详情页面");
		// tv.setTextSize(20);
		// tv.setTextColor(Color.RED);
		// tv.setGravity(Gravity.CENTER);

		View view = View.inflate(mActivity, R.layout.new_menu_detail, null);
		ViewUtils.inject(this, view);//把当前的View 对象注入到xUtils中
		return view;
	}

	@Override
	public void initData() {
		System.out.println("新闻菜单的页面初始化了");
		//准备数据
	mTabDetailPagerList=new ArrayList<TabDetailPage>();
		
		for (int i = 0; i < newsMenuList.size(); i++) {
			 mTabDetailPagerList.add(new TabDetailPage(mActivity,newsMenuList.get(i)));
		}
		NewsMenuAdapter menuAdapter=new NewsMenuAdapter();//得到适配器对象
		mVipewPager.setAdapter(menuAdapter);
		//给TabPageIdicator关联ViewPager
		//当mIndicator和ViewPage关联起来之后,mIndicator的数据关联就由关联上的ViewPage的adapter来提供
		mIndicator.setViewPager(mVipewPager);
	}
	
	
	/**
	 * 适配器
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
			pager.initData();//加载数据
			return pager.root;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
		}

		
	}
}
