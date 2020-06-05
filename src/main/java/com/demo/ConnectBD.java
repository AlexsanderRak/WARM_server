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
                    "company='" + company + "', access="+role+" ";
        } else {
            // addPerson
            query = "INSERT INTO users SET id=0, " +
                    "role="+role+", token='" + token + "', " +
                    "first_name='" + firstName + "', last_name='" + lastName + "', " +
                    "email='" + email + "', password='" + myPassword + "', " +
                    "company='" + company + "', access="+role+", " +
                    "skills='"+skills+"', level_qualification='"+qualification+"', " +
                    "specialization='"+specialization+"' ";
        }

        statement.executeUpdate(query);


    }
    public static ResultSet getPerson(String company) throws ClassNotFoundException, SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = DriverManager.getConnection(url, userName, password);
        Statement statement = connection.createStatement();
        String query = "SELECT * from users WHERE company ='" + company + "'";
        return statement.executeQuery(query);
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

    public static ResultSet getEquipment(String company) throws ClassNotFoundException, SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = DriverManager.getConnection(url, userName, password);
        Statement statement = connection.createStatement();
        String query = "SELECT * from equipment WHERE company ='" + company + "'";
        return statement.executeQuery(query);
    }

    public static void addProject(String projectName, String description, String uid,
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
                " descriptions='"+description+"', " +
                " project_status='create', company='"+company_name+"'";

        statement.executeUpdate(query);


    }

    public static ResultSet getProject(String company) throws ClassNotFoundException, SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = DriverManager.getConnection(url, userName, password);
        Statement statement = connection.createStatement();
        String query = "SELECT * from projects WHERE company ='" + company + "'";
        return statement.executeQuery(query);
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

        // developer get
        String queryDev = "SELECT * from users WHERE email ='" + developer + "'";
        ResultSet getDeveloper = statement.executeQuery(queryDev);

        String developerEmail = "";
        String developersProject = "";
        while (getDeveloper.next()) {
            developerEmail = getDeveloper.getString("email");
            developersProject = getDeveloper.getString("projects");
        }

        //---------------------------------------------------------------------

        // Developers set
        String developersProjectList = "";
        if (developersProject == null) {
            developersProjectList= projectName;
        } else {
            int index = developersProject.indexOf(projectName);
            if (index == -1) {
                developersProjectList = developersProject+"&& "+projectName;
            } else  {
                developersProjectList = developersProject;
            }
        }
        String querySetDeveloper ="UPDATE users SET projects='"+developersProjectList+"' WHERE email= '"+developerEmail+"'";

        statement.executeUpdate(querySetDeveloper);


        //---------------------------------------------------------------------

        // Project get

        String queryProject = "SELECT * from projects WHERE project_name ='" + projectName + "'";
        ResultSet getProject = statement.executeQuery(queryProject);

        String project_name = "";
        String projectDevelopers = "";
        int totalAmountDevelopers = 0;
        String projectTasks = "";
        while (getProject.next()) {
            project_name = getProject.getString("project_name");
            projectDevelopers = getProject.getString("developers");
            totalAmountDevelopers = getProject.getInt("totalAmountDevelopers");
            projectTasks = getProject.getString("tasks");
        }

        // --------------------------------------------------------------------
        // Project set
        String developersList = "";
        if (projectDevelopers == null) {
            developersList = developerEmail;
        } else {
            int indexDev = projectDevelopers.indexOf(developerEmail);
            if (indexDev == -1) {
                // if project don't have this developers
                developersList = projectDevelopers+"&& "+developerEmail;
            } else  {
                developersList = projectDevelopers;
            }
        }

        String taskList = "";
        if (projectTasks == null) {
            taskList = uid;
        } else {
            int indexTask = projectTasks.indexOf(uid);
            if (indexTask == -1) {
                // if project don't have this task
                taskList = projectTasks+"&& "+uid;
            } else  {
                taskList = projectTasks;
            }
        }
        totalAmountDevelopers = totalAmountDevelopers + 1;
        String querySetProject ="UPDATE projects SET totalAmountDevelopers= "+totalAmountDevelopers+", developers='"+developersList+"'," +
                " tasks='"+taskList+"' WHERE project_name='"+project_name+"'";

        statement.executeUpdate(querySetProject);

        //---------------------------------------------------------------------

        // Tasks set

        String query ="INSERT INTO tasks SET id=0, task_name='"+taskName+"'," +
                " uid='"+uid+"', project='"+project_name+"'," +
                " developer='"+developer+"', description='"+description+"', " +
                " urgency='"+urgency+"', company='"+company_name+"', " +
                "timeToNeed='"+timeToNeed+"', deadline='"+deadline+"', task_status='to_do' ";

        statement.executeUpdate(query);

        //-----------------------------------------------------------------------


    }

    public static ResultSet getTasks(String company) throws ClassNotFoundException, SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = DriverManager.getConnection(url, userName, password);
        Statement statement = connection.createStatement();
        String query = "SELECT * from tasks WHERE company ='" + company + "'";
        return statement.executeQuery(query);
    }


    public static void changeTaskStatus(String company, int access, String status, String uid) throws ClassNotFoundException, SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = DriverManager.getConnection(url, userName, password);
        Statement statement = connection.createStatement();
        String query = "UPDATE tasks SET task_status='"+status+"' WHERE uid= '"+uid+"'";
        statement.executeUpdate(query);
    }
}

