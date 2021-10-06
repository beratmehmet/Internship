package com.example.servletsharing;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "DestinationServlet", value = "/destination-servlet")
public class DestinationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Destination:------------------");

        String msg = req.getAttribute("parameter").toString();
        req.setAttribute("parameter","asdasdasd");
        System.out.println("Attribute: "+msg);

        //msg="from destination-> "+msg;

        resp.sendRedirect(req.getContextPath()+"/origin-servlet?txt="+msg);

        }
}
