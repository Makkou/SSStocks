package com.assignment.stocks.stock;

import java.util.Date;
import java.util.NavigableMap;

import com.assignment.stocks.trade.Trade;

public interface Stock {

	String getSymbol();

	/**
	 * record a Buy trade
	 * 
	 * @param stockQuantity
	 * @param tradePrice
	 *            in penny
	 */
	void buy(Integer stockQuantity, Integer tradePrice);

	/**
	 * record a Sell trade
	 * 
	 * @param stockQuantity
	 * @param price
	 *            in penny
	 */
	void sell(Integer stockQuantity, Integer price);

	/**
	 * For a given market price, the Dividend Yield is :
	 * <ul>
	 * <li>For common stock: Last Dividend / Market Price
	 * <li>For preferred stock: Fixed Dividend * Par Value / Market Price
	 * </ul>
	 * 
	 * @param marketPrice
	 *            the price (in penny) to use to calculate the dividend yield
	 * 
	 * @return Dividend Yield
	 * @throws IllegalArgumentException
	 *             when the marketPrice is null or Zero.
	 */
	Double calculateDividendYield(Integer marketPrice) throws IllegalArgumentException;

	/**
	 * the Price/Earnings Ratio = Market Price / Dividend
	 * 
	 * @param marketPrice
	 *            the price (in penny) to use to calculate the P/E Ratio
	 * @return the P/E Ratio (null when the Dividend is 0).
	 */
	Double calculatePriceEarningsRatio(Integer marketPrice);

	/**
	 * for a given numberOfMinutes, the Volume Weighted Stock Price = sum(Trade
	 * Price(i) × Quantity(i)) / sum(Quantity(i))
	 * 
	 * @param numberOfMinutes
	 *            calculate the VWSP for the last numberOfMinutes.
	 * @return
	 */
	Double calculateVolumeWeightedStockPrice(Integer numberOfMinutes);

	/**
	 * get the latest stock price.
	 * 
	 * @return the price (in penny)
	 */
	Integer getPrice();

	public NavigableMap<Date, Trade> getStockTrades();

}