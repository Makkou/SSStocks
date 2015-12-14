package com.assignment.superSimpleStocks;

import java.util.Random;

import org.apache.log4j.Logger;

import com.assignment.superSimpleStocks.exception.StockException;
import com.assignment.superSimpleStocks.stock.Stock;
import com.assignment.superSimpleStocks.stock.StockType;
import com.assignment.superSimpleStocks.stockExchange.StockExchangeService;
import com.assignment.superSimpleStocks.stockExchange.StockExchangeServiceImpl;

public class TestApp {

	private static final Logger logger = Logger.getLogger(TestApp.class);

	public static void main(String[] args) {
		StockExchangeService se = new StockExchangeServiceImpl();

		se.addStock("TEA", StockType.COMMON, 0, null, 100);
		se.addStock("POP", StockType.COMMON, 8, null, 100);
		se.addStock("ALE", StockType.COMMON, 23, null, 60);
		se.addStock("GIN", StockType.PREFERRED, 8, 0.02d, 100);
		se.addStock("JOE", StockType.COMMON, 13, null, 250);

		Random random = new Random();
		Integer minRandom = 1;
		Integer maxRandom = 9;

		for (Stock stock : se.getStocks().values()) {
			try {
				logger.info("Trading " + stock);
				for (int i = 1; i <= 10; i++) {
					Integer randomQuantity = random.nextInt(maxRandom + 1) + minRandom;
					Integer randomPrice = random.nextInt(maxRandom + 1) + minRandom;
					if (random.nextBoolean()) {
						stock.buy(randomQuantity, randomPrice);
						logger.info("Buy  : " + randomQuantity + "\tat " + randomPrice);
					} else {
						stock.sell(randomQuantity, randomPrice);
						logger.info("Sell : " + randomQuantity + "\tat " + randomPrice);
					}
					Thread.sleep(1000);
				}

				logger.info("Dividend Yield       : " + se.calculateDividendYield(stock.getSymbol(), 4));
				logger.info("Price Earnings Ratio : " + se.calculatePriceEarningsRatio(stock.getSymbol(), 4));
				logger.info("Volume Weighted Stock Price (past 15mn trades): "
						+ se.calculateVolumeWeightedStockPrice(stock.getSymbol(), 15));
			} catch (StockException e) {
				logger.error("Error : " + e.getMessage(), e);
			} catch (InterruptedException e) {
				logger.info("Interrupted : " + e.getMessage(), e);
			}
		}

		logger.info("GBCE All Share Index : " + se.getAllShareIndex());

	}

}
