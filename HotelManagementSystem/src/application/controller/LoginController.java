package application.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;

import application.database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LoginController implements Initializable {
	@FXML BorderPane HomePane;
	
   @Override
   public void initialize(URL location, ResourceBundle resources) {
      try {
         // Retrieve DB credentials
         Properties properties = new Properties();
         properties.load(new FileInputStream(new File("resources/credentials.properties")));
         
         String url = properties.getProperty("url");
         String user = properties.getProperty("user");
         String password = properties.getProperty("password");
         
         // Test connection
         Connection connection = DatabaseConnection.getDatabaseConnection(url, user, password);
         if (connection != null) {
            System.out.println("Connection established\n");
            
            Statement statement = connection.createStatement();
            
            System.out.println("AuthenticationSystem Data:");
            ResultSet resultSet = statement.executeQuery("select * from AuthenticationSystem;");
            while (resultSet.next())
               System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
            
            connection.close();
            System.out.println("\nConnection closed");
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   
  
}
