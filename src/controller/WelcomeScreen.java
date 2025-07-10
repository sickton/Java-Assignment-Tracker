package controller;

import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.util.Objects;

public class WelcomeScreen extends StackPane {

    public WelcomeScreen(AssignmentTrackerUI app) {
        // Background image with opacity
        Image bg = new Image(Objects.requireNonNull(getClass().getResource("/resources/Images/bg-TrackMyWork.jpg")).toExternalForm());
        BackgroundImage bgImage = new BackgroundImage(bg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, new BackgroundSize(1.0, 1.0, true, true, false, true));

        Region overlay = new Region();
        overlay.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
        overlay.setPrefSize(600, 500);

        VBox content = new VBox(20);
        content.setAlignment(Pos.CENTER);
        content.getStyleClass().add("welcome-container");

        Label title = new Label("Welcome to TrackMyWork");
        title.getStyleClass().add("title");

        Label subTitle = new Label("Assignments. Deadlines. Get. Them. All.");
        subTitle.getStyleClass().add("subtitle");

        ImageView logo = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/resources/Images/TrackYourWork.png")).toExternalForm()));
        logo.setFitWidth(150);
        logo.setPreserveRatio(true);
        logo.setSmooth(true);
        logo.setStyle("-fx-effect: dropshadow(gaussian, #222, 10, 0.5, 0, 0); -fx-background-radius: 10;");

        Button signUpBtn = new Button("Sign Up");
        Button loginBtn = new Button("Login");
        signUpBtn.setOnAction(e -> app.showSignupScreen());
        loginBtn.setOnAction(e -> app.showLoginScreen());

        content.getChildren().addAll(title, subTitle, logo, signUpBtn, loginBtn);

        this.getChildren().addAll(new Pane() {{ setBackground(new Background(bgImage)); }}, overlay, content);

        // Animation
        FadeTransition fade = new FadeTransition(Duration.seconds(1.2), content);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }
}
