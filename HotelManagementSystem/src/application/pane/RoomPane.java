package application.pane;

import application.room.Room;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class RoomPane extends VBox {
   private Room room;
   private Label roomId, customerId, price, date;
   private VBox titlePane, descPane;
   
   public RoomPane(Room room) {
      this.room = room;
      roomId = new Label("Room " + Integer.toString(this.room.getRoomId()));
      customerId = new Label("Customer Number: " + Integer.toString(this.room.getCustomerId()));
      price = new Label("Price: " + Double.toString(this.room.getPrice()));
      date = new Label("Check-In Date: " + this.room.getDate().toString());
      titlePane = new VBox();
      descPane = new VBox();
      
      titlePane.getChildren().add(roomId);
      descPane.getChildren().addAll(customerId, price, date);
      getChildren().addAll(titlePane, descPane);
      
      titlePane.getStyleClass().add("title-pane");
      descPane.getStyleClass().add("desc-pane");
      getStyleClass().add("object-pane");
   }
}
