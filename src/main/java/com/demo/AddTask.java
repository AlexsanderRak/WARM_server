package com.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.sql.ResultSet;
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
        setAccessControlHeaders(response);
        request.setCharacterEncoding("UTF-8");

        String company = request.getParameter("company");
        String access = request.getParameter("access");
        int accessInt = Integer.parseInt(access);



        try {
            // get Project
            ResultSet res = ConnectBD.getProject(company);

            String projectName = null;
            String projects = "";
            while (res.next()) {
                projectName = res.getString("project_name");
                if (projects.equals("")) {
                    projects = projectName;
                } else {
                    projects += "&& " + projectName;
                }

            }

            // get Tasks
            ResultSet resTasks = ConnectBD.getTasks(company);

            String uid = null;
            String taskName = null;
            String descriptions = null;
            String developers = null;
            String timeToNeed = null;
            String timeReal = null;
            String urgency = null;
            String project = null;
            String task_status = null;
            String deadline = null;

            String json = "";

            while (resTasks.next()) {
                uid = resTasks.getString("uid");
                taskName = resTasks.getString("task_name");
                descriptions = resTasks.getString("description");
                developers = resTasks.getString("developer");
                timeToNeed = resTasks.getString("timeToNeed");
                timeReal = resTasks.getString("timeReal");
                urgency = resTasks.getString("urgency");
                project = resTasks.getString("project");
                task_status = resTasks.getString("task_status");
                deadline = resTasks.getString("deadline");


                json +="uid:" + uid + "," +
                        "taskName:" + taskName + "," +
                        "descriptions:" + descriptions + "," +
                        "developers:" + developers + "," +
                        "timeToNeed:" + timeToNeed + "," +
                        "timeReal:" + timeReal + "," +
                        "urgency:" + urgency + "," +
                        "project:" + project + "," +
                        "projectName:" + projects + "," +
                        "task_status:" + task_status + "," +
                        "deadline:" + deadline + ";";
            }

            response.setStatus(HttpServletResponse.SC_OK);


            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }





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

