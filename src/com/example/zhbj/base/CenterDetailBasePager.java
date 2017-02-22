package com.example.zhbj.base;

import android.app.Activity;
import android.view.View;

/*
 * 左侧页面详情的基类
 */

public abstract class CenterDetailBasePager {
	public Activity maActivity;
	public View rootview;
	public CenterDetailBasePager(Activity activity){
		this.maActivity = activity;
		rootview = initview();
	}
	/*
	 * 当前页面的布局子类必须实现自己的布局
	 */
	public abstract View initview();
	/*
	 * 子类实现数据
	 */
	public void initdata(){
		
	}
}
