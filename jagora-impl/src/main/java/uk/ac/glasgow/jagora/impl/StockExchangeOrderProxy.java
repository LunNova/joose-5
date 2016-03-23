package uk.ac.glasgow.jagora.impl;

import uk.ac.glasgow.jagora.*;

public class StockExchangeOrderProxy implements StockExchangeOrderView {
	public StockExchange proxied;

	public StockExchangeOrderProxy(StockExchange proxied) {
		this.proxied = proxied;
	}

	@Override
	public Double getBestBid(Stock stock) {
		return proxied.getBestBid(stock);
	}

	@Override
	public Double getBestOffer(Stock stock) {
		return proxied.getBestOffer(stock);
	}

	@Override
	public void cancelSellOrder(SellOrder sellOrder) {
		proxied.cancelSellOrder(sellOrder);
	}

	@Override
	public void cancelBuyOrder(BuyOrder buyOrder) {
		proxied.cancelBuyOrder(buyOrder);
	}

	@Override
	public void placeSellOrder(SellOrder sellOrder) {
		proxied.placeSellOrder(sellOrder);
	}

	@Override
	public void placeBuyOrder(BuyOrder buyOrder) {
		proxied.placeBuyOrder(buyOrder);
	}
}
