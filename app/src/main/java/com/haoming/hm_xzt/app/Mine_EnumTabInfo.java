package com.haoming.hm_xzt.app;

public enum Mine_EnumTabInfo {

	Tab_Index(0, "Tab_Index", MypublishActivity.class), Tab_RecentContact(1,
			"Tab_RecentContact", MyStoreActivity.class), Tab_Contact(2,
			"Tab_Contact", CollectstoreActivity.class), Tab_My(3, "Tab_My",
			CollectgoodsActivity.class);

	private int index;
	private String tag;
	private Class<?> clss;

	// private int icon;
	// private int iconSelected;

	private Mine_EnumTabInfo(int index, String tag, Class<?> clss) {
		this.index = index;
		this.tag = tag;
		this.clss = clss;
		// this.icon = icon;
		// this.iconSelected = iconSelected;
	}

	/**
	 * 通过索引得到枚举
	 * 
	 * @param index
	 * @return
	 */
	public static Mine_EnumTabInfo getTabInfoByIndex(int index) {
		Mine_EnumTabInfo[] values = Mine_EnumTabInfo.values();
		for (int i = 0; i < values.length; i++) {
			if (index == values[i].index)
				return values[i];
		}

		return null;
	}

	/**
	 * 通过tag得到枚举信息
	 * 
	 * @param tag
	 * @return
	 */
	public static Mine_EnumTabInfo getTabInfoByTag(String tag) {

		Mine_EnumTabInfo[] values = Mine_EnumTabInfo.values();
		for (int i = 0; i < values.length; i++) {
			if (tag.equals(values[i].tag))
				return values[i];
		}
		return null;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Class<?> getClss() {
		return clss;
	}

	public void setClss(Class<?> clss) {
		this.clss = clss;
	}

	// public int getIcon() {
	// return icon;
	// }
	//
	// public void setIcon(int icon) {
	// this.icon = icon;
	// }
	//
	// public int getIconSelected() {
	// return iconSelected;
	// }
	//
	// public void setIconSelected(int iconSelected) {
	// this.iconSelected = iconSelected;
	// }

}
