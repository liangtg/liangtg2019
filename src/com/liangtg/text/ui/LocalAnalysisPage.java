package com.liangtg.text.ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

import com.liangtg.text.Nav;
import com.liangtg.text.util.DbHelper;

public class LocalAnalysisPage extends BackPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3572413755951835372L;
	private File file;
	private HashMap<String, Integer> single = new HashMap<>(5000);
	private HashMap<String, Integer> two = new HashMap<>(5000);
	private HashMap<String, Integer> three = new HashMap<>(5000);
	private HashMap<String, Integer> four = new HashMap<>(5000);
	private int fid;
	private JProgressBar progressBar;

	public LocalAnalysisPage(int id) throws Exception {
		super("");
		fid = id;
		try {
			this.file = DbHelper.instance().findFile(id);
		} catch (Exception e) {
			dismiss();
			throw e;
		}
		getTitleBar().setTitle(file.getAbsolutePath());
		Box box = Box.createVerticalBox();
		box.setBorder(new EmptyBorder(16, 16, 16, 16));
		progressBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
		box.add(progressBar);
		setContent(box);
		new AnalysisTask().start();
	}

	private void postProgress(int progress) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				progressBar.setValue(progress);
			}
		});
	}

	private void saveFail() {
		JOptionPane.showMessageDialog(this, "文件保存失败");
		dismiss();
	}

	private class AnalysisTask extends Thread {
		@Override
		public void run() {
			try {
				processFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
			postProgress(30);
			try {
				DbHelper.instance().saveHistory(fid, single, two, three, four);
				postProgress(90);
				showDetail();
			} catch (Exception e) {
				e.printStackTrace();
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						saveFail();
					}
				});
			}
			postProgress(100);
		}

		private void showDetail() {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					dismiss();
					Nav.getInstance().showSubpage(new AnalysisDetailPage(fid));
				}
			});
		}

		private void processFile() throws FileNotFoundException, IOException {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			char[] buffer = new char[1024];
			int p = reader.read(buffer);
			StringBuilder sb = new StringBuilder(2);
			while (p >= 0) {
				process(buffer, p, sb);
				p = reader.read(buffer);
			}
			reader.close();
		}

		private void process(char[] buffer, int p, StringBuilder sb) {
			for (int i = 0; i < p; i++) {
				if (isNotSpecial(buffer[i])) {
					sb.append(buffer[i]);
					updateSize(single, String.valueOf(buffer[i]));
					if (sb.length() > 1)
						updateSize(two, sb.substring(sb.length() - 2));
					if (sb.length() > 2)
						updateSize(three, sb.substring(sb.length() - 3));
					if (sb.length() > 3) {
						updateSize(four, sb.substring(sb.length() - 4));
						sb.deleteCharAt(0);
					}
				} else if (sb.length() != 0) {
					sb.delete(0, sb.length());
				}
			}
		}

		private int[][] scope = { { 0, 0x2e7f }, { 0x3000, 0x303f }, { 0xFF00, 0xFFEF } };

		private boolean isNotSpecial(char c) {
			boolean result = true;
			for (int i = 0; i < scope.length; i++) {
				result = isNotIn(c, scope[i][0], scope[i][1]);
				if (!result) {
					return false;
				}
			}
			return true;
		}

		private boolean isNotIn(char c, int start, int end) {
			return c < start || c > end;
		}

		private void updateSize(HashMap<String, Integer> map, String key) {
			Integer old = map.get(key);
			if (null == old) {
				map.put(key, 1);
			} else {
				map.put(key, old.intValue() + 1);
			}
		}
	}

}
