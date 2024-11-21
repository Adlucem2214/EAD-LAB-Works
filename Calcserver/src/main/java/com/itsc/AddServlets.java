package com.itsc;


import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddServlets extends HttpServlet{
public void service(
      HttpServletRequest req,
      HttpServletResponse resp)

{
   int n1 = Integer.parseInt(req.getParameter("num1"));
   int n2 = Integer.parseInt(req.getParameter("num2"));

System.out.println(n1 + n2);
}
}
