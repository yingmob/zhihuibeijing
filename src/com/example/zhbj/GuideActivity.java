package com.example.zhbj;

import java.util.ArrayList;
import java.util.List;

import com.example.zhbj.utils.ContentValue;
import com.example.zhbj.utils.SpUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class GuideActivity extends Activity {
	private List<ImageView> mimagesView;
	private LinearLayout mll_ptgroup;
	private View mv_pt_red;
	private int distlength;
	private Button mbtn_star_try;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_guide);
		init();
	}

	private void init() {
		mbtn_star_try = (Button) findViewById(R.id.btn_star_try);
		mll_ptgroup = (LinearLayout) findViewById(R.id.ll_ptgroup);
		mv_pt_red = findViewById(R.id.v_pt_red);
		mbtn_star_try.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				SpUtil.pubBoolean(GuideActivity.this, ContentValue.WELCOME_IS_GUIDE, true);
				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(intent);
				finish();
			}
		});

		initData();
		ViewPager vp_container = (ViewPager) findViewById(R.id.vp_container);
		vp_container.setAdapter(new guideViewPagerAdatpter());
		vp_container.setOnPageChangeListener(new myPageChangeListener());
	}

	private void initData() {
		int[] imagesId = { R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3 };
		mimagesView = new ArrayList<ImageView>();

		for (int i = 0; i < imagesId.length; i++) {
			ImageView iv = new ImageView(this);
			iv.setBackgroundResource(imagesId[i]);
			mimagesView.add(iv);

			View view = new View(this);
			view.setBackgroundResource(R.drawable.pt_shap_normal);
			LayoutParams params = new LayoutParams(20, 20);
			if (i != 0) {
				params.leftMargin = 20;
			}
			view.setLayoutParams(params);
			mll_ptgroup.addView(view);
		}
		// 设置监听点间距
		mv_pt_red.getViewTreeObserver().addOnGlobalLayoutListener(new myOnGlobalLayOutListener());
	}

	private class guideViewPagerAdatpter extends PagerAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mimagesView.size(); // 返回页卡数量
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {

			container.removeView((View) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView iv = mimagesView.get(position);
			container.addView(iv);

			return iv;
		}
	}

	private class myPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		/*
		 * (non-Javadoc)页面滚动时触发此方法
		 * 
		 * @see
		 * android.support.v4.view.ViewPager.OnPageChangeListener#onPageScrolled
		 * (int, float, int)
		 */
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			// 当ViewPager在滑动过程中 动态计算出小红点的的位移值
			int marginLeft = (int) ((position + positionOffset) * distlength);
			// 改变红色小球的位置
			android.widget.RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) mv_pt_red
					.getLayoutParams();
			params.leftMargin = marginLeft;// 设置小红点相对自己初始位置的偏移量
			mv_pt_red.setLayoutParams(params);
		}

		@Override
		/*
		 * (non-Javadoc)页面选中时触发此方法
		 * 
		 * @see
		 * android.support.v4.view.ViewPager.OnPageChangeListener#onPageSelected
		 * (int)
		 */
		public void onPageSelected(int position) {
			if (position == mimagesView.size() - 1) {
				mbtn_star_try.setVisibility(View.VISIBLE);
			} else {
				mbtn_star_try.setVisibility(View.INVISIBLE);
			}
		}
	}

	// 获取点间距
	private class myOnGlobalLayOutListener implements OnGlobalLayoutListener {

		@Override
		public void onGlobalLayout() {
			// 当进入这个方法时：整个view树都布局完成了 可以调用view.getlef()了
			distlength = mll_ptgroup.getChildAt(1).getLeft() - mll_ptgroup.getChildAt(0).getLeft();
			// 当测到2点之间的距离之后 取消监听
			mv_pt_red.getViewTreeObserver().removeGlobalOnLayoutListener(this);

		}
	}

}
