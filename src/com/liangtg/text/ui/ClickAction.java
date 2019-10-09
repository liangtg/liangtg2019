package com.liangtg.text.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickAction implements ActionListener {
	private Component component;
	private OnClickListener listener;

	public ClickAction(Component component, OnClickListener listener) {
		super();
		this.component = component;
		this.listener = listener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (ActionEvent.ACTION_PERFORMED == e.getID()) {
			listener.onClick(component);
		}
	}

}
