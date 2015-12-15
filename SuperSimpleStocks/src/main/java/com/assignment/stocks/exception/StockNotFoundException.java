package com.assignment.stocks.exception;

public class StockNotFoundException extends StockException {

	private static final long serialVersionUID = -7080485159161718569L;

	public StockNotFoundException(String message) {
		super(message);
	}

	public StockNotFoundException() {
		super();
	}

}
