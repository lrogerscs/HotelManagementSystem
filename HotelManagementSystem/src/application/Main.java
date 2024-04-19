package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
   @Override
   public void start(Stage stage) {
      try {
         // Load window
         Scene scene = new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml")));
         stage.setTitle("HotelManagementSystem");
         stage.setScene(scene);
         stage.show();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public static void main(String[] args) {
      launch(args);
   }
}
