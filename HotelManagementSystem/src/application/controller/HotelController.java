package application.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

import application.database.DatabaseConnection;
import application.employee.Employee;
import application.hotel.Hotel;
import application.pane.EmployeePane;
import application.pane.RoomPane;
import application.room.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HotelController implements Initializable {
   @FXML
   private VBox roomPanelPane;
   
   @FXML
   private VBox amenitiesPanelPane;
   @FXML
   private VBox employeePanelPane;
   
   private Hotel hotel;
   private String dbUrl;
   private String dbUser;
   private String dbPassword;
   
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
      displayEmployees();
      try {
         ArrayList<String> amenities = new ArrayList<String>();
         ArrayList<Room> rooms = new ArrayList<Room>();
         Connection connection = DatabaseConnection.getDatabaseConnection(dbUrl, dbUser, dbPassword);
         Statement statement = connection.createStatement();
         
         
         // Find amenities
         ResultSet resultSet = statement.executeQuery("select * from Amenities where HotelID = " + this.hotel.getHotelId());
         while (resultSet.next())
            amenities.add(resultSet.getString(2));
         
         // Find rooms
         resultSet = statement.executeQuery("select * from Room where HotelID = " + this.hotel.getHotelId());
         while (resultSet.next()) {
            int roomId = resultSet.getInt(1);
            int hotelId = resultSet.getInt(2);
            int customerId = resultSet.getInt(3);
            double price = resultSet.getDouble(4);
            Date date = resultSet.getDate(5);
            
            rooms.add(new Room(roomId, hotelId, customerId, price, date));
         }
         
         for (String amenity : amenities)
            amenitiesPanelPane.getChildren().add(new Label(amenity));
         for (Room room : rooms)
            roomPanelPane.getChildren().add(new RoomPane(room));
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
   
   private void displayEmployees() {
	      try {
	         ArrayList<Employee> employees = new ArrayList<Employee>();
	         Connection connection = DatabaseConnection.getDatabaseConnection(dbUrl, dbUser, dbPassword);
	         PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee WHERE HotelID = ?");
	         preparedStatement.setInt(1, hotel.getHotelId());
	         ResultSet resultSet = preparedStatement.executeQuery();
	         
	         while (resultSet.next()) {
	            int employeeId = resultSet.getInt("EmployeeID");
	            int loginId = resultSet.getInt("LoginID");
	            String name = resultSet.getString("Name");
	            String title = resultSet.getString("Title");
	            String email = resultSet.getString("Email");
	            String phoneNumber = resultSet.getString("Phone_number");
	            String address = resultSet.getString("Address");
	            
	            employees.add(new Employee(employeeId, loginId, name, title, email, phoneNumber, address));
	         }
	         
	         for (Employee employee : employees)
	            employeePanelPane.getChildren().add(new EmployeePane(employee));
	         
	         connection.close();
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
