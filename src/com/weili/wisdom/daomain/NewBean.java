package com.weili.wisdom.daomain;

import java.util.List;

/*
 * ������������ʵ����
 */
public class NewBean {


	public List <NewsCenterMenu> data;
	public List<String> extend;
	public int retcode;
	
//	���˵�ʵ����
	public class NewsCenterMenu {

		public List<NewMenuTab>  children;
		public int id;
		public String title;
		public int type;
		public String  url;
		public String url1;
		public String dayurl;
		public String excurl;
		public String weekurl;
		@Override
		public String toString() {
			return "NewsCenterMenu [children=" + children + ", id=" + id
					+ ", title=" + title + ", type=" + type + ", url=" + url
					+ ", url1=" + url1 + ", dayurl=" + dayurl + ", excurl="
					+ excurl + ", weekurl=" + weekurl + "]";
		}
		
		
	}
	//���Ų˵���������bean
public class NewMenuTab{

	public int id;
	public String title;
	public int type;
	public String  url;
	
}
}