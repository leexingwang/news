package com.example.qianlong.bean;

public class LiveBean {
	private String AdminName;

	private String LiveFrom;

	private int LiveID;

	private String LiveContent;

	private String LivePic;

	private String LiveDate;

	private int LiveType;

	public void setAdminName(String AdminName) {
		this.AdminName = AdminName;
	}

	public String getAdminName() {
		return this.AdminName;
	}

	public void setLiveFrom(String LiveFrom) {
		this.LiveFrom = LiveFrom;
	}

	public String getLiveFrom() {
		return this.LiveFrom;
	}

	public void setLiveID(int LiveID) {
		this.LiveID = LiveID;
	}

	public int getLiveID() {
		return this.LiveID;
	}

	public void setLiveContent(String LiveContent) {
		this.LiveContent = LiveContent;
	}

	public String getLiveContent() {
		return this.LiveContent;
	}

	public void setLivePic(String LivePic) {
		this.LivePic = LivePic;
	}

	public String getLivePic() {
		return this.LivePic;
	}

	public void setLiveDate(String LiveDate) {
		this.LiveDate = LiveDate;
	}

	public String getLiveDate() {
		return this.LiveDate;
	}

	public void setLiveType(int LiveType) {
		this.LiveType = LiveType;
	}

	public int getLiveType() {
		return this.LiveType;
	}

}