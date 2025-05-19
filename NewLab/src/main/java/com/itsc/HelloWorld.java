package com.itsc;



import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/servlet")
public class HelloWorld extends HttpServlet { 
protected void doGet(HttpServletRequest req,  HttpServletResponse res) throws IOException{ 
  
	res.setContentType("text/html");
	
	PrintWriter pw = res.getWriter(); 
	//Example 1
    pw.println("Hello World"); 
    
    //Example2 and Example 3
    String userName = req.getParameter("username");
    int age = Integer.parseInt(req.getParameter("age"));
    pw.printf("%s is %d years old", userName, age);
    
    
    //Example 4 
    
    String fname = req.getParameter("first-name");
    if(fname == null) {
    fname = "Guest";
    }
    
    pw.println("<html><body>");
    pw.println("<h1>Hello, " + fname  + "</h1>");
    pw.println("</body></html>");
    
    //Example 5
    String username = req.getParameter("username");
    String email = req.getParameter("email");
    
    pw.println("<html><body>");
    pw.println("<h1> Form Submitted</h1>");
    pw.println("<p>Username: " + username + "</p>");
    pw.println("<p>Email: " + email + "</p>");
    pw.println("<p>Email: <a href='mailto:" + email + "'>" + email +
    "</a></p>");
    pw.println("</body></html>");
    
    //Example 6
    
    
    String password = req.getParameter("password");
    
    if ("admin".equals(username) && "123".equals(password)) {
    pw.println("<h1>Welcome, " + username + "!</h1>");
    }
    else {
    pw.println("<h1>Invalid login, please try again.</h1>");
    }
} 
} 
