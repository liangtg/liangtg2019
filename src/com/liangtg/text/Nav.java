package com.liangtg.text;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JPanel;

import com.liangtg.text.ui.AboutPage;
import com.liangtg.text.ui.HistoryPage;
import com.liangtg.text.ui.HomePage;
import com.liangtg.text.ui.RemoteUrlPage;
import com.liangtg.text.ui.SettingPage;

public class Nav {
	private static final String PAGE_HOME = "home";
	private static final String PAGE_SUBPAGE = "subpage";
	private static final String PAGE_SETTING = "setting";
	private static final String PAGE_ABOUT = "about";
	private static final String PAGE_HISTORY = "history";
	private static final String PAGE_REMOTE = "remote";
	private static Nav instance = new Nav();
	private JPanel subPage;
	private String curPage = PAGE_HOME;
	private String lastPage = null;

	public Nav() {
		subLayout = new CardLayout();
		subPage = new JPanel(subLayout);
		subPage.setOpaque(false);
	}

	public static Nav getInstance() {
		return instance;
	}

	private Container container;

	private CardLayout cardLayout;
	private CardLayout subLayout;

	public CardLayout getCardLayout() {
		return cardLayout;
	}

	public Nav setContainer(Container container) {
		cardLayout = new CardLayout();
		this.container = container;
		container.setLayout(cardLayout);
		container.add(PAGE_HOME, new HomePage());
		container.add(PAGE_SETTING, new SettingPage());
		container.add(PAGE_ABOUT, new AboutPage());
		container.add(PAGE_HISTORY, new HistoryPage());
		container.add(PAGE_REMOTE, new RemoteUrlPage());
		container.add(PAGE_SUBPAGE, subPage);
		return this;
	}

	public void showHome() {
		cardLayout.first(container);
		curPage = PAGE_HOME;
	}

	public void showSetting() {
		cardLayout.show(container, PAGE_SETTING);
		curPage = PAGE_SETTING;
	}

	public void showAbout() {
		cardLayout.show(container, PAGE_ABOUT);
		curPage = PAGE_ABOUT;
	}

	public void showHistory() {
		cardLayout.show(container, PAGE_HISTORY);
		curPage = PAGE_HISTORY;
	}

	public void showRemote() {
		cardLayout.show(container, PAGE_REMOTE);
		curPage = PAGE_REMOTE;
	}

	public void showSubpage(Component comp) {
		if (null == lastPage) {
			lastPage = curPage;
		}
		subPage.add(comp);
		cardLayout.show(container, PAGE_SUBPAGE);
		subLayout.last(subPage);
	}

	public void subNavBack() {
		if (subPage.getComponentCount() > 1) {
			subPage.remove(subPage.getComponentCount() - 1);
			subLayout.last(subPage);
		} else {
			subPage.removeAll();
			cardLayout.show(container, lastPage == null ? PAGE_HOME : lastPage);
			lastPage = null;
		}
	}

}
