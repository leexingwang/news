package com.example.qianlong.bean;

import java.io.Serializable;

/**
 * <h1>��ȡʵ�����ŵ�ʱ���� �ɱ༭�˹�¼�����Ҫʱ����¼�</h1>
 * <p>
 * Id int ʱ�����¼�ID��ÿ���¼���ID��Identity
 * <p>
 * TrackDate Datetime ʱ����ʱ�䷢��ʱ��
 * <p>
 * TrackContent string �¼�����
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
