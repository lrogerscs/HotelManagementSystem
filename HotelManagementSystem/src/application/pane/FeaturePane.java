package application.pane;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import application.controller.RoomController;
import application.database.DatabaseConnection;
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
import javafx.stage.Stage;

public class FeaturePane extends HBox {
   private Hotel hotel;
   private Room room;
   private Label feature;
   private Button delete;
   
   public FeaturePane(Hotel hotel, Room room, String feature) {
      this.hotel = hotel;
      this.room = room;
      this.feature = new Label(feature);
      delete = new Button("Delete");
      
      delete.setOnAction(event -> deleteAmenity(event));
      
      getChildren().addAll(this.feature, delete);
      
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
         statement.executeUpdate("delete from Features where RoomID = " + room.getRoomId() 
               + " and Feature = '" + feature.getText() + "'");
         connection.close();
         
         // Reload hotel
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
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
}
