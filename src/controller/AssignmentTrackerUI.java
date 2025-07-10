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

        Image appIcon = new Image(Objects.requireNonNull(getClass().getResource("/resources/Images/TrackYourWork.png")).toExternalForm());
        primaryStage.getIcons().add(appIcon);
        showWelcomeScreen();
    }

    public void showWelcomeScreen() {
        WelcomeScreen welcome = new WelcomeScreen(this);
        Scene scene = new Scene(welcome, 600, 500);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/resources/CSSFiles/dark-theme.css")).toExternalForm());
        primaryStage.setTitle("TrackMyWork - Welcome");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showLoginScreen() {
        LoginScreen login = new LoginScreen(this);
        Scene scene = new Scene(login, 500, 450);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/resources/CSSFiles/dark-theme.css")).toExternalForm());
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
    }

    public void showSignupScreen() {
        SignUpScreen signup = new SignUpScreen(this);
        Scene scene = new Scene(signup, 600, 550);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/resources/CSSFiles/dark-theme.css")).toExternalForm());
        primaryStage.setTitle("Sign Up");
        primaryStage.setScene(scene);
    }

    public void showDashboard() {
        DashboardScreen dashboard = new DashboardScreen(this, system);
        Scene scene = new Scene(dashboard, 800, 600);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/resources/CSSFiles/dark-theme.css")).toExternalForm());
        primaryStage.setTitle("Dashboard");
        primaryStage.setScene(scene);
    }

    public TrackYourWork getSystem() {
        return system;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
