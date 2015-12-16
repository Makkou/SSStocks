package com.assignment.stocks.service;

import java.util.List;
import java.util.Map;

import com.assignment.stocks.model.trade.Trade;

public interface TradeService {

	void addTrade(Trade tradeRecord);

	Map<String, List<Trade>> getTradeMap();

	List<Trade> getTradeListByStockSymbol(String stockSymbol);

	/**
	 * Get the trade list of the last given numberOfMinutes.
	 * 
	 * @param tradeList
	 * @param numberOfSeconds
	 * @return
	 */
	List<Trade> getLatestTrades(List<Trade> tradeList, int numberOfSeconds);

	/**
	 * Get the latest price of a given trade list.
	 * 
	 * @param tradeList
	 * @return
	 */
	Double getLatestPrice(List<Trade> tradeList);

	Double getLatestPrice(String stockSymbol);

	/**
	 * 
	 * for a given numberOfMinutes, the Volume Weighted Stock Price = sum(Trade
	 * Price(i) × Quantity(i)) / sum(Quantity(i))
	 * 
	 * @param stockSymbol
	 * @param numberOfSeconds
	 *            calculate the VWSP for the last numberOfSeconds.
	 * @return
	 */
	Double calculateVolumeWeightedStockPrice(String stockSymbol, Integer numberOfSeconds);

}