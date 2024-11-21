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


@WebServlet(name = "editScreen", urlPatterns = { "/editScreen" })


public class EditScreenServlet extends HttpServlet {
    private static final String query = "SELECT bookname, bookedition, bookprice FROM books WHERE id=?";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");
        int id = Integer.parseInt(req.getParameter("id"));

        pw.println("<!DOCTYPE html>");
        pw.println("<html lang='en'>");
        pw.println("<head>");
        pw.println("<meta charset='UTF-8'>");
        pw.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        pw.println("<title>Edit Book</title>");
        pw.println("<link rel='stylesheet' href='css/bootstrap.min.css'>");
        pw.println("<style>");
        pw.println("body { background-image: url('images/library-bg.jpg'); background-size: cover; background-position: center; font-family: Arial, sans-serif; }");
        pw.println(".container { background: rgba(255, 255, 255, 0.1); border-radius: 15px; box-shadow: 0 8px 32px 0 rgba(31, 38, 135, 0.37); backdrop-filter: blur(10px); -webkit-backdrop-filter: blur(10px); border: 1px solid rgba(255, 255, 255, 0.18); padding: 20px; margin-top: 50px; width: 40rem; }");
        pw.println("h2 { font-weight: bold; text-align: center; color: #333; }");
        pw.println("</style>");
        pw.println("</head>");
        pw.println("<body>");
        pw.println("<div class='container'>");
        pw.println("<h2>Edit Book Details</h2>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookregister", "root", "SLYsly001422");
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                pw.println("<form action='editurl?id=" + id + "' method='post'>");
                pw.println("<div class='form-group'>");
                pw.println("<label for='bookName'>Book Name:</label>");
                pw.println("<input type='text' id='bookName' name='bookName' class='form-control' value='" + rs.getString(1) + "' required>");
                pw.println("</div>");
                pw.println("<div class='form-group'>");
                pw.println("<label for='bookEdition'>Book Edition:</label>");
                pw.println("<input type='text' id='bookEdition' name='bookEdition' class='form-control' value='" + rs.getString(2) + "' required>");
                pw.println("</div>");
                pw.println("<div class='form-group'>");
                pw.println("<label for='bookPrice'>Book Price:</label>");
                pw.println("<input type='number' id='bookPrice' name='bookPrice' class='form-control' value='" + rs.getFloat(3) + "' required>");
                pw.println("</div>");
                pw.println("<div class='d-flex justify-content-between'>");
                pw.println("<button type='submit' class='btn btn-success mt-3'>Save Changes</button>");
                pw.println("<button type='reset' class='btn btn-danger mt-3'>Reset</button>");
                pw.println("</div>");
                pw.println("</form>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            pw.println("<p class='text-danger'>Error fetching book details!</p>");
        }

        pw.println("<div class='text-center mt-3'>");
        pw.println("<a href='booklist' class='btn btn-primary'>Back to Book List</a>");
        pw.println("</div>");
        pw.println("</div>");
        pw.println("</body>");
        pw.println("</html>");
    }
}
