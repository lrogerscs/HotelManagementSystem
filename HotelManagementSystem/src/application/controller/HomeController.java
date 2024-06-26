package application.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

import application.database.DatabaseConnection;
import application.hotel.Hotel;
import application.pane.HotelPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class HomeController implements Initializable {
	   
   @FXML
   private FlowPane hotelPanelPane;
   
   @FXML
   private Label usernameLabel; 
   
   private String dbUrl;
   private String dbUser;
   private String dbPassword;
   
   @FXML
   private void onAddHotelButtonClick(ActionEvent event) {
      try {
         Parent root = FXMLLoader.load(getClass().getResource("/fxml/add_hotel.fxml"));
         Scene scene = new Scene(root);
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         stage.setScene(scene);
         stage.show();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   @Override
   public void initialize(URL location, ResourceBundle resources) {
      try {
         ArrayList<Hotel> hotels = new ArrayList<Hotel>();
         
         // Retrieve DB credentials
         Properties properties = new Properties();
         properties.load(new FileInputStream(new File("resources/credentials/credentials.properties")));
         
         dbUrl = properties.getProperty("url");
         dbUser = properties.getProperty("user");
         dbPassword = properties.getProperty("password");
         
         // Read hotels
         Connection connection = DatabaseConnection.getDatabaseConnection(dbUrl, dbUser, dbPassword);
         Statement statement = connection.createStatement();

         // Read login user name
         Connection connectionLogin = DatabaseConnection.getDatabaseConnection(dbUrl, dbUser, dbPassword);
         Statement statementLogin = connectionLogin.createStatement();
         
         String username = LoginController.loggedInUser;
         
         String usernameQuery = String.format("SELECT Name FROM employee WHERE LoginID = '%s'", username);
         ResultSet ename = statementLogin.executeQuery(usernameQuery);
         
         ResultSet resultSet = statement.executeQuery("select * from Hotel");
    
         
         
         while (resultSet.next()) {
            int hotelId = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String phoneNumber = resultSet.getString(3);
            String streetAddress = resultSet.getString(4);
            String city = resultSet.getString(5);
            String country = resultSet.getString(6);
            
            hotels.add(new Hotel(hotelId, name, phoneNumber, streetAddress, city, country));
         }

         while(ename.next()) {
        	 username = ename.getString(1);
         }
         usernameLabel.setText(username);
         
         // Display hotels
         for (Hotel hotel : hotels)
            hotelPanelPane.getChildren().add(new HotelPane(hotel));
         
         connection.close();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
}
