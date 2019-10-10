package com.liangtg.text;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JPanel;

import com.liangtg.text.ui.HomePage;
import com.liangtg.text.ui.SettingPage;

public class Nav {
	private static final String PAGE_HOME = "home";
	private static final String PAGE_SUBPAGE = "subpage";
	private static final String PAGE_SETTING = "setting";
	private static Nav instance = new Nav();
	private JPanel subPage;

	public Nav() {
		subPage = new JPanel(new GridLayout(1, 1));
		subPage.setOpaque(false);
	}

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
		container.add(PAGE_HOME, new HomePage());
		container.add(PAGE_SETTING, new SettingPage());
		container.add(PAGE_SUBPAGE, subPage);
		return this;
	}

	public void showHome() {
		cardLayout.first(container);
	}

	public void showSetting() {
		cardLayout.show(container, PAGE_SETTING);
	}

	public void showSubpage(Component comp) {
		subPage.removeAll();
		subPage.add(comp);
		cardLayout.show(container, PAGE_SUBPAGE);
	}

	public void removeSubpage() {
		subPage.removeAll();
	}

	public void subNavBack() {
		removeSubpage();
		showHome();
	}

}
