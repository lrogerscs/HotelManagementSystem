package application.employee;

public class Employee {
    private int employeeId;
    private int loginId;
    private String name;
    private String title;
    private String email;
    private String phoneNumber;
    private String address;

    public Employee(int employeeId, int loginId, String name, String title, String email, String phoneNumber, String address) {
        this.employeeId = employeeId;
        this.loginId = loginId;
        this.name = name;
        this.title = title;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getLoginId() {
        return loginId;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }
}
