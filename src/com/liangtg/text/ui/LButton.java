package com.liangtg.text.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class LButton extends JButton {

	public LButton(String text, ActionListener listener) {
		super(text);
		setBackground(new Color(0x80FFFFFF, true));
		setFont(new Font("", Font.BOLD, 20));
		setFocusPainted(false);
		if (null != listener) {
			addActionListener(listener);
		}
	}

}