package com.itsc;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;


@WebServlet("/circle")
public class CircleServlet extends HttpServlet {

 @Override
 protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
     res.setContentType("text/html");
     PrintWriter pw = res.getWriter();

     try {
         // Retrieve radius from request parameters
         int radius = Integer.parseInt(req.getParameter("radius"));
         String unit = req.getParameter("unit");

         // Calculate area
         double area = Math.PI * Math.pow(radius, 2);

         // Generate response
         pw.println("<html>");
         pw.println("<body>");
         pw.println("<h1>Circle Area Result</h1>");
         pw.printf("<p>Radius: %d %s</p>", radius, unit);
         pw.printf("<p>Area: %.2f square %s</p>", area, unit);
         pw.println("</body>");
         pw.println("</html>");
     } catch (NumberFormatException e) {
         // Handle invalid input
         pw.println("<html>");
         pw.println("<body>");
         pw.println("<h1>Error</h1>");
         pw.println("<p>Please provide a valid numeric value for the radius.</p>");
         pw.println("</body>");
         pw.println("</html>");
     }
 }

 @Override
 protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
     // Forward POST requests to the same processing logic as GET
     doGet(req, res);
 }
}
