package application.pane;

import application.hotel.Hotel;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class HotelPane extends VBox {
   private Hotel hotel;
   private Label name, phoneNumber, streetAddress, city, country;
   
   public HotelPane(Hotel hotel) {
      this.hotel = hotel;
      name = new Label(this.hotel.getName());
      phoneNumber = new Label(this.hotel.getPhoneNumber());
      streetAddress = new Label(this.hotel.getStreetAddress());
      city = new Label(this.hotel.getCity());
      country = new Label(this.hotel.getCountry());
      
      getChildren().addAll(name, phoneNumber, streetAddress, city, country);
   }
}
