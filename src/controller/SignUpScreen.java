package controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.util.Objects;

public class SignUpScreen extends GridPane {

    public SignUpScreen(AssignmentTrackerUI app) {
        this.setVgap(10);
        this.setHgap(10);
        this.setPadding(new Insets(30));
        this.setAlignment(Pos.CENTER);
        this.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/resources/CSSFiles/dark-theme.css")).toExternalForm());

        // Fields
        TextField firstName = new TextField();
        TextField lastName = new TextField();
        TextField age = new TextField();
        TextField studentID = new TextField();
        TextField email = new TextField();
        TextField major = new TextField();
        PasswordField password = new PasswordField();
        PasswordField retype = new PasswordField();

        // Tooltip
        Tooltip tooltip = new Tooltip("""
            Password must contain at least:
            • One uppercase letter (A-Z)
            • One lowercase letter (a-z)
            • One digit (0-9)
            • One special character (!,@,#, etc.)
            """);
        password.setTooltip(tooltip);

        // Message
        Label message = new Label();
        message.getStyleClass().add("message-label");

        // Buttons
        Button signUpBtn = new Button("Create Account");
        Button backBtn = new Button("← Back");
        Button loginBtn = new Button("Already have an account? Login");

        signUpBtn.getStyleClass().add("primary-button");
        backBtn.getStyleClass().add("ghost-button");
        loginBtn.getStyleClass().add("ghost-button");

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

        backBtn.setOnAction(e -> app.showWelcomeScreen());
        loginBtn.setOnAction(e -> app.showLoginScreen());

        HBox navButtons = new HBox(15, backBtn, loginBtn);
        navButtons.setAlignment(Pos.CENTER);

        // Layout
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
        add(navButtons, 0, 10, 2, 1);
    }
}
