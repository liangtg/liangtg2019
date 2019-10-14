package com.liangtg.text.util;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.liangtg.text.db.*;

public class DbHelper {
	private static final String JDBC_H2 = "jdbc:h2:";
	private static DbHelper instance = new DbHelper();
	private Connection historyConn;

	public DbHelper() {
		super();
		org.h2.Driver.class.getName();
		try {
			historyConn = DriverManager.getConnection(JDBC_H2 + AppDir.instance().historyDB.getAbsolutePath());
			Statement statement = historyConn.createStatement();
			statement.execute("CREATE TABLE IF NOT EXISTS history(id INTEGER PRIMARY KEY AUTO_INCREMENT,"
					+ "url VARCHAR, file_path VARCHAR, db_path VARCHAR, ts INT8);");
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int addLocal(File file) throws SQLException {
		PreparedStatement prepareCall = historyConn.prepareStatement("INSERT INTO history(file_path,ts) VALUES(?,?);",
				Statement.RETURN_GENERATED_KEYS);
		prepareCall.setString(1, file.getAbsolutePath());
		prepareCall.setLong(2, System.currentTimeMillis());
		return prepareCall.executeUpdate();
	}

	public File findFile(int id) throws SQLException {
		CallableStatement call = historyConn.prepareCall("SELECT file_path FROM history WHERE id=?;",
				ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		call.setInt(1, id);
		call.closeOnCompletion();
		ResultSet set = call.executeQuery();
		File result = null;
		if (set.first()) {
			result = new File(set.getString(1));
		}
		return result;

	}

	public void saveHistory(int id, HashMap<String, Integer>... first) throws Exception {
		File db = File.createTempFile("his", null, AppDir.instance().historyDir);
		db.delete();
		Connection connection = DriverManager.getConnection(JDBC_H2 + db.getAbsolutePath());
		connection.setAutoCommit(false);
		connection.prepareCall("create table if not exists str(str varchar,num integer,len integer);").execute();
		PreparedStatement statement = connection.prepareStatement("INSERT INTO str(str,num,len) VALUES(?,?,?);");
		for (int i = 0; i < first.length; i++) {
			addBatch(first[i], statement, i + 1);
			statement.executeBatch();
			statement.clearBatch();
		}
		connection.commit();
		connection.close();
		CallableStatement call = historyConn.prepareCall("UPDATE history SET db_path = ? WHERE id = ?;");
		call.setString(1, db.getAbsolutePath());
		call.setInt(2, id);
		call.execute();
		historyConn.commit();
	}

	private void addBatch(HashMap<String, Integer> first, PreparedStatement statement, int len) throws SQLException {
		int i = 0;
		for (Iterator<Entry<String, Integer>> iterator = first.entrySet().iterator(); iterator.hasNext(); i++) {
			Entry<String, Integer> type = (Entry<String, Integer>) iterator.next();
			statement.setString(1, type.getKey());
			statement.setInt(2, type.getValue());
			statement.setInt(3, len);
			statement.addBatch();
			if (i % 1000 == 0) {
				statement.executeBatch();
				statement.clearBatch();
			}
		}
	}

	public void close() {
		try {
			historyConn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static DbHelper instance() {
		return instance;
	}

	public ArrayList<HistoryItem> getHistoryList() {
		ArrayList<HistoryItem> result = new ArrayList<HistoryItem>();
		try {
			ResultSet set = historyConn.prepareStatement("select * from history order by ts;").executeQuery();
			while (set.next()) {
				HistoryItem item = new HistoryItem();
				item.id = set.getInt(1);
				item.url = set.getString(2);
				item.file = set.getString(3);
				item.db = set.getString(4);
				item.date = set.getLong(5);
				result.add(item);
			}
			set.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
