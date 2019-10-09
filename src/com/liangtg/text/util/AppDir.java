package com.liangtg.text.util;

import java.io.File;

import com.liangtg.text.Main;

/**
 * @author liangtg
 *
 */
public class AppDir {

	/**
	 * app所在目录
	 */
	public File appPath;
	/**
	 * 下载目录
	 */
	public File download;

	/**
	 * 设置文件的位置
	 */
	public File setting;

	private static AppDir ins = new AppDir();

	public static AppDir instance() {
		return ins;
	}

	public AppDir() {
		super();
		init();
	}

	private void init() {
		this.appPath = new File(Main.class.getResource("/").getPath());
		download = new File(appPath, "download");
		if (!download.exists()) {
			download.mkdir();
		}
		setting = new File(appPath, "setting.prop");
	}

}
