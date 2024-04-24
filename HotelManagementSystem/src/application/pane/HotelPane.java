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
   private Label name, address, phoneNumber, streetAddress, city, country;
   private Button select;
   private VBox titlePane, descPane, buttonPane;
   
   public HotelPane(Hotel hotel) {
      this.hotel = hotel;
      name = new Label(this.hotel.getName());
      address = new Label("Location: " + this.hotel.getStreetAddress() + ", " + this.hotel.getCity() + ", " + this.hotel.getCountry());
      phoneNumber = new Label("Phone: " + this.hotel.getPhoneNumber());
      select = new Button("Select");
      titlePane = new VBox();
      descPane = new VBox();
      buttonPane = new VBox();
      
      select.setOnAction(event -> loadHotel(event));
      
      titlePane.getChildren().add(name);
      descPane.getChildren().addAll(address, phoneNumber, select);
      buttonPane.getChildren().add(select);
      getChildren().addAll(titlePane, descPane, buttonPane);
      
      titlePane.getStyleClass().add("title-pane");
      descPane.getStyleClass().add("desc-pane");
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
