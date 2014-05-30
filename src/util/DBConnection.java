package util;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class DBConnection {

	private Connection conn=null;
	// 连接MySql数据库，用户名和密码都是root
	private String url = "jdbc:mysql://localhost:3306/nailldb";
	private String username = "root";
	private String password = "root";

	public DBConnection() {
		try {
			/*
			InputStream in = new BufferedInputStream(new FileInputStream(
					DBConnection.class.getClassLoader().getResource("")+"db.properties"));
			Properties p = new Properties();
			p.load(in);
			url = p.getProperty("url");
			username = p.getProperty("username");
			password = p.getProperty("password");
			System.out.println("url = "+url);
			*/
			
			// 加载MySql的驱动类
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("找不到驱动程序类 ，加载驱动失败！");
			e.printStackTrace();
		} 
	}

	public Connection getConnection() {
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException se) {
			System.out.println("数据库连接失败！");
			se.printStackTrace();
		}
		return conn;
	}

	public void colseConnection(Connection conn) {
		if (conn != null) { // 关闭连接对象
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
