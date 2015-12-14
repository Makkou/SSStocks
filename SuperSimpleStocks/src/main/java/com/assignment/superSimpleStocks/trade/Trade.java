package com.assignment.superSimpleStocks.trade;

/**
 * class to hold the trade details
 */
public class Trade {

	private TradeDirection direction;
	private Integer quantity;
	private Integer price;

	public Trade(TradeDirection direction, Integer quantity, Integer price) {
		setDirection(direction);
		setQuantity(quantity);
		setPrice(price);
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

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((direction == null) ? 0 : direction.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
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
		return true;
	}

	@Override
	public String toString() {
		return "Trade [direction=" + direction + ", quantity=" + quantity + ", price=" + price + "]";
	}

}
