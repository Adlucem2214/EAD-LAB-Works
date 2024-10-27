package com.itsc;

import java.sql.Connection;
import java.sql.DriverManager;

public class Demo_2 {

	public static void main(String[] args) {
		 try {
	            //String driver = "com.mysql.cj.jdbc.Driver"; 
	            String url = "jdbc:mysql://localhost:3306/student"; 
	            String username = "root"; 
	            String password = "SLYsly001422"; 
	            
	            
	            
	            Connection conn = DriverManager.getConnection(url, username, password);
	            System.out.println("Established Connection");

	            // Close the connection (optional, but recommended)
	            conn.close();

	        } 
	        catch (Exception e) {
	            System.out.println("Failed to establish a connection to the database.");
	            e.printStackTrace();
	        }
		// TODO Auto-generated method stub
		//Connection conn = DriverManager.getConnection(null)
	}

}
