package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import model.Data.*;
import view.AppFlowLogic.TrackYourWork;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class DashboardScreen extends VBox {

    private Assignment selectedAssignment = null;

    private final TextField courseCode = new TextField();
    private final TextField courseNum = new TextField();
    private final TextField title = new TextField();
    private final TextField desc = new TextField();
    private final ComboBox<AssignmentType> type = new ComboBox<>();
    private final ComboBox<AssignmentStatus> status = new ComboBox<>();
    private final DatePicker dueDate = new DatePicker();
    private final Label message = new Label();
    private final Button addBtn = new Button("Add Assignment");
    private final Button deleteBtn = new Button("Delete Assignment");

    public DashboardScreen(AssignmentTrackerUI app, TrackYourWork logic) {
        this.setPadding(new Insets(20));
        this.setSpacing(15);

        // Top bar with logout
        Button logoutBtn = new Button("Logout");
        logoutBtn.setOnAction(e -> {logic.saveAssignments(); app.showWelcomeScreen();});
        Label welcome = new Label("Track Your Work efficiently!");
        HBox topBar = new HBox(10, logoutBtn, welcome);

        ListView<String> assignmentList = new ListView<>();
        updateList(assignmentList, logic.getAssignments());

        ComboBox<AssignmentType> typeFilter = new ComboBox<>();
        typeFilter.getItems().addAll(AssignmentType.values());
        typeFilter.setPromptText("Filter by Type");

        DatePicker dueDatePicker = new DatePicker();
        dueDatePicker.setPromptText("Filter by Due Date");

        Button overdueBtn = new Button("Show Overdue");
        Button showAllBtn = new Button("Show All");
        Button removeCompletedAssignmentsBtn = new Button("Remove Completed Assignments");

        typeFilter.setOnAction(e -> updateList(assignmentList, logic.getAssignmentByType(typeFilter.getValue())));
        dueDatePicker.setOnAction(e -> updateList(assignmentList, logic.getAssignmentsOnDueDate(dueDatePicker.getValue())));
        overdueBtn.setOnAction(e -> updateList(assignmentList, logic.getOverdue()));
        showAllBtn.setOnAction(e -> updateList(assignmentList, logic.getAssignments()));
        removeCompletedAssignmentsBtn.setOnAction(e -> updateList(assignmentList, logic.removeCompletedAssignments()));

        HBox filters = new HBox(10, typeFilter, dueDatePicker, overdueBtn, showAllBtn, removeCompletedAssignmentsBtn);

        courseCode.setPromptText("Course Code");
        courseNum.setPromptText("Course Number");
        title.setPromptText("Title");
        desc.setPromptText("Description");

        type.setItems(FXCollections.observableArrayList(AssignmentType.values()));
        type.setPromptText("Type");

        status.setItems(FXCollections.observableArrayList(AssignmentStatus.values()));
        status.setPromptText("Status");

        dueDate.setPromptText("Due Date");

        addBtn.setOnAction(e -> {
            try {
                if (selectedAssignment == null) {
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
                } else {
                    logic.editAssignment(
                            selectedAssignment.getAssignmentID(),
                            courseCode.getText(),
                            Integer.parseInt(courseNum.getText()),
                            type.getValue(),
                            title.getText(),
                            desc.getText(),
                            status.getValue(),
                            dueDate.getValue()
                    );
                    message.setText("Assignment updated!");
                    selectedAssignment = null;
                    addBtn.setText("Add Assignment");
                }
                updateList(assignmentList, logic.getAssignments());
                clearForm();
            } catch (Exception ex) {
                message.setText("Error: " + ex.getMessage());
            }
        });

        deleteBtn.setOnAction(e -> {
            if (selectedAssignment != null) {
                logic.deleteAssignment(selectedAssignment.getAssignmentID());
                message.setText("Assignment deleted!");
                selectedAssignment = null;
                addBtn.setText("Add Assignment");
                updateList(assignmentList, logic.getAssignments());
                clearForm();
            } else {
                message.setText("No assignment selected to delete.");
            }
        });

        assignmentList.setOnMouseClicked(event -> {
            String selectedText = assignmentList.getSelectionModel().getSelectedItem();
            if (selectedText != null) {
                for (Assignment a : logic.getAssignments()) {
                    if (a.toString().equals(selectedText)) {
                        selectedAssignment = a;
                        courseCode.setText(a.getCourseCode());
                        courseNum.setText(String.valueOf(a.getCourseNumber()));
                        title.setText(a.getTitle());
                        desc.setText(a.getDescription());
                        type.setValue(a.getAssignmentType());
                        status.setValue(a.getStatus());
                        dueDate.setValue(a.getDueDate());
                        addBtn.setText("Update Assignment");
                        break;
                    }
                }
            }
        });

        Button clearBtn = new Button("Clear Form");
        clearBtn.setOnAction(e -> {
            clearForm();
            selectedAssignment = null;
            addBtn.setText("Add Assignment");
        });

        GridPane addForm = new GridPane();
        addForm.setHgap(10);
        addForm.setVgap(10);
        addForm.addRow(0, courseCode, courseNum);
        addForm.addRow(1, title, desc);
        addForm.addRow(2, type, status);
        addForm.addRow(3, dueDate, addBtn);
        addForm.add(deleteBtn, 0, 4);
        addForm.add(clearBtn, 1, 4);
        addForm.add(message, 1, 5);

        Button saveBtn = new Button("Save All");
        saveBtn.setOnAction(e -> message.setText(logic.saveAssignments()));

        this.getChildren().addAll(topBar, filters, assignmentList, new Label("Add or Edit Assignment:"), addForm, saveBtn);
    }

    private void updateList(ListView<String> listView, List<Assignment> assignments) {
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Assignment a : assignments) {
            items.add(a.toString());
        }
        listView.setItems(items);
    }

    private void clearForm() {
        courseCode.clear();
        courseNum.clear();
        title.clear();
        desc.clear();
        type.setValue(null);
        status.setValue(null);
        dueDate.setValue(null);
        message.setText("");
    }
}
