package application.pane;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import application.controller.HotelController;
import application.database.DatabaseConnection;
import application.hotel.Hotel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AmenityPane extends HBox {
   private Hotel hotel;
   private Label amenity;
   private Button delete;
   
   public AmenityPane(Hotel hotel, String amenity) {
      this.hotel = hotel;
      this.amenity = new Label(amenity);
      delete = new Button("Delete");
      
      delete.setOnAction(event -> deleteAmenity(event));
      
      getChildren().addAll(this.amenity, delete);
      
      delete.getStyleClass().add("delete-button");
      getStyleClass().add("sub-pane");
   }
   
   private void deleteAmenity(ActionEvent event) {
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
         statement.executeUpdate("delete from Amenities where HotelID = " + hotel.getHotelId() 
               + " and Amenity = '" + amenity.getText() + "'");
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
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
}
