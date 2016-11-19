package com.weili.wisdom;


import java.util.ArrayList;
import java.util.List;

import com.weili.wisdom.utils.CacheUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;

public class GildeActivity extends Activity  implements OnClickListener{

	private List<ImageView> imageViewList;
	private LinearLayout ll_guide_all_position;//点的组

	private View view_duide_red_position;//红色的idan

	private int basiWidth;//点的间距
	private Button btn_start_experience;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_gilde);//红色点的图
		init();
	}
/*
 * 初始化数据
 */
	private void init() {
		// TODO Auto-generated method stub
		ViewPager vp_guide=(ViewPager) findViewById(R.id.vp_guide);
		btn_start_experience = (Button) findViewById(R.id.btn_start_experience);
		ll_guide_all_position = (LinearLayout) findViewById(R.id.ll_guide_all_position);
		view_duide_red_position=findViewById(R.id.view_duide_red_position);
		btn_start_experience.setOnClickListener(this);
		//准备数据;
		initData();
		
		GuidePagerAdapter madapter=new GuidePagerAdapter();
		vp_guide.setAdapter(madapter);
		
		//页面改变监听
		vp_guide.setOnPageChangeListener(new MyOnPageChangeListener());
		
		
		//获得视图树观察者，观察整个布局Layou
		view_duide_red_position.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			/**
			 * 当全局开始回调时回调此方法
			 */
			@Override
			public void onGlobalLayout() {
				//此方法只需要执行一次
				view_duide_red_position.getViewTreeObserver().removeGlobalOnLayoutListener(this);
			//点的距离=第1个点的左边 - 第0个点的左边；
				 basiWidth=ll_guide_all_position.getChildAt(1).getLeft()-ll_guide_all_position.getChildAt(0).getLeft();
				System.out.println("点的间距"+basiWidth);
			}
		});
	}
	
	
	
	
	
		private void initData() {
				int [] imageResIds={R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};
				imageViewList = new ArrayList<ImageView>();
				ImageView iv;
	for (int i = 0; i < imageResIds.length; i++) {
		iv=new ImageView(this);
		iv.setBackgroundResource(imageResIds[i]);
		imageViewList.add(iv);
		
		//向线性布局添加灰色点
		View  view=new View(this);
		view.setBackgroundResource(R.drawable.point_nonmal);
		//添加宽和g高
		LayoutParams params=new LayoutParams(20,20);
		if(i!=0){
		params.leftMargin=10;
		}
		view.setLayoutParams(params);
		
		ll_guide_all_position.addView(view);
	}
}

		class GuidePagerAdapter extends PagerAdapter{

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return imageViewList.size();
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return arg0==arg1;
			}
			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				container.removeView((View)object);
			}
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			//向ViewPage添加一个ImageIVew
			ImageView iv=imageViewList.get(position);
			container.addView(iv);
			
			return iv;
		}	
		
		}
	class MyOnPageChangeListener implements OnPageChangeListener{


		/**
		 * 当页面滚动时出发方法
		 * @param position当前正在被选择的Positon
		 * @param positionOffset页面移动比值
		 * @param positionOffsetPoxels 页面移动的像素
		 */
		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
				int leftMargin= (int)(basiWidth*(position+positionOffset));
			System.out.println("当前页面比值"+position+"比值"+positionOffsetPixels);
			//点的最终移动的距离=间距*比值
			
				RelativeLayout.LayoutParams lp=(RelativeLayout.LayoutParams) view_duide_red_position.getLayoutParams();
		
				lp.leftMargin=leftMargin;
				view_duide_red_position.setLayoutParams(lp);
		}
/**
 * 当前页面选中时出发的方法
 */
		@Override
		public void onPageSelected(int position) {
			if(position==imageViewList.size()-1){
				btn_start_experience.setVisibility(View.VISIBLE);
			}
			else{
				btn_start_experience.setVisibility(View.GONE);				
			}
		}
/**
 * 当前页面状态该百年时出发的方法
 */
		@Override
		public void onPageScrollStateChanged(int state) {
			// TODO Auto-generated method stub
			
		}
	
		
	}
	@Override
	public void onClick(View arg0) {
		//想ShardPreences存储一个打开的标记
		CacheUtils.putBoolean(this,ActivityWelcome.IS_OPEN_MAIN_KEY, true);
		//跳转主界面
		startActivity(new Intent(GildeActivity.this,MainActivity.class));
		finish();
	}
}
