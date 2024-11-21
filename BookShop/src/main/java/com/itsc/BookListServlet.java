package com.itsc;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "booklist", urlPatterns = { "/booklist" })

public class BookListServlet extends HttpServlet {
    private static final String query = "SELECT id, bookname, bookedition, bookprice FROM books";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");

        pw.println("<!DOCTYPE html>");
        pw.println("<html lang='en'>");
        pw.println("<head>");
        pw.println("<meta charset='UTF-8'>");
        pw.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        pw.println("<title>Book List</title>");
        pw.println("<link rel='stylesheet' href='css/bootstrap.min.css'>");
        pw.println("<style>");
        pw.println("body { background-image: url('images/library-bg.jpg'); background-size: cover; background-position: center; font-family: Arial, sans-serif; }");
        pw.println(".container { background-color: rgba(255, 255, 255, 0.9); border-radius: 10px; padding: 20px; margin-top: 50px; box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2); }");
        pw.println(".table th, .table td { text-align: center; }");
        pw.println("</style>");
        pw.println("</head>");
        pw.println("<body>");
        pw.println("<div class='container'>");
        pw.println("<h2 class='text-center text-primary'>Book List</h2>");
        pw.println("<table class='table table-bordered table-hover'>");
        pw.println("<thead class='thead-dark'>");
        pw.println("<tr>");
        pw.println("<th>Book ID</th>");
        pw.println("<th>Book Name</th>");
        pw.println("<th>Book Edition</th>");
        pw.println("<th>Book Price</th>");
        pw.println("<th>Edit</th>");
        pw.println("<th>Delete</th>");
        pw.println("</tr>");
        pw.println("</thead>");
        pw.println("<tbody>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookregister", "root", "SLYsly001422");
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                pw.println("<tr>");
                pw.println("<td>" + rs.getInt(1) + "</td>");
                pw.println("<td>" + rs.getString(2) + "</td>");
                pw.println("<td>" + rs.getString(3) + "</td>");
                pw.println("<td>" + rs.getFloat(4) + "</td>");
                pw.println("<td><a href='editScreen?id=" + rs.getInt(1) + "' class='btn btn-warning btn-sm'>Edit</a></td>");
                pw.println("<td><a href='deleteurl?id=" + rs.getInt(1) + "' class='btn btn-danger btn-sm'>Delete</a></td>");
                pw.println("</tr>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            pw.println("<tr><td colspan='6' class='text-danger'>Error fetching data!</td></tr>");
        }

        pw.println("</tbody>");
        pw.println("</table>");
        pw.println("<div class='text-center mt-3'>");
        pw.println("<a href='home.html' class='btn btn-primary'>Back to Home</a>");
        pw.println("</div>");
        pw.println("</div>");
        pw.println("</body>");
        pw.println("</html>");
    }
}
