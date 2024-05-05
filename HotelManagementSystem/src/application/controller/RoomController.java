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

import application.customer.Customer;
import application.database.DatabaseConnection;
import application.hotel.Hotel;
import application.pane.CustomerPane;
import application.pane.PaymentPane;
import application.payment.Payment;
import application.room.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RoomController implements Initializable {
   @FXML
   private HBox featuresPanelPane;
   
   @FXML
   private VBox customerPanelPane;
   
   @FXML
   private VBox paymentPanelPane;
   
   private Hotel hotel;
   private Room room;
   private String dbUrl;
   private String dbUser;
   private String dbPassword;
   
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
   
   public void setHotelRoom(Hotel hotel, Room room) {
      this.hotel = hotel;
      this.room = room;
      
      try {
         ArrayList<String> features = new ArrayList<String>();
         ArrayList<Customer> customers = new ArrayList<Customer>();
         ArrayList<Payment> payments = new ArrayList<Payment>();
         Connection connection = DatabaseConnection.getDatabaseConnection(dbUrl, dbUser, dbPassword);
         Statement statement = connection.createStatement();
         
         // Find features
         ResultSet resultSet = statement.executeQuery("select * from Features where RoomID = " + this.room.getRoomId());
         while (resultSet.next())
            features.add(resultSet.getString(2));
         
         // Find customers
         resultSet = statement.executeQuery("select * from Customer where RoomID = " + this.room.getRoomId());
         while (resultSet.next()) {
            int customerId = resultSet.getInt(1);
            int roomId = resultSet.getInt(2);
            String name = resultSet.getString(3);
            Date dob = resultSet.getDate(4);
            int age = resultSet.getInt(5);
            String email = resultSet.getString(6);
            String phoneNumber = resultSet.getString(7);
            String paymentMethod = resultSet.getString(8);
            String address = resultSet.getString(9);
            
            customers.add(new Customer(customerId, roomId, name, dob, age, email, phoneNumber, paymentMethod, address));
         }
         
         // Find Payments
         resultSet = statement.executeQuery("select * from Payment where RoomID = " + this.room.getRoomId());
         while (resultSet.next()) {
            int paymentId = resultSet.getInt(1);
            int customerId = resultSet.getInt(2);
            int roomId = resultSet.getInt(3);
            String method = resultSet.getString(4);
            double amount = resultSet.getDouble(5);
            Date date = resultSet.getDate(6);
            
            payments.add(new Payment(paymentId, customerId, roomId, method, amount, date));
         }
         
         connection.close();
         
         for (String feature : features)
            featuresPanelPane.getChildren().add(new Label("• " + feature));
         for (Customer customer : customers)
            customerPanelPane.getChildren().add(new CustomerPane(customer));
         for (Payment payment : payments)
            paymentPanelPane.getChildren().add(new PaymentPane(payment));
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
