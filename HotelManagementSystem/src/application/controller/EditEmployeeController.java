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
import application.employee.Employee;
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

public class EditEmployeeController implements Initializable {
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
   private Employee employee;
   
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
         statement.executeUpdate("update Employee set EmployeeID = " + employeeId.getText() 
               + ", HotelID = " + hotelId.getText() + ", LoginID = '" + loginId.getText() 
               + "', Name = '" + name.getText() + "', Title = '" + title.getText() 
               + "', Email = '" + email.getText() + "', PhoneNumber = '" + phoneNumber.getText() 
               + "', Address = '" + address.getText() 
               + "' where EmployeeID = " + employee.getEmployeeId());
         // TODO: Add query to update AuthenticationSystem
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
   
   public void setHotelEmployee(Hotel hotel, Employee employee) {
      this.hotel = hotel;
      this.employee = employee;
      employeeId.setText(Integer.toString(this.employee.getEmployeeId()));
      hotelId.setText(Integer.toString(this.employee.getHotelId()));
      loginId.setText(this.employee.getLoginId());
      name.setText(this.employee.getName());
      title.setText(this.employee.getTitle());
      email.setText(this.employee.getEmail());
      phoneNumber.setText(this.employee.getPhoneNumber());
      address.setText(this.employee.getAddress());
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