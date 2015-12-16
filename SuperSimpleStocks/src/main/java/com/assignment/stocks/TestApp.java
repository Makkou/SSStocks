package com.assignment.stocks;

import java.util.Random;

import org.apache.log4j.Logger;

import com.assignment.stocks.exception.StockException;
import com.assignment.stocks.model.stock.Stock;
import com.assignment.stocks.model.stock.StockType;
import com.assignment.stocks.service.ExchangeService;
import com.assignment.stocks.service.ExchangeServiceImpl;

public class TestApp {

	private static final Logger logger = Logger.getLogger(TestApp.class);

	public static void main(String[] args) {
		ExchangeService se = new ExchangeServiceImpl();

		se.getStockService().addStock("TEA", StockType.COMMON, 0d, null, 100d);
		se.getStockService().addStock("POP", StockType.COMMON, 8d, null, 100d);
		se.getStockService().addStock("ALE", StockType.COMMON, 23d, null, 60d);
		se.getStockService().addStock("GIN", StockType.PREFERRED, 8d, 0.02d, 100d);
		se.getStockService().addStock("JOE", StockType.COMMON, 13d, null, 250d);

		Random random = new Random();
		Integer minRandom = 1;
		Integer maxRandom = 9;

		for (Stock stock : se.getStockService().getStockMap().values()) {
			try {
				logger.info("Trading " + stock);
				for (int i = 1; i <= 10; i++) {
					Integer randomQuantity = random.nextInt(maxRandom + 1) + minRandom;
					Double randomPrice = new Double(random.nextInt(maxRandom + 1)) + minRandom;
					if (random.nextBoolean()) {
						se.buyStock(stock.getSymbol(), randomQuantity, randomPrice);
						logger.info("Buy  : " + randomQuantity + "\tat " + randomPrice);
					} else {
						se.sellStock(stock.getSymbol(), randomQuantity, randomPrice);
						logger.info("Sell : " + randomQuantity + "\tat " + randomPrice);
					}
					Thread.sleep(250);
				}

				logger.info(
						"Dividend Yield       : " + se.getStockService().calculateDividendYield(stock.getSymbol(), 4d));
				logger.info("Price Earnings Ratio : "
						+ se.getStockService().calculatePriceEarningsRatio(stock.getSymbol(), 4d));
				logger.info("Volume Weighted Stock Price (past 15mn trades): "
						+ se.getTradeService().calculateVolumeWeightedStockPrice(stock.getSymbol(), 1));
			} catch (StockException e) {
				logger.error("Error : " + e.getMessage(), e);
			} catch (InterruptedException e) {
				logger.info("Interrupted : " + e.getMessage(), e);
			}
		}

		logger.info("GBCE All Share Index : " + se.getAllShareIndex());

	}

}
