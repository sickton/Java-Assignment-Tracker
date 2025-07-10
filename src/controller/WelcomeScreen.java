package controller;

import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.util.Objects;

public class WelcomeScreen extends StackPane {

    public WelcomeScreen(AssignmentTrackerUI app) {
        // Background image
        ImageView background = new ImageView(new Image(
                Objects.requireNonNull(getClass().getResource("/resources/Images/bg-TrackMyWork.jpg")).toExternalForm()
        ));
        background.setFitWidth(600); // adjust or bind to scene if needed
        background.setPreserveRatio(true);
        background.setOpacity(0.6);

        // Title
        Label title = new Label("Welcome to TrackMyWork");
        title.getStyleClass().add("title");

        // Subtitle
        Label subTitle = new Label("Assignments. Deadlines. Get. Them. All.");
        subTitle.getStyleClass().add("subtitle");

        // App logo
        ImageView appLogoView = new ImageView(new Image(
                Objects.requireNonNull(getClass().getResource("/resources/Images/TrackYourWork.png")).toExternalForm()
        ));
        appLogoView.setFitWidth(180);
        appLogoView.setPreserveRatio(true);
        appLogoView.setSmooth(true);
        appLogoView.setEffect(new DropShadow(10, Color.BLACK));

        // Buttons
        Button signUpBtn = new Button("Sign Up");
        Button loginBtn = new Button("Login");

        signUpBtn.getStyleClass().add("button");
        loginBtn.getStyleClass().add("button");

        signUpBtn.setOnAction(e -> app.showSignupScreen());
        loginBtn.setOnAction(e -> app.showLoginScreen());

        // Central content box
        VBox contentBox = new VBox(title, subTitle, appLogoView, signUpBtn, loginBtn);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setSpacing(20);
        contentBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5); -fx-padding: 30px; -fx-background-radius: 15;");

        // Fade-in animation
        FadeTransition fade = new FadeTransition(Duration.seconds(1), contentBox);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();

        // Add stylesheet
        this.getStylesheets().add(Objects.requireNonNull(
                getClass().getResource("/resources/CSSFiles/dark-theme.css")).toExternalForm());

        this.getChildren().addAll(background, contentBox);
    }
}