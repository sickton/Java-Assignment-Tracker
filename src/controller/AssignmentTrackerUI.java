package controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import view.AppFlowLogic.TrackYourWork;

import java.util.Objects;

public class AssignmentTrackerUI extends Application {

    private Stage primaryStage;
    private TrackYourWork system;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.system = new TrackYourWork();

        this.primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/resources/images/logo.png"))));
        showWelcomeScreen();
    }

    public void showWelcomeScreen() {
        WelcomeScreen welcome = new WelcomeScreen(this);
        Scene scene = new Scene(welcome, 600, 400);

        // Attach dark mode CSS
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/resources/styles/darkTheme.css")).toExternalForm());

        primaryStage.setTitle("TrackMyWork - Welcome");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showLoginScreen() {
        LoginScreen login = new LoginScreen(this);
        Scene scene = new Scene(login, 600, 400);
        primaryStage.setTitle("Login");
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/resources/styles/darkTheme.css")).toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showSignupScreen() {
        SignUpScreen signup = new SignUpScreen(this);
        Scene scene = new Scene(signup, 600, 400);
        primaryStage.setTitle("Sign Up");
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/resources/styles/darkTheme.css")).toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showDashboard() {
        DashboardScreen dashboard = new DashboardScreen(this, system);
        Scene scene = new Scene(dashboard, 800, 600);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/resources/styles/darkTheme.css")).toExternalForm());
        primaryStage.setTitle("Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public TrackYourWork getSystem() {
        return system;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
