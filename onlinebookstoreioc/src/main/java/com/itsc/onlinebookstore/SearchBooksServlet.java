package com.itsc.onlinebookstore;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet("/SearchBooksServlet")
public class SearchBooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
		 private DBConnectionManager dbManager = new DBConnectionManager();
		 protected void doGet(HttpServletRequest request, HttpServletResponse response)
		 throws ServletException, IOException {
		 String searchQuery = request.getParameter("title");
		 try {
		 dbManager.openConnection();
		 Connection connection = dbManager.openConnection();
		 String sql = "SELECT * FROM books WHERE title LIKE ?";
		 PreparedStatement statement = connection.prepareStatement(sql);
		 
		 statement.setString(1, "%" + searchQuery + "%");
		 ResultSet resultSet = statement.executeQuery();
		 PrintWriter out = response.getWriter();
		 
		out.println("<table><tr><th>ID</th><th>Title</th><th>Author</th><th>Price</th></tr>");
		 while (resultSet.next()) {
		 out.println("<tr><td>" + resultSet.getInt("id") + "</td><td>" +
		 resultSet.getString("title") + "</td><td>" + resultSet.getString("author") + "</td><td>" +
		 resultSet.getString("price") + "</td><td>");
		 }
		 out.println("</table>");
		 } catch (SQLException e) {
		 e.printStackTrace();
		 } finally {
		 
		 }
		 }
}
