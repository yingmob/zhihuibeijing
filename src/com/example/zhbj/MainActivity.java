package com.example.zhbj;

import com.example.zhbj.fragment.LeftFragmentActivity;
import com.example.zhbj.fragment.MainFragmentActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

public class MainActivity extends SlidingFragmentActivity {
	private final static String MAIN = "main";
	private final static String LEFT_MAIN = "left_main";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.fragment_main);
		setBehindContentView(R.layout.fragment_leftmenu);
		//获取slidingmenu
		SlidingMenu slidingMenu = getSlidingMenu();
		slidingMenu.setMode(SlidingMenu.LEFT);
		// 设置滑动模式
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		// 设置停留位置
		slidingMenu.setBehindOffset(300);
		
		FragmentManager sfm = getSupportFragmentManager();
		FragmentTransaction ft = sfm.beginTransaction();
		ft.replace(R.id.fl_main, new MainFragmentActivity(),MAIN);//获取到主界面替换
		ft.replace(R.id.fl_left_menu, new LeftFragmentActivity(),LEFT_MAIN);//获取左侧界面替换
		ft.commit();
	}
	/*
	 * 获取左侧菜单的freament
	 */
	public LeftFragmentActivity getLeftFrament(){
		FragmentManager fm = getSupportFragmentManager();
		LeftFragmentActivity lfa = (LeftFragmentActivity)fm.findFragmentByTag(LEFT_MAIN);
		return lfa;
		
	}
	/*
	 * 或者左侧详情页面的菜单
	 */
	public MainFragmentActivity getMainCenterpager(){
		FragmentManager fm = getSupportFragmentManager();
		MainFragmentActivity mfa = (MainFragmentActivity)fm.findFragmentByTag(MAIN);
		return mfa;
		
	}
	
}
