package com.liangtg.text.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author liangtg
 *
 *         标题栏
 */
public class TitleBar extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7055129485955438463L;
	private static final int PREF_SIZE = 64;
	private static Font titleFont = new Font(null, Font.BOLD, 16);

	/**
	 * 返回按钮
	 */
	private JButton backButton;
	private OnClickListener listener;

	public OnClickListener getListener() {
		return listener;
	}

	public void setListener(OnClickListener listener) {
		this.listener = listener;
		ActionListener[] old = backButton.getActionListeners();
		for (ActionListener actionListener : old) {
			backButton.removeActionListener(actionListener);
		}
		backButton.addActionListener(new ClickAction(backButton, listener));
	}

	public TitleBar(String title) {
		super();
		setPreferredSize(new Dimension(PREF_SIZE, PREF_SIZE));
		setBackground(new Color(0xFF, 0xFF, 0xFF, 0x80));
		setLayout(new GridBagLayout());
		initBack();
		initTitle(title);
	}

	private void initTitle(String title) {
		GridBagConstraints con;
		JLabel label = new JLabel(title);
		label.setFont(titleFont);
		label.setForeground(Color.white);
		label.setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 0));
		con = new GridBagConstraints();
		con.weighty = 1;
		con.weightx = 1;
		con.fill = GridBagConstraints.BOTH;
		add(label, con);
	}

	private void initBack() {
		backButton = new JButton(" < 返回");
		backButton.setBackground(new Color(0x00FFFFFF, true));
		backButton.setForeground(Color.WHITE);
		backButton.setMargin(new Insets(0, 0, 0, 0));
		backButton.setFont(titleFont);
		GridBagConstraints con = new GridBagConstraints();
		con.weighty = 1;
		con.fill = GridBagConstraints.BOTH;
		add(backButton, con);
	}

}
