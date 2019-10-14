package com.liangtg.text.ui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import com.liangtg.text.db.HistoryItem;
import com.liangtg.text.util.DbHelper;

public class HistoryPage extends BackPage {

	private JList<HistoryItem> listView;

	public HistoryPage() {
		super("历史记录");
		Vector<HistoryItem> vec = new Vector<HistoryItem>(DbHelper.instance().getHistoryList());
		listView = new JList<HistoryItem>(vec);
		listView.setCellRenderer(new ListRender());
		setContent(listView);
	}

	@Override
	public void setVisible(boolean aFlag) {
		super.setVisible(aFlag);
		if (aFlag) {
			Vector<HistoryItem> vec = new Vector<HistoryItem>(DbHelper.instance().getHistoryList());
			listView.setListData(vec);
		}
	}

	private class ListRender implements ListCellRenderer<HistoryItem> {

		@Override
		public Component getListCellRendererComponent(JList<? extends HistoryItem> list, HistoryItem value, int index,
				boolean isSelected, boolean cellHasFocus) {
			JPanel panel = new JPanel(new GridBagLayout());
			GridBagConstraints con = new GridBagConstraints();
			panel.add(new JLabel(String.valueOf(index + 1)), con);
			return panel;
		}

	}
}
