/*
  Reading Student info from a CSV file and inserting into SQL Server Database table.

*/
package com.priya.ClassDesign;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
	
public class StudentCreate {
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
			System.exit(-1);
		}
		Connection dbconn=null;
		try
		{
			BufferedReader br=null;
			FileReader fr = null;
			br = new BufferedReader(new FileReader("c:\\priyafiles\\student.txt") );
			String line;
			ResultSet rs=null;
			dbconn=DriverManager.getConnection(jdbcURL);
			System.out.println("connected to the database");
			String sql= "insert into Student"+"(name,id,age,class,grade)values"+"(?,?,?,?,?)";
			PreparedStatement p = dbconn.prepareStatement(sql);
			while((line=br.readLine())!=null)
			{
				System.out.println(line);
				String[] studInfo = line.split(",");
				String studName = studInfo[0];
				int studID = Integer.parseInt(studInfo[1]);
				int age = Integer.parseInt(studInfo[2]);
				int studClass = Integer.parseInt(studInfo[3]);
				String grade = studInfo[4];
				p.setString(1,studName); 
				p.setInt(2, studID);
				p.setInt(3, age);
				p.setInt(4,studClass);
				p.setString(5,grade);
				p.executeUpdate();	  

			}
			br.close();
			
			dbconn.close();
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

			
	


