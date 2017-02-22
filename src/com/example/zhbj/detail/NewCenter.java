package com.example.zhbj.detail;

import com.example.zhbj.base.CenterDetailBasePager;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

public class NewCenter extends CenterDetailBasePager {

	public NewCenter(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View initview() {
		// TODO Auto-generated method stub
		TextView textView = new TextView(maActivity);
		textView.setText("新闻详情页面");
		textView.setTextColor(Color.RED);
		return textView;
	}

}
