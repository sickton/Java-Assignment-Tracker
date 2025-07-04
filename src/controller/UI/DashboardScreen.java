package controller.UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Data.*;
import view.AppFlowLogic.TrackYourWork;

import java.time.LocalDate;
import java.util.List;

public class DashboardScreen extends VBox{

    public static void display(Stage stage, TrackYourWork app) {
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));

        Label welcome = new Label("Welcome to TrackMyWork!");

        // --- Assignment List
        ListView<String> assignmentList = new ListView<>();
        updateList(assignmentList, app.getAssignments());

        // --- Filter Section
        ComboBox<AssignmentType> typeFilter = new ComboBox<>();
        typeFilter.getItems().addAll(AssignmentType.values());
        typeFilter.setPromptText("Filter by Type");

        DatePicker dueDatePicker = new DatePicker();
        dueDatePicker.setPromptText("Filter by Due Date");

        Button overdueBtn = new Button("Show Overdue");
        Button showAllBtn = new Button("Show All");

        typeFilter.setOnAction(e -> {
            if (typeFilter.getValue() != null) {
                updateList(assignmentList, app.getAssignmentByType(typeFilter.getValue()));
            }
        });

        dueDatePicker.setOnAction(e -> {
            if (dueDatePicker.getValue() != null) {
                updateList(assignmentList, app.getAssignmentsOnDueDate(dueDatePicker.getValue()));
            }
        });

        overdueBtn.setOnAction(e -> updateList(assignmentList, app.getOverdue()));
        showAllBtn.setOnAction(e -> updateList(assignmentList, app.getAssignments()));

        HBox filters = new HBox(10, typeFilter, dueDatePicker, overdueBtn, showAllBtn);

        // --- Add Assignment Section
        TextField courseCode = new TextField(); courseCode.setPromptText("Course Code");
        TextField courseNum = new TextField(); courseNum.setPromptText("Course Number");
        TextField title = new TextField(); title.setPromptText("Title");
        TextField desc = new TextField(); desc.setPromptText("Description");

        ComboBox<AssignmentType> type = new ComboBox<>(FXCollections.observableArrayList(AssignmentType.values()));
        type.setPromptText("Type");

        ComboBox<AssignmentStatus> status = new ComboBox<>(FXCollections.observableArrayList(AssignmentStatus.values()));
        status.setPromptText("Status");

        DatePicker dueDate = new DatePicker();

        Button addBtn = new Button("Add Assignment");
        Label message = new Label();

        addBtn.setOnAction(e -> {
            try {
                Assignment a = new Assignment(
                        courseCode.getText(),
                        Integer.parseInt(courseNum.getText()),
                        type.getValue(),
                        title.getText(),
                        desc.getText(),
                        status.getValue(),
                        dueDate.getValue()
                );
                app.addAssignment(a);
                message.setText("Assignment added!");
                updateList(assignmentList, app.getAssignments());
            } catch (Exception ex) {
                message.setText("Error: " + ex.getMessage());
            }
        });

        GridPane addForm = new GridPane();
        addForm.setHgap(10);
        addForm.setVgap(10);
        addForm.addRow(0, courseCode, courseNum);
        addForm.addRow(1, title, desc);
        addForm.addRow(2, type, status);
        addForm.addRow(3, dueDate, addBtn);
        addForm.add(message, 0, 4, 2, 1);

        // --- Save Button
        Button saveBtn = new Button("Save All");
        saveBtn.setOnAction(e -> message.setText(app.saveAssignments()));

        layout.getChildren().addAll(welcome, filters, assignmentList, new Label("Add New Assignment:"), addForm, saveBtn);
        Scene scene = new Scene(layout, 700, 600);
        stage.setScene(scene);
    }

    private static void updateList(ListView<String> listView, List<Assignment> assignments) {
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Assignment a : assignments) {
            items.add(a.toString());
        }
        listView.setItems(items);
    }
}
