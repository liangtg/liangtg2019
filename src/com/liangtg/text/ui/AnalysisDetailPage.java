package com.liangtg.text.ui;

import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.AbstractTableModel;

import com.liangtg.text.db.HistoryDetail;
import com.liangtg.text.db.HistoryDetail.DetailItem;
import com.liangtg.text.util.DbHelper;

/**
 * @author liangtg
 *
 */
public class AnalysisDetailPage extends BackPage {

	public AnalysisDetailPage(int id) {
		super("详情");
		HistoryDetail detail = null;
		try {
			detail = DbHelper.instance().getHistoryDetail(id);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(AnalysisDetailPage.this, "打开失败");
			dismiss();
			return;
		}
		JTable table = new JTable(new TModel(detail));
		table.setFont(new Font(null, Font.PLAIN, 16));
		JScrollPane scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		setContent(scrollPane);
	}

	private class TModel extends AbstractTableModel {
		/**
		 * 
		 */
		private static final long serialVersionUID = -2040644200518031420L;
		HistoryDetail detail;

		public TModel(HistoryDetail detail) {
			super();
			this.detail = detail;
		}

		@Override
		public int getRowCount() {
			return Math.max(detail.single.size(),
					Math.max(detail.two.size(), Math.max(detail.three.size(), detail.four.size())));
		}

		@Override
		public int getColumnCount() {
			return 8;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			int col = columnIndex / 2;
			int pcol = columnIndex % 2;
			if (col == 0) {
				return getData(detail.single, rowIndex, pcol);
			} else if (col == 1) {
				return getData(detail.two, rowIndex, pcol);
			} else if (col == 2) {
				return getData(detail.three, rowIndex, pcol);
			} else if (col == 3) {
				return getData(detail.four, rowIndex, pcol);
			}
			return null;
		}

		private String getData(ArrayList<DetailItem> data, int row, int col) {
			if (row >= data.size()) {
				return "";
			} else if (col == 0) {
				return data.get(row).text;
			} else {
				return String.valueOf(data.get(row).count);
			}
		}

		private String name[] = { "单字", "次数", "双字", "次数", "三字", "次数", "四字", "次数" };

		@Override
		public String getColumnName(int column) {
			return name[column];
		}

	}

}
