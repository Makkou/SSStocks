package com.assignment.superSimpleStocks.stock;

/**
 * Class to store the StockData details. <br>
 * All number values in pennies
 */
public class StockData {

	protected String symbol;
	protected StockType stockType;
	protected Integer lastDividend;
	protected Double fixedDividend;
	protected Integer parValue;

	public StockData() {
		super();
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public StockType getStockType() {
		return stockType;
	}

	public void setStockType(StockType type) {
		this.stockType = type;
	}

	public Integer getLastDividend() {
		return lastDividend;
	}

	public void setLastDividend(Integer lastDividend) {
		this.lastDividend = lastDividend;
	}

	public Double getFixedDividend() {
		return fixedDividend;
	}

	public void setFixedDividend(Double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

	public Integer getParValue() {
		return parValue;
	}

	public void setParValue(Integer parValue) {
		this.parValue = parValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fixedDividend == null) ? 0 : fixedDividend.hashCode());
		result = prime * result + ((lastDividend == null) ? 0 : lastDividend.hashCode());
		result = prime * result + ((parValue == null) ? 0 : parValue.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		result = prime * result + ((stockType == null) ? 0 : stockType.hashCode());
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
		if (!(obj instanceof StockData)) {
			return false;
		}
		StockData other = (StockData) obj;
		if (fixedDividend == null) {
			if (other.fixedDividend != null) {
				return false;
			}
		} else if (!fixedDividend.equals(other.fixedDividend)) {
			return false;
		}
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
		if (stockType != other.stockType) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "StockData [symbol=" + symbol + ", stockType=" + stockType + ", lastDividend=" + lastDividend
				+ ", fixedDividend=" + fixedDividend + ", parValue=" + parValue + "]";
	}

}