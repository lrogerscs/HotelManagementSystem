package application.hotel;

public class Hotel {
   private int hotelId;
   private String name;
   private String phoneNumber;
   private String streetAddress;
   private String city;
   private String country;
   
   public Hotel(int hotelId, String name, String phoneNumber, String streetAddress, String city, String country) {
      this.hotelId = hotelId;
      this.name = name;
      this.phoneNumber = phoneNumber;
      this.streetAddress = streetAddress;
      this.city = city;
      this.country = country;
   }
   
   public int getHotelId() {
      return hotelId;
   }
   
   public String getName() {
      return name;
   }
   
   public String getPhoneNumber() {
      return phoneNumber;
   }
   
   public String getStreetAddress() {
      return streetAddress;
   }
   
   public String getCity() {
      return city;
   }
   
   public String getCountry() {
      return country;
   }
}
