package com.example.zhbj.fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import com.example.zhbj.MainActivity;
import com.example.zhbj.R;
import com.example.zhbj.base.BasePager;
import com.example.zhbj.imap.HomePager;
import com.example.zhbj.imap.IntelligencePager;
import com.example.zhbj.imap.NewPager;
import com.example.zhbj.imap.PoliticsPager;
import com.example.zhbj.imap.SeetingPager;
import com.example.zhbj.view.MyViewPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.graphics.pdf.PdfDocument.Page;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainFragmentActivity extends BaseFragmentActivity implements android.widget.RadioGroup.OnCheckedChangeListener, OnPageChangeListener {
	
	@ViewInject(R.id.vp_content_fragment)
	private MyViewPager mViewPager;
	
	@ViewInject(R.id.rg_bottom_tab_container)
	private RadioGroup mRadioGroup;
	private List<BasePager> pagelist;

	@Override
	public View initView() {
		// TODO Auto-generated method stub
		View view = View.inflate(mActivity, R.layout.activity_main, null);
		ViewUtils.inject(this,view);
		return view;
	}
	
	public void initData(){
		
		mViewPager.setAdapter(new ContentAdapter());
		
		// 准备 viewpager的数据源
				pagelist = new ArrayList<BasePager>();
				pagelist.add(new HomePager(mActivity));
				pagelist.add(new NewPager(mActivity));
				pagelist.add(new IntelligencePager(mActivity));
				pagelist.add(new PoliticsPager(mActivity));
				pagelist.add(new SeetingPager(mActivity));
		//绑定radiogroup的切换事件		
		mRadioGroup.setOnCheckedChangeListener(this);
		//设置viewgroup的数据
		mViewPager.setOnPageChangeListener(this);
		//设置默认显示页面
		mRadioGroup.check(R.id.rb_home);
		pagelist.get(0).initData();
		
	}
	
	public void isSlidingMenu(boolean flg){
		MainActivity mActivity2 = (MainActivity) mActivity;
		SlidingMenu slidingMenu = mActivity2.getSlidingMenu();
		if(flg){
			slidingMenu.setTouchModeAbove(slidingMenu.TOUCHMODE_FULLSCREEN);
		}else{
			slidingMenu.setTouchModeAbove(slidingMenu.TOUCHMODE_NONE);
		}
	}
	
	
	public class ContentAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return pagelist.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView((View)object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			BasePager v = pagelist.get(position);
			// 在此处加载数据  viewPager的预加载机制会 浪费流量
			 // 点击某个按钮时只加载对应的资源
			//pagelist.get(position).initData();//初始化数据
			container.addView(v.rootview);
			//v.initData();
			return v.rootview;
		}
		
	}


	@Override
	public void onCheckedChanged(RadioGroup arg0, int checkid) {
		// TODO Auto-generated method stub
		switch(checkid){
		case R.id.rb_home:
			mViewPager.setCurrentItem(0);
			isSlidingMenu(false);
			break;
		case R.id.rb_newscenter:
			mViewPager.setCurrentItem(1);
			isSlidingMenu(true);
			break;
		case R.id.rb_smartservice:
			mViewPager.setCurrentItem(2);
			isSlidingMenu(true);
			break;
		case R.id.rb_govaffairs:
			mViewPager.setCurrentItem(3);
			isSlidingMenu(true);
			break;
		case R.id.rb_setting:
			mViewPager.setCurrentItem(4);
			isSlidingMenu(false);
			break;
		default:
			break;
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		pagelist.get(arg0).initData();
	}
	
	public NewPager getNewCenter(){
		NewPager newpager = (NewPager) pagelist.get(1);
		return newpager;
	}

}
