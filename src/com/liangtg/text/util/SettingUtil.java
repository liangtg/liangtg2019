package com.liangtg.text.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.liangtg.text.Main;

public class SettingUtil {
	private Properties defPro = new Properties();
	private Properties properties;
	public static String KEY_SCREEN = "screen";
	public static final String[] SCREEN_LIST = { "800x600", "1440x900" };
	private static SettingUtil instance = new SettingUtil();

	private SettingUtil() {
		defPro.setProperty(KEY_SCREEN, SCREEN_LIST[0]);
		properties = new Properties(defPro);
		File savePath = AppDir.instance().setting;
		if (savePath.exists()) {
			try {
				properties.load(new FileInputStream(savePath));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static SettingUtil instance() {
		return instance;
	}

	public String getScreen() {
		return properties.getProperty(KEY_SCREEN);
	}

	public void setScreen(String screen) {
		properties.put(KEY_SCREEN, screen);
	}

	public void store() throws FileNotFoundException, IOException {
		FileOutputStream out = new FileOutputStream(AppDir.instance().setting);
		properties.store(out, "");
		out.close();
	}

}
