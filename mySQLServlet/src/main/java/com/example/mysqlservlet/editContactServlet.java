package com.example.mysqlservlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "editContact", value = "/editContact-servlet")
public class editContactServlet extends HttpServlet {
    mySQL db=null;
    static String name;
    @Override
    public void init() throws ServletException {
        db = new mySQL("jdbc:mysql://localhost/contactsServlet","root","admin123");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        name=req.getParameter("name");
        String tel_number=db.getContact(name);

        if (tel_number!=null){

            req.setAttribute("name",name);
            req.setAttribute("tel_number",tel_number);

            req.getRequestDispatcher("editContact.jsp").forward(req,resp);
        }else {

            req.setAttribute("parameter","No result");
            req.setAttribute("page","search");

            req.getRequestDispatcher("result.jsp").forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String tel_number = req.getParameter("tel_number");
        db.updateContact(name,tel_number);

        req.setAttribute("parameter","Contact Edited");
        req.setAttribute("page","search");

        req.getRequestDispatcher("result.jsp").forward(req,resp);
    }

    public void destroy() {
        db.connectionClose();
    }
}
