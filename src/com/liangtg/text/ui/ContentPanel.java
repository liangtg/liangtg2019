package com.liangtg.text.ui;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ContentPanel extends JPanel {

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
		Graphics2D gra = (Graphics2D) g;
		gra.setPaint(bgPaint);
		g.fillRect(0, 0, getWidth(), getHeight());
		gra.setColor(new Color(0x888888));
		for (int i = 0; i < text.length; i++) {
			gra.drawString(text[i], x[i], y[i]);
			updateTextAt(i);
		}
	}
	
	@Override
	protected void paintChildren(Graphics g) {
		super.paintChildren(g);
		g.setPaintMode();
	}
}