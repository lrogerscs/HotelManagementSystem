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
import javafx.scene.control.Alert;
import application.SimpleSubstitutionCipher;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements Initializable {
   @FXML
   private BorderPane HomePane;

   @FXML
   private TextField userField;

   static String loggedInUser;

   @FXML
   private Button loginButton;

   @FXML
   private CheckBox showPasswordCheckbox;

   @FXML
   private PasswordField passwordField;

   @FXML
   private Button changePasswordButton;

   private String dbUrl;
   private String dbUser;
   private String dbPassword;

   @FXML
   private void onLoginButtonClick(ActionEvent event) {
      if (userField.getText() == null || userField.getText().isEmpty() || passwordField.getText() == null
            || passwordField.getText().isEmpty())
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

   @FXML
   private void onChangePasswordButtonClick(ActionEvent event) {
      try {
         Parent root = FXMLLoader.load(getClass().getResource("/fxml/change_password.fxml"));
         Scene scene = new Scene(root);
         Stage stage = new Stage();
         stage.setScene(scene);
         stage.show();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   private boolean isValidLogin(String loginUser, String loginPassword) {
	    boolean valid = false;
	    boolean correctPassword = false;

	    // Check if login exists in table
	    try {
	        Connection connection = DatabaseConnection.getDatabaseConnection(dbUrl, dbUser, dbPassword);
	        Statement statement = connection.createStatement();

	        ResultSet resultSet = statement.executeQuery("SELECT LoginID, Password FROM AuthenticationSystem "
	                + "WHERE LoginID = '" + loginUser + "'");
	        
	        if (resultSet.isBeforeFirst()) {
	            // User exists, now check password
	            while (resultSet.next()) {
	                String storedPassword = resultSet.getString("Password");
	                if (loginPassword.equals(SimpleSubstitutionCipher.decrypt(storedPassword))) {
	                    correctPassword = true;
	                    loggedInUser = loginUser;
	                    break;
	                }
	            }
	            if (correctPassword) {
	                valid = true;
	            } else {
	                // Password is incorrect
	                showAlert(Alert.AlertType.ERROR, "Incorrect Password", "The password you entered is incorrect. Please try again.");
	            }
	        } else {
	            // User not found
	            showAlert(Alert.AlertType.ERROR, "User Not Found", "The user does not exist.");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return valid;
	}

	private void showAlert(Alert.AlertType alertType, String title, String message) {
	    Alert alert = new Alert(alertType);
	    alert.setTitle(title);
	    alert.setHeaderText(null);
	    alert.setContentText(message);
	    alert.showAndWait();
	}
	
   @Override
   public void initialize(URL location, ResourceBundle resources) {
      try {
         // Retrieve DB credentials
         Properties properties = new Properties();
         properties.load(new FileInputStream(new File("resources/credentials/credentials.properties")));

         showPasswordCheckbox.setOnAction(e -> {
            if (showPasswordCheckbox.isSelected()) {
               passwordField.setPromptText(passwordField.getText());
               passwordField.setText("");
            } else {
               passwordField.setText(passwordField.getPromptText());
               passwordField.setPromptText("Password");
            }
         });

         dbUrl = properties.getProperty("url");
         dbUser = properties.getProperty("user");
         dbPassword = properties.getProperty("password"); // Store the actual password
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

}
