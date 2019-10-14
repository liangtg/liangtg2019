package com.liangtg.text.ui;

import java.awt.Color;

import javax.swing.JLabel;

/**
 * @author liangtg
 *
 *         关于页面
 */
public class AboutPage extends BackPage {
	private String license = "<html><h1 align=\"center\">License</h1><h2 align=\"center\">H2 Database Engine</h2><h2>H2 is dual licensed and available under the MPL 2.0 (Mozilla Public License Version 2.0) or under the EPL 1.0 (Eclipse Public License). There is a license FAQ for both the MPL and the EPL.\n"
			+ "\n" + "You can use H2 for free.\n"
			+ "You can integrate it into your applications (including in commercial applications) and distribute it.\n"
			+ "Files containing only your code are not covered by this license (it is 'commercial friendly').\n"
			+ "Modifications to the H2 source code must be published.\n"
			+ "You don't need to provide the source code of H2 if you did not modify anything.\n"
			+ "If you distribute a binary that includes H2, you need to add a disclaimer of liability</h2>";

	/**
	 * 
	 */
	private static final long serialVersionUID = -5816960208672874046L;

	public AboutPage() {
		super("关于");
		JLabel label = new JLabel(license);
		label.setVerticalAlignment(JLabel.TOP);
		label.setForeground(Color.white);
		setContent(label);
	}

}
