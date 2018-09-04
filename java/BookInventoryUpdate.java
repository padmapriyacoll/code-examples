/*
   Example using multiple database connections. Program reads from SQL Server database tables and updates book inventory info. 

 */
package com.priya.ClassDesign;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class BookInventoryUpdate {
	private static final String jdbcDriver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String jdbcURL="jdbc:sqlserver://localhost:53551;databasename=PriyaDB;integratedsecurity=true;";
	private static final String jdbcDriver1="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String jdbcURL1="jdbc:sqlserver://localhost:53551;databasename=PriyaDB;integratedsecurity=true;";

	public static void main(String[] args) {
		System.out.println("program started");
		try
		{
			Class.forName(jdbcDriver).newInstance();
			System.out.println("jdbc driver loaded");
			Class.forName(jdbcDriver1).newInstance();
			System.out.println("jdbc driver loaded");
		}
		catch(Exception err)
		{
			System.out.println("error loading jdbc driver");
			err.printStackTrace(System.err);
			System.exit(-1);
		}	
		Connection databaseconnection=null;
		Connection databaseconnection1=null;
		try
		{
			databaseconnection=DriverManager.getConnection(jdbcURL);
			databaseconnection1=DriverManager.getConnection(jdbcURL1);
			System.out.println("connected to the database");
			Statement sqlStatement = databaseconnection.createStatement();
			Statement sqlStatement1 = databaseconnection1.createStatement();
			ResultSet rs =null;
			ResultSet rs1=null;
			String queryString1="select count from books_info where book_id=?";
			PreparedStatement p1 = databaseconnection1.prepareStatement(queryString1); 
			String queryString = "select book_id from check_info where check_in_date is null";
			System.out.println(queryString);
			rs=sqlStatement.executeQuery(queryString);
			String sql= "insert into book_inventory1(book_id,count) values(?,?)"; 
			 PreparedStatement p = databaseconnection.prepareStatement(sql); 
			 Map<Integer,Integer>m=new HashMap<>();
			 while(rs.next())
			{   
		           int book_id = rs.getInt("book_id");
	               if(m.containsKey(book_id))
					{
					    int s = m.get(book_id)+1;
						m.put(book_id,s);
					}
					else
					{
						m.put(book_id,1);
					}
			}    
			for (Map.Entry<Integer, Integer> entry :m.entrySet())
			{         
				       int count1=0;
					   int book_id=entry.getKey();
					   int count = entry.getValue(); 
					   p1.setInt(1, book_id);
					   rs1=p1.executeQuery();
					   while(rs1.next())
					   {
						    count1 = rs1.getInt(1);
					   }
					   count=count1-count;
					   p.setInt(1,book_id);
					   p.setInt(2, count);
					   p.executeUpdate();

			}
			rs.close();
			rs1.close();
			databaseconnection.close();
			databaseconnection1.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}

	}
}

