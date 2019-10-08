package com.liangtg.text;

import java.awt.CardLayout;
import java.awt.Container;

import javax.swing.JPanel;

import com.liangtg.text.ui.HomePage;

public class Nav {
	private static Nav instance = new Nav();

	public static Nav getInstance() {
		return instance;
	}

	private Container container;

	private CardLayout cardLayout;

	public CardLayout getCardLayout() {
		return cardLayout;
	}

	public Nav setContainer(Container container) {
		cardLayout = new CardLayout();
		this.container = container;
		container.setLayout(cardLayout);
		container.add("", new HomePage());
		return this;
	}

	public void showHome() {
		cardLayout.first(container);
	}

}
