package com.assignment.stocks.stock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.assignment.stocks.stock.StockImpl;
import com.assignment.stocks.stock.StockType;
import com.assignment.stocks.trade.Trade;
import com.assignment.stocks.trade.TradeDirection;

public class StockTest {

	StockImpl stock;

	@Before
	public void beforeTests() {
		stock = new StockImpl();
		stock.setLastDividend(10);
	}

	@Test
	public void getPriceReturnNullWhenTheStockDoesNotExists() {
		assertNull(stock.getPrice());
	}

	@Test
	public void buyShouldRecordABuyTrade() {
		stock.buy(10, 5);
		assertEquals(new Integer(5), stock.getPrice());
		Trade lastTrade = stock.getStockTrades().lastEntry().getValue();
		assertEquals(TradeDirection.BUY, lastTrade.getDirection());
		assertEquals(new Integer(10), lastTrade.getQuantity());
		assertEquals(new Integer(5), lastTrade.getPrice());
	}

	@Test
	public void sellShouldRecordASellTrade() {
		stock.sell(11, 6);
		assertEquals(new Integer(6), stock.getPrice());
		Trade lastTrade = stock.getStockTrades().lastEntry().getValue();
		assertEquals(TradeDirection.SELL, lastTrade.getDirection());
		assertEquals(new Integer(11), lastTrade.getQuantity());
		assertEquals(new Integer(6), lastTrade.getPrice());
	}

	@Test(expected = IllegalArgumentException.class)
	public void calculateDividendYieldShouldRaiseExceptionForZeroMarketPrice() {
		stock.calculateDividendYield(0);
	}

	@Test
	public void calculateCommonStockDividendYieldReturnZeroWhenLastDividendIsNull() {
		stock.setStockType(StockType.COMMON);
		stock.setLastDividend(null);
		assertEquals(new Double(0), stock.calculateDividendYield(5));
	}

	@Test
	public void calculateCommonStockDividendYieldReturnTheDividendYield() {
		stock.setStockType(StockType.COMMON);
		stock.setLastDividend(10);
		assertEquals(new Double(2), stock.calculateDividendYield(5));
	}

	@Test
	public void calculatePrefferedStockDividendYieldReturnZeroWhenFixedDividendIsNull() {
		stock.setStockType(StockType.PREFERRED);
		stock.setFixedDividend(null);

		assertEquals(new Double(0), stock.calculateDividendYield(5));
	}

	@Test
	public void calculatePrefferedStockDividendYieldReturnTheDividendValue() {
		stock.setStockType(StockType.PREFERRED);
		stock.setFixedDividend(10d);
		stock.setParValue(2);

		assertEquals(new Double(4), stock.calculateDividendYield(5));
	}

	@Test
	public void calculatePriceEarningsRatioReturnNullWhenDividendIsZero() {
		stock.setStockType(StockType.COMMON);
		stock.setLastDividend(15);
		Double expectedCommonStockDividend = new Double(5);
		assertEquals(expectedCommonStockDividend, stock.calculateDividendYield(3));

	}

	@Test
	public void calculatePriceEarningsRatioReturnCorrectPERation() {
		stock.setStockType(StockType.COMMON);
		stock.setLastDividend(15);
		Double commonStockDividend = new Double(5);
		Integer marketPrice = 3;
		assertEquals(commonStockDividend, stock.calculateDividendYield(marketPrice));
		assertEquals(new Double(15), stock.calculatePriceEarningsRatio(15));
	}

	@Test
	public void calculateVolumeWeightedStockPrice() throws InterruptedException {
		stock.buy(10, 5);
		Thread.sleep(10);
		stock.buy(5, 2);
		Thread.sleep(10);
		stock.sell(6, 5);
		Thread.sleep(10);
		assertEquals(new Double(90d / 21), stock.calculateVolumeWeightedStockPrice(1));
	}

	@Test
	public void calculateVolumeWeightedStockPriceReturnZeroWhenNoTradeAreReccorded() throws InterruptedException {
		assertEquals(new Double(0), stock.calculateVolumeWeightedStockPrice(1));
	}

}
