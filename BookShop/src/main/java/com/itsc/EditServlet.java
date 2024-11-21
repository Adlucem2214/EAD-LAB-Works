package com.itsc;

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


@WebServlet(name = "editurl", urlPatterns = { "/editurl" })


public class EditServlet extends HttpServlet {
    private static final String query = "UPDATE books SET bookname=?, bookedition=?, bookprice=? WHERE id=?";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");
        int id = Integer.parseInt(req.getParameter("id"));
        String bookName = req.getParameter("bookName");
        String bookEdition = req.getParameter("bookEdition");
        float bookPrice = Float.parseFloat(req.getParameter("bookPrice"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookregister", "root", "SLYsly001422");
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, bookName);
            ps.setString(2, bookEdition);
            ps.setFloat(3, bookPrice);
            ps.setInt(4, id);
            ps.executeUpdate();
            pw.println("<h2>Record updated successfully</h2>");
        } catch (Exception e) {
            e.printStackTrace();
        }
        pw.println("<a href='booklist'>Book List</a>");
    }
}
