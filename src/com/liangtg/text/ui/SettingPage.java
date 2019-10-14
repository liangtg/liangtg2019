package com.liangtg.text.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.liangtg.text.Main;
import com.liangtg.text.util.AppDir;
import com.liangtg.text.util.SettingUtil;

public class SettingPage extends BackPage {

	public SettingPage() {
		super("设置");
		JPanel content = new JPanel(new GridBagLayout());
		content.add(new JLabel("保存位置:\t"));
		content.add(new JLabel(AppDir.instance().setting.getAbsolutePath()));
		GridBagConstraints con = new GridBagConstraints();
		con.gridx = 0;
		con.gridy = 1;
		content.add(new JLabel("分辨率: "), con);
		SettingUtil.instance();
		JComboBox<String> screen = new JComboBox<String>(SettingUtil.SCREEN_LIST);
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
		setContent(content);
	}

}
