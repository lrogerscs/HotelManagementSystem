package application.pane;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import application.controller.EditEmployeeController;
import application.controller.HotelController;
import application.database.DatabaseConnection;
import application.employee.Employee;
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

public class EmployeePane extends VBox {
   private Hotel hotel;
   private Employee employee;
   private Label employeeId, loginId, name, title, email, phone, address;
   private Button edit, delete;
   private VBox titlePane, descPane;
   private HBox buttonPane;

   public EmployeePane(Hotel hotel, Employee employee) {
      this.hotel = hotel;
      this.employee = employee;
      employeeId = new Label("EmployeeID: " + this.employee.getEmployeeId());
      loginId = new Label("LoginID: " + this.employee.getLoginId());
      name = new Label(this.employee.getName());
      title = new Label("Title: " + this.employee.getTitle());
      email = new Label("Email: " + this.employee.getEmail());
      phone = new Label("Phone: " + this.employee.getPhoneNumber());
      address = new Label("Address: " + this.employee.getAddress());
      edit = new Button("Edit");
      delete = new Button("Delete");
      titlePane = new VBox(name);
      descPane = new VBox(employeeId, loginId, title, email, phone, address);
      buttonPane = new HBox(edit, delete);
      
      edit.setOnAction(event -> loadEditEmployee(event));
      delete.setOnAction(event -> deleteEmployee(event));
      
      getChildren().addAll(titlePane, descPane, buttonPane);

      titlePane.getStyleClass().add("title-pane");
      descPane.getStyleClass().add("desc-pane");
      buttonPane.getStyleClass().add("button-pane");
      getStyleClass().add("object-pane");
   }

   private void loadEditEmployee(ActionEvent event) {
      try {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/edit_employee.fxml"));
         Parent root = loader.load();
         EditEmployeeController controller = loader.getController();
         Scene scene = new Scene(root);
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

         stage.setScene(scene);
         stage.show();
         controller.setHotelEmployee(hotel, employee);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   private void deleteEmployee(ActionEvent event) {
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
         statement.executeUpdate("delete from Employee where EmployeeID = " + employee.getEmployeeId());
         // TODO: Add query to delete from AuthenticationSystem
         connection.close();
         
         // Reload hotel
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/hotel.fxml"));
         Parent root = loader.load();
         HotelController controller = loader.getController();
         Scene scene = new Scene(root);
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

         stage.setScene(scene);
         stage.show();
         controller.setHotel(hotel);
      } catch (IOException e) {
         e.printStackTrace();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
}