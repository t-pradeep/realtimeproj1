package com.lwl.ems.util;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public enum DbConnectionUtil {
	dbutil;

	private static final Properties PROPERTIES = new Properties();
	static {
		try {
			PROPERTIES.load(DbConnectionUtil.class.getResourceAsStream("/db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(PROPERTIES.getProperty("url"), PROPERTIES);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public void close(ResultSet rs, Statement st, Connection con) {
		try {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close(Statement st, Connection con) {
		try {

			if (st != null)
				st.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close(ResultSet rs, PreparedStatement pst, Connection con) {
		try {
			if (rs != null)
				rs.close();
			if (pst != null)
				pst.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close(PreparedStatement pst, Connection con) {
		try {

			if (pst != null)
				pst.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close(ResultSet rs, CallableStatement cst, Connection con) {
		try {
			if (rs != null)
				rs.close();
			if (cst != null)
				cst.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close(CallableStatement cst, Connection con) {
		try {

			if (cst != null)
				cst.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
