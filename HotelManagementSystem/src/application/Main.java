package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import application.database.DatabaseConnection;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			// Connection test
			Connection connection = DatabaseConnection.getDatabaseConnection();
			if (connection != null) {
			   System.out.println("Connection established\n");
			   
			   Statement statement = connection.createStatement();
			   
			   System.out.println("AuthenticationSystem Data:");
			   ResultSet resultSet = statement.executeQuery("select * from AuthenticationSystem;");
			   while (resultSet.next())
			      System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
			   
			   connection.close();
			   System.out.println("\nConnection closed");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
