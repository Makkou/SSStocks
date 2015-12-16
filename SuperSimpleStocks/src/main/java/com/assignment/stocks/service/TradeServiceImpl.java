package com.assignment.stocks.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.assignment.stocks.model.trade.Trade;
import com.assignment.stocks.model.trade.TradeTimestampReverseOrder;

public class TradeServiceImpl implements TradeService {

	/**
	 * The list of trade by stock symbol.
	 */
	private Map<String, List<Trade>> tradeMap = new HashMap<String, List<Trade>>();

	public void addTrade(Trade tradeRecord) {
		if (!getTradeMap().containsKey(tradeRecord.getStockSymbol())) {
			getTradeMap().put(tradeRecord.getStockSymbol(), new ArrayList<Trade>());
		}
		getTradeMap().get(tradeRecord.getStockSymbol()).add(tradeRecord);
	}

	public Double calculateVolumeWeightedStockPrice(String stockSymbol, Integer numberOfSeconds) {
		List<Trade> latestTradeList = getLatestTrades(getTradeListByStockSymbol(stockSymbol), numberOfSeconds);

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

	public List<Trade> getTradeListByStockSymbol(String stockSymbol) {
		return (getTradeMap().containsKey(stockSymbol)) ? getTradeMap().get(stockSymbol) : null;
	}

	public List<Trade> getLatestTrades(List<Trade> tradeList, int numberOfSeconds) {
		if (tradeList == null || tradeList.size() == 0) {
			return null;
		}

		List<Trade> resultList = new ArrayList<Trade>();
		Date startTime = new Date(new Date().getTime() - (numberOfSeconds * 1000));
		Collections.sort(tradeList, new TradeTimestampReverseOrder<Trade>());
		for (Trade trade : tradeList) {
			if (trade.getTimestamp().before(startTime)) {
				break;
			}
			resultList.add(trade);
		}

		return resultList;
	}

	public Double getLatestPrice(String stockSymbol) {
		return getTradeMap().containsKey(stockSymbol) ? getLatestPrice(getTradeMap().get(stockSymbol)) : null;
	}

	public Double getLatestPrice(List<Trade> tradeList) {
		if (tradeList == null || tradeList.size() == 0) {
			return 0d;
		}

		Collections.sort(tradeList, new TradeTimestampReverseOrder<Trade>());
		return tradeList.get(0).getPrice();
	}

	public Map<String, List<Trade>> getTradeMap() {
		return tradeMap;
	}
}
