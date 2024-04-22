package application.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

import application.database.DatabaseConnection;
import application.hotel.Hotel;
import application.pane.RoomPane;
import application.room.Room;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

public class HotelController implements Initializable {
   @FXML
   private VBox roomPanelPane;
   
   private Hotel hotel;
   private String dbUrl;
   private String dbUser;
   private String dbPassword;
   
   public void setHotel(Hotel hotel) {
      this.hotel = hotel;
      
      try {
         ArrayList<Room> rooms = new ArrayList<Room>();
         
         // Find rooms
         Connection connection = DatabaseConnection.getDatabaseConnection(dbUrl, dbUser, dbPassword);
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery("select * from Room where HotelID = " + hotel.getHotelId());
         
         while (resultSet.next()) {
            int roomId = resultSet.getInt(1);
            int hotelId = resultSet.getInt(2);
            int customerId = resultSet.getInt(3);
            double price = resultSet.getDouble(4);
            Date date = resultSet.getDate(5);
            
            rooms.add(new Room(roomId, hotelId, customerId, price, date));
         }
         
         for (Room room : rooms)
            roomPanelPane.getChildren().add(new RoomPane(room));
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
   
   @Override
   public void initialize(URL location, ResourceBundle resources) {
      try {
         // Retrieve DB credentials
         Properties properties = new Properties();
         properties.load(new FileInputStream(new File("resources/credentials/credentials.properties")));
         
         dbUrl = properties.getProperty("url");
         dbUser = properties.getProperty("user");
         dbPassword = properties.getProperty("password");
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
