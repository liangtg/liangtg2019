package com.liangtg.text.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class BackPage extends BasePanel {
	private OnClickListener dismissListener = new OnClickListener() {
		@Override
		public void onClick(Component component) {
			onBackPressed();
		}
	};
	private Component content = new JPanel();
	private TitleBar titleBar;
	private GridBagLayout layout;

	public TitleBar getTitleBar() {
		return titleBar;
	}

	public BackPage(String title) {
		super();
		layout = new GridBagLayout();
		setLayout(layout);
		addTitlebar(title);
		addContent();
	}

	private void addContent() {
		GridBagConstraints con;
		con = new GridBagConstraints();
		con.gridx = 0;
		con.gridy = 1;
		con.weightx = 1;
		con.weighty = 1;
		con.fill = GridBagConstraints.BOTH;
		content.setBackground(new Color(0x00FFFFFF, true));
		add(content, con);
	}

	public void setContent(Component component) {
		GridBagConstraints con = layout.getConstraints(content);
		remove(content);
		add(component, con);
		content = component;
	}

	private void addTitlebar(String title) {
		titleBar = new TitleBar(title);
		titleBar.setListener(dismissListener);
		GridBagConstraints con = new GridBagConstraints();
		con.weightx = 1;
		con.fill = GridBagConstraints.HORIZONTAL;
		add(titleBar, con);
	}

	public void runOnUiThread(Runnable runnable) {
		SwingUtilities.invokeLater(runnable);
	}

	protected void onBackPressed() {
		dismiss();
	}

}
