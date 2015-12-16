package com.assignment.stocks.model.trade;

import java.util.Date;

/**
 * Trade details
 */
public class Trade {

	private String stockSymbol;
	private TradeDirection direction;
	private Integer quantity;
	private Double price;
	private Date timestamp;

	public Trade(String stockSymbol, TradeDirection direction, Integer quantity, Double price, Date timestamp) {
		this.setStockSymbol(stockSymbol);
		this.setDirection(direction);
		this.setQuantity(quantity);
		this.setPrice(price);
		this.setTimestamp(timestamp);
	}

	public Trade(String stockSymbol, TradeDirection direction, Integer quantity, Double price) {
		this.setStockSymbol(stockSymbol);
		this.setDirection(direction);
		this.setQuantity(quantity);
		this.setPrice(price);
		this.setTimestamp(new Date());
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return Buy or Sell indicator
	 */
	public TradeDirection getDirection() {
		return this.direction;
	}

	/**
	 * @param direction
	 *            Buy or Sell indicator
	 */
	public void setDirection(TradeDirection direction) {
		this.direction = direction;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((direction == null) ? 0 : direction.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Trade)) {
			return false;
		}
		Trade other = (Trade) obj;
		if (direction != other.direction) {
			return false;
		}
		if (price == null) {
			if (other.price != null) {
				return false;
			}
		} else if (!price.equals(other.price)) {
			return false;
		}
		if (quantity == null) {
			if (other.quantity != null) {
				return false;
			}
		} else if (!quantity.equals(other.quantity)) {
			return false;
		}
		if (timestamp == null) {
			if (other.timestamp != null) {
				return false;
			}
		} else if (!timestamp.equals(other.timestamp)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Trade [direction=");
		builder.append(direction);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append(", price=");
		builder.append(price);
		builder.append(", timestamp=");
		builder.append(timestamp);
		builder.append("]");
		return builder.toString();
	}

}
