package controller.UI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Data.*;
import view.Handlers.AssignmentHandler;

import java.time.LocalDate;

public class AssignmentTrackerUI extends Application {

    private AssignmentHandler handler;
    private ListView<String> assignmentList;

    @Override
    public void start(Stage primaryStage) {
        // Initialize with a sample student
        handler = new AssignmentHandler("John", "Doe", 20, "jd123", "john.doe@example.com", "Computer Science");

        // Main layout
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        // Top Title
        Label titleLabel = new Label("Assignment Tracker");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        root.setTop(titleLabel);
        BorderPane.setMargin(titleLabel, new Insets(10));
        BorderPane.setAlignment(titleLabel, javafx.geometry.Pos.CENTER);

        // Center: Assignment List
        assignmentList = new ListView<>();
        refreshAssignmentList();
        root.setCenter(assignmentList);

        // Bottom: Form to Add Assignment
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(10));

        TextField courseCodeField = new TextField();
        TextField courseNumberField = new TextField();
        TextField titleField = new TextField();
        TextField descriptionField = new TextField();

        DatePicker dueDatePicker = new DatePicker();
        ComboBox<AssignmentType> typeBox = new ComboBox<>();
        typeBox.getItems().addAll(AssignmentType.values());

        Button addButton = new Button("Add Assignment");

        form.addRow(0, new Label("Course Code:"), courseCodeField);
        form.addRow(1, new Label("Course Number:"), courseNumberField);
        form.addRow(2, new Label("Title:"), titleField);
        form.addRow(3, new Label("Description:"), descriptionField);
        form.addRow(4, new Label("Due Date:"), dueDatePicker);
        form.addRow(5, new Label("Type:"), typeBox);
        form.add(addButton, 1, 6);

        addButton.setOnAction(e -> {
            try {
                String courseCode = courseCodeField.getText().toUpperCase().trim();
                int courseNumber = Integer.parseInt(courseNumberField.getText().trim());
                String title = titleField.getText().trim();
                String desc = descriptionField.getText().trim();
                LocalDate dueDate = dueDatePicker.getValue();
                AssignmentType type = typeBox.getValue();

                Assignment newAssignment = new Assignment(courseCode, courseNumber, type, title, desc, AssignmentStatus.ASSIGNED, dueDate);
                handler.addAssignment(newAssignment);
                refreshAssignmentList();

                courseCodeField.clear();
                courseNumberField.clear();
                titleField.clear();
                descriptionField.clear();
                dueDatePicker.setValue(null);
                typeBox.setValue(null);

            } catch (Exception ex) {
                showAlert("Error", "Failed to add assignment:\n" + ex.getMessage());
            }
        });

        root.setBottom(form);

        // Scene Setup
        Scene scene = new Scene(root, 600, 500);
        primaryStage.setTitle("TrackMyWork - Assignment Tracker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void refreshAssignmentList() {
        assignmentList.getItems().clear();
        for (Assignment a : handler.getAssignments()) {
            assignmentList.getItems().add(a.toString());
        }
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
