package com.example.qianlong.bean;

/**
 * <p>
 * <h1>��ȡʵ�����ŵ�֪ʶ����� ÿ��������һ�����ʽ���</h1>
 * <p>
 * Id int ��������ID��ÿ���������õ�ID��Identity
 * <p>
 * LemmaID int ����ID��ÿ������ID�ǹ̶���
 * <p>
 * LemmaName string ��������������
 * <p>
 * EnglishName string ������Ӣ�����ƣ�����Ϊ��
 * <p>
 * LemmaBody string ���������ģ����ı�������HTML
 * 
 * @author lixingwang
 * 
 */
public class NewsLemmas {
	private int Id;

	private int LemmaID;

	private String LemmaName;

	private String EnglishName;

	private String LemmaBody;

	public void setId(int Id) {
		this.Id = Id;
	}

	public int getId() {
		return this.Id;
	}

	public void setLemmaID(int LemmaID) {
		this.LemmaID = LemmaID;
	}

	public int getLemmaID() {
		return this.LemmaID;
	}

	public void setLemmaName(String LemmaName) {
		this.LemmaName = LemmaName;
	}

	public String getLemmaName() {
		return this.LemmaName;
	}

	public void setEnglishName(String EnglishName) {
		this.EnglishName = EnglishName;
	}

	public String getEnglishName() {
		return this.EnglishName;
	}

	public void setLemmaBody(String LemmaBody) {
		this.LemmaBody = LemmaBody;
	}

	public String getLemmaBody() {
		return this.LemmaBody;
	}
}
