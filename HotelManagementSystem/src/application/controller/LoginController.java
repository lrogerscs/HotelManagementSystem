package application.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;

import application.database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController implements Initializable {
	@FXML
	BorderPane HomePane;
	
	@FXML
	TextField userField;
	
	@FXML
	TextField passwordField;
	
	@FXML
	Button loginButton;
	
	private String dbUrl;
	private String dbUser;
	private String dbPassword;
	
	@FXML
	private void onLoginButtonClick(ActionEvent event) {
	   if (userField.getText() == null || userField.getText().isEmpty() || passwordField.getText() == null || passwordField.getText().isEmpty())
	      return;
	   
	   if (isValidLogin(userField.getText(), passwordField.getText())) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/home.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
         } catch (Exception e) {
            e.printStackTrace();
         }
	   }
	}
	
	private boolean isValidLogin(String loginUser, String loginPassword) {
	   boolean valid = false;
	   
	   // Check if login exists in table
	   try {
	      Connection connection = DatabaseConnection.getDatabaseConnection(dbUrl, dbUser, dbPassword);
	      Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery("select LoginID, Password from AuthenticationSystem "
               + "where LoginID = '" + loginUser + "' and Password = '" + loginPassword + "'");
         
         if (resultSet.isBeforeFirst())
            valid = true;
	   } catch (Exception e) {
	      e.printStackTrace();
	   }
	   
	   return valid;
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
