package com.example.zhbj.imap;

import com.example.zhbj.base.BasePager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class PoliticsPager extends BasePager {

	public PoliticsPager(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		super.initData();
		mtv_pagetitle.setText("政务");
		mib_menu.setVisibility(View.VISIBLE);
		
		TextView tv = new TextView(mActivity);
		tv.setText("政务");
		tv.setGravity(Gravity.CENTER);
		tv.setTextColor(Color.RED);
		mfl_container_basepager.addView(tv);
	}

}
