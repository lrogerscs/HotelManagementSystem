package application.employee;

public class Employee {
		
	private int customerId;
	private int hotelId;
	private String loginId;
	private String name;
	private String title;
	private String email;
	private String phoneNumber;
	private String streetAddress;
	
	public Employee(int customerId, int hotelId, String loginId, String name, String title, String email, String phoneNumber, String streetAddress) {
		this.customerId = customerId;
		this.hotelId = hotelId;
		this.loginId = loginId;
		this.name = name;
		this.title = title;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.streetAddress = streetAddress;
	}
	
	public int getCustomerId() {
       return customerId;
    }
	
	public int getHotelId() {
	   return hotelId;
	}
	
	public String getLoginId() {
	   return loginId;
	}
   
    public String getName() {
       return name;
    }
    
    public String getTitle() {
    	return title;
    }
   
    public String getPhoneNumber() {
       return phoneNumber;
    }
   
    public String getStreetAddress() {
       return streetAddress;
    }
   
    public String getEmail() {
       return email;
    }


}
