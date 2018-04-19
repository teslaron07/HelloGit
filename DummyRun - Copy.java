package com.learn.java;

import java.sql.Connection;
import java.sql.SQLException;

public class DummyRun {

	private static Connection Conn;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
			Conn = SQLRunner.getSqlConnection();
			String Query = "Select * from account where account_id =? and cust_id =? ";
			System.out.println("--------Begin--------------");
			SQLRunner.selectAll(Query, "1","1");
			
			System.out.println("--------END--------------");
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
