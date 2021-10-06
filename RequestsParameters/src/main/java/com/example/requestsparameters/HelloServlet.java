package com.example.requestsparameters;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");

        String url_str=request.getParameter("parameter");

        PrintWriter out = response.getWriter();


        out.println("Get:" +url_str);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        String str=req.getParameter("txt");
        PrintWriter out = resp.getWriter();
        out.println("Post: "+str);
    }

    public void destroy() {
    }
}