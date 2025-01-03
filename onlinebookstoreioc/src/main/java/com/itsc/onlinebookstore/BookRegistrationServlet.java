package com.itsc.onlinebookstore;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;




@WebServlet("/BookRegistrationServlet")
public class BookRegistrationServlet extends HttpServlet {
    private static final String query = "INSERT INTO books (title, author,price) VALUES (?, ?, ?)";

    	 
    	 ApplicationContext context = new ClassPathXmlApplicationContext("applicationConfig.xml");
    	 DBConnectionManager dbManager = context.getBean("bookRegistrationServlet", DBConnectionManager.class);
    	 
    	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
    	 throws ServletException, IOException {
    	 String title = request.getParameter("title");
    	 String author = request.getParameter("author");
    	 String price = request.getParameter("price");
    	 
    	 try {
    	 dbManager.openConnection();
    	 Connection connection = dbManager.openConnection();
    	 String sql = "INSERT INTO books (title, author, price) VALUES (?, ?, ?)";
    	 
 
    	 PreparedStatement statement = connection.prepareStatement(sql);
    	 statement.setString(1, title);
    	 statement.setString(2, author);
    	 statement.setString(3, price);
 
    	 statement.executeUpdate();
    	 response.getWriter().println("Book registered successfully.");
    	 } catch (SQLException e) {
    	 e.printStackTrace();
    	 } 
    	 }
}
    	 
    	 
