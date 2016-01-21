package com.example.qianlong.bean;

import java.io.Serializable;

/**
 * <h1>ʵ�����Ŷ�ȡ ������ʵ������</h1>
 * <p>
 * NewsID int ����ID
 * <p>
 * NewsType int 10=���ţ�11=ͼ����12=��Ƶ��13=������14=ר�⣻15=ֱ����16=ͨ����17=������18=����᣻19=���¼�
 * 110=����������111=ͼ������
 * ��112=��Ƶ������113=����������114=ר��������115=ֱ��������116=ͨ��������117=����������118=�����������119=���¼�����
 * <p>
 * NewsTitle string ����
 * <p>
 * ChannelRoot int ����������һ����ĿID
 * <p>
 * ChannelID int ����������������ĿID
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
 * LiveLink string ����Ǵ��¼�����Ϊֱ������ַ�������LiveDateһ���ж�ǰ��ҳ���Ƿ���Ҫչʾ������ LiveDate Datetime
 * ����Ǵ��¼�����Ϊֱ���Ŀ�ʼʱ�䣬������������Ϊ�գ�ʹ��ǰһ���ж�null
 * <p>
 * LiveAddress string ����Ǵ��¼���������������Ϊ���췽���л�ص����Ϣ
 * <p>
 * LiveState int ֱ��״̬��0=δ��ʼ��1=�����У�2=�ѽ�����
 * ��״̬��Ա�����������ͼ��ֱ��������Ӱ�죬�ѽ���=ͼ��ֱ�����µ������£�δ��ʼ�������=ͼ��ֱ�����µ�������
 * <p>
 * NewsCover string ������ҳ��Ҫ��ȫ����ͼ������Ϊ�գ�����ֻ��APP��Ҫ����ֶ� Stocks json �����ŵĹ�����Ʊ��Ϣ��json��ʽ
 * <p>
 * Keywords string �ؼ��֣������Ƕ���������ÿո�ָ�
 * <p>
 * CountInners int ���������õ�ͼ������Ƶ�����������Ϊ0�ٵ��ö�ȡ���÷�����ȡ��ϸ��������Ϣ
 * <p>
 * CountRelates int ��������ص����š�ͼ������Ƶ����
 * <p>
 * CountExperts int ������ר������ֻ��ָ����ר�ҽ�����ܻش��û�����
 * <p>
 * CountAsks int �������û��������� CountLemmas int ������֪ʶ������������� CountTracks int
 * ������ʱ�����¼����� CountScenes int ������ͼ��ֱ��������
 * <p>
 * CountVotes int ������ͶƱ�������
 * <p>
 * CountStocks int �����Ź�����Ʊ��������ʵͨ�������и���json�ֶ�Stocks��Ҳ�������������
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
	 * 10=���ţ�11=ͼ����12=��Ƶ��13=������14=ר�⣻15=ֱ����16=ͨ����17=������18=����᣻19=���¼�
	 * 110=����������111=ͼ������
	 * ��112=��Ƶ������113=����������114=ר��������115=ֱ��������116=ͨ��������117=����������118
	 * =�����������119=���¼�����
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
