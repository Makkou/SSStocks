package com.assignment.stocks.model.trade;

import java.util.Comparator;

/**
 * Comparator class to be used to sort trades by timestamp in reverse order.
 * Useful to get the latest trade list or the latest price of a Stock.
 * 
 * @param <T>
 *            subtype of a Trade
 */
public final class TradeTimestampReverseOrder<T extends Trade> implements Comparator<T> {
	public int compare(T t1, T t2) {
		return t2.getTimestamp().compareTo(t1.getTimestamp());
	}
}
