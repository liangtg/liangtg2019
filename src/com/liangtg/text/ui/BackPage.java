package com.liangtg.text.ui;

import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class BackPage extends BasePanel {
	private JPanel content = new JPanel();

	public BackPage(String title, OnClickListener listener) {
		super();
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		TitleBar bar = new TitleBar(title, listener);
		add(bar);
		layout.putConstraint(SpringLayout.WEST, bar, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, bar, 0, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, bar, 0, SpringLayout.NORTH, this);
		add(content);
		layout.putConstraint(SpringLayout.WEST, content, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, content, 0, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, content, 0, SpringLayout.SOUTH, this);
	}

}
