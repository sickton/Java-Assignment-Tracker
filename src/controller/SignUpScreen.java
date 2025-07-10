package controller;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class SignUpScreen extends GridPane {

    public SignUpScreen(AssignmentTrackerUI app) {
        this.setVgap(10);
        this.setHgap(10);
        this.getStyleClass().add("signup-grid");

        TextField firstName = new TextField();
        TextField lastName = new TextField();
        TextField age = new TextField();
        TextField studentID = new TextField();
        TextField email = new TextField();
        TextField major = new TextField();
        PasswordField password = new PasswordField();
        PasswordField retype = new PasswordField();

        Tooltip tooltip = new Tooltip("""
            Password must contain at least:
            - One uppercase letter (A-Z)
            - One lowercase letter (a-z)
            - One digit (0-9)
            - One special character (!,@,#, etc.)
        """);
        password.setTooltip(tooltip);

        Label message = new Label();
        message.getStyleClass().add("message-label");

        Button signUpBtn = new Button("Create Account");
        signUpBtn.setOnAction(e -> {
            try {
                int ageVal = Integer.parseInt(age.getText());
                String result = app.getSystem().signupStudent(
                        firstName.getText(),
                        lastName.getText(),
                        ageVal,
                        studentID.getText(),
                        email.getText(),
                        major.getText(),
                        password.getText(),
                        retype.getText()
                );
                message.setText(result);
            } catch (Exception ex) {
                message.setText("Error: " + ex.getMessage());
            }
        });

        Button backBtn = new Button("← Back");
        Button loginBtn = new Button("Already have an account? Login");
        backBtn.setOnAction(e -> app.showWelcomeScreen());
        loginBtn.setOnAction(e -> app.showLoginScreen());

        HBox navButtons = new HBox(10, backBtn, loginBtn);

        add(new Label("First Name:"), 0, 0); add(firstName, 1, 0);
        add(new Label("Last Name:"), 0, 1); add(lastName, 1, 1);
        add(new Label("Age:"), 0, 2); add(age, 1, 2);
        add(new Label("Student ID:"), 0, 3); add(studentID, 1, 3);
        add(new Label("Email:"), 0, 4); add(email, 1, 4);
        add(new Label("Major:"), 0, 5); add(major, 1, 5);
        add(new Label("Password:"), 0, 6); add(password, 1, 6);
        add(new Label("Retype Password:"), 0, 7); add(retype, 1, 7);
        add(signUpBtn, 1, 8);
        add(message, 1, 9);
        add(navButtons, 1, 10);
    }
}
