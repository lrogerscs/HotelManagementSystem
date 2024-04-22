package application.pane;

import application.controller.HotelController;
import application.hotel.Hotel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HotelPane extends VBox {
   private Hotel hotel;
   private Label name, phoneNumber, streetAddress, city, country;
   private Button select;
   
   public HotelPane(Hotel hotel) {
      this.hotel = hotel;
      name = new Label(this.hotel.getName());
      phoneNumber = new Label(this.hotel.getPhoneNumber());
      streetAddress = new Label(this.hotel.getStreetAddress());
      city = new Label(this.hotel.getCity());
      country = new Label(this.hotel.getCountry());
      select = new Button("Select");
      
      select.setOnAction(event -> loadHotel(event));
      
      getChildren().addAll(name, phoneNumber, streetAddress, city, country, select);
      getStyleClass().add("object-pane");
   }
   
   private void loadHotel(ActionEvent event) {
      try {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/hotel.fxml"));
         Parent root = loader.load();
         HotelController controller = loader.getController();
         Scene scene = new Scene(root);
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         
         stage.setScene(scene);
         stage.show();
         controller.setHotel(hotel);
      } catch (Exception e) {
         e.printStackTrace();
      }
      
   }
}
