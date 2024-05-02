package application.pane;

import java.io.IOException;

import application.controller.RoomController;
import application.hotel.Hotel;
import application.room.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
      roomId = new Label("Room " + Integer.toString(this.room.getRoomId()));
      customerId = new Label("Customer Number: " + Integer.toString(this.room.getCustomerId()));
      price = new Label("Price: $" + Double.toString(this.room.getPrice()));
      date = new Label("Check-In Date: " + this.room.getDate().toString());
      details = new Button("Details");
      edit = new Button("Edit");
      delete = new Button("Delete");
      titlePane = new VBox();
      descPane = new VBox();
      buttonPane = new HBox();
      
      details.setOnAction(event -> loadRoom(event));
      
      titlePane.getChildren().add(roomId);
      descPane.getChildren().addAll(customerId, price, date);
      buttonPane.getChildren().addAll(details, edit, delete);
      getChildren().addAll(titlePane, descPane, buttonPane);
      
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
         
         stage.setScene(scene);
         stage.show();
         controller.setHotelRoom(hotel, room);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
