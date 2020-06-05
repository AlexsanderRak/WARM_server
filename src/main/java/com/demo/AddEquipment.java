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
                ResultSet res = ConnectBD.getEquipment(company);

                String uid = null;
                String equipment_name = null;
                String description = null;
                String equipment_condition = null;
                String cost = null;
                int quantity = 0;
                String json = "";
                while (res.next()) {
                    uid = res.getString("uid");
                    equipment_name = res.getString("equipment_name");
                    description = res.getString("description");
                    equipment_condition = res.getString("equipment_condition");
                    cost = res.getString("cost");
                    quantity = res.getInt("quantity");
                    json +="uid:" + uid + "," +
                            "equipment_name:" + equipment_name + "," +
                            "description:" + description + "," +
                            "equipment_condition:" + equipment_condition + "," +
                            "cost:" + cost + "," +
                            "quantity:" + quantity + ";";
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
//        out.println("<h3>Hello AddEquipment!</h3> <br/>" +
//                "*String equipmentName\n <br/>" +
//                "String description\n <br/>" +
//                "*String equipmentCondition\n <br/>" +
//                "*String equipmentCost \n <br/>" +
//                "*String companiesName \n <br/>" +
//                "*int numberUnits");
    }
}

