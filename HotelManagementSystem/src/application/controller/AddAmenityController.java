package application.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;

import application.database.DatabaseConnection;
import application.hotel.Hotel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddAmenityController implements Initializable {
   @FXML
   private TextField amenity;
   
   @FXML
   private VBox inputFieldPanelPane;
   
   private String dbUrl;
   private String dbUser;
   private String dbPassword;
   private Hotel hotel;
   
   @FXML
   private void onSaveButtonClick(ActionEvent event) {
      if (amenity.getText() == null || amenity.getText().isEmpty())
         return;
      
      try {
         Connection connection = DatabaseConnection.getDatabaseConnection(dbUrl, dbUser, dbPassword);
         Statement statement = connection.createStatement();
         statement.executeUpdate("insert into Amenities(HotelID, Amenity) values (" 
                  + hotel.getHotelId() + ", '" + amenity.getText() + "')");
         connection.close();
         
         Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
         successAlert.setTitle("Success");
         successAlert.setHeaderText(null);
         successAlert.setContentText("Amenity added successfully!");
         successAlert.showAndWait();
         
         // Return to hotel
         onBackButtonClick(event);
      } catch (SQLException e) {
         e.printStackTrace();
         Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
         successAlert.setTitle("Failure");
         successAlert.setHeaderText(null);
         successAlert.setContentText("Failed to add amenity: " + e.getMessage());
         successAlert.showAndWait();
      }
   }
   
   @FXML
   private void onBackButtonClick(ActionEvent event) {
      try {
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
      }
   }
   
   public void setHotel(Hotel hotel) {
      this.hotel = hotel;
   }
   
   @Override
   public void initialize(URL location, ResourceBundle resources) {
      try {
         // Retrieve DB credentials
         Properties properties = new Properties();
         properties.load(new FileInputStream(new File("resources/credentials/credentials.properties")));
         
         dbUrl = properties.getProperty("url");
         dbUser = properties.getProperty("user");
         dbPassword = properties.getProperty("password");
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}