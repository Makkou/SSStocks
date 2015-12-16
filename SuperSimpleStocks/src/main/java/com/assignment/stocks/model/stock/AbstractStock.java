package com.assignment.stocks.model.stock;

/**
 * Base for any Stock sub-type. <br>
 */
public abstract class AbstractStock implements Stock {

	private String symbol;
	private Double lastDividend;
	private Double parValue;

	private StockType type;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public StockType getType() {
		return type;
	}

	public void setType(StockType type) {
		this.type = type;
	}

	public Double getLastDividend() {
		return lastDividend;
	}

	public void setLastDividend(Double lastDividend) {
		this.lastDividend = lastDividend;
	}

	public Double getParValue() {
		return parValue;
	}

	public void setParValue(Double parValue) {
		this.parValue = parValue;
	}

	public abstract Double getDividendYield(Double marketPrice);

	public Double getPriceEarningsRatio(Double marketPrice) {
		double dividend = this.getDividendYield(marketPrice);
		return (dividend == 0) ? null : marketPrice / dividend;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lastDividend == null) ? 0 : lastDividend.hashCode());
		result = prime * result + ((parValue == null) ? 0 : parValue.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		if (!(obj instanceof AbstractStock)) {
			return false;
		}
		AbstractStock other = (AbstractStock) obj;
		if (lastDividend == null) {
			if (other.lastDividend != null) {
				return false;
			}
		} else if (!lastDividend.equals(other.lastDividend)) {
			return false;
		}
		if (parValue == null) {
			if (other.parValue != null) {
				return false;
			}
		} else if (!parValue.equals(other.parValue)) {
			return false;
		}
		if (symbol == null) {
			if (other.symbol != null) {
				return false;
			}
		} else if (!symbol.equals(other.symbol)) {
			return false;
		}
		if (type != other.type) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Stock [symbol=");
		builder.append(symbol);
		builder.append(", lastDividend=");
		builder.append(lastDividend);
		builder.append(", parValue=");
		builder.append(parValue);
		builder.append(", type=");
		builder.append(type);
		builder.append("]");
		return builder.toString();
	}

}