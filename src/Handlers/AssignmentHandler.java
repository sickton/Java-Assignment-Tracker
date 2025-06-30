package Handlers;

import Data.Assignment;
import Data.AssignmentStatus;
import Users.Student;

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
        List<Assignment> userList = this.student.getAssignments();
        List<Assignment> overdue = new ArrayList<Assignment>();
        for(Assignment a : userList)
        {
            if(a.getStatus() == AssignmentStatus.OVERDUE)
                overdue.add(a);
        }
        return overdue;
    }
}
