package application.pane;

import application.employee.Employee;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class EmployeePane extends VBox {
    private Employee employee;
    private Label nameLabel;
    private Label titleLabel;
    private Label emailLabel;
    private Label phoneLabel;
    private Label addressLabel;

    public EmployeePane(Employee employee) {
        this.employee = employee;
        nameLabel = new Label("Name: " + employee.getName());
        titleLabel = new Label("Title: " + employee.getTitle());
        emailLabel = new Label("Email: " + employee.getEmail());
        phoneLabel = new Label("Phone: " + employee.getPhoneNumber());
        addressLabel = new Label("Address: " + employee.getAddress());

        getChildren().addAll(nameLabel, titleLabel, emailLabel, phoneLabel, addressLabel);
        getStyleClass().add("object-pane");
    }
}
