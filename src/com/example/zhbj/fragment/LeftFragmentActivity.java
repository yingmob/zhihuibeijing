package com.example.zhbj.fragment;

import java.util.List;

import com.example.zhbj.MainActivity;
import com.example.zhbj.R;
import com.example.zhbj.bean.NewCenterBean.CententMenu;
import com.example.zhbj.imap.NewPager;

import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class LeftFragmentActivity extends BaseFragmentActivity implements OnItemClickListener {

	private List<CententMenu> mData;
	private ListView mlistView;
	private int mDefaultState;
	private MyAdapter myAdapter;

	@Override
	public View initView() {
		mlistView = new ListView(mActivity);
		mlistView.setBackgroundColor(Color.BLACK);
		mlistView.setCacheColorHint(Color.TRANSPARENT);
		mlistView.setDividerHeight(0);
		mlistView.setDividerHeight(20);
		mlistView.setPadding(0, 40, 0, 0);

		return mlistView;
	}

	public void setMenuDataList(List<CententMenu> data) {
		this.mData = data;
		myAdapter = new MyAdapter();
		mlistView.setAdapter(myAdapter);
		mlistView.setOnItemClickListener(this);
		switchCenter(0);
	}

	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mData.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			View view = View.inflate(mActivity, R.layout.left_menu_list_item, null);
			TextView tv = (TextView) view.findViewById(R.id.tv_leftmenu_item);
			CententMenu menu = mData.get(arg0);
			tv.setText(menu.title);
			if (arg0 == mDefaultState) {
				tv.setEnabled(true);
			} else {
				tv.setEnabled(false);
			}
			return tv;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		mDefaultState = position;
		myAdapter.notifyDataSetChanged();

		MainActivity mainui = (MainActivity) mActivity;
		mainui.getSlidingMenu().toggle();
		// 让主界面显示对应的内容
		MainFragmentActivity mainCenterpager = mainui.getMainCenterpager();
		NewPager newCenter = mainCenterpager.getNewCenter();

		// 切换新闻中心的显示内容
		switchCenter(position);
	}

	public void switchCenter(int position) {
		MainActivity mainui = (MainActivity) mActivity;
		// 让主界面显示对应的内容
		MainFragmentActivity mainCenterpager = mainui.getMainCenterpager();
		NewPager newCenter = mainCenterpager.getNewCenter();

		// 切换新闻中心的显示内容
		newCenter.switchCenter(position);
	}

}
