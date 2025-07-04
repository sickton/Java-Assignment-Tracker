package controller.UI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WelcomeScreen extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("TrackMyWork - Welcome");

        Button signUpBtn = new Button("Sign Up");
        Button loginBtn = new Button("Login");

        signUpBtn.setOnAction(e -> SignUpScreen.display(primaryStage));
        loginBtn.setOnAction(e -> LoginScreen.display(primaryStage));

        VBox layout = new VBox(20);
        layout.getChildren().addAll(signUpBtn, loginBtn);
        layout.setStyle("-fx-padding: 40; -fx-alignment: center;");

        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
