package com.assignment.stocks.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.assignment.stocks.exception.StockException;
import com.assignment.stocks.exception.StockNotFoundException;
import com.assignment.stocks.model.stock.CommonStock;
import com.assignment.stocks.model.stock.PreferredStock;
import com.assignment.stocks.model.stock.Stock;
import com.assignment.stocks.model.stock.StockType;
import com.assignment.stocks.model.trade.Trade;
import com.assignment.stocks.model.trade.TradeDirection;

public class ExchangeServiceImpl implements ExchangeService {

	/**
	 * The stocks being traded within this exchange
	 */
	private Map<String, Stock> stockMap = new HashMap<String, Stock>();

	/**
	 * The list of trade by stock.
	 */
	private Map<String, List<Trade>> tradeMap = new HashMap<String, List<Trade>>();

	public void addStock(Stock stock) {
		getStockMap().put(stock.getSymbol(), stock);
	}

	public void addStock(String stockSymbol, StockType stockType, Double lastDividend, Double fixedDividend,
			Double parValue) {

		switch (stockType) {
		case COMMON:
			this.addStock(new CommonStock(stockSymbol, lastDividend, parValue));
			break;
		case PREFERRED:
			this.addStock(new PreferredStock(stockSymbol, lastDividend, parValue, fixedDividend));
			break;
		default:
			throw new IllegalArgumentException("Stock type " + stockType + " is not supported.");
		}
	}

	public Stock getStockBySymbol(String stockSymbol) throws StockNotFoundException {
		if (!getStockMap().containsKey(stockSymbol)) {
			throw new StockNotFoundException(stockSymbol + " not found in this exchange.");
		}

		return getStockMap().get(stockSymbol);
	}

	public void buyStock(String stockSymbol, Integer tradeQuantity, Double tradePrice) throws StockNotFoundException {
		if (!getStockMap().containsKey(stockSymbol)) {
			throw new StockNotFoundException(stockSymbol + " not found in this exchange.");
		}

		if (!getTradeMap().containsKey(stockSymbol)) {
			getTradeMap().put(stockSymbol, new ArrayList<Trade>());
		}

		getTradeMap().get(stockSymbol).add(new Trade(stockSymbol, TradeDirection.BUY, tradeQuantity, tradePrice));
	}

	public void sellStock(String stockSymbol, Integer tradeQuantity, Double tradePrice) throws StockNotFoundException {
		if (!getStockMap().containsKey(stockSymbol)) {
			throw new IllegalArgumentException(stockSymbol + " not found in this exchange.");
		}
		if (!getTradeMap().containsKey(stockSymbol)) {
			getTradeMap().put(stockSymbol, new ArrayList<Trade>());
		}

		getTradeMap().get(stockSymbol).add(new Trade(stockSymbol, TradeDirection.SELL, tradeQuantity, tradePrice));
	}

	public Double calculateDividendYield(String stockSymbol, Double marketPrice) throws StockException {
		return getStockBySymbol(stockSymbol).getDividendYield(marketPrice);
	}

	public Double calculatePriceEarningsRatio(String stockSymbol, Double marketPrice) throws StockException {
		return getStockBySymbol(stockSymbol).getPriceEarningsRatio(marketPrice);
	}

	public Double calculateVolumeWeightedStockPrice(String stockSymbol, Integer numberOfSeconds)
			throws StockNotFoundException {

		List<Trade> latestTradeList = getLatestTrades(getTradeMap().get(stockSymbol), numberOfSeconds);

		if (latestTradeList == null || latestTradeList.isEmpty()) {
			return 0d;
		}

		Double tradingValue = 0.0;
		Integer tradingVolume = 0;
		for (Trade trade : latestTradeList) {
			tradingValue += trade.getPrice() * trade.getQuantity();
			tradingVolume += trade.getQuantity();
		}

		return tradingValue / tradingVolume;
	}

	/**
	 * Get the trade list of the last given numberOfMinutes.
	 * 
	 * @param numberOfSeconds
	 * @return
	 */
	private List<Trade> getLatestTrades(List<Trade> trades, int numberOfSeconds) {
		if (trades == null) {
			return null;
		}

		Date startTime = new Date(new Date().getTime() - (numberOfSeconds * 1000));

		Collections.sort(trades, new Comparator<Trade>() {
			public int compare(Trade t1, Trade t2) {
				return t2.getTimestamp().compareTo(t1.getTimestamp());
			}
		});
		List<Trade> tradeList = new ArrayList<Trade>();
		for (Trade trade : trades) {
			if (trade.getTimestamp().before(startTime)) {
				break;
			}
			tradeList.add(trade);
		}

		return tradeList;
	}

	public Double getAllShareIndex() {
		if (getStockMap().size() == 0) {
			return 0.0;
		}

		double totalPriceProduct = 1d;
		for (Stock stock : getStockMap().values()) {
			Double stockPrice = getLatestPrice(stock.getSymbol());
			if (stockPrice != null) {
				totalPriceProduct *= stockPrice;
			}
		}
		return Math.pow(totalPriceProduct, 1.0 / getStockMap().size());
	}

	private Double getLatestPrice(String stockSymbol) {
		if (!getTradeMap().containsKey(stockSymbol)) {
			throw new IllegalArgumentException(stockSymbol + " not found in this exchange.");
		}

		List<Trade> trades = getTradeMap().get(stockSymbol);
		if (trades == null) {
			return 0d;
		}

		Collections.sort(trades);
		return trades.get(0).getPrice();

	}

	public Map<String, Stock> getStockMap() {
		return stockMap;
	}

	public Map<String, List<Trade>> getTradeMap() {
		return tradeMap;
	}
}
