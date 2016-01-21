package com.example.qianlong.bean;

import java.io.Serializable;

/**
 * <h1>ʵ�����Ŷ�ȡ ������ʵ������</h1>
 * <p>
 * NewsID int ����ID
 * <p>
 * NewsType int 10=���ţ�11=ͼ����12=��Ƶ��13=������14=ר�⣻15=ֱ����16=ͨ����17=������18=����᣻19=���¼�
 * 110=����������111=ͼ��������112=��Ƶ������113=����������114=ר��������115=ֱ��������116=ͨ��������117=����������118=
 * �����������119=���¼�����
 * <p>
 * NewsTitle string ����
 * <p>
 * ChannelID int ������ĿID���ڰ�һ����Ŀ����ʱ����
 * <p>
 * EntityNews int �������������Ϊ��ʵ������ID�����ؽ������һ���ֶ� IsEntity ���������ж��Ƿ���ʵ������
 * <p>
 * EntityPath string �������������Ϊ��ʵ�����ŵ�·����Ϣ����/�¸�ʽ������ǰ��ƴ����ҳ������Url
 * <p>
 * CreaterName string ���������α༭������
 * <p>
 * CreateDate Datetime �״δ���ʱ��
 * <p>
 * LastDate Datetime ����޸�ʱ��
 * <p>
 * NewsLength int ͼ�� = ͼƬ������λ���ţ���Ƶ = ʱ�䳤�ȣ���λ���룻�����������Ͷ����������ĵ���������λ����
 * <p>
 * NewsNotes string ���߰�������Ϊ��
 * <p>
 * NewsThumbs string ����ͼ��ַ����Ϊ��Ե�ַ
 * <p>
 * NewsSource string ������Դ����
 * <p>
 * NewsAuthor string ���������Ҫ��������Դһ���жϣ�һ�����û������ֻ��ʾ��Դ
 * <p>
 * OuterUrl string �������������Ϊ������ת��ַ���������ٲ�����ֱ������ �����ר�� �����Զ�����url����Ϊ��ֱ�Ӵ򿪵�ר��url
 * <p>
 * VideoUrl string �������Ƶ����Ƶ��������Ϊ��Ƶֱ�ӿɲ��ŵ�MP4��ַ
 * <p>
 * UniqueTag string Type��ǩ��Ψһ��һ�����ʣ�����ֱ�����ɱ�ǩר��
 * <p>
 * Tags string �ٲ�����ʾ�õ���ʽ��ǩ������ҡ����ء��ذ���
 * <p>
 * NewsAddons string �����ͼ������Ϊǰ3��ͼƬ�ĵ�ַ�������ٲ�����ʾ �������Ƶ����Ϊ�ý�Ŀ�ڵ����ϵĲ���ʱ��
 * <p>
 * IsEntity bool ���������ж��Ƿ���ʵ������
 * 
 * @author lixingwang
 * 
 */
public class News implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int NewsID;

	private int NewsType;

	private String NewsTitle;

	private int ChannelID;

	private int EntityNews;

	private String EntityPath;

	private String CreaterName;

	private String CreateDate;

	private String LastDate;

	private int NewsLength;

	private String NewsNotes;

	private String NewsThumbs;

	private String NewsSource;

	private String NewsAuthor;

	private String OuterUrl;

	private String VideoUrl;

	private String UniqueTag;

	private String Tags;

	private String NewsAddons;

	private boolean IsEntity;

	public void setNewsID(int NewsID) {
		this.NewsID = NewsID;
	}

	public int getNewsID() {
		return this.NewsID;
	}

	public void setNewsType(int NewsType) {
		this.NewsType = NewsType;
	}

	public int getNewsType() {
		return this.NewsType;
	}

	public void setNewsTitle(String NewsTitle) {
		this.NewsTitle = NewsTitle;
	}

	public String getNewsTitle() {
		return this.NewsTitle;
	}

	public void setChannelID(int ChannelID) {
		this.ChannelID = ChannelID;
	}

	public int getChannelID() {
		return this.ChannelID;
	}

	public void setEntityNews(int EntityNews) {
		this.EntityNews = EntityNews;
	}

	public int getEntityNews() {
		return this.EntityNews;
	}

	public void setEntityPath(String EntityPath) {
		this.EntityPath = EntityPath;
	}

	public String getEntityPath() {
		return this.EntityPath;
	}

	public void setCreaterName(String CreaterName) {
		this.CreaterName = CreaterName;
	}

	public String getCreaterName() {
		return this.CreaterName;
	}

	public void setCreateDate(String CreateDate) {
		this.CreateDate = CreateDate;
	}

	public String getCreateDate() {
		return this.CreateDate;
	}

	public void setLastDate(String LastDate) {
		this.LastDate = LastDate;
	}

	public String getLastDate() {
		return this.LastDate;
	}

	public void setNewsLength(int NewsLength) {
		this.NewsLength = NewsLength;
	}

	public int getNewsLength() {
		return this.NewsLength;
	}

	public void setNewsNotes(String NewsNotes) {
		this.NewsNotes = NewsNotes;
	}

	public String getNewsNotes() {
		return this.NewsNotes;
	}

	public void setNewsThumbs(String NewsThumbs) {
		this.NewsThumbs = NewsThumbs;
	}

	public String getNewsThumbs() {
		return this.NewsThumbs;
	}

	public void setNewsSource(String NewsSource) {
		this.NewsSource = NewsSource;
	}

	public String getNewsSource() {
		return this.NewsSource;
	}

	public void setNewsAuthor(String NewsAuthor) {
		this.NewsAuthor = NewsAuthor;
	}

	public String getNewsAuthor() {
		return this.NewsAuthor;
	}

	public void setOuterUrl(String OuterUrl) {
		this.OuterUrl = OuterUrl;
	}

	public String getOuterUrl() {
		return this.OuterUrl;
	}

	public void setVideoUrl(String VideoUrl) {
		this.VideoUrl = VideoUrl;
	}

	public String getVideoUrl() {
		return this.VideoUrl;
	}

	public void setUniqueTag(String UniqueTag) {
		this.UniqueTag = UniqueTag;
	}

	public String getUniqueTag() {
		return this.UniqueTag;
	}

	public void setTags(String Tags) {
		this.Tags = Tags;
	}

	public String getTags() {
		return this.Tags;
	}

	public void setNewsAddons(String NewsAddons) {
		this.NewsAddons = NewsAddons;
	}

	public String getNewsAddons() {
		return this.NewsAddons;
	}

	public void setIsEntity(boolean IsEntity) {
		this.IsEntity = IsEntity;
	}

	public boolean getIsEntity() {
		return this.IsEntity;
	}

	@Override
	public String toString() {
		return "News [NewsID=" + NewsID + ", NewsType=" + NewsType
				+ ", NewsTitle=" + NewsTitle + ", ChannelID=" + ChannelID
				+ ", EntityNews=" + EntityNews + ", EntityPath=" + EntityPath
				+ ", CreaterName=" + CreaterName + ", CreateDate=" + CreateDate
				+ ", LastDate=" + LastDate + ", NewsLength=" + NewsLength
				+ ", NewsNotes=" + NewsNotes + ", NewsThumbs=" + NewsThumbs
				+ ", NewsSource=" + NewsSource + ", NewsAuthor=" + NewsAuthor
				+ ", OuterUrl=" + OuterUrl + ", VideoUrl=" + VideoUrl
				+ ", UniqueTag=" + UniqueTag + ", Tags=" + Tags
				+ ", NewsAddons=" + NewsAddons + ", IsEntity=" + IsEntity + "]";
	}

}