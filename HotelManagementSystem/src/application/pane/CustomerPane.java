package application.pane;

import application.customer.Customer;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CustomerPane extends VBox {
   private Customer customer;
   private Label customerId, name, dob, age, email, phoneNumber, paymentMethod, address;
   private VBox titlePane, descPane;
   
   public CustomerPane(Customer customer) {
      this.customer = customer;
      customerId = new Label("Customer Number: " + this.customer.getCustomerId());
      name = new Label(this.customer.getName());
      dob = new Label("Date of Birth: " + this.customer.getDob().toString());
      age = new Label("Age: " + this.customer.getAge());
      email = new Label("Email: " + this.customer.getEmail());
      phoneNumber = new Label("Phone: " + this.customer.getPhoneNumber());
      paymentMethod = new Label("Payment Method: " + this.customer.getPaymentMethod());
      address = new Label("Address: " + this.customer.getAddress());
      titlePane = new VBox();
      descPane = new VBox();
      
      titlePane.getChildren().add(name);
      descPane.getChildren().addAll(customerId, dob, age, email, phoneNumber, paymentMethod, address);
      getChildren().addAll(titlePane, descPane);
      
      titlePane.getStyleClass().add("title-pane");
      descPane.getStyleClass().add("desc-pane");
      getStyleClass().add("object-pane");
   }
}
