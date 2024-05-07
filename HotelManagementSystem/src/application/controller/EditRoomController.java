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
import application.room.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EditRoomController implements Initializable {
   @FXML
   private TextField roomId;
   
   @FXML
   private TextField price;
   
   @FXML
   private VBox inputFieldPanelPane;
   
   @FXML
   private Label roomName;
   
   private String dbUrl;
   private String dbUser;
   private String dbPassword;
   private Hotel hotel;
   private Room room;
   
   @FXML
   private void onSaveButtonClick(ActionEvent event) {
      if (roomId.getText() == null || roomId.getText().isEmpty() 
            || price.getText() == null || price.getText().isEmpty())
         return;
      
      try {
         Connection connection = DatabaseConnection.getDatabaseConnection(dbUrl, dbUser, dbPassword);
         Statement statement = connection.createStatement();
         statement.executeUpdate("update Room set RoomID = " + roomId.getText() 
               + ", Price = " + price.getText() + " where RoomID = " + room.getRoomId());
         statement.executeUpdate("Update Features set RoomID = " + roomId.getText() 
               + " where RoomID = " + room.getRoomId());
         statement.executeUpdate("update Customer set RoomID = " + roomId.getText() 
               + " where RoomID = " + room.getRoomId());
         statement.executeUpdate("update Payment set RoomID = " + roomId.getText() 
               + " where RoomID = " + room.getRoomId());
         connection.close();
         
         Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
         successAlert.setTitle("Success");
         successAlert.setHeaderText(null);
         successAlert.setContentText("Room edited successfully");
         successAlert.showAndWait();
         
         // Return to home
         onBackButtonClick(event);
      } catch (SQLException e) {
         e.printStackTrace();
         Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
         successAlert.setTitle("Failure");
         successAlert.setHeaderText(null);
         successAlert.setContentText("Failed to edit room: " + e.getMessage());
         successAlert.showAndWait();
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
         
         controller.setHotel(hotel);
         stage.setScene(scene);
         stage.show();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   public void setHotelRoom(Hotel hotel, Room room) {
      this.hotel = hotel;
      this.room = room;
      roomName.setText("Room " + this.room.getRoomId());
      roomId.setText(Integer.toString(this.room.getRoomId()));
      price.setText(Double.toString(this.room.getPrice()));
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