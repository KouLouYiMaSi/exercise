package com.huo.demos.kafka;

import java.io.Serializable;

public class StockQuotationInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String stockCode;
    private String stockName;
    private long tradeTime;
    private float preClosePrice;
    private float currentPrice;
    private float highPrice;
    private float lowPrice;
    private float openPrice;

    public float getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(float openPrice) {
        this.openPrice = openPrice;
    }

    public String getStockCode() {
        return stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public long getTradeTime() {
        return tradeTime;
    }

    public float getPreClosePrice() {
        return preClosePrice;
    }

    public float getCurrentPrice() {
        return currentPrice;
    }

    public float getHighPrice() {
        return highPrice;
    }

    public float getLowPrice() {
        return lowPrice;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public void setTradeTime(long tradeTime) {
        this.tradeTime = tradeTime;
    }

    public void setPreClosePrice(float preClosePrice) {
        this.preClosePrice = preClosePrice;
    }

    public void setCurrentPrice(float currentPrice) {
        this.currentPrice = currentPrice;
    }

    public void setHighPrice(float highPrice) {
        this.highPrice = highPrice;
    }

    @Override
    public String toString() {
        return "StockQuotationInfo [stockCode=" + stockCode + ", stockName=" + stockName + ", tradeTime=" + tradeTime
                + ", preClosePrice=" + preClosePrice + ", currentPrice=" + currentPrice + ", highPrice=" + highPrice
                + ", lowPrice=" + lowPrice + ", openPrice=" + openPrice + "]";
    }

    public void setLowPrice(float lowPrice) {
        this.lowPrice = lowPrice;
    }

}
