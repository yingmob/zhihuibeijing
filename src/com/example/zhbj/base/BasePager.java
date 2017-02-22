package com.example.zhbj.base;

import com.example.zhbj.MainActivity;
import com.example.zhbj.R;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

public class BasePager implements OnClickListener {
	public Activity mActivity;
	public TextView mtv_pagetitle;
	public ImageButton mib_menu;
	public FrameLayout mfl_container_basepager;
	public View rootview;
	
	public BasePager(Activity activity){
		this.mActivity = activity;
		rootview = initView();
	}
	
	public View initView() {
		View view = View.inflate(mActivity, R.layout.basepager_content, null);
		mtv_pagetitle = (TextView) view.findViewById(R.id.tv_pagetitle);
		mib_menu = (ImageButton) view.findViewById(R.id.ib_menu);
		mfl_container_basepager = (FrameLayout) view.findViewById(R.id.fl_container_basepager);

		mib_menu.setOnClickListener(this);
		return view;
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 子类选择实现，完成数据的初始化
	 */
	public void initData(){
		
	}

	@Override
	public void onClick(View v) {
		MainActivity mainui = (MainActivity) mActivity; 
		mainui.getSlidingMenu().toggle();
	}
}
