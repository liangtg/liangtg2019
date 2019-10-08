package com.liangtg.text.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class HomePage extends JPanel {
	public HomePage() {
		super(new GridLayout(6, 1, 10, 10));
		setOpaque(false);
		add(new LButton("打开文件", new OpenLocalAction()));
		add(new LButton("打开链接", new OpenRemoteAction()));
		add(new LButton("历史", new OpenHistoryAction()));
		add(new LButton("设置", new SettingAction()));
		add(new LButton("关于", new AboutAction()));
		add(new LButton("退出", new ExitAction()));
	}

	private class OpenLocalAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			chooser.setFileFilter(new FileNameExtensionFilter("文本文件", "txt"));
			chooser.showOpenDialog(HomePage.this);
		}

	}

	private class OpenRemoteAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
		}

	}

	private class SettingAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
		}

	}

	private class AboutAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
		}

	}

	private class OpenHistoryAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
		}

	}

	private class ExitAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}

	}

}
