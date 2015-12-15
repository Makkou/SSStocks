package com.assignment.stocks.stockExchange;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.assignment.stocks.exception.StockException;
import com.assignment.stocks.exception.StockNotFoundException;
import com.assignment.stocks.stock.Stock;
import com.assignment.stocks.stock.StockImpl;
import com.assignment.stocks.stock.StockType;
import com.assignment.stocks.stockExchange.StockExchangeServiceImpl;

public class StockExchangeTest {

	StockExchangeServiceImpl se;

	@Mock
	Stock stock;

	@Before
	public void beforeTests() {
		MockitoAnnotations.initMocks(this);
		se = new StockExchangeServiceImpl();
	}

	@Test
	public void testAddStock() {
		Stock stock = new StockImpl("TEA", StockType.COMMON, 0, null, 100);
		se.addStock("TEA", StockType.COMMON, 0, null, 100);
		assertEquals(stock, se.getStocks().get("TEA"));

		stock = new StockImpl("POP", StockType.COMMON, 8, null, 100);
		se.addStock(stock);
		assertEquals(stock, se.getStocks().get("POP"));
	}

	@Test(expected = StockNotFoundException.class)
	public void buyTradeRaiseAnExceptionWhenTheStockIsNotFound() throws StockNotFoundException {
		se.buyStock("TTT", 10, 3);
	}

	@Test
	public void buyTradeRecordABuyTrade() throws StockNotFoundException {
		Mockito.when(stock.getSymbol()).thenReturn("POP");
		se.addStock(stock);
		se.buyStock(stock.getSymbol(), 10, 3);
		Mockito.verify(stock).buy(10, 3);
	}

	@Test
	public void sellTradeRecordASellTrade() throws StockNotFoundException {
		Mockito.when(stock.getSymbol()).thenReturn("TEA");
		se.addStock(stock);
		se.sellStock(stock.getSymbol(), 11, 4);
		Mockito.verify(stock).sell(11, 4);
	}

	@Test
	public void calculateVolumeWeightedStockPriceShouldBeCalledOnTheStock() throws StockNotFoundException {
		Mockito.when(stock.getSymbol()).thenReturn("TEA");
		se.addStock(stock);
		se.calculateVolumeWeightedStockPrice(stock.getSymbol(), 15);
		Mockito.verify(stock).calculateVolumeWeightedStockPrice(15);
	}

	@Test
	public void calculatePriceEarningsRatioShouldBeCalledOnTheStock() throws StockException {
		Mockito.when(stock.getSymbol()).thenReturn("TEA");
		se.addStock(stock);
		se.calculatePriceEarningsRatio(stock.getSymbol(), 100);
		Mockito.verify(stock).calculatePriceEarningsRatio(100);
	}

	@Test
	public void calculateDividendYieldShouldBeCalledOnTheStock() throws StockException {
		Mockito.when(stock.getSymbol()).thenReturn("TEA");
		se.addStock(stock);
		se.calculateDividendYield(stock.getSymbol(), 111);
		Mockito.verify(stock).calculateDividendYield(111);
	}

	@Test
	public void getAllShareIndexReturnZeroWhenTheStockSizeIsZero() {
		assertEquals(new Double(0), se.getAllShareIndex());
	}

	@Test
	public void getAllShareIndexReturnCorrectIndexValue() throws StockNotFoundException {
		se.addStock("TEA", StockType.COMMON, 0, null, 100);
		se.buyStock("TEA", 10, 200);

		se.addStock("POP", StockType.COMMON, 8, null, 100);
		se.buyStock("POP", 6, 100);

		se.addStock("ALE", StockType.COMMON, 23, null, 60);
		se.buyStock("ALE", 3, 50);

		Double expectedAllShareIndex = Math.pow(200 * 100 * 50, 1.0 / 3);
		assertEquals(expectedAllShareIndex, se.getAllShareIndex());
	}

}
