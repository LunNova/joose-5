package uk.ac.glasgow.jagora.impl;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import uk.ac.glasgow.jagora.Stock;
import uk.ac.glasgow.jagora.StockExchangeOrderView;

/**
 * Implements the behaviour of a passive default trader who never makes bids or
 * offers on the market.
 * 
 * @author tws
 *
 */
public class DefaultTrader extends AbstractTrader implements Observer {
	public DefaultTrader(String name, Double cash, Map<Stock, Integer> inventory) {
		super(name, cash, inventory);
	}

	@Override
	public void speak(StockExchangeOrderView stockExchange) {
		//Does nothing.
	}

	@Override
	public void update(Observable o, Object arg) {
		//something changed in stockexchange
	}
}
