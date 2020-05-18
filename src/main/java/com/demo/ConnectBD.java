package com.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;

public class ConnectBD {
    public static String userName = "root";
    public static String password = "bz56162hq";
    public static String url = "jdbc:mysql://localhost:3306/WARM?useSSL=false";


     public static void main(String[] args) throws SQLException {
//          String userName = "root";
//          String password = "bz56162hq";
//          String url = "jdbc:mysql://localhost:3306/WARM?useSSL=false";

//         addRole(0, "test1", "test2");
//         addUser("test1", "test2", "test3", "test4", "test5", "test6");
//         try {
//             Class.forName("com.mysql.jdbc.Driver");
//         } catch (ClassNotFoundException e) {
//             e.printStackTrace();
//         }
//         Connection db = DriverManager.getConnection(url, userName, password);
//         sq = db.createStatement();
//         try(Connection connection = DriverManager.getConnection(url, userName, password)) {
//             Statement statement = connection.createStatement();
////             String query ="INSERT INTO Role " +
////                     "VALUES (0, 0, 'admin', NULL ), " +
////                     "(0, 1, 'rm', 'resource manager'), " +
////                     "(0, 2, 'pr', 'project manager'), " +
////                     "(0, 3, 'tm','team leader'), " +
////                     "(0, 4, 'dev', 'developer'), " +
////                     "(0, 4, 'qa', 'QA engineer'), " +
////                     "(0, 4, 'des', 'designer'), " +
////                     "(0, 5, 'trainee', NULL);";
////             statement.executeUpdate(query);
//        } catch(SQLException e) {
//            e.printStackTrace();
//        }
    }

    public static void addRole(int key, String name, String description) {
         String userName = "root";
         String password = "bz56162hq";
         String url = "jdbc:mysql://localhost:3306/WARM?useSSL=false";
         try {
             Class.forName("com.mysql.jdbc.Driver");
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         }
         try(Connection connection = DriverManager.getConnection(url, userName, password)) {

             Statement statement = connection.createStatement();
             String query ="INSERT INTO Role " +
                     "VALUES (0, "+key+", '"+name+"', '"+description+"' )";
             statement.executeUpdate(query);
//             String query ="INSERT INTO Role " +
//                     "VALUES (0, 0, 'admin', NULL ), " +
//                     "(0, 1, 'rm', 'resource manager'), " +
//                     "(0, 2, 'pr', 'project manager'), " +
//                     "(0, 3, 'tm','team leader'), " +
//                     "(0, 4, 'dev', 'developer'), " +
//                     "(0, 4, 'qa', 'QA engineer'), " +
//                     "(0, 4, 'des', 'designer'), " +
//                     "(0, 5, 'trainee', NULL);";
//             statement.executeUpdate(query);
         } catch(SQLException e) {
             e.printStackTrace();
         }
     }

    public static ResultSet login(String login, String myPassword) throws ClassNotFoundException, SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = DriverManager.getConnection(url, userName, password);
        Statement statement = connection.createStatement();
        String query = "SELECT * from users WHERE email ='" + login + "' AND password = '" + myPassword + "' ";
        return statement.executeQuery(query);
    }

    public static void addUser(String token, String firstName, String lastName,
                               String email, String myPassword, String company,
                               int role, String skills, String qualification,
                               String specialization) throws ClassNotFoundException, SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = DriverManager.getConnection(url, userName, password);
        Statement statement = connection.createStatement();
        String query = "";
        if(skills.equals("null")) {
            // Registration
            query = "INSERT INTO users SET id=0, " +
                    "role="+role+", token='" + token + "', " +
                    "first_name='" + firstName + "', last_name='" + lastName + "', " +
                    "email='" + email + "', password='" + myPassword + "', " +
                    "company='" + company + "', access=0 ";
        } else {
            // addPerson
            query = "INSERT INTO users SET id=0, " +
                    "role="+role+", token='" + token + "', " +
                    "first_name='" + firstName + "', last_name='" + lastName + "', " +
                    "email='" + email + "', password='" + myPassword + "', " +
                    "company='" + company + "', access=0, " +
                    "skills='"+skills+"', level_qualification='"+qualification+"', " +
                    "specialization='"+specialization+"' ";
        }

        statement.executeUpdate(query);


    }

    public static void addEquipment(String equipmentName, String uid, String description, int numberUnits,
                                    String equipmentCondition, String equipmentCost,
                                    String companiesName) throws ClassNotFoundException, SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = DriverManager.getConnection(url, userName, password);
        Statement statement = connection.createStatement();

        String query ="INSERT INTO equipment SET id=0, quantity="+numberUnits+"," +
                " uid='"+uid+"', equipment_name='"+equipmentName+"'," +
                " description='"+description+"', " +
                " equipment_condition='"+equipmentCondition+"', cost='"+equipmentCost+"'," +
                " company='"+companiesName+"'";

        statement.executeUpdate(query);


    }

    public static void addProject(String projectName, String description, String uid, String teamLead_id,
                                    int needsDevelopers, String company_name) throws ClassNotFoundException, SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = DriverManager.getConnection(url, userName, password);
        Statement statement = connection.createStatement();

        String query ="INSERT INTO projects SET id=0, needsDevelopers="+needsDevelopers+"," +
                " uid='"+uid+"', project_name='"+projectName+"'," +
                " teamLead_id='"+teamLead_id+"', descriptions='"+description+"', " +
                " project_status='create', company='"+company_name+"'";

        statement.executeUpdate(query);


    }

    public static void addTask(String projectName, String taskName, String description,
                                  String uid, String developer, String urgency,
                                  String timeToNeed, String deadline, String company_name) throws ClassNotFoundException, SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = DriverManager.getConnection(url, userName, password);
        Statement statement = connection.createStatement();

        String query ="INSERT INTO tasks SET id=0, task_name='"+taskName+"'," +
                " uid='"+uid+"', project='"+projectName+"'," +
                " developer='"+developer+"', description='"+description+"', " +
                " urgency='"+urgency+"', company='"+company_name+"', " +
                "timeToNeed='"+timeToNeed+"', deadline='"+deadline+"' ";

        statement.executeUpdate(query);


    }
}

