package com.liangtg.text.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class RemoteUrlPage extends BackPage {

	public RemoteUrlPage() {
		super("下载");
		GridBagLayout layout = new GridBagLayout();
		JPanel content = new JPanel(layout);
		content.setBorder(new EmptyBorder(16, 16, 16, 16));
		GridBagConstraints con = new GridBagConstraints();
		con.weightx = 1;
		con.fill = GridBagConstraints.HORIZONTAL;
//		content.add(new JTextField("https://liangtg.github.io/liangtg2019/tlbb1-2.txt", 20), con);
		con = new GridBagConstraints();
//		content.add(new LButton("下载", null), con);
//		newLine(content);
		for (int i = 0; i < 20; i++) {
			extracted(content);
		}
		setContent(content);
	}

	private void extracted(JPanel content) {
		GridBagConstraints con;
		content.add(new JLabel("1"));
		JPanel b1 = new JPanel(new GridLayout(1, 1));
		con = new GridBagConstraints();
		con.weightx = 1;
		b1.add(new JLabel("11111111111111111111"));
		content.add(b1, con);
		b1 = new JPanel(new GridLayout(1, 1));
		con = new GridBagConstraints();
		con.weightx = 1;
		b1.add(new JLabel("11111111111111111111"));
		content.add(b1, con);
		content.add(new JButton("删除"));
		newLine(content);
	}

	private void newLine(JPanel panel) {
		GridBagConstraints con = new GridBagConstraints();
		con.gridwidth = GridBagConstraints.REMAINDER;
		con.gridx = 0;
		panel.add(new JPanel(), con);
	}

}
