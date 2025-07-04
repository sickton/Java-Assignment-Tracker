package controller;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class WelcomeScreen extends VBox {

    public WelcomeScreen(AssignmentTrackerUI app) {
        Button signUpBtn = new Button("Sign Up");
        Button loginBtn = new Button("Login");

        signUpBtn.setOnAction(e -> app.showSignupScreen());
        loginBtn.setOnAction(e -> app.showLoginScreen());

        this.getChildren().addAll(signUpBtn, loginBtn);
        this.setSpacing(20);
        this.setStyle("-fx-padding: 40; -fx-alignment: center;");
    }
}
