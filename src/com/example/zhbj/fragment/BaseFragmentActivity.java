package com.example.zhbj.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragmentActivity extends Fragment {
	public Activity mActivity;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//获取当前准备加载到的activity
		mActivity = getActivity();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		//
		View view = initView();
		return view;
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		//
		initData();
		super.onActivityCreated(savedInstanceState);
	}
	/*
	 * 子类必须实现用来展示view
	 */
	public abstract View initView();
	/*
	 * 用来展示数据的方法
	 */
	public void initData() {
		// TODO Auto-generated method stub
		
	};
}
