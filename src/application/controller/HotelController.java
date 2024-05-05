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
import application.employee.Employee;
import application.hotel.Hotel;
import application.pane.AmenityPane;
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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class HotelController implements Initializable {
   @FXML
   private HBox amenitiesPanelPane;
   
   @FXML
   private FlowPane employeePanelPane;
   
   @FXML
   private FlowPane roomPanelPane;
   
   @FXML
   private Label hotelName;
   
   private Hotel hotel;
   private String dbUrl;
   private String dbUser;
   private String dbPassword;
   
   @FXML
   private void onAddAmenityButtonClick(ActionEvent event) {
      try {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/add_amenity.fxml"));
         Parent root = loader.load();
         AddAmenityController controller = loader.getController();
         Scene scene = new Scene(root);
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         
         controller.setHotel(hotel);
         stage.setScene(scene);
         stage.show();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   @FXML
   private void onAddRoomButtonClick(ActionEvent event) {
      try {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/add_room.fxml"));
         Parent root = loader.load();
         AddRoomController controller = loader.getController();
         Scene scene = new Scene(root);
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         
         controller.setHotel(hotel);
         stage.setScene(scene);
         stage.show();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   @FXML
   private void onAddEmployeeButtonClick(ActionEvent event) {
      try {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/add_employee.fxml"));
         Parent root = loader.load();
         AddEmployeeController controller = loader.getController();
         Scene scene = new Scene(root);
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         
         controller.setHotel(hotel);
         stage.setScene(scene);
         stage.show();
      } catch (IOException e) {
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
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   public void setHotel(Hotel hotel) {
      this.hotel = hotel;
      hotelName.setText(this.hotel.getName());
      
      try {
         ArrayList<String> amenities = new ArrayList<String>();
         ArrayList<Employee> employees = new ArrayList<Employee>();
         ArrayList<Room> rooms = new ArrayList<Room>();
         Connection connection = DatabaseConnection.getDatabaseConnection(dbUrl, dbUser, dbPassword);
         Statement statement = connection.createStatement();
         
         // Find amenities
         ResultSet resultSet = statement.executeQuery("select * from Amenities where HotelID = " + this.hotel.getHotelId());
         while (resultSet.next())
            amenities.add(resultSet.getString(2));
         
         // Find Employees
         resultSet = statement.executeQuery("select * from Employee where HotelID = " + this.hotel.getHotelId());
         while (resultSet.next()) {
            int employeeId = resultSet.getInt(1);
            int hotelId = resultSet.getInt(2);
            String loginId = resultSet.getString(3);
            String name = resultSet.getString(4);
            String title = resultSet.getString(5);
            String email = resultSet.getString(6);
            String phoneNumber = resultSet.getString(7);
            String address = resultSet.getString(8);
            
            employees.add(new Employee(employeeId, hotelId, loginId, name, title, email, phoneNumber, address));
         }
         
         // Find rooms
         resultSet = statement.executeQuery("select * from Room where HotelID = " + this.hotel.getHotelId());
         while (resultSet.next()) {
            int roomId = resultSet.getInt(1);
            int hotelId = resultSet.getInt(2);
            Integer customerId = (Integer) resultSet.getObject(3);
            double price = resultSet.getDouble(4);
            Date date = resultSet.getDate(5);
            
            rooms.add(new Room(roomId, hotelId, customerId, price, date));
         }
         
         connection.close();
         
         for (String amenity : amenities)
            amenitiesPanelPane.getChildren().add(new AmenityPane(this.hotel, amenity));
         for (Employee employee : employees)
            employeePanelPane.getChildren().add(new EmployeePane(this.hotel, employee));
         for (Room room : rooms)
            roomPanelPane.getChildren().add(new RoomPane(this.hotel, room));
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
