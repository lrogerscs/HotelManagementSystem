package application.controller;

import application.database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ChangePasswordController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField oldPasswordField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private Text messageText;
    

    
    private String dbUrl;
	private String dbUser;
	private String dbPassword;

    @FXML
    private void onChangePasswordButtonClick(ActionEvent event) {
        String username = usernameField.getText();
        String oldPassword = oldPasswordField.getText();
        String newPassword = newPasswordField.getText();

        if (username.isEmpty() || oldPassword.isEmpty() || newPassword.isEmpty()) {
            messageText.setText("Please fill in all fields.");
            return;
        }
        
        initialize();

        try {
            Connection connection = DatabaseConnection.getDatabaseConnection(dbUrl, dbUser, dbPassword);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT Password FROM Authenticationsystem WHERE LoginID='" + username + "'");
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("Password");
                
                if (oldPassword.equals(storedPassword)) {
                    statement.executeUpdate("UPDATE Authenticationsystem SET Password='" + newPassword + "' WHERE LoginID='" + username + "'");
                    messageText.setText("Password changed successfully.");
                } else {
                    messageText.setText("Incorrect old password.");
                }
            } else {
                messageText.setText("User not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onBackButtonClick(ActionEvent event) {
        try {
            // Load the login page
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) newPasswordField.getScene().getWindow(); // Get the current stage
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void initialize() {
        try {
            
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
