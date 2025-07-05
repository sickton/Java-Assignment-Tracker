package view.Handlers;

import model.Data.Assignment;
import model.Data.AssignmentStatus;
import model.Data.AssignmentType;
import model.Users.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AssignmentHandler {

    /** Field to store a single user student in the tracker */
    private final Student student;

    /**
     * Constructor to initialize the user with student details
     * @param first first name
     * @param last last name
     * @param age age
     * @param studentID student ID
     * @param mail mail
     * @param major major
     */
    public AssignmentHandler(String first, String last, int age, String studentID, String mail, String major)
    {
        this.student = new Student(first, last, age, studentID, mail, major);
    }

    /**
     * Method to add an assignment to the student's assignment tracker
     * @param a assignment to be added
     */
    public void addAssignment(Assignment a)
    {
        updateAssignmentStatuses();
        boolean added = this.student.addAssignment(a);
        if(!added)
            throw new IllegalArgumentException("Invalid Assignment added!");
    }

    /**
     * Method to get assignment lists from the user
     * @return assignment list
     */
    public List<Assignment> getAssignments()
    {
        return this.student.getAssignments();
    }

    /**
     * Method to return and display all overdue assignments of the user
     * @return list of overdue assignments
     */
    public List<Assignment> getOverdueAssignments()
    {
        updateAssignmentStatuses();
        List<Assignment> userList = this.student.getAssignments();
        if(userList != null) {
            List<Assignment> overdue = new ArrayList<Assignment>();
            for (Assignment a : userList) {
                if (a.getStatus() != AssignmentStatus.COMPLETED && a.getStatus() == AssignmentStatus.OVERDUE)
                    overdue.add(a);
            }
            return overdue;
        }
        return new ArrayList<Assignment>();
    }

    /**
     * Method to return assignments by type
     * @return assignment list by the given type
     */
    public List<Assignment> getAssignmentsByType(AssignmentType type)
    {
        updateAssignmentStatuses();
        List<Assignment> userList = this.student.getAssignments();
        if(userList != null) {
            List<Assignment> listType = new ArrayList<Assignment>();
            for (Assignment a : userList) {
                if (a.getAssignmentType() == type)
                    listType.add(a);
            }
            return listType;
        }
        return  new ArrayList<Assignment>();
    }

    /**
     * Method to return assignments by dueDate
     * @return assignments due on the given date
     */
    public List<Assignment> getAssignmentsOnDueDate(LocalDate date)
    {
        updateAssignmentStatuses();
        List<Assignment> userList = this.student.getAssignments();
        if(userList != null) {
            List<Assignment> listDate = new ArrayList<Assignment>();
            for (Assignment a : userList) {
                if (a.getDueDate().equals(date))
                    listDate.add(a);
            }
            return listDate;
        }
        return new ArrayList<Assignment>();
    }

    /**
     * Method to set priority for all the student assignments
     * on the current date
     */
    public void updateAssignmentStatuses()
    {
        if(this.student.getAssignments() != null) {
            for (Assignment a : this.student.getAssignments()) {
                a.setPriorityOnDate(a.getDueDate());
                a.checkOverDue(a.getDueDate());
            }
        }
    }

    /**
     * Method to check if the assignment exists in the user's list
     * and edit it as required
     * @param id Assignment ID
     * @param courseCode new course code
     * @param courseNum new course number
     * @param type new type
     * @param title new title
     * @param description new description
     * @param status new status
     * @param date new date
     * @throws IllegalArgumentException if the list doesn't contain the assignment
     */
    public void editAssignment(UUID id,
                               String courseCode, int courseNum,
                               AssignmentType type, String title,
                               String description, AssignmentStatus status,
                               LocalDate date) {
        Assignment a = findAssignmentByID(id);
        if (a == null)
            throw new IllegalArgumentException("Invalid Assignment Selected !");

        a.editCourseCode(courseCode);
        a.editCourseNumber(courseNum);
        a.editType(type);
        a.editTitle(title);
        a.editDescription(description);
        a.editStatus(status);
        a.setNewDueDate(date);
    }


    /**
     * Private helper method to locate the assignment in the user assignment list
     * @param id ID of the assignment
     * @return the assignment object if found else null
     */
    public Assignment findAssignmentByID(UUID id) {
        for (Assignment a : this.student.getAssignments()) {
            if (a.getAssignmentID().equals(id)) return a;
        }
        return null;
    }

    /**
     * Method to return the student in this handler
     * @return student object
     */
    public Student getStudent()
    {
        return this.student;
    }

    public void deleteAssignment(UUID assignmentID) {
        this.student.deleteAssignment(assignmentID);
    }

    public List<Assignment> removeCompletedAssignments() {
        this.student.getAssignments().removeIf(a -> a.getStatus() == AssignmentStatus.COMPLETED);
        return this.student.getAssignments();
    }
}
