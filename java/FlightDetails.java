/*
   Reading Flight data from a SQL Server Database table and finding flight with minimum fair for each destination
   and printing to the console.

*/
package com.priya.ClassDesign;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FlightDetails {
	private static final String jdbcDriver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String jdbcURL="jdbc:sqlserver://localhost:53551;databasename=PriyaDB;integratedsecurity=true;";
	public static void main(String[] args) {
		System.out.println("program started");
		try
		{
			Class.forName(jdbcDriver).newInstance();
			System.out.println("jdbc driver loaded");
		}
		catch(Exception err)
		{
				System.out.println("error loading jdbc driver");
				err.printStackTrace(System.err);
				System.exit(0);
		}		
	    Connection databaseconnection=null;
		try
			{
				databaseconnection=DriverManager.getConnection(jdbcURL);
				Statement sqlStatement = databaseconnection.createStatement();
				ResultSet rs =null;
				String queryString = "select departure,flight_code,time,rate from flightd";
				System.out.println(queryString);
			
				rs=sqlStatement.executeQuery(queryString);
		
				 Map<String,Map>m=new HashMap<>();
				 while(rs.next())
				{ 
					 
				     Map<String,String>m1=new HashMap<>();
					 String departure=rs.getString("departure");
					 String flight_code=rs.getString("flight_code");
					 String time =rs.getString("time");
					 String rate=rs.getString("rate");
					 m1.put("flight_code",flight_code );
					 m1.put("time", time);
					 m1.put("rate", rate);
					 if (m.containsKey(departure))
						{
						 Map<String, String>s = m.get(departure);
						 int current_rate = Integer.parseInt(s.get("rate"));
						 System.out.println(current_rate);
						 int new_rate = Integer.parseInt(rate);
						 System.out.println(new_rate);
						 if( new_rate < current_rate)
						 {
						     m.put(departure, m1);
						 }
						}
					 else
					 {
						 m.put(departure, m1);
					 }
				}
				System.out.println(m);	 
		        rs.close();
		        databaseconnection.close();
          }
	   catch(SQLException e)
	     {
	     	e.printStackTrace();
         }
	
	}
}
	
