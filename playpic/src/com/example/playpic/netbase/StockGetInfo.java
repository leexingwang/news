package com.example.playpic.netbase;

import java.io.Serializable;

public class StockGetInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String action;
	public String stockcode;
	public String market;

	
	
	public StockGetInfo(String action, String stockcode, String market) {
		super();
		this.action = action;
		this.stockcode = stockcode;
		this.market = market;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getStockcode() {
		return stockcode;
	}

	public void setStockcode(String stockcode) {
		this.stockcode = stockcode;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

}
