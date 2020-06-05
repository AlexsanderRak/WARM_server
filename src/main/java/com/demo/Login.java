package com.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setAccessControlHeaders(response);
        request.setCharacterEncoding("UTF-8");

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        try {
            ResultSet res = ConnectBD.login(login, password);
            String token = null;
            String firstName = null;
            String lastName = null;
            String company = null;
            int access = 0;
            int role = 0;
            while (res.next()) {
                token = res.getString("token");
                firstName = res.getString("first_name");
                lastName = res.getString("last_name");
                company = res.getString("company");
                access = res.getInt("access");
                role = res.getInt("role");
            }
            if (token == null) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            } else {
                response.setStatus(HttpServletResponse.SC_OK);

                String json = "token:" + token + "," +
                        "firstName:" + firstName + "," +
                        "lastName:" + lastName + "," +
                        "company:" + company + "," +
                        "access:" + access + "," +
                        "role:" + role + "";
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
            }
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
        out.println("<h3>Hello Login!</h3><br/>" +
                "*String login\n <br/>" +
                "*String password\n");
    }
}
