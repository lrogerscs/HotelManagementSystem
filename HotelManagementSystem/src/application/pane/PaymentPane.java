package application.pane;

import application.payment.Payment;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PaymentPane extends VBox {
   private Payment payment;
   private Label paymentId, customerId, method, amount, date;
   private VBox titlePane, descPane;
   
   public PaymentPane(Payment payment) {
      this.payment = payment;
      paymentId = new Label("Payment " + Integer.toString(this.payment.getPaymentId()));
      customerId = new Label("Customer Number: " + Integer.toString(this.payment.getCustomerId()));
      method = new Label("Payment Method: " + this.payment.getMethod());
      amount = new Label("Amount: $" + Double.toString(this.payment.getAmount()));
      date = new Label("Payment Date: " + this.payment.getPayDate().toString());
      titlePane = new VBox();
      descPane = new VBox();
      
      titlePane.getChildren().add(paymentId);
      descPane.getChildren().addAll(customerId, method, amount, date);
      getChildren().addAll(titlePane, descPane);
      
      titlePane.getStyleClass().add("title-pane");
      descPane.getStyleClass().add("desc-pane");
      getStyleClass().add("object-pane");
   }
}
