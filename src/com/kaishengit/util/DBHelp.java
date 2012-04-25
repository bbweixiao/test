package com.kaishengit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DBHelp<T> {

	private String driver = "com.mysql.jdbc.Driver"; 
	private String url = "jdbc:mysql:///atm";
	private String username = "root";
	private String password = "root";
	
	/**
	 * 获取数据库连接对象
	 * @return
	 */
	public Connection getConnection() {
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url,username,password);
			return conn;
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 执行insert update delete语句
	 * @param sql
	 */
	public void executeSQL(String sql,Object... args) {
		Connection conn = null;
		PreparedStatement stat = null;
		try {
			conn = getConnection();
			stat = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				stat.setObject(i+1, args[i]);
			}
			stat.executeUpdate();
			System.out.println("SQL:" + sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stat,conn);
		}
	}
	
	public T executeQueryForObject(String sql,RowMapper<T> mapper,Object... args){
		Connection conn = getConnection();
		PreparedStatement stat = null;
		ResultSet rs = null;
		T t = null;
		
		try {
			stat = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				stat.setObject(i+1, args[i]);
			}
			rs = stat.executeQuery();
			if(rs.next()) {
				t = mapper.mapperRow(rs);
			}
			System.out.println("SQL:" + sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs,stat,conn);
		}
		return t;
		
	}
	
	/**
	 * 根据聚合函数进行查询
	 * @param sql
	 * @param args
	 * @return
	 */
	public int executeQueryForCount(String sql,Object... args){
		Connection conn = getConnection();
		PreparedStatement stat = null;
		ResultSet rs = null;
		int t = 0;
		
		try {
			stat = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				stat.setObject(i+1, args[i]);
			}
			rs = stat.executeQuery();
			if(rs.next()) {
				t = rs.getInt(1);
			}
			System.out.println("SQL:" + sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs,stat,conn);
		}
		return t;
		
	}
	
	
	
	
	public List<T> executeQueryForList(String sql,RowMapper<T> mapper,Object... args){
		Connection conn = getConnection();
		PreparedStatement stat = null;
		ResultSet rs = null;
		List<T> list = new ArrayList<T>();
		
		try {
			stat = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				stat.setObject(i+1, args[i]);
			}
			rs = stat.executeQuery();
			while(rs.next()) {
				T t = mapper.mapperRow(rs);
				list.add(t);
			}
			System.out.println("SQL:" + sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs,stat,conn);
		}
		return list;
		
	}
	
	
	/**
	 * 释放连接
	 * @param stat
	 * @param conn
	 */
	public void close(PreparedStatement stat,Connection conn) {
		close(null,stat,conn);
	}
	
	/**
	 * 释放连接
	 * @param rs
	 * @param stat
	 * @param conn
	 */
	public void close(ResultSet rs,PreparedStatement stat,Connection conn) {
		try {
			if(rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stat != null) {
					stat.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if(conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
