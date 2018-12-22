package com.chianghao.generator.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.chianghao.generator.App;

public class DBHelper {

	
	
//	private static final String URL="jdbc:mysql://127.0.0.1:3306/gateway?useUnicode=true&useSSL=false&characterEncoding=utf-8&serverTimezone=Hongkong";
//	private static final String USER="root";
//	private static final String PASSWORD="111111";
	
	/**
	 * 获取链接地址
	 * @return
	 */
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(App.URL, App.USER, App.PASSWORD);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 执行sql命令
	 * @param sql
	 */
	public void execute(String sql) {
		Connection conn = getConnection();
		Statement  st   = null;
		try {
			st = conn.createStatement();
			st.execute(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(st!=null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 查询所有的表
	 * @return
	 */
	public List<String> queryAllTable(){
		Connection conn = getConnection();
		ResultSet rs    = null; 
		try {
			DatabaseMetaData dbmeta = conn.getMetaData();	
			rs = dbmeta.getTables(null, null, "%",new String[] {"TABLE"});
			List<String> tables = new ArrayList<String>();
			while(rs.next()) {
				tables.add(rs.getString("TABLE_NAME"));
			}
			return tables;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		DBHelper dbHelper = new DBHelper();
		List<String> list = dbHelper.queryAllTable();
		for(String table:list) {
			System.out.println("\t\t"+table);
		}
	}
	
}
