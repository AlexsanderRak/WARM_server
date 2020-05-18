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

@WebServlet(name = "AddEquipment")
public class AddEquipment extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setAccessControlHeaders(response);
        request.setCharacterEncoding("UTF-8");

        String equipmentName = request.getParameter("equipmentName");
        String description = request.getParameter("description");
        String equipmentCondition = request.getParameter("equipmentCondition");
        String equipmentCost = request.getParameter("equipmentCost");
        String companiesName = request.getParameter("companiesName");
        String numberUnits = request.getParameter("numberUnits");
        int unitsInt = Integer.parseInt(numberUnits);

        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[80];
        random.nextBytes(bytes);
        String uid = bytes.toString();


        try {
            ConnectBD.addEquipment(equipmentName, uid, description, unitsInt, equipmentCondition, equipmentCost, companiesName);

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
        out.println("<h3>Hello AddEquipment!</h3> <br/>" +
                "*String equipmentName\n <br/>" +
                "String description\n <br/>" +
                "*String equipmentCondition\n <br/>" +
                "*String equipmentCost \n <br/>" +
                "*String companiesName \n <br/>" +
                "*int numberUnits");
    }
}

