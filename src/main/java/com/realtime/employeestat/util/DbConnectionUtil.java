package com.realtime.employeestat.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public enum DbConnectionUtil {

	dbutil;

	private static final HikariConfig CONFIG = new HikariConfig(
			DbConnectionUtil.class.getResource("/hikaricp.properties").getPath());;
	private static HikariDataSource dataSource = new HikariDataSource(CONFIG);

	public Connection getConnection() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
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
