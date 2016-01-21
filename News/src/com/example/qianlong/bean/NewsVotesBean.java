package com.example.qianlong.bean;

import java.io.Serializable;

/**
 * <h1>��ȡʵ�����ŵ�ͶƱ �����Ƕ������ĵ�ѡ��ѡ</h1>
 * <p>
 * VoteID int ����ID
 * <p>
 * VoteType int �������ͣ�1=��ѡ��2=��ѡ
 * <p>
 * VoteTitle string ��������
 * <p>
 * ItemID int ѡ����ID
 * <p>
 * ItemName string ѡ��������
 * <p>
 * ItemCount int ��ѡ���ǰͶƱ��
 * 
 * @author lixingwang
 * 
 */
public class NewsVotesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private int VoteID;

	private int VoteType;

	private String VoteTitle;

	private int ItemID;

	private String ItemName;

	private int ItemCount;

	public void setVoteID(int VoteID) {
		this.VoteID = VoteID;
	}

	public int getVoteID() {
		return this.VoteID;
	}

	public void setVoteType(int VoteType) {
		this.VoteType = VoteType;
	}

	public int getVoteType() {
		return this.VoteType;
	}

	public void setVoteTitle(String VoteTitle) {
		this.VoteTitle = VoteTitle;
	}

	public String getVoteTitle() {
		return this.VoteTitle;
	}

	public void setItemID(int ItemID) {
		this.ItemID = ItemID;
	}

	public int getItemID() {
		return this.ItemID;
	}

	public void setItemName(String ItemName) {
		this.ItemName = ItemName;
	}

	public String getItemName() {
		return this.ItemName;
	}

	public void setItemCount(int ItemCount) {
		this.ItemCount = ItemCount;
	}

	public int getItemCount() {
		return this.ItemCount;
	}

}
