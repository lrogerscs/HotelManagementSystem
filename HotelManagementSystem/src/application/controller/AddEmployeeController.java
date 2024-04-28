package application.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
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

public class AddEmployeeController implements Initializable {
   @FXML
   private TextField employeeId;
   
   @FXML
   private TextField hotelId;
   
   @FXML
   private TextField loginId;
   
   @FXML
   private TextField name;
   
   @FXML
   private TextField title;
   
   @FXML
   private TextField email;
   
   @FXML
   private TextField phoneNumber;
   
   @FXML
   private TextField address;
   
   private String dbUrl;
   private String dbUser;
   private String dbPassword;
   private Hotel hotel;
   
   @FXML
   private void onSaveButtonClick(ActionEvent event) {
      if (employeeId.getText() == null || employeeId.getText().isEmpty() 
            || hotelId.getText() == null || hotelId.getText().isEmpty()
            || loginId.getText() == null || loginId.getText().isEmpty()
            || name.getText() == null || name.getText().isEmpty() 
            || title.getText() == null || title.getText().isEmpty() 
            || email.getText() == null || email.getText().isEmpty()
            || phoneNumber.getText() == null || phoneNumber.getText().isEmpty()
            || address.getText() == null || address.getText().isEmpty())
         return;
      
      try {
         Connection connection = DatabaseConnection.getDatabaseConnection(dbUrl, dbUser, dbPassword);
         Statement statement = connection.createStatement();
         statement.executeUpdate("insert into Employee(EmployeeID, HotelID, LoginID, Name, Title, Email, PhoneNumber, Address) values (" 
                  + employeeId.getText() + ", " + hotelId.getText() + ", '" + loginId.getText() 
                  + "', '" + name.getText() + "', '" + title.getText() + "', '" + email.getText() 
                  + "', '" + phoneNumber.getText() + "', '" + address.getText() + "')");
         // TODO: Add query to insert into AuthenticationSystem
         connection.close();
         
         // Return to home
         onBackButtonClick(event);
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
   
   @FXML
   private void onBackButtonClick(ActionEvent event) {
      try {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/hotel.fxml"));
         Parent root = loader.load();
         HotelController controller = loader.getController();
         Scene scene = new Scene(root);
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         
         stage.setScene(scene);
         stage.show();
         controller.setHotel(hotel);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   public void setHotel(Hotel hotel) {
      this.hotel = hotel;
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