package com.example.requestdispatching;

import java.io.*;
import java.net.URL;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String dispatchName;

    public void init() {}

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<h1>Dispatcher Servlet</h1>");

        dispatchName=request.getParameter("dispatchName");
        dispatchName+=".jsp";
        URL url = getServletContext().getResource(dispatchName);

        if(url!=null){
            RequestDispatcher rd=request.getRequestDispatcher(dispatchName);
            rd.forward(request,response);
        }else {
            out.println("<p style='color:red;'>Incorrect file name!</p>");
        }
    }


    public void destroy() {
    }
}