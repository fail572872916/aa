package com.weili.wisdom.daomain;

import java.util.ArrayList;

public class News {

	public int retcode;
	public ArrayList<Integer> extend;
	public ArrayList<NewsMenuData> data;

	// 侧边栏菜单对象
	public class NewsMenuData {
		public int id;
		public String title;
		public int type;

		public ArrayList<NewsTabData> children;

		@Override
		public String toString() {
			return "NewsMenuData [title=" + title + ", children=" + children
					+ "]";
		}
	}

	// 页签的对象
	public class NewsTabData {
		public int id;
		public String title;
		public int type;
		public String url;

		@Override
		public String toString() {
			return "NewsTabData [title=" + title + "]";
		}

	}

	@Override
	public String toString() {
		return "NewsMenu [data=" + data + "]";
	}
}
