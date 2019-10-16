package com.liangtg.text.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import com.liangtg.text.Nav;
import com.liangtg.text.db.HistoryItem;
import com.liangtg.text.util.DbHelper;

/**
 * @author liangtg
 *
 */
public class HistoryPage extends BackPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4401055399335488918L;
	private JComponent listView;

	public HistoryPage() {
		super("历史记录");
		listView = new JPanel(new GridBagLayout());
		listView.setOpaque(false);
		listView.setBackground(new Color(0x80ffffff, true));
		setContent(new JScrollPane(listView));
	}

	@Override
	public void setVisible(boolean aFlag) {
		super.setVisible(aFlag);
		if (aFlag) {
			ArrayList<HistoryItem> data = DbHelper.instance().getHistoryList();
			listView.removeAll();
			for (int i = 0; i < data.size(); i++) {
				GridBagConstraints con = new GridBagConstraints();
				con.weightx = 1;
				con.fill = GridBagConstraints.BOTH;
				con.gridwidth = GridBagConstraints.REMAINDER;
				listView.add(extracted(data.get(i), i), con);
				newLine(listView);
			}
			GridBagConstraints con = new GridBagConstraints();
			con.anchor = GridBagConstraints.PAGE_END;
			con.weighty = 1;
			listView.add(Box.createVerticalStrut(8), con);
		}
	}

	private void newLine(JComponent panel) {
		GridBagConstraints con = new GridBagConstraints();
		con.gridwidth = GridBagConstraints.REMAINDER;
		con.gridx = 0;
		panel.add(Box.createVerticalStrut(8), con);
	}

	private JPanel extracted(HistoryItem value, int index) {
		JPanel content = new JPanel(new GridBagLayout());
		content.setBackground(new Color(0x0055ccff, true));
		GridBagConstraints con;
		content.add(new JLabel(String.valueOf(index + 1)));
		con = new GridBagConstraints();
		con.weightx = 1;
		con.fill = GridBagConstraints.HORIZONTAL;
		JLabel url = new JLabel("<html>" + value.url);
		url.setPreferredSize(new Dimension(10, 20));
		content.add(url, con);
		con = new GridBagConstraints();
		con.weightx = 1;
		con.fill = GridBagConstraints.HORIZONTAL;
		JLabel fileLabel = new JLabel("<html>" + value.file + "</html>");
		fileLabel.setPreferredSize(new Dimension(10, 20));
		content.add(fileLabel, con);
		JButton button = new JButton("查看");
		button.addActionListener(new ClickAction(button, new OnClickListener() {
			@Override
			public void onClick(Component component) {
				Nav.getInstance().showSubpage(new AnalysisDetailPage(value.id));
			}
		}));
		content.add(button);
		return content;
	}

}
