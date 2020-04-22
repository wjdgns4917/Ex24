package com.one.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlInsertTest {
	private static Connection dbConnection() {
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String user="scott";
		String pass="123456";
		Connection con = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection(url,user,pass);
			System.out.println("���� ����");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
}
	public static void main(String[] args) {
		Connection con=dbConnection();
	
		try {
			Statement stmt=con.createStatement();
			String sql="INSERT INTO books VALUES"
					+ "(7,'javaA','scott','2000',30000)";
		
			System.out.println("sql : "+sql);
			int cnt=stmt.executeUpdate(sql);
			System.out.println("insert Ƚ�� : "+cnt);
			if (cnt==1) {
				System.out.println("���ڵ� �߰� ����");
			}else {
				System.out.println("���ڵ� �߰� ����");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}