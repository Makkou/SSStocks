package com.assignment.stocks.service;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.assignment.stocks.exception.StockException;
import com.assignment.stocks.exception.StockNotFoundException;
import com.assignment.stocks.model.stock.CommonStock;
import com.assignment.stocks.model.stock.Stock;
import com.assignment.stocks.model.stock.StockType;
import com.assignment.stocks.model.trade.Trade;
import com.assignment.stocks.model.trade.TradeDirection;
import com.assignment.stocks.service.ExchangeServiceImpl;

public class ExchangeServiceTest {

	ExchangeServiceImpl se;

	@Mock
	Stock stock;

	@Before
	public void beforeTests() {
		MockitoAnnotations.initMocks(this);
		se = new ExchangeServiceImpl();
	}

	@Test
	public void testAddStock() {
		Stock stock = new CommonStock("TEA", 0d, 100d);
		se.addStock("TEA", StockType.COMMON, 0d, null, 100d);
		assertEquals(stock, se.getStockMap().get("TEA"));

		stock = new CommonStock("POP", 8d, 100d);
		se.addStock(stock);
		assertEquals(stock, se.getStockMap().get("POP"));
	}

	@Test(expected = StockNotFoundException.class)
	public void buyTradeRaiseAnExceptionWhenTheStockIsNotFound() throws StockNotFoundException {
		se.buyStock("TTT", 10, 3d);
	}

	@Test
	public void buyTradeRecordABuyTrade() throws StockNotFoundException {
		Mockito.when(stock.getSymbol()).thenReturn("POP");
		se.addStock(stock);
		se.buyStock(stock.getSymbol(), 10, 3d);

		assertEquals(new Trade("POP", TradeDirection.BUY, 10, 3d), se.getTradeMap().get("POP").get(0));
	}

	@Test
	public void sellTradeRecordASellTrade() throws StockNotFoundException {
		Mockito.when(stock.getSymbol()).thenReturn("TEA");
		se.addStock(stock);
		se.sellStock(stock.getSymbol(), 11, 4d);

		assertEquals(new Trade("TEA", TradeDirection.SELL, 11, 4d), se.getTradeMap().get("TEA").get(0));
	}

	@Test
	public void calculateVolumeWeightedStockPriceShouldBeCalledOnTheStock() throws StockNotFoundException {
		Mockito.when(stock.getSymbol()).thenReturn("TEA");
		se.addStock(stock);
		se.calculateVolumeWeightedStockPrice(stock.getSymbol(), 15);
		// TODO assert the result is OK
		Assert.assertTrue(true);
	}

	@Test
	public void calculatePriceEarningsRatioShouldBeCalledOnTheStock() throws StockException {
		Mockito.when(stock.getSymbol()).thenReturn("TEA");
		se.addStock(stock);
		se.calculatePriceEarningsRatio(stock.getSymbol(), 100d);
		Mockito.verify(stock).getPriceEarningsRatio(100d);
	}

	@Test
	public void calculateDividendYieldShouldBeCalledOnTheStock() throws StockException {
		Mockito.when(stock.getSymbol()).thenReturn("TEA");
		se.addStock(stock);
		se.calculateDividendYield(stock.getSymbol(), 111d);
		Mockito.verify(stock).getDividendYield(111d);
	}

	@Test
	public void getAllShareIndexReturnZeroWhenTheStockSizeIsZero() {
		assertEquals(new Double(0), se.getAllShareIndex());
	}

	@Test
	public void getAllShareIndexReturnCorrectIndexValue() throws StockNotFoundException {
		se.addStock("TEA", StockType.COMMON, 0d, null, 100d);
		se.buyStock("TEA", 10, 200d);

		se.addStock("POP", StockType.COMMON, 8d, null, 100d);
		se.buyStock("POP", 6, 100d);

		se.addStock("ALE", StockType.COMMON, 23d, null, 60d);
		se.buyStock("ALE", 3, 50d);

		Double expectedAllShareIndex = Math.pow(200 * 100 * 50, 1.0 / 3);
		assertEquals(expectedAllShareIndex, se.getAllShareIndex());
	}

}
