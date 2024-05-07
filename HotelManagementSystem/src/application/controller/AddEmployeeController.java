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
import application.SimpleSubstitutionCipher;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddEmployeeController implements Initializable {
   @FXML
   private TextField employeeId;
   
   @FXML
   private TextField name;
   
   @FXML
   private TextField title;
   
   @FXML
   private TextField email;
   
   @FXML
   private TextField phoneNumber;
   
   @FXML
   private TextField address;
   
   @FXML
   private CheckBox systemAccess;
   
   @FXML
   private VBox inputFieldPanelPane;
   
   private String dbUrl;
   private String dbUser;
   private String dbPassword;
   private Hotel hotel;
   private TextField loginId;
   private TextField loginPassword;
   private HBox loginIdPane;
   private HBox loginPasswordPane;
   
   @FXML
   private void onSaveButtonClick(ActionEvent event) {
      if (employeeId.getText() == null || employeeId.getText().isEmpty() 
            || name.getText() == null || name.getText().isEmpty() 
            || title.getText() == null || title.getText().isEmpty() 
            || email.getText() == null || email.getText().isEmpty()
            || phoneNumber.getText() == null || phoneNumber.getText().isEmpty()
            || address.getText() == null || address.getText().isEmpty())
         return;
      
      // If employee has system access, check if login fields are empty
      if (systemAccess.isSelected() && (loginId.getText() == null || loginId.getText().isEmpty() 
            || loginPassword.getText() == null || loginPassword.getText().isEmpty()))
         return;
      
      try {
         Connection connection = DatabaseConnection.getDatabaseConnection(dbUrl, dbUser, dbPassword);
         Statement statement = connection.createStatement();
         
         String encryptedPassword = SimpleSubstitutionCipher.encrypt(loginPassword.getText());
         
         statement.executeUpdate("insert into Employee(EmployeeID, HotelID, LoginID, Name, Title, Email, PhoneNumber, Address) values (" 
                  + employeeId.getText() + ", " + hotel.getHotelId() 
                  + (systemAccess.isSelected() ? ", '" + loginId.getText() + "', '" : ", null, '")
                  + name.getText() + "', '" + title.getText() + "', '" + email.getText() 
                  + "', '" + phoneNumber.getText() + "', '" + address.getText() + "')");
         statement.executeUpdate("INSERT INTO AuthenticationSystem (LoginID, Password) VALUES ('" + loginId.getText() 
         			+ "', '" + encryptedPassword + "')");
         connection.close();
         
         Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
         successAlert.setTitle("Success");
         successAlert.setHeaderText(null);
         successAlert.setContentText("Employee added successfully!");
         successAlert.showAndWait();
         
         // Return to home
         onBackButtonClick(event);
      } catch (SQLException e) {
         e.printStackTrace();
         Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
         successAlert.setTitle("Failure");
         successAlert.setHeaderText(null);
         successAlert.setContentText("Failed to add employee: " + e.getMessage());
         successAlert.showAndWait();
      }
   }
   
   @FXML
   private void onSystemAccessCheckBoxClick(ActionEvent event) {
      if (systemAccess.isSelected())
         inputFieldPanelPane.getChildren().addAll(loginIdPane, loginPasswordPane);
      else
         inputFieldPanelPane.getChildren().removeAll(loginIdPane, loginPasswordPane);
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
      loginId = new TextField();
      loginPassword = new TextField();
      loginIdPane = new HBox(new Label("LoginID:"), loginId);
      loginPasswordPane = new HBox(new Label("Login Password:"), loginPassword);
      
      loginIdPane.getStyleClass().add("prompt-pane");
      loginPasswordPane.getStyleClass().add("prompt-pane");
      
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