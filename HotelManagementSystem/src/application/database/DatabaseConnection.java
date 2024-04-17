package application.database;

import java.sql.DriverManager;
import java.sql.Connection;

public class DatabaseConnection {
   private static final String url = "jdbc:mysql://localhost:3306/HotelManagementSystem";
   private static final String user = "root";
   private static final String password = "";
   private static Connection connection;
   
   public static Connection getDatabaseConnection() {
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         connection = DriverManager.getConnection(url, user, password);
      } catch (Exception e) {
         e.printStackTrace();
      }
      
      return connection;
   }
}