package com.example.qianlong.bean;

import java.io.Serializable;

/**
 * <p>
 * <h1>��ȡʵ�����ŵ�ר�� ר�������ش��û�����</h1>
 * <p>
 * Id int �ʴ�ID��ÿ���ʴ��ID��Identity
 * <p>
 * AskUserID int �������û�ID����ǰ���û�ϵͳ�л�ȡ
 * <p>
 * AskUserName string �������û���
 * <p>
 * AskUserAvatar string �������û�ͷ�񣬴�ǰ���û�ϵͳ�л�ȡ
 * <p>
 * AskDate Datetime ����ʱ��
 * <p>
 * Asks string ���ʵ�����
 * <p>
 * ExpertID int ר��ID��ÿ��ר�ҵ�ID�ǹ̶��ģ����ExpertID=0�����û�лش�������ж��Ƿ��ѻش�ķ�����
 * <p>
 * ExpertName string ר������
 * <p>
 * ExpertTitle string ר�ҵ�ͷ�Σ�����ý���ˡ�ר�����һ�ר������
 * <p>
 * ExpertAvatar string ר��ͷ����Ե�ַ������url���� http://ͼƬCDN����/uppics/expert/ExpertPic
 * <p>
 * Answers string ר�ҵĻش�����ǿ������û��ר�һش�
 * <p>
 * AnswerDate Datetime ����ش���������Ϊ�ش�ʱ�䣬����Ϊnull
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
