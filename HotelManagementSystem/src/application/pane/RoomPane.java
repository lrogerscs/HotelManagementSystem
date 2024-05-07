package application.pane;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import application.controller.EditRoomController;
import application.controller.HotelController;
import application.controller.RoomController;
import application.database.DatabaseConnection;
import application.hotel.Hotel;
import application.room.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RoomPane extends VBox {
   private Hotel hotel;
   private Room room;
   private Label roomId, customerId, price, date;
   private Button details, edit, delete;
   private VBox titlePane, descPane;
   private HBox buttonPane;
   
   public RoomPane(Hotel hotel, Room room) {
      this.hotel = hotel;
      this.room = room;
      roomId = new Label("Room " + this.room.getRoomId());
      customerId = new Label("Customer Number: " + this.room.getCustomerId());
      price = new Label("Price: $" + this.room.getPrice());
      date = new Label("Check-In Date: " + this.room.getDate());
      details = new Button("Details");
      edit = new Button("Edit");
      delete = new Button("Delete");
      titlePane = new VBox();
      descPane = new VBox();
      buttonPane = new HBox();
      
      details.setOnAction(event -> loadRoom(event));
      edit.setOnAction(event -> loadEditRoom(event));
      delete.setOnAction(event -> deleteRoom(event));
      
      titlePane.getChildren().add(roomId);
      descPane.getChildren().addAll(customerId, price, date);
      buttonPane.getChildren().addAll(details, edit, delete);
      getChildren().addAll(titlePane, descPane, buttonPane);
      
      edit.getStyleClass().add("edit-button");
      delete.getStyleClass().add("delete-button");
      
      titlePane.getStyleClass().add("title-pane");
      descPane.getStyleClass().add("desc-pane");
      buttonPane.getStyleClass().add("button-pane");
      getStyleClass().add("object-pane");
   }
   
   private void loadRoom(ActionEvent event) {
      try {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/room.fxml"));
         Parent root = loader.load();
         RoomController controller = loader.getController();
         Scene scene = new Scene(root);
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         
         controller.setHotelRoom(hotel, room);
         stage.setScene(scene);
         stage.show();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   private void loadEditRoom(ActionEvent event) {
      try {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/edit_room.fxml"));
         Parent root = loader.load();
         EditRoomController controller = loader.getController();
         Scene scene = new Scene(root);
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

         controller.setHotelRoom(hotel, room);
         stage.setScene(scene);
         stage.show();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   private void deleteRoom(ActionEvent event) {
      try {
         // Retrieve DB credentials
         Properties properties = new Properties();
         properties.load(new FileInputStream(new File("resources/credentials/credentials.properties")));
         
         String dbUrl = properties.getProperty("url");
         String dbUser = properties.getProperty("user");
         String dbPassword = properties.getProperty("password");
         
         // Delete data
         Connection connection = DatabaseConnection.getDatabaseConnection(dbUrl, dbUser, dbPassword);
         Statement statement = connection.createStatement();
         statement.executeUpdate("delete from Room where RoomID = " + room.getRoomId());
         statement.executeUpdate("delete from Features where RoomID = " + room.getRoomId());
         statement.executeUpdate("delete from Customer where RoomID = " + room.getRoomId());
         statement.executeUpdate("delete from Payment where RoomID = " + room.getRoomId());
         connection.close();
         
         // Reload hotel
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
         Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
         successAlert.setTitle("Failure");
         successAlert.setHeaderText(null);
         successAlert.setContentText("Failed to delete room: " + e.getMessage());
         successAlert.showAndWait();
      } catch (SQLException e) {
         e.printStackTrace();
         Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
         successAlert.setTitle("Failure");
         successAlert.setHeaderText(null);
         successAlert.setContentText("Failed to delete room: " + e.getMessage());
         successAlert.showAndWait();
      }
   }
}
