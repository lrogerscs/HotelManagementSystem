package application.customer;

import java.util.Date;

public class Customer {
	
	private int customerId;
	private int roomId;
	private String name;
	private Date dob;
	private int age;
	private String email;
	private String phoneNumber;
	private String paymentMethod;
	private String streetAddress;
	
	public Customer(int customerId, int roomId, String name, Date dob, int age, String email, String phoneNumber, String paymentMethod, String streetAddress) {
		this.customerId = customerId;
		this.roomId = roomId;
		this.name = name;
		this.dob = dob;
		this.age = age;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.paymentMethod = paymentMethod;
		this.streetAddress = streetAddress;
	}
	
	public int getCustomerId() {
       return customerId;
    }
	
	public int getRoomId() {
	   return roomId;
	}
   
    public String getName() {
       return name;
    }
    
    public Date getDob() {
    	return dob;
    }
    
    public int getAge() {
    	return age;
    }
   
    public String getPhoneNumber() {
       return phoneNumber;
    }
   
    public String getStreetAddress() {
       return streetAddress;
    }
   
    public String getPaymentMethod() {
       return paymentMethod;
    }
   
    public String getEmail() {
       return email;
    }

}
