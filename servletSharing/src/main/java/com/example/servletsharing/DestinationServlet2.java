package com.example.servletsharing;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DestinationServlet2", value = "/destination-servlet2")
public class DestinationServlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Destination2:------------------");

        ServletContext servletContext = this.getServletContext();

        String msg = servletContext.getAttribute("msg").toString();
        servletContext.setAttribute("msg","a565");
        System.out.println("Attribute: "+msg);

        //msg="from destination-> "+msg;

        resp.sendRedirect(req.getContextPath()+"/origin-servlet2?txt="+msg);
    }
}
