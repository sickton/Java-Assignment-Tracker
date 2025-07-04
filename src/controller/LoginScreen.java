package controller;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class LoginScreen extends VBox {

    public LoginScreen(AssignmentTrackerUI app) {
        Label idLabel = new Label("Enter Student ID:");
        TextField studentID = new TextField();
        Button loginBtn = new Button("Login");
        Label message = new Label();

        loginBtn.setOnAction(e -> {
            try {
                app.getSystem().loginAs(studentID.getText());
                message.setText("Login successful!");
                app.showDashboard();
            } catch (Exception ex) {
                message.setText("Login failed: " + ex.getMessage());
            }
        });

        this.getChildren().addAll(idLabel, studentID, loginBtn, message);
        this.setSpacing(10);
        this.setStyle("-fx-padding: 20;");
    }
}
