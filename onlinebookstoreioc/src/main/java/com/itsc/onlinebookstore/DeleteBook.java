package com.itsc.onlinebookstore;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@WebServlet("/DeleteBook")
public class DeleteBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
		 private DBConnectionManager dbManager = new DBConnectionManager();
		 protected void doPost(HttpServletRequest request, HttpServletResponse response)
		 throws ServletException, IOException {
		 int bookId = Integer.parseInt(request.getParameter("id"));
		
		 try {
		 dbManager.openConnection();
		 Connection connection = dbManager.openConnection();
		 String sql = "DELETE FROM books WHERE id = ?";
		 PreparedStatement statement = connection.prepareStatement(sql);
		 statement.setInt(1, bookId);
		 statement.executeUpdate();
		 response.getWriter().println("Book deleted successfully.");
		 } catch (SQLException e) {
		 e.printStackTrace();
		 } finally {
		 
//		 try {
//		 dbManager.closeConnection(Connection connection);
//		 } catch (SQLException e) {
//		 e.printStackTrace();
//		 }
		 }
		 }
 

}
