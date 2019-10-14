package com.liangtg.text;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.liangtg.text.ui.ContentPanel;
import com.liangtg.text.util.DbHelper;
import com.liangtg.text.util.SettingUtil;

public class Main {
	private static MainFrame frame;
	private static boolean run = true;

	public static void main(String[] args) {
		frame = new MainFrame();
		String[] setScreen = SettingUtil.instance().getScreen().split("x");
		int width = Integer.parseInt(setScreen[0]);
		int height = Integer.parseInt(setScreen[1]);
		resize(width, height);
		frame.startShow();
	}

	public static void resize(int width, int height) {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(width, height);
		frame.setLocation((screen.width - width) / 2, (screen.height - height) / 2);
	}

	public static void exit() {
		run = false;
		frame.dispose();
		DbHelper.instance().close();
	}

	public static class MainFrame extends JFrame {

		private static final long serialVersionUID = 3799254018553153437L;

		public MainFrame() throws HeadlessException {
			super();
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setResizable(false);
			setUndecorated(true);
			new Thread() {
				public void run() {
					while (run) {
						try {
							SwingUtilities.invokeAndWait(new Runnable() {
								@Override
								public void run() {
									repaint();
								}
							});
							Thread.sleep(16);
						} catch (Exception e) {
						}
					}
				}
			}.start();

		}

		private void startShow() {
			ContentPanel contentPane = new ContentPanel();
			setContentPane(contentPane);
			setVisible(true);
			Nav.getInstance().setContainer(contentPane);
			Nav.getInstance().showHome();
		}

	}

}
