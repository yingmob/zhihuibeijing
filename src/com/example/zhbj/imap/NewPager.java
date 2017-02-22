package com.example.zhbj.imap;

import java.util.ArrayList;
import java.util.List;

import com.example.zhbj.MainActivity;
import com.example.zhbj.base.BasePager;
import com.example.zhbj.base.CenterDetailBasePager;
import com.example.zhbj.bean.NewCenterBean;
import com.example.zhbj.bean.NewCenterBean.CententMenu;
import com.example.zhbj.detail.InteractionCenter;
import com.example.zhbj.detail.NewCenter;
import com.example.zhbj.detail.PhotoCenter;
import com.example.zhbj.detail.SpecialCenter;
import com.example.zhbj.fragment.LeftFragmentActivity;
import com.example.zhbj.utils.ContentUrl;
import com.example.zhbj.utils.SpUtil;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.graphics.Color;
import android.text.NoCopySpan.Concrete;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class NewPager extends BasePager {

	private List<CenterDetailBasePager> pagerlist;   //左侧菜单对应的详情页面
	private List<CententMenu> data;   //左侧菜单对应的数据

	public NewPager(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		super.initData();
		mtv_pagetitle.setText("新闻");
		mib_menu.setVisibility(View.VISIBLE);
		
		String spdata = SpUtil.getString(mActivity, ContentUrl.NEW_CENTER_URL, "");
		if(spdata != ""){
			System.out.println("用的缓存数据");
		analysisData(spdata);
		}
		getDataFromNet();
	}

	private void getDataFromNet() {
		HttpUtils httpUtils = new HttpUtils();
		httpUtils.send(HttpMethod.GET, ContentUrl.NEW_CENTER_URL, new RequestCallBack<String>() {
			//请求成功回调
			//responseInfo  请求成功信息
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				SpUtil.putString(mActivity, ContentUrl.NEW_CENTER_URL, responseInfo.result);
				analysisData(responseInfo.result);
				System.out.println("数据进行了缓存");
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub
				System.out.println("数据请求失败"+msg);
			}
		});
		
	}
	/*
	 * 解析处理数据
	 */
	public void analysisData(String json){
		Gson gson = new Gson();
		NewCenterBean javabean = gson.fromJson(json, NewCenterBean.class);
		
		pagerlist = new ArrayList<CenterDetailBasePager>();
		pagerlist.add(new NewCenter(mActivity));
		pagerlist.add(new InteractionCenter(mActivity));
		pagerlist.add(new PhotoCenter(mActivity));
		pagerlist.add(new SpecialCenter(mActivity));
		
		data = javabean.data;
		//把左侧菜单数据传达给Leftframent
		MainActivity mainUi = (MainActivity)mActivity;
		LeftFragmentActivity leftFrament = mainUi.getLeftFrament();
		leftFrament.setMenuDataList(data);
		
	}
	
	public void switchCenter(int position){
		//获取左侧菜单的数据
		String title = data.get(position).title;
		mtv_pagetitle.setText(title);
		CenterDetailBasePager pager = pagerlist.get(position);
		mfl_container_basepager.removeAllViews();
		mfl_container_basepager.addView(pager.rootview);
		pager.initdata();
	}

}
