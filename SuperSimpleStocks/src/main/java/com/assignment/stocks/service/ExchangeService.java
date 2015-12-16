package com.assignment.stocks.service;

import com.assignment.stocks.exception.StockNotFoundException;

/**
 * The Stock Exchange Service. <br>
 */
public interface ExchangeService {

	StockService getStockService();

	TradeService getTradeService();

	/**
	 * record a Buy trading on a stock.
	 * 
	 * @param stockSymbol
	 * @param tradeQuantity
	 * @param tradePrice
	 * @throws StockNotFoundException
	 */
	void buyStock(String stockSymbol, Integer tradeQuantity, Double tradePrice) throws StockNotFoundException;

	/**
	 * record a Sell trading on a stock.
	 * 
	 * @param stockSymbol
	 * @param tradeQuantity
	 * @param tradePrice
	 * @throws StockNotFoundException
	 */
	void sellStock(String stockSymbol, Integer tradeQuantity, Double tradePrice) throws StockNotFoundException;

	/**
	 * Calculate the GBCE All Share Index using the geometric mean of prices for
	 * all stocks
	 * 
	 * @param stocks
	 * 
	 * @return The GBCE All Share Index
	 */
	Double getAllShareIndex();

}