package controller;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

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

        Button backBtn = new Button("← Back");
        Button signupBtn = new Button("Don't have an account? Sign Up");

        backBtn.setOnAction(e -> app.showWelcomeScreen());
        signupBtn.setOnAction(e -> app.showSignupScreen());

        HBox navButtons = new HBox(10, backBtn, signupBtn);

        this.getChildren().addAll(idLabel, studentID, loginBtn, message, navButtons);
        this.setSpacing(10);
        this.setStyle("-fx-padding: 20;");
    }
}