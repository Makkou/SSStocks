package com.assignment.stocks.model.stock;

/**
 * Preferred Stock details
 */
public class PreferredStock extends AbstractStock {
	{ // Initializing Stock Type
		this.setType(StockType.PREFERRED);
	}

	private Double fixedDividend;

	public PreferredStock() {
		super();
	}

	public PreferredStock(String stockSymbol, Double lastDividend, Double parValue) {
		this.setSymbol(stockSymbol);
		this.setLastDividend(lastDividend);
		this.setParValue(parValue);
	}

	public PreferredStock(String stockSymbol, Double lastDividend, Double parValue, Double fixedDividend) {
		this.setSymbol(stockSymbol);
		this.setLastDividend(lastDividend);
		this.setParValue(parValue);
		this.setFixedDividend(fixedDividend);
	}

	public Double getFixedDividend() {
		return fixedDividend;
	}

	public void setFixedDividend(Double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

	@Override
	public Double getDividendYield(Double marketPrice) {
		if (marketPrice == null || marketPrice == 0.0) {
			throw new IllegalArgumentException("marketPrice is zero or null.");
		}

		if (this.getFixedDividend() == null || this.getParValue() == null) {
			return 0.0;
		}

		return this.getFixedDividend() * this.getParValue() / marketPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((fixedDividend == null) ? 0 : fixedDividend.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof PreferredStock)) {
			return false;
		}
		PreferredStock other = (PreferredStock) obj;
		if (fixedDividend == null) {
			if (other.fixedDividend != null) {
				return false;
			}
		} else if (!fixedDividend.equals(other.fixedDividend)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PreferredStock [fixedDividend=");
		builder.append(fixedDividend);
		builder.append(", Parent=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
}
