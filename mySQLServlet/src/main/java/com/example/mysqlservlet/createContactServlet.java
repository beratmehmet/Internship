package com.example.mysqlservlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "createContact", value = "/createContact-servlet")
public class createContactServlet extends HttpServlet {
    mySQL db;
    public void init() {
        db = new mySQL("jdbc:mysql://localhost/contactsServlet","root","admin123");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        out.println("<h1>INSERT</h1>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        String name= req.getParameter("name");
        String tel_number = req.getParameter("tel_number");


        db.insertContact(name,tel_number);

        req.setAttribute("parameter","Contact Created");
        req.setAttribute("page","createContact");

        req.getRequestDispatcher("result.jsp").forward(req,resp);
    }

    public void destroy() {
        db.connectionClose();
    }
}
