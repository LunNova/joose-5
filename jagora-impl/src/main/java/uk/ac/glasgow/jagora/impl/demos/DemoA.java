package uk.ac.glasgow.jagora.impl.demos;

import static java.lang.String.format;
import static java.util.stream.IntStream.range;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import uk.ac.glasgow.jagora.Stock;
import uk.ac.glasgow.jagora.StockExchange;
import uk.ac.glasgow.jagora.TickEvent;
import uk.ac.glasgow.jagora.Trade;
import uk.ac.glasgow.jagora.Trader;
import uk.ac.glasgow.jagora.impl.*;

public class DemoA {
	public static void main (String [] args){
		StockExchange stockExchange = new DefaultStockExchange(new DefaultWorld());
		Stock lemons = new DefaultStock("lemons");
		
		
		Trader bootstrapper = new DefaultTraderBuilder()
				.setName("BootStrapper").setCash(100.0).addStock(lemons, 50)
				.build();
		stockExchange.placeBuyOrder(new LimitBuyOrder(bootstrapper, lemons, 5, 9.0));
		stockExchange.placeSellOrder(new LimitSellOrder(bootstrapper, lemons, 5, 10.0));

		Random sourceRandom = new Random(1);		
		List<Trader> traders = new ArrayList<Trader>();
		RandomTraderBuilder randomTraderBuilder = new RandomTraderBuilder();
		randomTraderBuilder.setRandomlyTradedStock(lemons);
		randomTraderBuilder.setCash(100.0);
		randomTraderBuilder.addStock(lemons, 50);
		randomTraderBuilder.setMaxQuantity(2);
		randomTraderBuilder.setPriceRange(5.0);

		range(0, 100)
			.forEach(i ->  {
				traders.add(randomTraderBuilder.setRandom(new Random(sourceRandom.nextInt()))
						.setName(format("Trader[%d]",i))
						.build());
			});
		
		for (Integer i : range(0,1000).toArray()){
			Integer nextIndex = sourceRandom.nextInt(traders.size());
			Trader trader = traders.get(nextIndex);
			trader.speak(new StockExchangeOrderProxy(stockExchange));
			stockExchange.doClearing();
		}
		
		List<TickEvent<Trade>> trades = stockExchange.getTradeHistory(lemons);
		trades.stream().forEach(System.out::println);
	}
}
