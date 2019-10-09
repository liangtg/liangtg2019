package com.liangtg.text.ui;

import java.awt.LayoutManager;

import javax.swing.JPanel;

import com.liangtg.text.Nav;

@SuppressWarnings("serial")
public class BasePanel extends JPanel {
	private boolean exit = false;

	public BasePanel() {
		setOpaque(false);
	}

	public BasePanel(LayoutManager gridLayout) {
		super(gridLayout);
		setOpaque(false);
	}

	public void dismiss() {
		exit = true;
		Nav.getInstance().removeSubpage();
	}

	public boolean isExit() {
		return exit;
	}

}
