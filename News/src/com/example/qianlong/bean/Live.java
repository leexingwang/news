package com.example.qianlong.bean;

import java.io.Serializable;

public class Live implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int LiveID;

	private String LiveTitle;

	private String LiveContent;

	private String LiveImages;

	private int Category;

	private int Region;

	private String CreateDate;

	private boolean IsImportant;

	public void setLiveID(int LiveID) {
		this.LiveID = LiveID;
	}

	public int getLiveID() {
		return this.LiveID;
	}

	public void setLiveTitle(String LiveTitle) {
		this.LiveTitle = LiveTitle;
	}

	public String getLiveTitle() {
		return this.LiveTitle;
	}

	public void setLiveContent(String LiveContent) {
		this.LiveContent = LiveContent;
	}

	public String getLiveContent() {
		return this.LiveContent;
	}

	public void setLiveImages(String LiveImages) {
		this.LiveImages = LiveImages;
	}

	public String getLiveImages() {
		return this.LiveImages;
	}

	public void setCategory(int Category) {
		this.Category = Category;
	}

	public int getCategory() {
		return this.Category;
	}

	public void setRegion(int Region) {
		this.Region = Region;
	}

	public int getRegion() {
		return this.Region;
	}

	public void setCreateDate(String CreateDate) {
		this.CreateDate = CreateDate;
	}

	public String getCreateDate() {
		return this.CreateDate;
	}

	public void setIsImportant(boolean IsImportant) {
		this.IsImportant = IsImportant;
	}

	public boolean getIsImportant() {
		return this.IsImportant;
	}

	@Override
	public String toString() {
		return "Live [LiveID=" + LiveID + ", LiveTitle=" + LiveTitle
				+ ", LiveContent=" + LiveContent + ", LiveImages=" + LiveImages
				+ ", Category=" + Category + ", Region=" + Region
				+ ", CreateDate=" + CreateDate + ", IsImportant=" + IsImportant
				+ "]";
	}

}
