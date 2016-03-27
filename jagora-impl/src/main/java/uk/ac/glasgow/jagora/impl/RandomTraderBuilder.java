package uk.ac.glasgow.jagora.impl;

import uk.ac.glasgow.jagora.Stock;

import java.util.Random;

public class RandomTraderBuilder extends AbstractTraderBuilder {
	private Random random;
	private double priceRange;
	private Integer maxQuantity;
	private Stock randomlyTradedStock;

	public RandomTraderBuilder setRandom(Random random) {
		this.random = random;
		return this;
	}

	public RandomTraderBuilder setPriceRange(double priceRange) {
		this.priceRange = priceRange;
		return this;
	}

	public RandomTraderBuilder setMaxQuantity(Integer maxQuantity) {
		this.maxQuantity = maxQuantity;
		return this;
	}

	public RandomTraderBuilder setRandomlyTradedStock(Stock stock) {
		randomlyTradedStock = stock;
		return this;
	}

	@Override
	public RandomTrader build() {
		return new RandomTrader(name, cash, inventory, random, priceRange, maxQuantity, randomlyTradedStock);
	}
}
