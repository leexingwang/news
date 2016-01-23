package com.example.playpic.netbase;

public class NetworkConstant {
	// 上证指数
	public static String Shanghai_Composite_IndexStock = "000001";
	// 深证指数
	public static String Shenzhen_Stock_Index = "399001";
	// 创业板指数
	public static String GEM_Index_Stock = "399006";

	public static String STOCK_HOME_URL = "http://quote.api.yicai.com/quotehandler/report?action=";

	public static String STOCK_HOME_URL_Column = "secucode,stockCode,secuname,tradeDate,marketType,securityType,snapTime,high,low,open,close,volume,value,peratio,upDown,upDownPer,turnOver,previousClose,innerVolume,outterVolume,bid1,bidVolume1,ask1,askVolume1";

	/**
	 * 股票接口key 熊股票key
	 */
	public static String STOCKKEY = "c8b7d83742c2";
}
