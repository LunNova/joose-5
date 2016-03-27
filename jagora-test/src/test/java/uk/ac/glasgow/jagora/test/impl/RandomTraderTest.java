package uk.ac.glasgow.jagora.test.impl;

import static org.junit.Assert.assertEquals;
import static uk.ac.glasgow.jagora.test.stub.StubStock.lemons;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import uk.ac.glasgow.jagora.impl.RandomTrader;
import uk.ac.glasgow.jagora.impl.RandomTraderBuilder;
import uk.ac.glasgow.jagora.impl.StockExchangeOrderProxy;
import uk.ac.glasgow.jagora.test.TraderTest;
import uk.ac.glasgow.jagora.test.stub.StubStockExchange;

public class RandomTraderTest  extends TraderTest{
	
	@Before
	public void setUp() throws Exception {
		this.name = "random";
		this.cash = 1000.0;
		this.stock = lemons;
		this.quantity = 100;
		Random random = new Random (1);
		RandomTraderBuilder randomTraderBuilder = new RandomTraderBuilder();
		randomTraderBuilder
				.setRandomlyTradedStock(stock)
				.setMaxQuantity(10)
				.setPriceRange(2.0)
				.setRandom(random)
				.setName(name)
				.setCash(cash)
				.addStock(stock, quantity);
		this.trader = randomTraderBuilder.build();
	}
	
	@Test
	public void testSpeak (){
		StubStockExchange stockExchange = new StubStockExchange();
		
		for (int i = 0 ; i < 20 ; i ++){
			trader.speak(new StockExchangeOrderProxy(stockExchange));
		}
		
		Double averageBuyPrice =
			stockExchange.buyOrders
				.stream()
				.mapToDouble(buyOrder->buyOrder.getPrice())
				.average()
				.getAsDouble();
		
		assertEquals(5.0, averageBuyPrice, 1.0);
		
		Double averageSellPrice =
			stockExchange.sellOrders
				.stream()
				.mapToDouble(buyOrder->buyOrder.getPrice())
				.average()
				.getAsDouble();

		assertEquals(5.0, averageSellPrice, 1.0);
	}

}
