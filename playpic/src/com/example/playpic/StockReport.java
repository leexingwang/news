package com.example.playpic;

import java.io.Serializable;

/**
 * 
 * @author lixingwang
 *
 */
public class StockReport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int ask1;

	private int askVolume1;

	private int bid1;

	private int bidVolume1;

	private double close;

	private double high;

	private int innerVolume;

	private double low;

	private int marketType;

	private double open;

	private int outterVolume;

	private int peratio;

	private double previousClose;

	private String secucode;

	private String secuname;

	private int securityType;

	private int snapTime;

	private String stockCode;

	private int tradeDate;

	private int turnOver;

	private double upDown;

	private double upDownPer;

	private double value;

	private int volume;

	public void setAsk1(int ask1) {
		this.ask1 = ask1;
	}

	public int getAsk1() {
		return this.ask1;
	}

	public void setAskVolume1(int askVolume1) {
		this.askVolume1 = askVolume1;
	}

	public int getAskVolume1() {
		return this.askVolume1;
	}

	public void setBid1(int bid1) {
		this.bid1 = bid1;
	}

	public int getBid1() {
		return this.bid1;
	}

	public void setBidVolume1(int bidVolume1) {
		this.bidVolume1 = bidVolume1;
	}

	public int getBidVolume1() {
		return this.bidVolume1;
	}

	public void setClose(double close) {
		this.close = close;
	}

	public double getClose() {
		return this.close;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getHigh() {
		return this.high;
	}

	public void setInnerVolume(int innerVolume) {
		this.innerVolume = innerVolume;
	}

	public int getInnerVolume() {
		return this.innerVolume;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getLow() {
		return this.low;
	}

	public void setMarketType(int marketType) {
		this.marketType = marketType;
	}

	public int getMarketType() {
		return this.marketType;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public double getOpen() {
		return this.open;
	}

	public void setOutterVolume(int outterVolume) {
		this.outterVolume = outterVolume;
	}

	public int getOutterVolume() {
		return this.outterVolume;
	}

	public void setPeratio(int peratio) {
		this.peratio = peratio;
	}

	public int getPeratio() {
		return this.peratio;
	}

	public void setPreviousClose(double previousClose) {
		this.previousClose = previousClose;
	}

	public double getPreviousClose() {
		return this.previousClose;
	}

	public void setSecucode(String secucode) {
		this.secucode = secucode;
	}

	public String getSecucode() {
		return this.secucode;
	}

	public void setSecuname(String secuname) {
		this.secuname = secuname;
	}

	public String getSecuname() {
		return this.secuname;
	}

	public void setSecurityType(int securityType) {
		this.securityType = securityType;
	}

	public int getSecurityType() {
		return this.securityType;
	}

	public void setSnapTime(int snapTime) {
		this.snapTime = snapTime;
	}

	public int getSnapTime() {
		return this.snapTime;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getStockCode() {
		return this.stockCode;
	}

	public void setTradeDate(int tradeDate) {
		this.tradeDate = tradeDate;
	}

	public int getTradeDate() {
		return this.tradeDate;
	}

	public void setTurnOver(int turnOver) {
		this.turnOver = turnOver;
	}

	public int getTurnOver() {
		return this.turnOver;
	}

	public void setUpDown(double upDown) {
		this.upDown = upDown;
	}

	public double getUpDown() {
		return this.upDown;
	}

	public void setUpDownPer(double upDownPer) {
		this.upDownPer = upDownPer;
	}

	public double getUpDownPer() {
		return this.upDownPer;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getValue() {
		return this.value;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public int getVolume() {
		return this.volume;
	}

}
