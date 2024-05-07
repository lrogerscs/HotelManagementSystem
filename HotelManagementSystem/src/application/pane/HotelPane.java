package application.pane;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import application.controller.EditHotelController;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HotelPane extends VBox {
   private Hotel hotel;
   private Label hotelId, name, address, phoneNumber;
   private Button details, edit, delete;
   private VBox titlePane, descPane;
   private HBox buttonPane;
   
   public HotelPane(Hotel hotel) {
      this.hotel = hotel;
      hotelId = new Label("Hotel ID: " + this.hotel.getHotelId());
      name = new Label(this.hotel.getName());
      address = new Label("Location: " + this.hotel.getStreetAddress() + ", " + this.hotel.getCity() + ", " + this.hotel.getCountry());
      phoneNumber = new Label("Phone: " + this.hotel.getPhoneNumber());
      details = new Button("Details");
      edit = new Button("Edit");
      delete = new Button("Delete");
      titlePane = new VBox();
      descPane = new VBox();
      buttonPane = new HBox();
      
      details.setOnAction(event -> loadHotel(event));
      edit.setOnAction(event -> loadEditHotel(event));
      delete.setOnAction(event -> deleteHotel(event));
      
      titlePane.getChildren().add(name);
      descPane.getChildren().addAll(hotelId, address, phoneNumber, details);
      buttonPane.getChildren().addAll(details, edit, delete);
      getChildren().addAll(titlePane, descPane, buttonPane);
      
      edit.getStyleClass().add("edit-button");
      delete.getStyleClass().add("delete-button");
      
      titlePane.getStyleClass().add("title-pane");
      descPane.getStyleClass().add("desc-pane");
      buttonPane.getStyleClass().add("button-pane");
      getStyleClass().add("object-pane");
   }
   
   private void loadHotel(ActionEvent event) {
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
   
   private void loadEditHotel(ActionEvent event) {
      try {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/edit_hotel.fxml"));
         Parent root = loader.load();
         EditHotelController controller = loader.getController();
         Scene scene = new Scene(root);
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         
         controller.setHotel(hotel);
         stage.setScene(scene);
         stage.show();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   private void deleteHotel(ActionEvent event) {
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
         statement.executeUpdate("delete from Hotel where HotelID = " + hotel.getHotelId());
         statement.executeUpdate("delete from Amenities where HotelID = " + hotel.getHotelId());
         
         // Delete employees and associated data
         ResultSet resultSet = statement.executeQuery("select LoginID from Employee where HotelID = " + hotel.getHotelId());
         ArrayList<String> loginIds = new ArrayList<String>();
         
         while (resultSet.next())
            loginIds.add(resultSet.getString(1));
         
         statement.executeUpdate("delete from Employee where HotelID = " + hotel.getHotelId());
         for (String loginId : loginIds) {
            if (loginId != null)
               statement.executeUpdate("delete from AuthenticationSystem where LoginID = '" + loginId + "'");
         }
         
         // Delete rooms and associated data
         resultSet = statement.executeQuery("select RoomID from Room where HotelID = " + hotel.getHotelId());
         ArrayList<Integer> roomIds = new ArrayList<Integer>();
         
         while (resultSet.next())
            roomIds.add(resultSet.getInt(1));
         
         statement.executeUpdate("delete from Room where HotelID = " + hotel.getHotelId());
         for (int roomId : roomIds) {
            statement.executeUpdate("delete from Features where RoomID = " + roomId);
            statement.executeUpdate("delete from Customer where RoomID = " + roomId);
            statement.executeUpdate("delete from Payment where RoomID = " + roomId);
         }
         
         connection.close();
         
         // Reload home
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
         Parent root = loader.load();
         Scene scene = new Scene(root);
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         stage.setScene(scene);
         stage.show();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
}