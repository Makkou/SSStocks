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

		se.addStock("TEA", StockType.COMMON, 0d, null, 100d);
		se.addStock("POP", StockType.COMMON, 8d, null, 100d);
		se.addStock("ALE", StockType.COMMON, 23d, null, 60d);
		se.addStock("GIN", StockType.PREFERRED, 8d, 0.02d, 100d);
		se.addStock("JOE", StockType.COMMON, 13d, null, 250d);

		Random random = new Random();
		Integer minRandom = 1;
		Integer maxRandom = 9;

		for (Stock stock : se.getStockMap().values()) {
			try {
				logger.info("Trading " + stock);
				for (int i = 1; i <= random.nextInt(20); i++) {
					Integer randomQuantity = random.nextInt(maxRandom + 1) + minRandom;
					Double randomPrice = new Double(random.nextInt(maxRandom + 1)) + minRandom;
					if (random.nextBoolean()) {
						se.buyStock(stock.getSymbol(), randomQuantity, randomPrice);
						logger.info("Buy  : " + randomQuantity + "\tat " + randomPrice);
					} else {
						se.sellStock(stock.getSymbol(), randomQuantity, randomPrice);
						logger.info("Sell : " + randomQuantity + "\tat " + randomPrice);
					}
					Thread.sleep(1000);
				}

				logger.info("Dividend Yield       : " + se.calculateDividendYield(stock.getSymbol(), 4d));
				logger.info("Price Earnings Ratio : " + se.calculatePriceEarningsRatio(stock.getSymbol(), 4d));
				logger.info("Volume Weighted Stock Price (past 15mn trades): "
						+ se.calculateVolumeWeightedStockPrice(stock.getSymbol(), 5));
			} catch (StockException e) {
				logger.error("Error : " + e.getMessage(), e);
			} catch (InterruptedException e) {
				logger.info("Interrupted : " + e.getMessage(), e);
			}
		}

		logger.info("GBCE All Share Index : " + se.getAllShareIndex());

	}

}
