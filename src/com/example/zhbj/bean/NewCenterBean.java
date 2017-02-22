package com.example.zhbj.bean;

import java.util.List;

/*
 * 将新闻中心的数据封装成Javabean
 */
public class NewCenterBean {
	/*
	    * 1 所有数据类型必须是public的
	    * 2 用list 代表json中的数组
	    * 3 属性名 需与 json中的一一对应
	    */
	public List<CententMenu> data;
	public List<String> extend;
	public int retcode;
	/*
	 * 定义集合类data的Javabean
	 */
	public class CententMenu{
		public List<childrenItem> children;
		public int id;
		public String title;
		public int type;
		public String url;
		public String url1;
		public String dayurl;
		public String excurl;
		public String weekurl;
	}
	
	public class childrenItem{
		public int id;
		public String title;
		public int type;
		public String url;
	}
	
	
	
}
