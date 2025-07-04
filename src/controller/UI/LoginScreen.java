package controller.UI;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.AppFlowLogic.TrackYourWork;

public class LoginScreen {

    public static void display(Stage stage) {
        TrackYourWork app = new TrackYourWork(); // or pass from main

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        Label idLabel = new Label("Enter Student ID:");
        TextField studentID = new TextField();
        Button loginBtn = new Button("Login");
        Label message = new Label();

        loginBtn.setOnAction(e -> {
            try {
                app.loginAs(studentID.getText());
                message.setText("Login successful!");
                DashboardScreen.display(stage, app);
            } catch (Exception ex) {
                message.setText("Login failed: " + ex.getMessage());
            }
        });

        layout.getChildren().addAll(idLabel, studentID, loginBtn, message);
        Scene scene = new Scene(layout, 300, 200);
        stage.setScene(scene);
    }
}
