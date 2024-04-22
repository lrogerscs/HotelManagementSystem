package application.pane;

import java.text.DateFormat;

import application.room.Room;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class RoomPane extends VBox {
   private Room room;
   private Label roomId, customerId, price, date;
   
   public RoomPane(Room room) {
      this.room = room;
      roomId = new Label(Integer.toString(room.getRoomId()));
      customerId = new Label(Integer.toString(room.getCustomerId()));
      price = new Label(Double.toString(room.getPrice()));
      date = new Label(room.getDate().toString());
      
      getChildren().addAll(roomId, customerId, price, date);
      getStyleClass().add("object-pane");
   }
}
