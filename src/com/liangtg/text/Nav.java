package com.liangtg.text;

import java.awt.CardLayout;
import java.awt.Container;

public class Nav {
	private static Nav instance = new Nav();
	private Container container;

	private CardLayout cardLayout;

	public CardLayout getCardLayout() {
		return cardLayout;
	}

	public void setCardLayout(CardLayout cardLayout) {
		this.cardLayout = cardLayout;
	}

	public void setContainer(Container container) {
		this.container = container;
	}

}
