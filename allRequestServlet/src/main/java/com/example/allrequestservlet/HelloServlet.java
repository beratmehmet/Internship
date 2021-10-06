package com.example.allrequestservlet;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String query = req.getQueryString();

        if(query!=null && query.contains("secured")){
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
        else {
            RequestDispatcher rd=req.getRequestDispatcher("Forwarded.jsp");
            rd.forward(req,resp);
        }


    }

    public void destroy() {
    }
}