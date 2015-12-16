package com.assignment.stocks.service;

import java.util.Map;

import com.assignment.stocks.exception.StockException;
import com.assignment.stocks.exception.StockNotFoundException;
import com.assignment.stocks.model.stock.Stock;
import com.assignment.stocks.model.stock.StockType;

/**
 * The Stock Exchange Service. <br>
 */
public interface ExchangeService {

	void addStock(Stock stock);

	void addStock(String stockSymbol, StockType stockType, Double lastDividend, Double fixedDividend, Double parValue);

	Map<String, Stock> getStockMap();

	Stock getStockBySymbol(String stockSymbol) throws StockNotFoundException;

	/**
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
	 */
	Double calculatePriceEarningsRatio(String stockSymbol, Double marketPrice) throws StockException;

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
	 * for a given numberOfMinutes, the Volume Weighted Stock Price = sum(Trade
	 * Price(i) × Quantity(i)) / sum(Quantity(i))
	 * 
	 * @param stockSymbol
	 * @param numberOfMinutes
	 *            calculate the VWSP for the last numberOfMinutes.
	 * @return
	 * @throws StockNotFoundException
	 */
	Double calculateVolumeWeightedStockPrice(String stockSymbol, Integer numberOfMinutes) throws StockNotFoundException;

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