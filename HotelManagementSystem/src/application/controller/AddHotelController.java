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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddHotelController implements Initializable {
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
         statement.executeUpdate("insert into Hotel(HotelID, Name, PhoneNumber, StreetAddress, City, Country)"
               + " values ('" + hotelId.getText() + "', '" + name.getText() + "', '" + phoneNumber.getText() + "', '"
               + streetAddress.getText() + "', '" + city.getText() + "', '" + country.getText() + "')");
         connection.close();
         
         Parent root = FXMLLoader.load(getClass().getResource("/fxml/home.fxml"));
         Scene scene = new Scene(root);
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         stage.setScene(scene);
         stage.show();
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
