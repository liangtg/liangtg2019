package com.liangtg.text.ui;

import java.io.File;

public class LocalAnalysisPage extends BasePanel {
	private File file;

	public LocalAnalysisPage(File file) {
		super();
		this.file = file;
		setOpaque(false);
	}

	private class AnalysisTask extends Thread {
		@Override
		public void run() {
		}
	}

}
