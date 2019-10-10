package com.liangtg.text.ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class LocalAnalysisPage extends BackPage {
	private File file;
	private HashMap<String, Integer> single = new HashMap<>(5000);
	private HashMap<String, Integer> two = new HashMap<>(5000);

	public LocalAnalysisPage(File file) {
		super(file.getAbsolutePath());
		this.file = file;
		new AnalysisTask().start();
	}

	private class AnalysisTask extends Thread {
		@Override
		public void run() {
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				char[] buffer = new char[1024];
				int p = reader.read(buffer);
				StringBuilder sb = new StringBuilder(2);
				while (p >= 0) {
					for (int i = 0; i < p; i++) {
						if (isNotSpecial(buffer[i])) {
							sb.append(buffer[i]);
							if (sb.length() == 2) {
								updateSize(two, sb.toString());
								sb.deleteCharAt(0);
							}
							updateSize(single, sb.toString());
						} else if (sb.length() != 0) {
							sb.deleteCharAt(0);
						}

					}
					p = reader.read(buffer);
				}
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			BufferedWriter bw = null;
			try {
				File write = new File("sql.txt");
				bw = new BufferedWriter(new FileWriter(write));
				bw.write("create table if not exists str(str text,num integer,len integer);\nbegin;\n");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			System.out.println(single.size() + two.size());
			for (Iterator<Entry<String, Integer>> iterator = single.entrySet().iterator(); iterator.hasNext();) {
				Entry<String, Integer> type = (Entry<String, Integer>) iterator.next();
				String line = String.format("INSERT INTO str(str,num,len) VALUES('%s',%d,1);", type.getKey(),
						type.getValue());
				try {
					bw.write(line);
					bw.write('\n');
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			for (Iterator<Entry<String, Integer>> iterator = two.entrySet().iterator(); iterator.hasNext();) {
				Entry<String, Integer> type = (Entry<String, Integer>) iterator.next();
				String line = String.format("INSERT INTO str(str,num,len) VALUES('%s',%d,2);", type.getKey(),
						type.getValue());
				try {
					bw.write(line);
					bw.write('\n');
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				bw.write("commit;");
				bw.flush();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
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
