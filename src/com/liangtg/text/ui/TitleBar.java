package com.liangtg.text.ui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class TitleBar extends JPanel {

	public TitleBar(String title, OnClickListener listener) {
		super();
		setOpaque(false);
		setSize(100, 64);
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		JButton button = new JButton(" < 返回");
		button.addActionListener(new ClickAction(button, listener));
		add(button);
		layout.putConstraint(SpringLayout.SOUTH, button, 0, SpringLayout.SOUTH, this);
		JLabel label = new JLabel(title);
		add(label);
		layout.putConstraint(SpringLayout.WEST, label, 0, SpringLayout.EAST, button);
		layout.putConstraint(SpringLayout.EAST, label, 0, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, label, 0, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.SOUTH, label, 0, SpringLayout.SOUTH, this);
	}

}
