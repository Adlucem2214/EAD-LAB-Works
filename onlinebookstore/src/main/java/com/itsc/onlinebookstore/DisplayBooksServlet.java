package com.itsc.onlinebookstore;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.itsc.onlinebookstore.DBConnectionManager;


@WebServlet("/DisplayBooksServlet")
public class DisplayBooksServlet extends HttpServlet {
	 private DBConnectionManager dbConnectionManager = new DBConnectionManager();
	 
	 protected void doGet(HttpServletResponse resp) throws ServletException, IOException, SQLException {
	 
	 try (Connection connection = dbConnectionManager.openConnection()) {
	 Statement statement = connection.createStatement();
	 ResultSet resultSet = statement.executeQuery("SELECT * FROM books");
	 
	 resp.setContentType("text/html");
	 resp.getWriter().write("<table border='1'>");
	 resp.getWriter().write("<tr><th>ID</th><th>Title</th><th>Author</th><th>ISBN</th><th>Publication Date</th></tr>");
	 while (resultSet.next()) {
	 resp.getWriter().write("<tr>");
	 resp.getWriter().write("<td>" + resultSet.getInt("id") + "</td>");
	 resp.getWriter().write("<td>" + resultSet.getString("title") + "</td>");
	 resp.getWriter().write("<td>" + resultSet.getString("author") + "</td>");
	 resp.getWriter().write("<td>" + resultSet.getString("isbn") + "</td>");
	 resp.getWriter().write("<td>" + resultSet.getDate("publication_date") + "</td>");
	 resp.getWriter().write("</tr>");
	 }
	 resp.getWriter().write("</table>");
	 } }}
	 