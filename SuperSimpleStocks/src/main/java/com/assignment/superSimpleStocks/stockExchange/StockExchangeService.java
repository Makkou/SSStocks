package com.assignment.superSimpleStocks.stockExchange;

import java.util.Map;

import com.assignment.superSimpleStocks.exception.StockException;
import com.assignment.superSimpleStocks.exception.StockNotFoundException;
import com.assignment.superSimpleStocks.stock.Stock;
import com.assignment.superSimpleStocks.stock.StockType;

/**
 * the Stock Exchange Service. <br>
 * All number values in pennies
 *
 */
public interface StockExchangeService {

	/**
	 * Add a stock details to the available stocks
	 * 
	 * @param stock
	 */
	void addStock(Stock stock);

	void addStock(String stockSymbol, StockType stockType, Integer lastDividend, Double fixedDividend,
			Integer parValue);

	public Map<String, Stock> getStocks();

	public Stock getStockBySymbol(String stockSymbol) throws StockNotFoundException;

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
	 *            the price (in penny) to use to calculate the dividend yield
	 * @return The dividend
	 * @throws StockException
	 */
	Double calculateDividendYield(String stockSymbol, Integer marketPrice) throws StockException;

	/**
	 * the Price/Earnings Ratio (P/E Ratio) = Market Price / Dividend
	 * 
	 * @param stockSymbol
	 *            * the stock symbol
	 * @param marketPrice
	 *            the price (in penny) to use to calculate the P/E Ratio
	 * @return
	 * @throws StockException
	 */
	Double calculatePriceEarningsRatio(String stockSymbol, Integer marketPrice) throws StockException;

	/**
	 * record a Buy trading on a stock.
	 * 
	 * @param stockSymbol
	 * @param tradeQuantity
	 * @param tradePrice
	 *            in penny
	 * @throws StockNotFoundException
	 */
	void buyStock(String stockSymbol, Integer tradeQuantity, Integer tradePrice) throws StockNotFoundException;

	/**
	 * record a Sell trading on a stock.
	 * 
	 * @param stockSymbol
	 * @param tradeQuantity
	 * @param tradePrice
	 *            in penny
	 * @throws StockNotFoundException
	 */
	void sellStock(String stockSymbol, Integer tradeQuantity, Integer tradePrice) throws StockNotFoundException;

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