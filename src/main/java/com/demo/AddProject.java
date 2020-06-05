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

@WebServlet(name = "AddProject")
public class AddProject extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setAccessControlHeaders(response);
        request.setCharacterEncoding("UTF-8");

        String projectName = request.getParameter("projectName");
        String description = request.getParameter("description");
//        String teamLead_id = request.getParameter("teamLead_id");
        String companiesName = request.getParameter("companiesName");
        String needDevelopers = request.getParameter("needDevelopers");
        int needDevelopersInt = Integer.parseInt(needDevelopers);

        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[80];
        random.nextBytes(bytes);
        String uid = bytes.toString();


        try {
            ConnectBD.addProject(projectName, description, uid, needDevelopersInt, companiesName);

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
            ResultSet res = ConnectBD.getProject(company);

            String uid = null;
            String projectName = null;
            String descriptions = null;
            String developers = null;
            int needsDevelopers = 0;
            int totalAmountDevelopers = 0;
            String tasks = null;
            String project_status = null;
            String json = "";
            while (res.next()) {
                uid = res.getString("uid");
                projectName = res.getString("project_name");
                descriptions = res.getString("descriptions");
                developers = res.getString("developers");
                needsDevelopers = res.getInt("needsDevelopers");
                tasks = res.getString("tasks");
                project_status = res.getString("project_status");
                totalAmountDevelopers = res.getInt("totalAmountDevelopers");

                json +="uid:" + uid + "," +
                        "projectName:" + projectName + "," +
                        "descriptions:" + descriptions + "," +
                        "developers:" + developers + "," +
                        "needsDevelopers:" + needsDevelopers + "," +
                        "totalAmountDevelopers:" + totalAmountDevelopers + "," +
                        "tasks:" + tasks + "," +
                        "project_status:" + project_status + ";";
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
//        out.println("<h3>Hello AddProject!</h3> <br/>" +
//                "*String projectName\n <br/>" +
//                "String description\n <br/>" +
//                "*String teamLead_id\n <br/>" +
//                "*int needDevelopers \n <br/>" +
//                "*String companiesName \n <br/>");
    }
}

