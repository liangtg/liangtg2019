package com.liangtg.text.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.liangtg.text.Nav;
import com.liangtg.text.util.AppDir;

public class HomePage extends BasePanel {
	public HomePage() {
		super(new GridLayout(0, 1, 10, 10));
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
			chooser.setSelectedFile(new File(AppDir.instance().appPath, "tlbb1-2.txt"));
			int result = chooser.showOpenDialog(HomePage.this);
			if (JFileChooser.APPROVE_OPTION == result) {
				Nav.getInstance().showSubpage(new LocalAnalysisPage(chooser.getSelectedFile()));
			}

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
			Nav.getInstance().showSetting();
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
