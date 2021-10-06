package com.example.servletsharing;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "OriginServlet", value = "/origin-servlet")
public class OriginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Origin:------------------");

        String str=req.getParameter("txt");

        System.out.println("Attribute: "+str);

        if (!(str.contains("from destination->"))) {
            req.setAttribute("parameter", "adadasd");
            req.getRequestDispatcher("/destination-servlet").forward(req, resp);
        }

    }
}
