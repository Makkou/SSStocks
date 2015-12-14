package com.assignment.superSimpleStocks.stock;

import java.util.Date;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.assignment.superSimpleStocks.TestApp;
import com.assignment.superSimpleStocks.trade.Trade;
import com.assignment.superSimpleStocks.trade.TradeDirection;

public class StockImpl extends StockData implements Stock {

	private static final Logger logger = Logger.getLogger(TestApp.class);

	private NavigableMap<Date, Trade> stockTrades;

	public StockImpl() {
		setStockTrades(new TreeMap<Date, Trade>());
	}

	public StockImpl(String stockSymbol, StockType stockType, Integer lastDividend, Double fixedDividend,
			Integer parValue) {
		setSymbol(stockSymbol);
		setStockType(stockType);
		setLastDividend(lastDividend);
		setFixedDividend(fixedDividend);
		setParValue(parValue);
		setStockTrades(new TreeMap<Date, Trade>());
	}

	public void buy(Integer stockQuantity, Integer tradePrice) {
		getStockTrades().put(new Date(), new Trade(TradeDirection.BUY, stockQuantity, tradePrice));
	}

	public void sell(Integer stockQuantity, Integer price) {
		getStockTrades().put(new Date(), new Trade(TradeDirection.SELL, stockQuantity, price));
	}

	public Double calculateDividendYield(Integer marketPrice) throws IllegalArgumentException {
		if (marketPrice == 0.0) {
			throw new IllegalArgumentException("marketPrice should not be zero or null.");
		}

		double dividend = 0d;
		switch (this.getStockType()) {
		case COMMON:
			Integer lastDividend = this.getLastDividend();
			if (lastDividend != null) {
				dividend = lastDividend.doubleValue() / marketPrice;
			}
			break;
		case PREFERRED:
			Double fixedDividend = this.getFixedDividend();
			if (fixedDividend != null) {
				dividend = fixedDividend * this.getParValue() / marketPrice;
			}
			break;
		}

		return dividend;
	}

	public Double calculatePriceEarningsRatio(Integer marketPrice) {
		double dividend = this.calculateDividendYield(marketPrice);
		if (dividend == 0) {
			logger.info("P/E Ratio can't be calculated because StockData Dividend is zero.");
			return null;
		}
		return marketPrice / dividend;
	}

	public Double calculateVolumeWeightedStockPrice(Integer numberOfMinutes) {

		SortedMap<Date, Trade> latestTrades = getLatestStockTrades(numberOfMinutes);

		if (latestTrades == null || latestTrades.isEmpty()) {
			return 0d;
		}

		Double tradingValue = 0.0;
		Integer tradingVolume = 0;
		for (Trade trade : latestTrades.values()) {
			tradingValue += trade.getPrice() * trade.getQuantity();
			tradingVolume += trade.getQuantity();
		}

		return tradingValue / tradingVolume;
	}

	public Integer getPrice() {
		Entry<Date, Trade> latestTrade = this.getStockTrades().lastEntry();
		return latestTrade == null ? null : latestTrade.getValue().getPrice();
	}

	/**
	 * Get the trades of the last given numberOfMinutes.
	 * 
	 * @param numberOfMinutes
	 * @return
	 */
	private SortedMap<Date, Trade> getLatestStockTrades(int numberOfMinutes) {
		Date startTime = new Date(new Date().getTime() - (numberOfMinutes * 60 * 1000));
		return this.getStockTrades().tailMap(startTime);
	}

	public NavigableMap<Date, Trade> getStockTrades() {
		return stockTrades;
	}

	private void setStockTrades(NavigableMap<Date, Trade> stockTrades) {
		this.stockTrades = stockTrades;
	}

}