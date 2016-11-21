package com.weili.wisdom.fragment;

import java.util.ArrayList;
import java.util.List;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.weili.wisdom.MainActivity;
import com.weili.wisdom.R;
import com.weili.wisdom.base.BaseFragment;
import com.weili.wisdom.base.BasePager;
import com.weili.wisdom.base.impl.GovaffiairsPage;
import com.weili.wisdom.base.impl.HomePage;
import com.weili.wisdom.base.impl.NewCenterPage;
import com.weili.wisdom.base.impl.SettingPage;
import com.weili.wisdom.base.impl.SmartservicesPage;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class CountentFragment extends BaseFragment implements OnCheckedChangeListener, OnPageChangeListener {
	@ViewInject(R.id.vp_content_fragment)
	private ViewPager viewPager;
	@ViewInject(R.id.rg_content_fragment)
	private RadioGroup  radioGroup;
	private List<BasePager> pagerList;
	@Override
	public View initView() {
		View view=	View.inflate(mActivity, R.layout.content_fragment,null);
		//把view对象住入到xUtils中
		ViewUtils.inject(this,view);
	return view;
	}
	

	
	@Override
	public void initData() {
	
		//初始化ViewPage的数据
		
		pagerList = new ArrayList<BasePager>();
		pagerList.add(new HomePage(mActivity));
		pagerList.add(new NewCenterPage(mActivity));
		pagerList.add(new SmartservicesPage(mActivity));
		pagerList.add(new GovaffiairsPage(mActivity));
		pagerList.add(new SettingPage(mActivity));

		ConentAdapter adapter=new ConentAdapter();
		viewPager.setAdapter(adapter);
		viewPager.setOnPageChangeListener(this);
		
		
		//监听单选按钮组中的按钮变化
		radioGroup.setOnCheckedChangeListener(this);
		//设置默认选中的页面为：首页
		radioGroup.check(R.id.rb_content_fragment_home);
		//把首页 的数据加载 了
	pagerList.get(0).initData();
	}
	
	/**
	 * 获取新闻中心界面的实例对象
	 * @return 
	 */
	public NewCenterPage getNewCenterPagerInstance(){
		NewCenterPage  newsCenterPager=(NewCenterPage) pagerList.get(1);
		return newsCenterPager;
	}
	class ConentAdapter extends  PagerAdapter{

		@Override
		public int getCount() {
			return pagerList.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			
			return view==object;
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
		
			container.removeView((View) object);
		}
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			//把对应pagerList集合中的position位置的页面布局添加到viewPager中，并返回
			BasePager pager=pagerList.get(position);
			container.addView(pager.rootView);//把布局添加到ViewPager中
//			pager.initData();//初始化数据，布恩那个在此加载，此方法会预加载下一个页面，加载下一个页面是数据
			return pager.rootView;
		}
	}
	@Override
	public void onCheckedChanged(RadioGroup group, int CheckedId) {
	switch (CheckedId) {
	case R.id.rb_content_fragment_home:
		viewPager.setCurrentItem(0);
		//把上下文实例转换成MainActivity的实例
		isEnabledSlidingMenu(false);
		break;
	case R.id.rb_content_fragment_newsscent:
		viewPager.setCurrentItem(1);
		isEnabledSlidingMenu(true);
		break;
	case R.id.rb_content_fragment_smarts:
		viewPager.setCurrentItem(2);
		isEnabledSlidingMenu(true);
		break;
	case R.id.rb_content_fragment_gavaffairs:
		viewPager.setCurrentItem(3);
		isEnabledSlidingMenu(true);
		break;
	case R.id.rb_content_fragment_setting:
		viewPager.setCurrentItem(4);
		isEnabledSlidingMenu(false);
		break;
		
	default:
		break;
	}
		
	}
	/**
	 * 是否启用侧滑菜单
	 * @param flag
	 */
	private void isEnabledSlidingMenu(boolean flag){
		MainActivity mainActivity=	((MainActivity)mActivity);
		SlidingMenu slidingMenu=mainActivity.getSlidingMenu();
		if(flag){
		slidingMenu.setTouchModeAbove(slidingMenu.TOUCHMODE_FULLSCREEN);
		}else{
			slidingMenu.setTouchModeAbove(slidingMenu.TOUCHMODE_NONE);
		}
	}
	
	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
		// TODO Auto-generated method stub
		
	}
	/*
	 * 当页面被选中时出发此方法,position就是当前页面的索引
	 */
	@Override
	public void onPageSelected(int position) {
		pagerList.get(position).initData();	
	}
	@Override
	public void onPageScrollStateChanged(int state) {
		// TODO Auto-generated method stub
		
	}
	
}
