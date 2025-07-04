package controller.UI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import controller.UI.LoginScreen;
import controller.UI.SignUpScreen;
import controller.UI.DashboardScreen;
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
        StackPane root = new WelcomeScreen(this);
        primaryStage.setTitle("TrackMyWork - Welcome");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public void showLoginScreen() {
        StackPane login = new LoginScreen(this);
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(login, 600, 400));
    }

    public void showSignupScreen() {
        StackPane signup = new SignUpScreen(this);
        primaryStage.setTitle("Sign Up");
        primaryStage.setScene(new Scene(signup, 600, 400));
    }

    public void showDashboard() {
        StackPane dashboard = new DashboardScreen(this, system);
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
