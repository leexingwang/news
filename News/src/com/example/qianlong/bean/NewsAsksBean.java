package com.example.qianlong.bean;

import java.io.Serializable;

/**
 * <p>
 * <h1>获取实体新闻的专家 专家用来回答用户提问</h1>
 * <p>
 * Id int 问答ID，每个问答的ID是Identity
 * <p>
 * AskUserID int 提问者用户ID，从前端用户系统中获取
 * <p>
 * AskUserName string 提问者用户名
 * <p>
 * AskUserAvatar string 提问者用户头像，从前端用户系统中获取
 * <p>
 * AskDate Datetime 提问时间
 * <p>
 * Asks string 提问的问题
 * <p>
 * ExpertID int 专家ID，每个专家的ID是固定的，如果ExpertID=0则代表还没有回答（最快速判断是否已回答的方法）
 * <p>
 * ExpertName string 专家姓名
 * <p>
 * ExpertTitle string 专家的头衔，比如媒体人、专栏作家或专栏作家
 * <p>
 * ExpertAvatar string 专家头像，相对地址，完整url请用 http://图片CDN域名/uppics/expert/ExpertPic
 * <p>
 * Answers string 专家的回答，如果是空则代表还没有专家回答
 * <p>
 * AnswerDate Datetime 如果回答了问题则为回答时间，否则为null
 * 
 * @author lixingwang
 * 
 */
public class NewsAsksBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int Id;

	private String AskUserID;

	private String AskUserName;

	private String AskUserAvatar;

	private String Asks;

	private int ExpertID;

	private String ExpertName;

	private String ExpertTitle;

	private String ExpertAvatar;

	private String Answers;

	private String AskDate;

	private String AnswerDate;

	public void setId(int Id) {
		this.Id = Id;
	}

	public int getId() {
		return this.Id;
	}

	public void setAskUserID(String AskUserID) {
		this.AskUserID = AskUserID;
	}

	public String getAskUserID() {
		return this.AskUserID;
	}

	public void setAskUserName(String AskUserName) {
		this.AskUserName = AskUserName;
	}

	public String getAskUserName() {
		return this.AskUserName;
	}

	public void setAskUserAvatar(String AskUserAvatar) {
		this.AskUserAvatar = AskUserAvatar;
	}

	public String getAskUserAvatar() {
		return this.AskUserAvatar;
	}

	public void setAsks(String Asks) {
		this.Asks = Asks;
	}

	public String getAsks() {
		return this.Asks;
	}

	public void setExpertID(int ExpertID) {
		this.ExpertID = ExpertID;
	}

	public int getExpertID() {
		return this.ExpertID;
	}

	public void setExpertName(String ExpertName) {
		this.ExpertName = ExpertName;
	}

	public String getExpertName() {
		return this.ExpertName;
	}

	public void setExpertTitle(String ExpertTitle) {
		this.ExpertTitle = ExpertTitle;
	}

	public String getExpertTitle() {
		return this.ExpertTitle;
	}

	public void setExpertAvatar(String ExpertAvatar) {
		this.ExpertAvatar = ExpertAvatar;
	}

	public String getExpertAvatar() {
		return this.ExpertAvatar;
	}

	public void setAnswers(String Answers) {
		this.Answers = Answers;
	}

	public String getAnswers() {
		return this.Answers;
	}

	public void setAskDate(String AskDate) {
		this.AskDate = AskDate;
	}

	public String getAskDate() {
		return this.AskDate;
	}

	public void setAnswerDate(String AnswerDate) {
		this.AnswerDate = AnswerDate;
	}

	public String getAnswerDate() {
		return this.AnswerDate;
	}
}
