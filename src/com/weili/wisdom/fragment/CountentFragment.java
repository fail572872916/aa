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
		//��view����ס�뵽xUtils��
		ViewUtils.inject(this,view);
	return view;
	}
	

	
	@Override
	public void initData() {
	
		//��ʼ��ViewPage������
		
		pagerList = new ArrayList<BasePager>();
		pagerList.add(new HomePage(mActivity));
		pagerList.add(new NewCenterPage(mActivity));
		pagerList.add(new SmartservicesPage(mActivity));
		pagerList.add(new GovaffiairsPage(mActivity));
		pagerList.add(new SettingPage(mActivity));

		ConentAdapter adapter=new ConentAdapter();
		viewPager.setAdapter(adapter);
		viewPager.setOnPageChangeListener(this);
		
		
		//������ѡ��ť���еİ�ť�仯
		radioGroup.setOnCheckedChangeListener(this);
		//����Ĭ��ѡ�е�ҳ��Ϊ����ҳ
		radioGroup.check(R.id.rb_content_fragment_home);
		//����ҳ �����ݼ��� ��
	pagerList.get(0).initData();
	}
	
	/**
	 * ��ȡ�������Ľ����ʵ������
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
			//�Ѷ�ӦpagerList�����е�positionλ�õ�ҳ�沼����ӵ�viewPager�У�������
			BasePager pager=pagerList.get(position);
			container.addView(pager.rootView);//�Ѳ�����ӵ�ViewPager��
//			pager.initData();//��ʼ�����ݣ������Ǹ��ڴ˼��أ��˷�����Ԥ������һ��ҳ�棬������һ��ҳ��������
			return pager.rootView;
		}
	}
	@Override
	public void onCheckedChanged(RadioGroup group, int CheckedId) {
	switch (CheckedId) {
	case R.id.rb_content_fragment_home:
		viewPager.setCurrentItem(0);
		//��������ʵ��ת����MainActivity��ʵ��
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
	 * �Ƿ����ò໬�˵�
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
	 * ��ҳ�汻ѡ��ʱ�����˷���,position���ǵ�ǰҳ�������
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
