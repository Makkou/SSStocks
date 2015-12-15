package com.assignment.stocks.stockExchange;

import java.util.HashMap;
import java.util.Map;

import com.assignment.stocks.exception.StockException;
import com.assignment.stocks.exception.StockNotFoundException;
import com.assignment.stocks.stock.Stock;
import com.assignment.stocks.stock.StockImpl;
import com.assignment.stocks.stock.StockType;

public class StockExchangeServiceImpl implements StockExchangeService {

	/**
	 * the stocks being traded within this exchange
	 */
	private Map<String, Stock> stocks = new HashMap<String, Stock>();

	public void addStock(Stock stock) {
		getStocks().put(stock.getSymbol(), stock);
	}

	public void addStock(String stockSymbol, StockType stockType, Integer lastDividend, Double fixedDividend,
			Integer parValue) {

		this.addStock(new StockImpl(stockSymbol, stockType, lastDividend, fixedDividend, parValue));
	}

	public Stock getStockBySymbol(String stockSymbol) throws StockNotFoundException {
		if (!getStocks().containsKey(stockSymbol)) {
			throw new StockNotFoundException(stockSymbol + " must be one of the not found in the available stocks.");
		}

		Stock stock = getStocks().get(stockSymbol);
		return stock;
	}

	public Double calculateDividendYield(String stockSymbol, Integer marketPrice) throws StockException {
		return getStockBySymbol(stockSymbol).calculateDividendYield(marketPrice);
	}

	public Double calculatePriceEarningsRatio(String stockSymbol, Integer marketPrice) throws StockException {
		return getStockBySymbol(stockSymbol).calculatePriceEarningsRatio(marketPrice);
	}

	public void buyStock(String stockSymbol, Integer tradeQuantity, Integer tradePrice) throws StockNotFoundException {
		getStockBySymbol(stockSymbol).buy(tradeQuantity, tradePrice);
	}

	public void sellStock(String stockSymbol, Integer tradeQuantity, Integer tradePrice) throws StockNotFoundException {
		getStockBySymbol(stockSymbol).sell(tradeQuantity, tradePrice);
	}

	public Double calculateVolumeWeightedStockPrice(String stockSymbol, Integer numberOfMinutes)
			throws StockNotFoundException {
		return getStockBySymbol(stockSymbol).calculateVolumeWeightedStockPrice(numberOfMinutes);
	}

	public Double getAllShareIndex() {
		if (getStocks().size() == 0) {
			return 0.0;
		}

		double totalPriceProduct = 1d;
		for (Stock stock : getStocks().values()) {
			if (stock.getPrice() != null) {
				totalPriceProduct *= stock.getPrice();
			}
		}
		return Math.pow(totalPriceProduct, 1.0 / getStocks().size());
	}

	public Map<String, Stock> getStocks() {
		return stocks;
	}

}
