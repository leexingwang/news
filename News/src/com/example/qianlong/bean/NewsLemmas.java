package com.example.qianlong.bean;

/**
 * <p>
 * <h1>获取实体新闻的知识库词条 每个词条是一个名词解释</h1>
 * <p>
 * Id int 词条引用ID，每个词条引用的ID是Identity
 * <p>
 * LemmaID int 词条ID，每个词条ID是固定的
 * <p>
 * LemmaName string 词条的中文名称
 * <p>
 * EnglishName string 词条的英文名称，可能为空
 * <p>
 * LemmaBody string 词条的正文，纯文本，不含HTML
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
