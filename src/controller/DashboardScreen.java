package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.Data.*;
import view.AppFlowLogic.TrackYourWork;

import java.time.LocalDate;
import java.util.List;

public class DashboardScreen extends VBox {

    public DashboardScreen(AssignmentTrackerUI app, TrackYourWork logic) {
        this.setPadding(new Insets(20));
        this.setSpacing(15);

        Label welcome = new Label("Welcome to TrackMyWork Dashboard!");

        ListView<String> assignmentList = new ListView<>();
        updateList(assignmentList, logic.getAssignments());

        ComboBox<AssignmentType> typeFilter = new ComboBox<>();
        typeFilter.getItems().addAll(AssignmentType.values());
        typeFilter.setPromptText("Filter by Type");

        DatePicker dueDatePicker = new DatePicker();
        dueDatePicker.setPromptText("Filter by Due Date");

        Button overdueBtn = new Button("Show Overdue");
        Button showAllBtn = new Button("Show All");

        typeFilter.setOnAction(e -> updateList(assignmentList, logic.getAssignmentByType(typeFilter.getValue())));
        dueDatePicker.setOnAction(e -> updateList(assignmentList, logic.getAssignmentsOnDueDate(dueDatePicker.getValue())));
        overdueBtn.setOnAction(e -> updateList(assignmentList, logic.getOverdue()));
        showAllBtn.setOnAction(e -> updateList(assignmentList, logic.getAssignments()));

        HBox filters = new HBox(10, typeFilter, dueDatePicker, overdueBtn, showAllBtn);

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
                logic.addAssignment(a);
                message.setText("Assignment added!");
                updateList(assignmentList, logic.getAssignments());
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

        Button saveBtn = new Button("Save All");
        saveBtn.setOnAction(e -> message.setText(logic.saveAssignments()));

        getChildren().addAll(welcome, filters, assignmentList, new Label("Add New Assignment:"), addForm, saveBtn);
    }

    private void updateList(ListView<String> listView, List<Assignment> assignments) {
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Assignment a : assignments) {
            items.add(a.toString());
        }
        listView.setItems(items);
    }
}
