package com.example.qianlong.bean;

import java.io.Serializable;

/**
 * <h1>实体新闻读取 必须是实体新闻</h1>
 * <p>
 * NewsID int 新闻ID
 * <p>
 * NewsType int 10=新闻；11=图集；12=视频；13=外链；14=专题；15=直播；16=通栏；17=报名；18=见面会；19=大事件
 * 110=新闻跳链；111=图集跳链
 * ；112=视频跳链；113=外链跳链；114=专题跳链；115=直播跳链；116=通栏跳链；117=报名跳链；118=见面会跳链；119=大事件跳链
 * <p>
 * NewsTitle string 标题
 * <p>
 * ChannelRoot int 本新闻所属一级栏目ID
 * <p>
 * ChannelID int 本新闻所属二级栏目ID
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
 * LiveLink string 如果是大事件，此为直播流地址，请根据LiveDate一起判断前端页面是否需要展示播放器 LiveDate Datetime
 * 如果是大事件，此为直播的开始时间，其他类型新闻为空，使用前一定判断null
 * <p>
 * LiveAddress string 如果是大事件、见面会或报名，此为主办方城市或地点等信息
 * <p>
 * LiveState int 直播状态：0=未开始；1=进行中；2=已结束；
 * 此状态会对本新闻所属的图文直播排序有影响，已结束=图文直播最新的在最下；未开始或进行中=图文直播最新的在最上
 * <p>
 * NewsCover string 新闻内页需要的全背景图，可能为空，好像只有APP需要这个字段 Stocks json 本新闻的关联股票信息，json格式
 * <p>
 * Keywords string 关键字，可能是多个，单词用空格分隔
 * <p>
 * CountInners int 本新闻引用的图集和视频个数，如果不为0再调用读取引用方法读取详细的引用信息
 * <p>
 * CountRelates int 本新闻相关的新闻、图集、视频个数
 * <p>
 * CountExperts int 本新闻专家数，只有指派了专家进入才能回答用户提问
 * <p>
 * CountAsks int 本新闻用户提问总数 CountLemmas int 本新闻知识库词条引用总数 CountTracks int
 * 本新闻时间轴事件总数 CountScenes int 本新闻图文直播总条数
 * <p>
 * CountVotes int 本新闻投票问题个数
 * <p>
 * CountStocks int 本新闻关联股票个数，其实通过正文中给的json字段Stocks，也可以算出来个数
 * 
 * @author lixingwang
 * 
 */
public class NewsEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int NewsID;

	/**
	 * 10=新闻；11=图集；12=视频；13=外链；14=专题；15=直播；16=通栏；17=报名；18=见面会；19=大事件
	 * 110=新闻跳链；111=图集跳链
	 * ；112=视频跳链；113=外链跳链；114=专题跳链；115=直播跳链；116=通栏跳链；117=报名跳链；118
	 * =见面会跳链；119=大事件跳链
	 */
	private int NewsType;

	private String NewsTitle;

	private int ChannelRoot;

	private int ChannelID;

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

	private String LiveLink;

	private String LiveAddress;

	private int LiveState;

	private String NewsCover;

	private String Stocks;

	private String Keywords;

	private int CountInners;

	private int CountRelates;

	private int CountExperts;

	private int CountAsks;

	private int CountLemmas;

	private int CountTracks;

	private int CountScenes;

	private int CountVotes;

	private int CountStocks;

	private String NewsBody;

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

	public void setChannelRoot(int ChannelRoot) {
		this.ChannelRoot = ChannelRoot;
	}

	public int getChannelRoot() {
		return this.ChannelRoot;
	}

	public void setChannelID(int ChannelID) {
		this.ChannelID = ChannelID;
	}

	public int getChannelID() {
		return this.ChannelID;
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

	public void setLiveLink(String LiveLink) {
		this.LiveLink = LiveLink;
	}

	public String getLiveLink() {
		return this.LiveLink;
	}

	public void setLiveAddress(String LiveAddress) {
		this.LiveAddress = LiveAddress;
	}

	public String getLiveAddress() {
		return this.LiveAddress;
	}

	public void setLiveState(int LiveState) {
		this.LiveState = LiveState;
	}

	public int getLiveState() {
		return this.LiveState;
	}

	public void setNewsCover(String NewsCover) {
		this.NewsCover = NewsCover;
	}

	public String getNewsCover() {
		return this.NewsCover;
	}

	public void setStocks(String Stocks) {
		this.Stocks = Stocks;
	}

	public String getStocks() {
		return this.Stocks;
	}

	public void setKeywords(String Keywords) {
		this.Keywords = Keywords;
	}

	public String getKeywords() {
		return this.Keywords;
	}

	public void setCountInners(int CountInners) {
		this.CountInners = CountInners;
	}

	public int getCountInners() {
		return this.CountInners;
	}

	public void setCountRelates(int CountRelates) {
		this.CountRelates = CountRelates;
	}

	public int getCountRelates() {
		return this.CountRelates;
	}

	public void setCountExperts(int CountExperts) {
		this.CountExperts = CountExperts;
	}

	public int getCountExperts() {
		return this.CountExperts;
	}

	public void setCountAsks(int CountAsks) {
		this.CountAsks = CountAsks;
	}

	public int getCountAsks() {
		return this.CountAsks;
	}

	public void setCountLemmas(int CountLemmas) {
		this.CountLemmas = CountLemmas;
	}

	public int getCountLemmas() {
		return this.CountLemmas;
	}

	public void setCountTracks(int CountTracks) {
		this.CountTracks = CountTracks;
	}

	public int getCountTracks() {
		return this.CountTracks;
	}

	public void setCountScenes(int CountScenes) {
		this.CountScenes = CountScenes;
	}

	public int getCountScenes() {
		return this.CountScenes;
	}

	public void setCountVotes(int CountVotes) {
		this.CountVotes = CountVotes;
	}

	public int getCountVotes() {
		return this.CountVotes;
	}

	public void setCountStocks(int CountStocks) {
		this.CountStocks = CountStocks;
	}

	public int getCountStocks() {
		return this.CountStocks;
	}

	public void setNewsBody(String NewsBody) {
		this.NewsBody = NewsBody;
	}

	public String getNewsBody() {
		return this.NewsBody;
	}

	@Override
	public String toString() {
		return "NewsEntity [NewsID=" + NewsID + ", NewsType=" + NewsType
				+ ", NewsTitle=" + NewsTitle + ", ChannelRoot=" + ChannelRoot
				+ ", ChannelID=" + ChannelID + ", EntityPath=" + EntityPath
				+ ", CreaterName=" + CreaterName + ", CreateDate=" + CreateDate
				+ ", LastDate=" + LastDate + ", NewsLength=" + NewsLength
				+ ", NewsNotes=" + NewsNotes + ", NewsThumbs=" + NewsThumbs
				+ ", NewsSource=" + NewsSource + ", NewsAuthor=" + NewsAuthor
				+ ", OuterUrl=" + OuterUrl + ", VideoUrl=" + VideoUrl
				+ ", UniqueTag=" + UniqueTag + ", Tags=" + Tags
				+ ", NewsAddons=" + NewsAddons + ", LiveLink=" + LiveLink
				+ ", LiveAddress=" + LiveAddress + ", LiveState=" + LiveState
				+ ", NewsCover=" + NewsCover + ", Stocks=" + Stocks
				+ ", Keywords=" + Keywords + ", CountInners=" + CountInners
				+ ", CountRelates=" + CountRelates + ", CountExperts="
				+ CountExperts + ", CountAsks=" + CountAsks + ", CountLemmas="
				+ CountLemmas + ", CountTracks=" + CountTracks
				+ ", CountScenes=" + CountScenes + ", CountVotes=" + CountVotes
				+ ", CountStocks=" + CountStocks + ", NewsBody=" + NewsBody
				+ "]";
	}

}
