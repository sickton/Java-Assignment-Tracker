package controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.AppFlowLogic.TrackYourWork;

public class AssignmentTrackerUI extends Application {

    private Stage primaryStage;
    private TrackYourWork system;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.system = new TrackYourWork();
        showWelcomeScreen();
    }

    public void showWelcomeScreen() {
        WelcomeScreen welcome = new WelcomeScreen(this);
        primaryStage.setTitle("TrackMyWork - Welcome");
        primaryStage.setScene(new Scene(welcome, 600, 400));
        primaryStage.show();
    }

    public void showLoginScreen() {
        LoginScreen login = new LoginScreen(this);
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(login, 600, 400));
    }

    public void showSignupScreen() {
        SignUpScreen signup = new SignUpScreen(this);
        primaryStage.setTitle("Sign Up");
        primaryStage.setScene(new Scene(signup, 600, 400));
    }

    public void showDashboard() {
        DashboardScreen dashboard = new DashboardScreen(this, system);
        primaryStage.setTitle("Dashboard");
        primaryStage.setScene(new Scene(dashboard, 800, 600));
    }

    public TrackYourWork getSystem() {
        return system;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
