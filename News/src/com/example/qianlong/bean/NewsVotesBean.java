package com.example.qianlong.bean;

import java.io.Serializable;

/**
 * <h1>获取实体新闻的投票 可以是多个问题的单选或复选</h1>
 * <p>
 * VoteID int 问题ID
 * <p>
 * VoteType int 问题类型：1=单选；2=多选
 * <p>
 * VoteTitle string 问题描述
 * <p>
 * ItemID int 选择项ID
 * <p>
 * ItemName string 选择项描述
 * <p>
 * ItemCount int 该选择项当前投票数
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
