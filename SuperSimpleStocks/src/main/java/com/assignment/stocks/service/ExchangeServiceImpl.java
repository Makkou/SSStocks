package com.assignment.stocks.service;

import java.util.List;
import java.util.Map;

import com.assignment.stocks.exception.StockException;
import com.assignment.stocks.exception.StockNotFoundException;
import com.assignment.stocks.model.stock.Stock;
import com.assignment.stocks.model.stock.StockType;
import com.assignment.stocks.model.trade.Trade;
import com.assignment.stocks.model.trade.TradeDirection;

public class ExchangeServiceImpl implements ExchangeService {

	private StockService stockService = new StockServiceImpl();

	private TradeService tradeService = new TradeServiceImpl();

	public ExchangeServiceImpl() {
		super();
		stockService = new StockServiceImpl();
		tradeService = new TradeServiceImpl();
	}

	public ExchangeServiceImpl(StockService stockService, TradeService tradeService) {
		super();
		this.stockService = stockService;
		this.tradeService = tradeService;
	}

	public Map<String, Stock> getStockMap() {
		return getStockService().getStockMap();
	}

	public StockService getStockService() {
		return stockService;
	}

	public void setStockService(StockService stockService) {
		this.stockService = stockService;
	}

	public Map<String, List<Trade>> getTradeMap() {
		return getTradeService().getTradeMap();
	}

	public TradeService getTradeService() {
		return tradeService;
	}

	public void setTradeService(TradeService tradeService) {
		this.tradeService = tradeService;
	}

	public void addStock(Stock stock) {
		getStockService().addStock(stock);
	}

	public void addStock(String stockSymbol, StockType stockType, Double lastDividend, Double fixedDividend,
			Double parValue) {
		getStockService().addStock(stockSymbol, stockType, lastDividend, fixedDividend, parValue);
	}

	public Stock getStockBySymbol(String stockSymbol) throws StockNotFoundException {
		return getStockService().getStockBySymbol(stockSymbol);
	}

	public void buyStock(String stockSymbol, Integer tradeQuantity, Double tradePrice) throws StockNotFoundException {
		if (!getStockService().haveSymbol(stockSymbol)) {
			throw new StockNotFoundException(stockSymbol + " not found in this exchange.");
		}
		getTradeService().addTrade(new Trade(stockSymbol, TradeDirection.BUY, tradeQuantity, tradePrice));
	}

	public void sellStock(String stockSymbol, Integer tradeQuantity, Double tradePrice) throws StockNotFoundException {
		if (!getStockService().haveSymbol(stockSymbol)) {
			throw new IllegalArgumentException(stockSymbol + " not found in this exchange.");
		}
		getTradeService().addTrade(new Trade(stockSymbol, TradeDirection.SELL, tradeQuantity, tradePrice));
	}

	public Double calculateDividendYield(String stockSymbol, Double marketPrice) throws StockException {
		return getStockService().calculateDividendYield(stockSymbol, marketPrice);
	}

	public Double calculatePriceEarningsRatio(String stockSymbol, Double marketPrice) throws StockException {
		return getStockService().calculatePriceEarningsRatio(stockSymbol, marketPrice);
	}

	public Double calculateVolumeWeightedStockPrice(String stockSymbol, Integer numberOfSeconds) {
		return getTradeService().calculateVolumeWeightedStockPrice(stockSymbol, numberOfSeconds);
	}

	public List<Trade> getLatestTrades(List<Trade> tradeList, int numberOfSeconds) {
		return getTradeService().getLatestTrades(tradeList, numberOfSeconds);
	}

	public Double getAllShareIndex() {
		Map<String, Stock> stockMap = getStockService().getStockMap();
		int stockSize = stockMap.size();
		if (stockSize == 0) {
			return 0.0;
		}

		double totalPriceProduct = 1d;
		for (Stock stock : stockMap.values()) {
			Double stockPrice = getTradeService().getLatestPrice(stock.getSymbol());
			if (stockPrice != null) {
				totalPriceProduct *= stockPrice;
			}
		}
		return Math.pow(totalPriceProduct, 1.0 / stockSize);
	}

}
