package com.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "ChangeStatus")
public class ChangeStatus extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setAccessControlHeaders(response);
        request.setCharacterEncoding("UTF-8");

        String company = request.getParameter("company");
        String access = request.getParameter("access");
        int accessInt = Integer.parseInt(access);
        String uid = request.getParameter("uid");
        String status = request.getParameter("status");


        try {
            ConnectBD.changeTaskStatus(company, accessInt, status, uid);

            response.setStatus(HttpServletResponse.SC_OK);
            String json = "value:all good";
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

    }

    private void setAccessControlHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        resp.setHeader("Access-Control-Allow-Methods", "POST");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        setAccessControlHeaders(response);
//
//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//        out.println("<h3>Hello AddTask!</h3> <br/>" +
//                "*String taskName\n <br/>" +
//                "String description\n <br/>" +
//                "String developer\n <br/>" +
//                "*String projectName \n <br/>" +
//                "*String urgency \n <br/>" +
//                "String time \n <br/>" +
//                "String deadline \n <br/>" +
//                "*String companiesName \n <br/>");
    }
}

