package com.github.xzzpig.pigapi.sql.sqlite;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.github.xzzpig.pigapi.sql.TSql;

public class TSqlite extends TSql {
	Connection c;

	public TSqlite(String name) {
		type = Type.SQLITE;
		if (!name.endsWith(".db")) {
			name += ".db";
		}
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + name);
		} catch (Exception e) {
		}
		this.conn = c;
	}

	public TSqlite(File file) {
		this(file.getPath());
	}

	public void set(String table, String key, Object value) {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		ObjectOutputStream oout;
		try {
			oout = new ObjectOutputStream(bout);
			oout.writeObject(value);
			oout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] bs = bout.toByteArray();
		try {
			bout.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (!isTabbleExist(table)) {
			try {
				c.createStatement().execute("CREATE TABLE " + table + " ([key] TEXT PRIMARY KEY,value BLOB);");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			c.createStatement().execute("delete from " + table + " where key == '" + key + "' or value is NULL");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String sb = ("insert into " + table + "  (key,value) values('" + key + "',?)");
		PreparedStatement prep = null;
		try {
			prep = c.prepareStatement(sb);
			prep.setBytes(1, bs);
			prep.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T get(String table, String key, Class<T> type) {
		ResultSet result = null;
		try {
			result = c.createStatement().executeQuery("select value from " + table + " where key == '" + key + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			byte[] bs = (byte[]) result.getObject(1);
			if (bs == null)
				return null;
			ByteArrayInputStream bin = new ByteArrayInputStream(bs);
			ObjectInputStream oin = new ObjectInputStream(bin);
			oin.close();
			bin.close();
			Object object = oin.readObject();
			return (T) object;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean isTabbleExist(String tableName) {
		if (tableName == null) {
			return false;
		}
		Statement stm = null;
		try {
			stm = c.createStatement();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			stm.executeQuery("select * from " + tableName);
		} catch (SQLException e1) {
			return false;
		}
		return true;
	}

	@Override
	protected void build() {

	}

	@Override
	public void close() {
	}
}