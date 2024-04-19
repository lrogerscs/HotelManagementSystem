module HotelManagementSystem {
   requires javafx.controls;
   requires java.sql;
   requires javafx.fxml;
   requires javafx.graphics;
   requires javafx.base;
   
   opens application to javafx.graphics, javafx.fxml;
   opens application.controller to javafx.fxml;
}
