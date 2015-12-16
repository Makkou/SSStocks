package com.assignment.stocks.model.stock;

public interface Stock {

	String getSymbol();

	void setSymbol(String symbol);

	StockType getType();

	void setType(StockType type);

	Double getLastDividend();

	void setLastDividend(Double lastDividend);

	Double getParValue();

	void setParValue(Double parValue);

	/**
	 * Calculate the Dividend Yield for a given market price.
	 * 
	 * @param marketPrice
	 * @return
	 */
	Double getDividendYield(Double marketPrice);

	/**
	 * the Price/Earnings Ratio = Market Price / Dividend
	 * 
	 * @param marketPrice
	 *            the price to use to calculate the P/E Ratio
	 * @return the P/E Ratio (null when the Dividend is 0).
	 */
	Double getPriceEarningsRatio(Double marketPrice);

}