package com.example.qianlong.constants;

public class Constants {

	// 频道管理常量
	public static final int CHANGE_CHANNEL = 1000;
	public static final String CHANGE_BACK_TYPE = "BackType";
	public static final String CHANGE_CLICK_NUMBER = "ClickNum";
	// 点击频道返回
	public static final int CHANGE_BACK_TYPE_CLICK = 1;
	// 按返回键返回
	public static final int CHANGE_BACK_TYPE_BACKKEY = 2;

	// 循环焦点图时间
	public static final int LOOP_TIME = 3000;
	/**
	 * 获取新闻常量
	 */

	public static final String GetNewsALL_KEY = "aiB6Fkiusod0GMTp";

	// 二级栏目URL
	public static final String GetNewsListByChannel_URL = "http://10.26.130.222:8001/handler/app/GetNewsListByChannelId.ashx";

	// 二级栏目KEY
	public static final String GetNewsListByChannel_KEY = "aiB6Fkiusod0GMTp";

	// 获取栏目内新闻个数URL
	public static final String GetNewsCountByChannel_URL = "http://10.26.130.222:8001/handler/app/GetNewsCountByChannel.ashx";

	// 获取栏目内新闻个数KEY
	public static final String GetNewsCountByChannel_KEY = "aiB6Fkiusod0GMTp";

	// 实体新闻读取URL
	public static final String GetNews_URL = "http://10.26.130.222:8001/handler/app/GetNews.ashx";

	// 实体新闻读取KEY
	public static final String GetNews_KEY = "aiB6Fkiusod0GMTp";

	// 获取实体新闻的引用URL
	public static final String GetNewsInners_URL = "http://10.26.130.222:8001/handler/app/GetNewsInners.ashx";

	// 获取实体新闻的引用KEY
	public static final String GetNewsInners_KEY = "aiB6Fkiusod0GMTp";

	// 获取实体新闻的相关新闻URL
	public static final String GetNewsRelates_URL = "http://10.26.130.222:8001/handler/app/GetNewsRelates.ashx";

	// 获取实体新闻的相关新闻KEY
	public static final String GetNewsRelates_KEY = "aiB6Fkiusod0GMTp";

	// 获取实体新闻的专家URL
	public static final String GetNewsExperts_URL = "http://10.26.130.222:8001/handler/app/GetNewsExperts.ashx";

	// 获取实体新闻的专家KEY
	public static final String GetNewsExperts_KEY = "aiB6Fkiusod0GMTp";

	// 获取实体新闻的问答URL
	public static final String GetNewsAsks_URL = "http://10.26.130.222:8001/handler/app/GetNewsAsks.ashx";

	// 获取实体新闻的问答KEY
	public static final String GetNewsAsks_KEY = "aiB6Fkiusod0GMTp";

	// 获取实体新闻的知识库词条URL
	public static final String GetNewsLemmas_URL = "http://10.26.130.222:8001/handler/app/GetNewsLemmas.ashx";

	// 获取实体新闻的知识库词条KEY
	public static final String GetNewsLemmas_KEY = "aiB6Fkiusod0GMTp";

	// 获取实体新闻的时间轴URL
	public static final String GetNewsTracks_URL = "http://10.26.130.222:8001/handler/app/GetNewsTracks.ashx";

	// 获取实体新闻的时间轴KEY
	public static final String GetNewsTracks_KEY = "aiB6Fkiusod0GMTp";

	// 获取实体新闻的图文URL
	public static final String GetNewsScenes_URL = "http://10.26.130.222:8001/handler/app/GetNewsScenes.ashx";

	// 获取实体新闻的图文KEY
	public static final String GetNewsScenes_KEY = "aiB6Fkiusod0GMTp";

	// 获取实体新闻的投票URL
	public static final String GetNewsVotes_URL = "http://10.26.130.222:8001/handler/app/GetNewsVotes.ashx";

	// 获取实体新闻的投票KEY
	public static final String GetNewsVotes_KEY = "aiB6Fkiusod0GMTp";

	// 快速按分页获取7x24直播URL
	public static final String GetLives_URL = "http://10.26.130.222:8001/handler/app/GetLivesByPaging.ashx";

	// 快速按分页获取7x24直播KEY
	public static final String GetLives_KEY = "aiB6Fkiusod0GMTp";

	// 获取单个资讯流URL
	public static final String GetLive_URL = "http://10.26.130.222:8001/handler/app/GetLiveById.ashx";

	// 获取单个资讯流KEY
	public static final String GetLive_KEY = "aiB6Fkiusod0GMTp";

	/**
	 * <p>
	 * 所有从接口获取的图片地址如果是 相对地址（请用是否http://开头先判断是否是相对地址） 请根据需要自己选择目录
	 * <p>
	 * CMSS专用图地址（95x55） 路径为 /uppics/news/cmss/
	 * <p>
	 * 原始版大图 路径为 /uppics/news/origin/
	 * <p>
	 * 专用封面图（裁剪过的） 路径为 /uppics/news/thumb/
	 * <p>
	 * 文件名不变通过换目录可以得到自己需要尺寸的图
	 * 
	 * <p>
	 * 如果不是相对地址，那么就直接使用该完整地址就行了（一般外部系统导入的图都是绝对地址），上面的各目录也是无效
	 */

	// 图片URL
	public static final String IMGURL = "http://10.26.130.222";

	// 图片类型：CMSS专用图地址（95x55） 路径
	public static final String IMGURL_CMSS = "/uppics/news/cmss/";

	// 图片类型：原始版大图 路径为
	public static final String IMGURL_ORIGIN = "/uppics/news/origin/";

	// 图片类型：专用封面图（裁剪过的）
	public static final String IMGURL_THUMB = "/uppics/news/thumb/";

	// 7*24小时直播常量
	public static final String TEXT_LIVE = "LIVE";

	/**
	 * <p>
	 * NewsType - 新闻类型
	 * <p>
	 * 类型 说明
	 * <p>
	 * int 新闻类型分为 实体新闻 和 跳连新闻 两大类，有一个字段 IsEntity 用来快速判断是否是实体新闻
	 * 实体类型：实体类型+100=对应的跳链类型
	 * <p>
	 * 10=新闻；11=图集；12=视频；13=外链；14=专题；15=直播；16=通栏；17=报名；18=见面会；19=大事件
	 * 跳链类型：跳链类型-100=对应的实体类型
	 * <p>
	 * 110=新闻跳链；111=图集跳链；112=视频跳链；113=外链跳链；114=专题跳链；115=直播跳链；116=通栏跳链；117=报名跳链；
	 * 118=见面会跳链；119=大事件跳链
	 * <p>
	 * EntityPath 为实体新闻的 年/月 格式，用来拼接完整url用，如果是跳链则为实体新闻的年/月
	 */

	/**
	 * 新闻；
	 */
	public static final int NewsType_XINWEN = 10;
	/**
	 * 图集；
	 */
	public static final int NewsType_TUJI = 11;
	/**
	 * 视频；
	 */
	public static final int NewsType_SHIPING = 12;
	/**
	 * 外链；
	 */
	public static final int NewsType_WAILIAN = 13;
	/**
	 * 专题；
	 */
	public static final int NewsType_ZHUANTI = 14;
	/**
	 * 直播；
	 */
	public static final int NewsType_ZHIBO = 15;
	/**
	 * 通栏；
	 */
	public static final int NewsType_TONGLAN = 16;
	/**
	 * 报名；
	 */
	public static final int NewsType_BAOMING = 17;
	/**
	 * 见面会；
	 */
	public static final int NewsType_JIANMIANHUI = 18;
	/**
	 * 大事件；
	 */
	public static final int NewsType_DASHIJIAN = 19;
	/**
	 * 新闻跳链；
	 */
	public static final int NewsType_TIAOLIAN_XINWEN = 110;
	/**
	 * 图集跳链；
	 */
	public static final int NewsType_TIAOLIAN_TUJI = 111;
	/**
	 * 视频跳链；
	 */
	public static final int NewsType_TIAOLIAN_SHIPING = 112;
	/**
	 * 外链跳链；
	 */
	public static final int NewsType_TIAOLIAN_WAILIAN = 113;
	/**
	 * 专题跳链；
	 */
	public static final int NewsType_TIAOLIAN_ZHUANTI = 114;
	/**
	 * 直播跳链；
	 */
	public static final int NewsType_TIAOLIAN_ZHIBO = 115;
	/**
	 * 通栏跳链；
	 */
	public static final int NewsType_TIAOLIAN_TONGLAN = 116;
	/**
	 * 报名跳链；
	 */
	public static final int NewsType_TIAOLIAN_BAOMING = 117;
	/**
	 * 见面会跳链；
	 */
	public static final int NewsType_TIAOLIAN_JIANMIANHUI = 118;
	/**
	 * 大事件跳链；
	 */
	public static final int NewsType_TIAOLIAN_DASHIJIAN = 119;
	/**
	 * 广告；
	 */
	public static final int NewsType_AD = 1000;

}
