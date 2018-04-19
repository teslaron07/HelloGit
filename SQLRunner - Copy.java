package com.learn.java;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQLRunner {
	
	private static Connection conn = null;
	private static Statement  stmt = null;
	private static ResultSet  rs   = null;
	private static PreparedStatement ps = null;
	private static ResultSetMetaData rsmd = null;
	
	private static String User="system";
	private static String Password ="Jarvis@1";
	private static String Host ="jdbc:oracle:thin:@localhost:1521:xe";
	
	public static Connection getSqlConnection() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(Host,User , Password);
		} catch (ClassNotFoundException|SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static List<Map<String,Object>> selectAll(String Query,Object... args) throws SQLException{
		     ps = conn.prepareStatement(Query);
		    try {
		      setParameters(ps, args);
		      rs = ps.executeQuery();
		      return getResults(rs);
		    } finally {
		      try {
		        ps.close();
		      } catch (SQLException e) {
		        //ignore
		      }
		    }
		
	}

	 public static void setParameters(PreparedStatement ps, Object[] args) {
		 for (int i = 0, n = args.length; i < n; i++) {
			 try{ 
			 if (args[i] == null) {
		        throw new SQLException("SqlRunner requires an instance of Null to represent typed null values for JDBC compatibility");
		      } 
		      else if (args[i] instanceof Date) {
		          ps.setTimestamp(i++, new Timestamp(((Date) args[i]).getTime()));
		      } else if (args[i] instanceof Integer) {
		          ps.setInt(i+1, (Integer) args[i]);
		      } else if (args[i] instanceof Long) {
		          ps.setLong(i++, (Long) args[i]);
		      } else if (args[i] instanceof Double) {
		          ps.setDouble(i++, (Double) args[i]);
		      } else if (args[i] instanceof Float) {
		          ps.setFloat(i++, (Float) args[i]);
		      } else {
		          ps.setString(i+1, (String) args[i]);
		       }
		      }catch(Exception e) {
		    	e.printStackTrace();  
		      }
		     }
	}

	public static List<Map<String, Object>> getResults(ResultSet rs) throws SQLException {
		    try {
		      List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		      List<String> columns = new ArrayList<String>();
		      ResultSetMetaData rsmd = rs.getMetaData();
		      for (int i = 0, n = rsmd.getColumnCount(); i < n; i++) {
		        columns.add(rsmd.getColumnLabel(i + 1));
		      }
		      while (rs.next()) {
		        Map<String, Object> row = new HashMap<String, Object>();
		        for (int i = 0, n = columns.size(); i < n; i++) {
		          String name = columns.get(i);		      
		          row.put(name,rs.getObject(name));
		          }
		        list.add(row);		        	     
		      }
		      for (int i = 0; i < list.size(); i++) {
					System.out.println(list.get(i));
				}
		      return list;
		    } finally {
		      if (rs != null) {
		        try {
		            rs.close();
		        } catch (Exception e) {
		          // ignore
		        }
		      }
		    }
		  }

		}
