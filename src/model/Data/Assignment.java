package model.Data;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

/**
 * Class to create a type of assignment and store it in the
 * system.
 * @author Srivathsa
 */
public class Assignment {

    /** All the constants required for calculations and checking */
    private static final int THREE = 3;
    private static final int ROUGH_MONTH = 30;
    private static final int ROUGH_YEAR = 365;
    private static final int FOURTEEN = 14;
    private static final int TEN = 10;
    private static final int SEVEN = 7;

    /** Field to store the 3 character course code: Ex - BUS, CSC, ECE*/
    private String courseCode;

    /** Field to store the course number. Ex - 116, 201, etc. */
    private int courseNumber;

    /** Field to store the type of assignment - Essay, homework, etc. */
    private AssignmentType assignmentType;

    /** Field to store the title of the assignment. Ex - Homework 1, Project Part 1, etc. */
    private String title;

    /** Field to store any additional details like office hours etc. */
    private String description;

    /** Field to store the priority of the assignment automated based on due date */
    private Priority priority;

    /** Field to store the Status of the assignment that can be updated by the user */
    private AssignmentStatus status;

    /** Field to store the due date with the local date of creation of the assignment */
    private LocalDate dueDate;

    /** Field to store the unique ID for this assignment */
    private final UUID assignmentID = UUID.randomUUID();

    /**
     * Constructor that creates a new assignment
     * @param courseCode course code of the subject
     * @param courseNum course number of the subject
     * @param type type of assignment
     * @param title title for the assignment
     * @param description additional details about the assignment
     * @param status status for the assignment
     * @param date due date for the assignment
     */
    public Assignment(String courseCode, int courseNum, AssignmentType type, String title, String description,
                      AssignmentStatus status, LocalDate date)
    {
        setCourseCode(courseCode);
        if(courseNum == 0)
            throw new IllegalArgumentException("Invalid Course number");
        this.courseNumber = courseNum;
        this.assignmentType = type;
        this.status = status;
        this.dueDate = date;
        if(title == null || title.isEmpty())
            throw new IllegalArgumentException("Invalid Assignment Title !");
        this.title = title;
        if(description == null)
            throw new IllegalArgumentException("Invalid Assignment Description !");
        this.description = description;
        setPriorityOnDate(this.dueDate);
    }

    /**
     * Method to set the course code and check for an invalid code
     * @param courseCode course code to be set.
     */
    public void setCourseCode(String courseCode)
    {
        if(courseCode.length() > THREE || courseCode.isEmpty())
            throw new IllegalArgumentException("Invalid Course Code !");
        for(int i = 0; i < courseCode.length(); i++)
        {
            if(!Character.isLetter(courseCode.charAt(i)))
                throw new IllegalArgumentException("Invalid Course Code !");
        }
        this.courseCode = courseCode;
    }

    /**
     * Method to set the priority for the assignment based on the due date.
     * @param date due date of the assignment
     */
    public void setPriorityOnDate(LocalDate date)
    {
        LocalDate current = LocalDate.now();
        Period period = current.until(date);
        int day = period.getDays();
        int month = period.getMonths();
        int year = period.getYears();

        int totalDays = day + (month * ROUGH_MONTH) + (year * ROUGH_YEAR);

        if (totalDays > FOURTEEN)
            this.priority = Priority.LOW;
        else if (totalDays >= SEVEN)
            this.priority = Priority.MEDIUM;
        else
            this.priority = Priority.HIGH;
    }

    /**
     * Method to check if the assignment is overdue or not
     * @param date due date of the assignment.
     */
    public void checkOverDue(LocalDate date)
    {
        LocalDate current = LocalDate.now();
        if(current.isAfter(date) && this.status != AssignmentStatus.COMPLETED)
            this.status = AssignmentStatus.OVERDUE;
    }

    /**
     * Overridden method to check if the user is trying to input the same assignment twice;
     * @param a another assignment object
     * @return true if both the object are same else false
     */
    @Override
    public boolean equals(Object a)
    {
        if(a.getClass() != this.getClass())
            return false;
        Assignment assignment = (Assignment) a;
        return (this.assignmentID.equals(assignment.getAssignmentID()));
    }

    /**
     * Method to return the hash code of assignment ID to identify
     * a unique assignment
     * @return assignment ID hash
     */
    @Override
    public int hashCode()
    {
        return assignmentID.hashCode();
    }

    /**
     * Method to return the title for assignment
     * @return title
     */
    public String getTitle()
    {
        return this.title;
    }

    /**
     * Method to return the course code of the subject
     * @return subject code
     */
    public String getCourseCode()
    {
        return this.courseCode;
    }

    /**
     * Method to return the course number
     * @return course number
     */
    public int getCourseNumber()
    {
        return this.courseNumber;
    }

    /**
     * Method to return the due date for the assignment
     * @return due date
     */
    public LocalDate getDueDate()
    {
        return this.dueDate;
    }

    /**
     * Method to return the description for the assignment
     * @return description
     */
    public String getDescription()
    {
        return this.description;
    }

    /**
     * Method to return the status of the assignment
     * @return status
     */
    public AssignmentStatus getStatus()
    {
        return this.status;
    }

    /**
     * Method to return the priority of the assignment
     * @return priority
     */
    public Priority getPriority()
    {
        return this.priority;
    }

    /**
     * Method to return the type of the assignment
     * @return assignment type
     */
    public AssignmentType getAssignmentType()
    {
        return this.assignmentType;
    }

    /**
     * Method to return the unique assignment ID for this object
     * @return assignment ID
     */
    public UUID getAssignmentID()
    {
        return this.assignmentID;
    }

    /**
     * Method to enable user to edit the due date
     * @param dueDate new due date
     */
    public void setNewDueDate(LocalDate dueDate)
    {
        this.dueDate = dueDate;
    }

    /**
     * Method to enable user to edit the title of the assignment
     * @param newTitle new title
     */
    public void editTitle(String newTitle)
    {
        if(newTitle == null || newTitle.isEmpty())
            throw new IllegalArgumentException("Invalid Assignment Title !");
        this.title = newTitle;
    }

    /**
     * Method to enable user to edit the status of the assignment
     * @param newStatus user edited status
     */
    public void editStatus(AssignmentStatus newStatus)
    {
        if(newStatus == null)
            throw new IllegalArgumentException("Invalid status assigned!");
        this.status = newStatus;
    }

    /**
     * Method to enable user to edit the type of the assignment
     * @param newType new type of assignment
     */
    public void editType(AssignmentType newType)
    {
        this.assignmentType = newType;
    }

    /**
     * Method to enable user to edit the description
     * @param newDescription new description
     */
    public void editDescription(String newDescription)
    {
        if(newDescription == null)
            throw new IllegalArgumentException("Invalid Assignment Description !");
        this.description = newDescription;
    }

    /**
     * Method to enable user to edit the course code
     * @param newCourseCode new course code
     */
    public void editCourseCode(String newCourseCode)
    {
        if(newCourseCode == null)
            throw new IllegalArgumentException("Invalid Course Code !");
        setCourseCode(newCourseCode);
    }

    /**
     * Method to enable the user to edit the course number
     * @param num new course number
     */
    public void editCourseNumber(int num)
    {
        if(num == 0)
            throw new IllegalArgumentException("Invalid course number !");
        this.courseNumber = num;
    }

    /**
     * Method to return a string representation of the assignment details
     * @return assignment details
     */
    @Override
    public String toString()
    {
        return getCourseCode() +
                " " +
                getCourseNumber() +
                "\n" +
                getTitle() +
                "\n" +
                getDueDate();
    }
}