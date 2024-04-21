package application.controller;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

import application.database.DatabaseConnection;
import application.hotel.Hotel;
import application.pane.HotelPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

public class HotelController implements Initializable {
   @FXML
   private VBox hotelPanelPane;
   
   private String dbUrl;
   private String dbUser;
   private String dbPassword;
   
   @Override
   public void initialize(URL location, ResourceBundle resources) {
      try {
         ArrayList<Hotel> hotels = new ArrayList<Hotel>();
         
         // Retrieve DB credentials
         Properties properties = new Properties();
         properties.load(new FileInputStream(new File("resources/credentials.properties")));
         
         dbUrl = properties.getProperty("url");
         dbUser = properties.getProperty("user");
         dbPassword = properties.getProperty("password");
         
         // Read hotels
         Connection connection = DatabaseConnection.getDatabaseConnection(dbUrl, dbUser, dbPassword);
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery("select * from Hotel");
         
         while (resultSet.next()) {
            int hotelId = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String phoneNumber = resultSet.getString(3);
            String streetAddress = resultSet.getString(4);
            String city = resultSet.getString(5);
            String country = resultSet.getString(6);
            
            hotels.add(new Hotel(hotelId, name, phoneNumber, streetAddress, city, country));
         }
         
         // Display hotels
         for (Hotel hotel : hotels)
            hotelPanelPane.getChildren().add(new HotelPane(hotel));
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}