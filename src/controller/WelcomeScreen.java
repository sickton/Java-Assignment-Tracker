package controller;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.Objects;

public class WelcomeScreen extends VBox {

    public WelcomeScreen(AssignmentTrackerUI app) {
        this.setSpacing(20);
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-padding: 40;");

        // Load logo image
        Image logo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/resources/Images/TrackYourWork.png"))); // Assuming it's saved as logo.png
        ImageView logoView = new ImageView(logo);
        logoView.setFitHeight(150);
        logoView.setPreserveRatio(true);
        Label welcomeMessage = new Label("Welcome To Track Your Work!");
        welcomeMessage.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 25px; -fx-font-weight: 700;");

        VBox message = new VBox(welcomeMessage);
        message.setAlignment(Pos.CENTER);

        // Buttons
        Button signUpBtn = new Button("Sign Up");
        Button loginBtn = new Button("Login");

        signUpBtn.setOnAction(e -> app.showSignupScreen());
        loginBtn.setOnAction(e -> app.showLoginScreen());

        this.getChildren().addAll(logoView, message, signUpBtn, loginBtn);
    }
}