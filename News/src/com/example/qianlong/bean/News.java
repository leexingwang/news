package com.example.qianlong.bean;

import java.io.Serializable;

/**
 * <h1>实体新闻读取 必须是实体新闻</h1>
 * <p>
 * NewsID int 新闻ID
 * <p>
 * NewsType int 10=新闻；11=图集；12=视频；13=外链；14=专题；15=直播；16=通栏；17=报名；18=见面会；19=大事件
 * 110=新闻跳链；111=图集跳链；112=视频跳链；113=外链跳链；114=专题跳链；115=直播跳链；116=通栏跳链；117=报名跳链；118=
 * 见面会跳链；119=大事件跳链
 * <p>
 * NewsTitle string 标题
 * <p>
 * ChannelID int 二级栏目ID，在按一级栏目调用时有用
 * <p>
 * EntityNews int 如果是跳链，此为其实体新闻ID，返回结果中有一个字段 IsEntity 用来快速判断是否是实体新闻
 * <p>
 * EntityPath string 如果是跳链，此为其实体新闻的路径信息，年/月格式，用于前端拼正文页的完整Url
 * <p>
 * CreaterName string 此新闻责任编辑的姓名
 * <p>
 * CreateDate Datetime 首次创建时间
 * <p>
 * LastDate Datetime 最后修改时间
 * <p>
 * NewsLength int 图集 = 图片数，单位：张；视频 = 时间长度，单位：秒；其他所有类型都是新闻正文的字数，单位：字
 * <p>
 * NewsNotes string 编者按，可能为空
 * <p>
 * NewsThumbs string 封面图地址，此为相对地址
 * <p>
 * NewsSource string 新闻来源名称
 * <p>
 * NewsAuthor string 署名，最好要和新闻来源一起判断，一般外稿没有署名只显示来源
 * <p>
 * OuterUrl string 如果是外链，此为外链跳转地址，可以在瀑布流中直接贴出 如果是专题 并且自定义了url，此为可直接打开的专题url
 * <p>
 * VideoUrl string 如果是视频或视频跳链，此为视频直接可播放的MP4地址
 * <p>
 * UniqueTag string Type标签，唯一的一个单词，用于直接生成标签专栏
 * <p>
 * Tags string 瀑布流显示用的样式标签，如独家、揭秘、重磅等
 * <p>
 * NewsAddons string 如果是图集，此为前3张图片的地址，用于瀑布流显示 如果是视频，此为该节目在电视上的播放时间
 * <p>
 * IsEntity bool 用来快速判断是否是实体新闻
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