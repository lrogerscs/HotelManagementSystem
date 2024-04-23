package application.pane;

import application.room.Room;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class RoomPane extends VBox {
   private Room room;
   private Label roomId, customerId, price, date;
   
   public RoomPane(Room room) {
      this.room = room;
      roomId = new Label("Room Number: " + Integer.toString(this.room.getRoomId()));
      customerId = new Label("Customer Number: " + Integer.toString(this.room.getCustomerId()));
      price = new Label("Price: " + Double.toString(this.room.getPrice()));
      date = new Label("Check-In Date: " + this.room.getDate().toString());
      
      getChildren().addAll(roomId, customerId, price, date);
      getStyleClass().add("object-pane");
   }
}
