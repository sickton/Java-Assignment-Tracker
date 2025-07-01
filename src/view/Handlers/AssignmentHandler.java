package view.Handlers;

import model.Data.Assignment;
import model.Data.AssignmentStatus;
import model.Data.AssignmentType;
import model.Users.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
                if (a.getStatus() == AssignmentStatus.OVERDUE)
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
            LocalDate current = LocalDate.now();
            for (Assignment a : this.student.getAssignments()) {
                a.setPriorityOnDate(current);
                a.checkOverDue(current);
            }
        }
    }

    /**
     * Method to check if the assignment exists in the user's list
     * and edit it as required
     * @param a assignment
     * @param courseCode new course code
     * @param courseNum new course number
     * @param type new type
     * @param title new title
     * @param description new description
     * @param status new status
     * @param date new date
     * @throws IllegalArgumentException if the list doesn't contain the assignment
     */
    public void editAssignment(Assignment a, String courseCode, int courseNum,
                                   AssignmentType type,
                                   String title, String description,
                                   AssignmentStatus status, LocalDate date)
    {
        if(!this.student.getAssignments().contains(a))
            throw new IllegalArgumentException("Invalid Assignment selected!");
        a.editDescription(description);
        a.editStatus(status);
        a.editType(type);
        a.editTitle(title);
        a.editCourseCode(courseCode);
        a.setNewDueDate(date);
        a.editCourseNumber(courseNum);
    }
}
