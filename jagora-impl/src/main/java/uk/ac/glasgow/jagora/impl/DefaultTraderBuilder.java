package uk.ac.glasgow.jagora.impl;

public class DefaultTraderBuilder extends AbstractTraderBuilder {
	@Override
	public DefaultTrader build() {
		return new DefaultTrader(name, cash, inventory);
	}
}
