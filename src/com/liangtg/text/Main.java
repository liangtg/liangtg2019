package com.liangtg.text;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
	}

	public static class MainFrame extends JFrame {

		private static final long serialVersionUID = 3799254018553153437L;

		public MainFrame() throws HeadlessException {
			super();
			this.setSize(800, 600);
			this.setLocation(700, 500);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setResizable(false);
			setUndecorated(true);
			setContentPane(new ContentPanel());
			setVisible(true);
			new Thread() {
				public void run() {
					while (true) {
						try {
							SwingUtilities.invokeAndWait(new Runnable() {
								@Override
								public void run() {
									repaint();
								}
							});
							Thread.sleep(16);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}.start();

		}

	}

	@SuppressWarnings("serial")
	private static class ContentPanel extends JPanel {

		static int textNum = 50;
		private String[] textSource = { "甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸" };
		private float[] textSpeed = { -0.2f, -0.1f, 0.1f, 0.2f };
		String[] text = new String[textNum];
		float[] x = new float[textNum];
		float[] y = new float[textNum];
		float[] vx = new float[textNum];
		float[] vy = new float[textNum];
		private GradientPaint bgPaint;

		public ContentPanel() {
			super();
			setLayout(new GridLayout(6, 1, 10, 10));
			add(new LButton("打开文件"));
			add(new LButton("打开链接"));
			add(new LButton("历史"));
			add(new LButton("设置"));
			add(new LButton("关于"));
			add(new LButton("退出"));
		}

		private void initText() {
			for (int i = 0; i < text.length; i++) {
				createTextAt(i);
			}
		}

		private Random random = new Random();

		private void createTextAt(int index) {
			text[index] = textSource[random.nextInt(textSource.length)];
			x[index] = random.nextInt(getWidth());
			y[index] = random.nextInt(getHeight());
			vx[index] = textSpeed[random.nextInt(textSpeed.length)];
			vy[index] = textSpeed[random.nextInt(textSpeed.length)];
		}

		private void updateTextAt(int index) {
			x[index] += vx[index];
			y[index] += vy[index];
			if (x[index] < -10 || x[index] > getWidth() || y[index] < -10 || y[index] > getHeight())
				createTextAt(index);
		}

		@Override
		public void setBounds(int x, int y, int width, int height) {
			super.setBounds(x, y, width, height);
			initText();
			bgPaint = new GradientPaint(0, 0, new Color(0x2B1F38), getWidth(), getHeight(), new Color(0x594570), false);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D gra = (Graphics2D) g;
			gra.setPaint(bgPaint);
			g.fillRect(0, 0, getWidth(), getHeight());
			gra.setColor(new Color(0x888888));
			for (int i = 0; i < text.length; i++) {
				gra.drawString(text[i], x[i], y[i]);
				updateTextAt(i);
			}
		}
	}

	@SuppressWarnings("serial")
	public static class LButton extends JButton {

		public LButton(String text) {
			super(text);
			setBackground(new Color(0x80888888, true));
			setFont(new Font("", Font.BOLD, 20));
			setFocusPainted(false);
		}

	}

}
