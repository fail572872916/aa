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
	private LinearLayout ll_guide_all_position;//�����

	private View view_duide_red_position;//��ɫ��idan

	private int basiWidth;//��ļ��
	private Button btn_start_experience;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_gilde);//��ɫ���ͼ
		init();
	}
/*
 * ��ʼ������
 */
	private void init() {
		// TODO Auto-generated method stub
		ViewPager vp_guide=(ViewPager) findViewById(R.id.vp_guide);
		btn_start_experience = (Button) findViewById(R.id.btn_start_experience);
		ll_guide_all_position = (LinearLayout) findViewById(R.id.ll_guide_all_position);
		view_duide_red_position=findViewById(R.id.view_duide_red_position);
		btn_start_experience.setOnClickListener(this);
		//׼������;
		initData();
		
		GuidePagerAdapter madapter=new GuidePagerAdapter();
		vp_guide.setAdapter(madapter);
		
		//ҳ��ı����
		vp_guide.setOnPageChangeListener(new MyOnPageChangeListener());
		
		
		//�����ͼ���۲��ߣ��۲���������Layou
		view_duide_red_position.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			/**
			 * ��ȫ�ֿ�ʼ�ص�ʱ�ص��˷���
			 */
			@Override
			public void onGlobalLayout() {
				//�˷���ֻ��Ҫִ��һ��
				view_duide_red_position.getViewTreeObserver().removeGlobalOnLayoutListener(this);
			//��ľ���=��1�������� - ��0�������ߣ�
				 basiWidth=ll_guide_all_position.getChildAt(1).getLeft()-ll_guide_all_position.getChildAt(0).getLeft();
				System.out.println("��ļ��"+basiWidth);
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
		
		//�����Բ�����ӻ�ɫ��
		View  view=new View(this);
		view.setBackgroundResource(R.drawable.point_nonmal);
		//��ӿ��g��
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
			//��ViewPage���һ��ImageIVew
			ImageView iv=imageViewList.get(position);
			container.addView(iv);
			
			return iv;
		}	
		
		}
	class MyOnPageChangeListener implements OnPageChangeListener{


		/**
		 * ��ҳ�����ʱ��������
		 * @param position��ǰ���ڱ�ѡ���Positon
		 * @param positionOffsetҳ���ƶ���ֵ
		 * @param positionOffsetPoxels ҳ���ƶ�������
		 */
		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
				int leftMargin= (int)(basiWidth*(position+positionOffset));
			System.out.println("��ǰҳ���ֵ"+position+"��ֵ"+positionOffsetPixels);
			//��������ƶ��ľ���=���*��ֵ
			
				RelativeLayout.LayoutParams lp=(RelativeLayout.LayoutParams) view_duide_red_position.getLayoutParams();
		
				lp.leftMargin=leftMargin;
				view_duide_red_position.setLayoutParams(lp);
		}
/**
 * ��ǰҳ��ѡ��ʱ�����ķ���
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
 * ��ǰҳ��״̬�ð���ʱ�����ķ���
 */
		@Override
		public void onPageScrollStateChanged(int state) {
			// TODO Auto-generated method stub
			
		}
	
		
	}
	@Override
	public void onClick(View arg0) {
		//��ShardPreences�洢һ���򿪵ı��
		CacheUtils.putBoolean(this,ActivityWelcome.IS_OPEN_MAIN_KEY, true);
		//��ת������
		startActivity(new Intent(GildeActivity.this,MainActivity.class));
		finish();
	}
}
