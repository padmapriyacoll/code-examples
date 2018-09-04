/*
Example to read data from a text file and update a table in a SQL Server Database table.
Using JDBC to connect to the database.
*/

package com.priya.ClassDesign;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class EmpUpdate 
{
	private static final String jdbcDriver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String jdbcURL="jdbc:sqlserver://localhost:53551;databasename=PriyaDB;integratedsecurity=true;";

	
	public static void main(String[] args)
	{
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
			BufferedReader br=null;
			FileReader fr = null;
			br = new BufferedReader(new FileReader("c:\\priyafiles\\employeesalary1.txt") );
			String line;
			String [] line1;
			ResultSet rs=null;
			while((line=br.readLine())!=null)
			{
				System.out.println(line);
				line1=line.split(",");
				int sal =Integer.parseInt( line1[2])+500;
				int id = Integer.parseInt(line1[0]);   
				databaseconnection=DriverManager.getConnection(jdbcURL);
				System.out.println("connected to the database");
				PreparedStatement p = databaseconnection.prepareStatement("update  emp_detail set salary=? where emp_id=?");
				p.setInt(1,sal); 
				p.setInt(2, id);
		        p.executeUpdate();	  
			}
			br.close();
			databaseconnection.close();
		    System.out.println("closing database connection");
		}
	    catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	
	}
		
	
}

		

	
	
		
	
	

	


