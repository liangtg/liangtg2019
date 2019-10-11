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
	/**
	 * 设置文件的位置
	 */
	public File historyDB;
	/**
	 * 设置文件的位置
	 */
	public File historyDir;

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
		download.mkdirs();
		setting = new File(appPath, "setting.prop");
		historyDB = new File(appPath, "history.db");
		historyDir = new File(appPath, "history");
		historyDir.mkdirs();
	}

}
