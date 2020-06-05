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

@WebServlet(name = "AddPerson")
public class AddPerson extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setAccessControlHeaders(response);
        request.setCharacterEncoding("UTF-8");

        String companiesName = request.getParameter("companiesName");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String skills = request.getParameter("skills");
        String qualification = request.getParameter("qualification");
        String specialization = request.getParameter("specialization");
        String role = request.getParameter("role");
        int roleInt = Integer.parseInt(role);

        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[80];
        random.nextBytes(bytes);
        String token = bytes.toString();
        String password = token; // because we dont know password

        try {
            ConnectBD.addUser(token, firstName, lastName, email, password, companiesName, roleInt, skills, qualification, specialization);

            response.setStatus(HttpServletResponse.SC_OK);
            String json = "token:" + token + ",value:all good";
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
            ResultSet res = ConnectBD.getPerson(company);

            String companyName = null;
            String firstName = null;
            String lastName = null;
            String email = null;
            String skills = null;
            String qualification = null;
            String specialization = null;
            String token = null;
            String json = "";
            while (res.next()) {
                token = res.getString("token");
                firstName = res.getString("first_name");
                lastName = res.getString("last_name");
                email = res.getString("email");
                skills = res.getString("skills");
                qualification = res.getString("level_qualification");
                specialization = res.getString("specialization");
                companyName = res.getString("company");
                json +="token:" + token + "," +
                        "firstName:" + firstName + "," +
                        "lastName:" + lastName + "," +
                        "email:" + email + "," +
                        "skills:" + skills + "," +
                        "specialization:" + specialization + "," +
                        "company:" + companyName + "," +
                        "qualification:" + qualification + ";";
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
//        out.println("<h3>Hello AddPerson!</h3><br/>" +
//                "*String firstName\n <br/>" +
//                "String lastName\n <br/>" +
//                "*String email\n <br/>" +
//                "String skills \n <br/>" +
//                "String qualification \n <br/>" +
//                "String specialization \n <br/>" +
//                "*String companiesName \n <br/>" +
//                "*int role");
    }
}

