package uk.ac.glasgow.jagora.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import uk.ac.glasgow.jagora.*;

public class RandomTrader extends AbstractTrader {
	
	private final Random random;
	private double priceRange;
	private final Integer maxQuantity;
	private final Stock stock;
	
	private Double lastKnownBestOffer;
	private Double lastKnownBestBid;

	public RandomTrader(String name, Double cash, Map<Stock, Integer> inventory, Random random, double priceRange,
						Integer maxQuantity, Stock randomlyTradedStock) {
		super(name, cash, inventory);
		this.random = random;
		this.priceRange = priceRange;
		this.maxQuantity = maxQuantity;
		this.stock = randomlyTradedStock;
	}

	private static Map<Stock, Integer> createInventoryMap(Stock stock, Integer quantity) {
		Map<Stock,Integer> inventory = new HashMap<Stock,Integer>();
		inventory.put(stock, quantity);
		return inventory;
	}

	@Override
	public void speak(StockExchangeOrderView stockExchange) {

		Integer quantity = random.nextInt(maxQuantity);
		if (random.nextBoolean()){
			
			Double bestBidOnMarket = stockExchange.getBestBid(stock);
			if (bestBidOnMarket != null)
				lastKnownBestBid = bestBidOnMarket;
			else
				bestBidOnMarket = lastKnownBestBid;
			
			if (bestBidOnMarket!=null){
				Double sellPrice = 
					createRandomPrice(bestBidOnMarket);

				SellOrder sellOrder = new LimitSellOrder (this, stock, quantity, sellPrice);
				stockExchange.placeSellOrder(sellOrder);
			}
			
		} else {
			
			Double bestOfferOnMarket = stockExchange.getBestOffer(stock);
			if (bestOfferOnMarket != null)
				lastKnownBestOffer = bestOfferOnMarket;
			else
				bestOfferOnMarket = lastKnownBestOffer;
			
			if (bestOfferOnMarket!=null){
			
				Double buyPrice = 
					createRandomPrice(bestOfferOnMarket);

				BuyOrder buyOrder = new LimitBuyOrder (this, stock, quantity, buyPrice);
				stockExchange.placeBuyOrder(buyOrder);
			}
		}
		
	}

	private Double createRandomPrice(Double currentPrice) {
		return (random.nextDouble() - 0.5) * priceRange + currentPrice;
	}
}
