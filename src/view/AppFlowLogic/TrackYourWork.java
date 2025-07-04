package view.AppFlowLogic;

import model.Data.AssignmentStatus;
import model.Utils.ReaderClass;
import model.Utils.WriterClass;
import model.Data.Assignment;
import model.Data.AssignmentType;
import model.Exceptions.SystemException;
import model.Users.Student;
import view.Handlers.AssignmentHandler;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class TrackYourWork {

    private Map<String, AssignmentHandler> studentWork;

    private AssignmentHandler currentHandler;

    public TrackYourWork() {
        studentWork = ReaderClass.loadMap();
        currentHandler = null;
    }

    public String signupStudent(String first, String last, int age, String studentID, String mail, String major) {
        Student s = new Student(first, last, age, studentID, mail, major);
        studentWork.put(studentID, new AssignmentHandler(first, last, age, studentID, mail, major));
        return WriterClass.saveStudentDetails(s);
    }

    public void loginAs(String studentID) {
        if (studentWork.get(studentID) == null)
            throw new SystemException();
        else {
            currentHandler = studentWork.get(studentID);
        }
    }

    public void addAssignment(Assignment a) {
        if (currentHandler != null)
            currentHandler.addAssignment(a);
        else
            throw new SystemException();
    }

    public String saveAssignments() {
        return WriterClass.saveAssignmentsOfHandle(this.currentHandler);
    }

    public List<Assignment> getAssignmentByType(AssignmentType type)
    {
        if(currentHandler == null)
            throw new SystemException();
        return currentHandler.getAssignmentsByType(type);
    }

    public List<Assignment> getOverdue()
    {
        if(currentHandler == null)
            throw new SystemException();
        return currentHandler.getOverdueAssignments();
    }

    public List<Assignment> getAssignmentsOnDueDate(LocalDate date)
    {
        if(currentHandler == null)
            throw new SystemException();
        return currentHandler.getAssignmentsOnDueDate(date);
    }

    public List<Assignment> getAssignments() {
        if(currentHandler == null)
            throw new SystemException();
        return currentHandler.getAssignments();
    }

    public void editAssignment(UUID id, String courseCode, int courseNum,
                               AssignmentType type,
                               String title, String description,
                               AssignmentStatus status, LocalDate date)
    {
        currentHandler.editAssignment(id,
                courseCode, courseNum, type, title, description, status, date);
    }

    public void deleteAssignment(UUID assignmentID) {
        currentHandler.deleteAssignment(assignmentID);
    }

    public List<Assignment> removeCompletedAssignments() {
        return currentHandler.removeCompletedAssignments();
    }
}