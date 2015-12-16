package com.assignment.stocks.model.stock;

/**
 * Common Stock details
 */
public class CommonStock extends AbstractStock {
	{ // Initializing Stock Type
		this.setType(StockType.COMMON);
	}

	public CommonStock() {
		super();
	}

	public CommonStock(String stockSymbol, Double lastDividend, Double parValue) {
		this.setSymbol(stockSymbol);
		this.setLastDividend(lastDividend);
		this.setParValue(parValue);
	}

	@Override
	public Double getDividendYield(Double marketPrice) {
		if (marketPrice == null || marketPrice == 0.0) {
			throw new IllegalArgumentException("marketPrice is zero or null.");
		}

		if (this.getLastDividend() == null) {
			return 0.0;
		}
		return this.getLastDividend() / marketPrice;
	}

}
