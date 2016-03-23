package uk.ac.glasgow.jagora.impl;

import uk.ac.glasgow.jagora.Stock;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractTraderBuilder {
	protected String name;
	protected Double cash;
	protected final Map<Stock, Integer> inventory = new HashMap<>();

	public AbstractTraderBuilder setName(String name) {
		this.name = name;
		return this;
	}

	public AbstractTraderBuilder setCash(Double cash) {
		this.cash = cash;
		return this;
	}

	public AbstractTraderBuilder setStock(Stock stock, int count) {
		inventory.put(stock, count);
		return this;
	}

	public AbstractTraderBuilder addAllStocks(Map<Stock, Integer> stocks) {
		for (Map.Entry<Stock, Integer> entry : stocks.entrySet()) {
			addStock(entry.getKey(), entry.getValue());
		}
		return this;
	}

	public AbstractTraderBuilder addStock(Stock stock, int count) {
		int current = inventory.getOrDefault(stock, 0);
		inventory.put(stock, current + count);
		return this;
	}

	public abstract AbstractTrader build();
}
