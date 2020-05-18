package com.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.sql.SQLException;

@WebServlet(name = "AddTask")
public class AddTask extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setAccessControlHeaders(response);
        request.setCharacterEncoding("UTF-8");

        String taskName = request.getParameter("taskName");
        String description = request.getParameter("description");
        String developer = request.getParameter("developer");
        String time = request.getParameter("time");
        String projectName = request.getParameter("projectName");
        String urgency = request.getParameter("urgency");
        String deadline = request.getParameter("deadline");
        String companiesName = request.getParameter("companiesName");

        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[80];
        random.nextBytes(bytes);
        String uid = bytes.toString();


        try {
            ConnectBD.addTask(projectName, taskName, description, uid, developer, urgency, time, deadline, companiesName);

            response.setStatus(HttpServletResponse.SC_OK);
            String json = "{key: 'response', value: 'all good'}";
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
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h3>Hello AddTask!</h3> <br/>" +
                "*String taskName\n <br/>" +
                "String description\n <br/>" +
                "String developer\n <br/>" +
                "*String projectName \n <br/>" +
                "*String urgency \n <br/>" +
                "String time \n <br/>" +
                "String deadline \n <br/>" +
                "*String companiesName \n <br/>");
    }
}

