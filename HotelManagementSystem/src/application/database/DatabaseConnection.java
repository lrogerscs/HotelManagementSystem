package application.database;

import java.sql.DriverManager;
import java.sql.Connection;

public class DatabaseConnection {
   private static Connection connection;
   
   public static Connection getDatabaseConnection(String url, String user, String password) {
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         connection = DriverManager.getConnection(url, user, password);
      } catch (Exception e) {
         e.printStackTrace();
      }
      
      return connection;
   }
}