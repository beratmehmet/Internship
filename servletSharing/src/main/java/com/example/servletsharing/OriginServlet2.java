package com.example.servletsharing;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(name = "OriginServlet2", value = "/origin-servlet2")
public class OriginServlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Origin2:------------------");

        String str=req.getParameter("txt");

        ServletContext servletContext = this.getServletContext();

        System.out.println("Attribute: "+"987987897");

        servletContext.setAttribute("msg",str);

        if (!(str.contains("from destination->"))) {
            servletContext.getRequestDispatcher("/destination-servlet2").forward(req, resp);
        }
    }
}
