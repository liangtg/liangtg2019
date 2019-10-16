package com.liangtg.text.ui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.liangtg.text.Main;
import com.liangtg.text.util.AppDir;
import com.liangtg.text.util.SettingUtil;

public class SettingPage extends BackPage {
	private Font font = new Font(null, Font.PLAIN, 20);

	public SettingPage() {
		super("设置");
		JPanel content = new JPanel(new GridBagLayout());
		JLabel label = new JLabel("保存位置:\t");
		label.setFont(font);
		content.add(label);
		JLabel path = new JLabel(AppDir.instance().setting.getAbsolutePath());
		path.setFont(font);
		content.add(path);
		newLine(content);

		GridBagConstraints con = new GridBagConstraints();
		con.gridx = 0;
		con.gridy = 1;
		JLabel fenbian = new JLabel("分辨率: ");
		fenbian.setFont(font);
		content.add(fenbian, con);

		SettingUtil.instance();
		JComboBox<String> screen = new JComboBox<String>(SettingUtil.SCREEN_LIST);
		screen.setFont(font);
		String select = SettingUtil.instance().getScreen();
		for (int i = 0; i < SettingUtil.SCREEN_LIST.length; i++) {
			if (select.equals(SettingUtil.SCREEN_LIST[i])) {
				screen.setSelectedIndex(i);
				break;
			}
		}
		screen.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (ItemEvent.SELECTED == e.getStateChange()) {
					String size = (String) e.getItem();
					String[] point = size.split("x");
					SettingUtil.instance().setScreen(size);
					Main.resize(Integer.parseInt(point[0]), Integer.parseInt(point[1]));
					try {
						SettingUtil.instance().store();
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(SettingPage.this, "保存失敗");
					}
				}
			}
		});
		con = new GridBagConstraints();
		con.gridx = 1;
		con.gridy = 1;
		content.add(screen, con);
		newLine(content);

		con = new GridBagConstraints();
		con.weighty = 1;
		con.gridwidth = 2;
		content.add(Box.createVerticalStrut(8), con);

		setContent(content);
	}

	private void newLine(JPanel panel) {
		GridBagConstraints con = new GridBagConstraints();
		con.gridwidth = GridBagConstraints.REMAINDER;
		con.gridx = 0;
		panel.add(Box.createVerticalStrut(8), con);
	}
}
