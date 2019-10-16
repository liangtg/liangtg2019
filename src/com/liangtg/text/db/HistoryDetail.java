package com.liangtg.text.db;

import java.util.ArrayList;

public class HistoryDetail {
	public ArrayList<DetailItem> single = new ArrayList<>();
	public ArrayList<DetailItem> two = new ArrayList<>();
	public ArrayList<DetailItem> three = new ArrayList<>();
	public ArrayList<DetailItem> four = new ArrayList<>();

	public static class DetailItem {
		public String text;
		public int count;
	}

}
