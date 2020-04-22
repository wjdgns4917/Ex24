package com.one.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlInsertTest2 {
	private static Connection dbConnection() {
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String user="scott";
		String pass="123456";
		Connection con = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection(url,user,pass);
			System.out.println("연결 성공");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
}
	public static void main(String[] args) {
		insertData(9,"javaA","scott","2000",30000);
	}
	private static void insertData(int no,String title,String pub,String year,int price) {
		Connection con=dbConnection();
	
		try {
			Statement stmt=con.createStatement();
			String sql="INSERT INTO books VALUES"
					+ "("+no+",'"+title+"','"+pub+"','"+year+"',"+price+")";
		
			System.out.println("sql : "+sql);
			int cnt=stmt.executeUpdate(sql);
			System.out.println("insert 횟수 : "+cnt);
			if (cnt==1) {
				System.out.println("레코드 추가 성공");
			}else {
				System.out.println("레코드 추가 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}