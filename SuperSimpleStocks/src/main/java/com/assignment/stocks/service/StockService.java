package com.assignment.stocks.service;

import java.util.Map;

import com.assignment.stocks.exception.StockException;
import com.assignment.stocks.exception.StockNotFoundException;
import com.assignment.stocks.model.stock.Stock;
import com.assignment.stocks.model.stock.StockType;

public interface StockService {

	void addStock(Stock stock);

	void addStock(String stockSymbol, StockType stockType, Double lastDividend, Double fixedDividend, Double parValue);

	Map<String, Stock> getStockMap();

	Stock getStockBySymbol(String stockSymbol) throws StockNotFoundException;

	boolean haveSymbol(String stockSymbol);

	/**
	 * 
	 * For a given a market price, the Dividend Yield is :
	 * <ul>
	 * <li>For common stock: Last Dividend / Market Price
	 * <li>For preferred stock: Fixed Dividend * Par Value / Market Price
	 * </ul>
	 * 
	 * @param stockSymbol
	 *            the stock symbol
	 * @param marketPrice
	 *            the price to use to calculate the dividend yield
	 * @return The dividend
	 * @throws StockException
	 */
	Double calculateDividendYield(String stockSymbol, Double marketPrice) throws StockException;

	/**
	 * the Price/Earnings Ratio (P/E Ratio) = Market Price / Dividend
	 * 
	 * @param stockSymbol
	 *            the stock symbol
	 * @param marketPrice
	 *            the price to use to calculate the P/E Ratio
	 * @return
	 * @throws StockException
	 * 
	 */
	Double calculatePriceEarningsRatio(String stockSymbol, Double marketPrice) throws StockException;

}