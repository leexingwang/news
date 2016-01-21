package com.example.qianlong.bean;

import java.io.Serializable;

/**
 * <h1>获取实体新闻的时间轴 由编辑人工录入的重要时间点事件</h1>
 * <p>
 * Id int 时间轴事件ID，每个事件的ID是Identity
 * <p>
 * TrackDate Datetime 时间轴时间发生时间
 * <p>
 * TrackContent string 事件描述
 * 
 * @author lixingwang
 * 
 */
public class NewsTracksBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int Id;

	private String TrackDate;

	private String TrackContent;

	public void setId(int Id) {
		this.Id = Id;
	}

	public int getId() {
		return this.Id;
	}

	public void setTrackDate(String TrackDate) {
		this.TrackDate = TrackDate;
	}

	public String getTrackDate() {
		return this.TrackDate;
	}

	public void setTrackContent(String TrackContent) {
		this.TrackContent = TrackContent;
	}

	public String getTrackContent() {
		return this.TrackContent;
	}
}
