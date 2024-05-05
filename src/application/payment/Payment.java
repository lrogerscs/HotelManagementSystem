package application.payment;

import java.util.Date;

public class Payment {
   private int paymentId;
   private int customerId;
   private int roomId;
   private String method;
   private double amount;
   private Date payDate;

   public Payment(int paymentId, int customerId, int roomId, String method, double amount, Date payDate) {
      this.paymentId = paymentId;
      this.customerId = customerId;
      this.roomId = roomId;
      this.method = method;
      this.amount = amount;
      this.payDate = payDate;
   }

   public int getPaymentId() {
      return paymentId;
   }

   public int getCustomerId() {
      return customerId;
   }

   public int getRoomId() {
      return roomId;
   }

   public String getMethod() {
      return method;
   }

   public double getAmount() {
      return amount;
   }

   public Date getPayDate() {
      return payDate;
   }
}
