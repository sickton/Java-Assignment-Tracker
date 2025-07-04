package controller.UI;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import view.AppFlowLogic.TrackYourWork;

public class SignUpScreen {

    public static void display(Stage stage) {
        TrackYourWork app = new TrackYourWork(); // or inject this globally

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

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
                String result = app.signupStudent(
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

        grid.add(new Label("First Name:"), 0, 0); grid.add(firstName, 1, 0);
        grid.add(new Label("Last Name:"), 0, 1); grid.add(lastName, 1, 1);
        grid.add(new Label("Age:"), 0, 2); grid.add(age, 1, 2);
        grid.add(new Label("Student ID:"), 0, 3); grid.add(studentID, 1, 3);
        grid.add(new Label("Email:"), 0, 4); grid.add(email, 1, 4);
        grid.add(new Label("Major:"), 0, 5); grid.add(major, 1, 5);
        grid.add(signUpBtn, 0, 6);
        grid.add(message, 1, 6);

        Scene scene = new Scene(grid, 400, 350);
        stage.setScene(scene);
    }
}
