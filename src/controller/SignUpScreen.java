package controller;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class SignUpScreen extends GridPane {

    public SignUpScreen(AssignmentTrackerUI app) {
        this.setVgap(10);
        this.setHgap(10);
        this.setStyle("-fx-padding: 20;");

        TextField firstName = new TextField();
        TextField lastName = new TextField();
        TextField age = new TextField();
        TextField studentID = new TextField();
        TextField email = new TextField();
        TextField major = new TextField();
        Label message = new Label();

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
                        major.getText()
                );
                message.setText(result);
            } catch (Exception ex) {
                message.setText("Error: " + ex.getMessage());
            }
        });

        add(new Label("First Name:"), 0, 0); add(firstName, 1, 0);
        add(new Label("Last Name:"), 0, 1); add(lastName, 1, 1);
        add(new Label("Age:"), 0, 2); add(age, 1, 2);
        add(new Label("Student ID:"), 0, 3); add(studentID, 1, 3);
        add(new Label("Email:"), 0, 4); add(email, 1, 4);
        add(new Label("Major:"), 0, 5); add(major, 1, 5);
        add(signUpBtn, 0, 6);
        add(message, 1, 6);
    }
}
