package application.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;

import application.database.DatabaseConnection;
import application.hotel.Hotel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditHotelController implements Initializable {
   @FXML
   private TextField hotelId;
   
   @FXML
   private TextField name;
   
   @FXML
   private TextField phoneNumber;
   
   @FXML
   private TextField streetAddress;
   
   @FXML
   private TextField city;
   
   @FXML
   private TextField country;
   
   private String dbUrl;
   private String dbUser;
   private String dbPassword;
   private Hotel hotel;
   
   @FXML
   private void onSaveButtonClick(ActionEvent event) {
      if (hotelId.getText() == null || hotelId.getText().isEmpty() 
            || name.getText() == null || name.getText().isEmpty()
            || phoneNumber.getText() == null || phoneNumber.getText().isEmpty()
            || streetAddress.getText() == null || streetAddress.getText().isEmpty() 
            || city.getText() == null || city.getText().isEmpty() 
            || country.getText() == null || country.getText().isEmpty())
         return;
      
      try {
         Connection connection = DatabaseConnection.getDatabaseConnection(dbUrl, dbUser, dbPassword);
         Statement statement = connection.createStatement();
         statement.executeUpdate("update Hotel set HotelID = " + hotelId.getText() 
               + ", Name = '" + name.getText() + "', PhoneNumber = '" + phoneNumber.getText() 
               + "', City = '" + city.getText() + "', Country = '" + country.getText() 
               + "' where HotelID = " + hotel.getHotelId());
         statement.executeUpdate("update Amenities set HotelID = " + hotelId.getText() 
               + " where HotelId = " + hotel.getHotelId());
         statement.executeUpdate("update Room set HotelID = " + hotelId.getText() 
               + " where HotelID = " + hotel.getHotelId());
         statement.executeUpdate("update Employee set HotelID = " + hotelId.getText() 
               + "where HotelID = " + hotel.getHotelId());
         connection.close();
         
         // Return to home
         onBackButtonClick(event);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   @FXML
   private void onBackButtonClick(ActionEvent event) {
      try {
         Parent root = FXMLLoader.load(getClass().getResource("/fxml/home.fxml"));
         Scene scene = new Scene(root);
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         stage.setScene(scene);
         stage.show();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   public void setHotel(Hotel hotel) {
      this.hotel = hotel;
      hotelId.setText(Integer.toString(this.hotel.getHotelId()));
      name.setText(this.hotel.getName());
      phoneNumber.setText(this.hotel.getPhoneNumber());
      streetAddress.setText(this.hotel.getStreetAddress());
      city.setText(this.hotel.getCity());
      country.setText(this.hotel.getCountry());
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
