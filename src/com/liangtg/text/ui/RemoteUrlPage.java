package com.liangtg.text.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import com.liangtg.text.util.AppDir;

public class RemoteUrlPage extends BackPage {

	private LButton download;
	private JTextField input;
	private OnClickListener downClickListener = new OnClickListener() {

		@Override
		public void onClick(Component component) {
			onDownClick();
		}
	};
	private JLabel errorLabel;

	public RemoteUrlPage() {
		super("下载");
		GridBagLayout layout = new GridBagLayout();
		JPanel content = new JPanel(layout);
		content.setBackground(new Color(0x00ffffff, true));
		content.setBorder(new EmptyBorder(16, 16, 16, 16));
		GridBagConstraints con = new GridBagConstraints();
		con.weightx = 1;
		con.fill = GridBagConstraints.BOTH;
		input = new JTextField("https://liangtg.github.io/liangtg2019/tlbb1-2.txt", 20);
		input.setFont(new Font("", Font.BOLD, 20));
		content.add(input, con);

		con = new GridBagConstraints();
		download = new LButton("下载", null);
		download.addActionListener(new ClickAction(download, downClickListener));
		content.add(download, con);
		newLine(content);

		errorLabel = new JLabel(" ");
		errorLabel.setFont(new Font("", Font.BOLD, 20));
		errorLabel.setForeground(new Color(0xFF0000));
		con = new GridBagConstraints();
		con.gridwidth = 2;
		con.fill = GridBagConstraints.HORIZONTAL;
		content.add(errorLabel, con);
		newLine(content);

		con = new GridBagConstraints();
		con.gridwidth = 2;
		con.fill = GridBagConstraints.BOTH;
		content.add(new JProgressBar(JProgressBar.HORIZONTAL, 0, 100), con);
		newLine(content);

		con = new GridBagConstraints();
		con.weighty = 1;
		con.gridwidth = 3;
		content.add(Box.createVerticalStrut(8), con);

		setContent(content);
	}

	private void showError(String text) {
		if (null == text || text.length() == 0) {
			errorLabel.setText(" ");
			errorLabel.setOpaque(false);
			errorLabel.setBackground(new Color(0x00ffffff, true));
		} else {
			errorLabel.setText(text);
			errorLabel.setOpaque(true);
			errorLabel.setBackground(new Color(0x80FFFFFF, true));
		}
	}

	private void onDownClick() {
		String text = input.getText();
		URL url;
		try {
			url = new URL(text);
		} catch (MalformedURLException e) {
			showError("地址输入错误");
			return;
		}
		download.setEnabled(false);
	}

	private class DownTask extends Thread {
		private URL url;

		public DownTask(URL url) {
			super();
			this.url = url;
		}

		@Override
		public void run() {
			HttpURLConnection con;
			try {
				con = (HttpURLConnection) url.openConnection();
				con.setConnectTimeout(10);
				if (con.getResponseCode() == 200) {
					File target = File.createTempFile("down", null, AppDir.instance().download);
//					BufferedOutputStream bo = new BufferedOutputStream(new FileWriter(target));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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
		panel.add(Box.createVerticalStrut(8), con);
	}

}
