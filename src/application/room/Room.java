package application.room;

import java.util.Date;

public class Room {
   private int roomId;
   private int hotelId;
   private Integer customerId;
   private double price;
   private Date date;
   
   public Room(int roomId, int hotelId, Integer customerId, double price, Date date) {
      this.roomId = roomId;
      this.hotelId = hotelId;
      this.customerId = customerId;
      this.price = price;
      this.date = date;
   }
   
   public int getRoomId() {
      return roomId;
   }
   
   public int getHotelId() {
      return hotelId;
   }
   
   public Integer getCustomerId() {
      return customerId;
   }
   
   public double getPrice() {
      return price;
   }
   
   public Date getDate() {
      return date;
   }
}
