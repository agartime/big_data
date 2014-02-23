package com.agartime.utad.hbase.nasdaq;

//------------
//		Fichero transformado
//		------------
//
//			NASDAQ_daily_prices_subset_RK-emp.tsv
//
//		------------
//		Datos fichero transformado
//		------------
//
//			stock_symbol,
//			stock_price_open,
//			stock_price_high,
//			stock_price_low,
//			stock_price_close,
//			stock_volume,
//			stock_price_adj_close,
//			timstamp
//			
//			

 
public class EmpresaStockValues {
	
	private String stockSymbol;
	private float stockPriceOpen;
	private float stockPriceHigh;
	private float stockPriceLow;
	private float stockPriceClose;
	private int stockVolume;
	private float stockPriceAdjClose;
	private long timestamp;
	
	public EmpresaStockValues(String stockSymbol,
			float stockPriceOpen,
			float stockPriceHigh,
			float stockPriceLow,
			float stockPriceClose,
			int stockVolume,
			float stockPriceAdjClose,
			long timestamp) {
		super();
		this.timestamp = timestamp;
		this.stockPriceOpen = stockPriceOpen;
		this.stockPriceLow = stockPriceLow;
		this.stockPriceAdjClose = stockPriceAdjClose;
		this.stockPriceClose = stockPriceClose;
		this.stockVolume = stockVolume;
		this.stockPriceHigh = stockPriceHigh;
		this.stockSymbol = stockSymbol;
		
	}
	
	public EmpresaStockValues(String[] input) {
		if (input.length == 8) {
			this.stockSymbol=input[0];
			this.stockPriceOpen=Float.parseFloat(input[1]);
			this.stockPriceHigh=Float.parseFloat(input[2]);
			this.stockPriceLow=Float.parseFloat(input[3]);
			this.stockPriceClose=Float.parseFloat(input[4]);
			this.stockVolume=Integer.parseInt(input[5]);
			this.stockPriceAdjClose=Float.parseFloat(input[6]);
			this.timestamp=Long.parseLong(input[7]);
		}
	}

	public EmpresaStockValues() {
	}
	
	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public float getStockPriceOpen() {
		return stockPriceOpen;
	}

	public void setStockPriceOpen(float stockPriceOpen) {
		this.stockPriceOpen = stockPriceOpen;
	}

	public float getStockPriceHigh() {
		return stockPriceHigh;
	}

	public void setStockPriceHigh(float stockPriceHigh) {
		this.stockPriceHigh = stockPriceHigh;
	}

	public float getStockPriceLow() {
		return stockPriceLow;
	}

	public void setStockPriceLow(float stockPriceLow) {
		this.stockPriceLow = stockPriceLow;
	}

	public float getStockPriceClose() {
		return stockPriceClose;
	}

	public void setStockPriceClose(float stockPriceClose) {
		this.stockPriceClose = stockPriceClose;
	}

	public int getStockVolume() {
		return stockVolume;
	}

	public void setStockVolume(int stockVolume) {
		this.stockVolume = stockVolume;
	}

	public float getStockPriceAdjClose() {
		return stockPriceAdjClose;
	}

	public void setStockPriceAdjClose(float stockPriceAdjClose) {
		this.stockPriceAdjClose = stockPriceAdjClose;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
}
