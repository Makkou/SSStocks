package com.assignment.stocks.service;

import java.util.HashMap;
import java.util.Map;

import com.assignment.stocks.exception.StockException;
import com.assignment.stocks.exception.StockNotFoundException;
import com.assignment.stocks.model.stock.CommonStock;
import com.assignment.stocks.model.stock.PreferredStock;
import com.assignment.stocks.model.stock.Stock;
import com.assignment.stocks.model.stock.StockType;

public class StockServiceImpl implements StockService {

	/**
	 * The stocks being traded within this exchange
	 */
	private Map<String, Stock> stockMap = new HashMap<String, Stock>();

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
		if (!haveSymbol(stockSymbol)) {
			throw new StockNotFoundException(stockSymbol + " not found in this exchange.");
		}

		return getStockMap().get(stockSymbol);
	}

	public boolean haveSymbol(String stockSymbol) {
		return getStockMap().containsKey(stockSymbol);
	}

	public Double calculateDividendYield(String stockSymbol, Double marketPrice) throws StockException {
		return getStockBySymbol(stockSymbol).getDividendYield(marketPrice);
	}

	public Double calculatePriceEarningsRatio(String stockSymbol, Double marketPrice) throws StockException {
		return getStockBySymbol(stockSymbol).getPriceEarningsRatio(marketPrice);
	}

	public Map<String, Stock> getStockMap() {
		return stockMap;
	}
}
