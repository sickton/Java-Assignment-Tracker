package model.Users;

import model.Data.Assignment;
import model.Data.AssignmentStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Class to define a user - student with related fields
 * @author Srivathsa
 */
public class Student {
    /** Constants required for calculations and error checks */
    private static final int EIGHT = 8;
    private static final int ZERO = 0;

    /** Field to store the first name of the student */
    private String firstName;

    /** Field to store the last name of the student */
    private String lastName;

    /** Field to store the ae of the student */
    private final int age;

    /** Field to store the student ID of the person */
    private final String studentID;

    /** Field to store the email of the student */
    private String email;

    /** Field to store the major of student */
    private String major;

    /** Field to store the assignments list for the student */
    private List<Assignment> assignments;

    /**
     * Constructor to create a student
     * @param first first name of the student
     * @param last last name of the student
     * @param age age of the student
     * @param studentID student ID of the student
     * @param mail mail ID of the student
     * @param major Major of the student
     */
    public Student(String first, String last, int age, String studentID, String mail, String major)
    {
        setFirst(first);
        setLast(last);
        setEmail(mail);
        if(studentID.length() > EIGHT)
            throw new IllegalArgumentException("Invalid student id!");
        this.studentID = studentID;
        if(age <= ZERO)
            throw new IllegalArgumentException("Invalid Age!");
        this.age = age;
        setMajor(major);
        this.assignments = new ArrayList<Assignment>();
    }

    /**
     * Method to set the name of the student
     * @param name first name of student
     */
    public void setFirst(String name)
    {
        if(name == null || name.isEmpty())
            throw new IllegalArgumentException("Invalid first name !");
        for(int i = 0; i < name.length(); i++)
        {
            if(!Character.isLetter(name.charAt(i)))
                throw new IllegalArgumentException("Invalid First Name !");
        }
        this.firstName = name;
    }

    /**
     * Method to set the last name of the student
     * @param name last name of student
     */
    public void setLast(String name)
    {
        if(name == null || name.isEmpty())
            throw new IllegalArgumentException("Invalid last name !");
        for(int i = 0; i < name.length(); i++)
        {
            if(!Character.isLetter(name.charAt(i)))
                throw new IllegalArgumentException("Invalid Last Name !");
        }
        this.lastName = name;
    }

    /**
     * Method to set the Major of the student
     * @param major major of student
     */
    public void setMajor(String major)
    {
        if(major == null || major.isEmpty())
            throw new IllegalArgumentException("Invalid major !");
        for(int i = 0; i < major.length(); i++)
        {
            if(!Character.isLetter(major.charAt(i)) && !Character.isSpaceChar(major.charAt(i)))
                throw new IllegalArgumentException("Invalid Major !");
        }
        this.major = major;
    }

    /**
     * Method to set the email ID of the student
     * @param mail E-mail ID of the student
     */
    public void setEmail(String mail)
    {
        if(mail == null || mail.isEmpty())
            throw new IllegalArgumentException("Invalid E-mail address!");
        boolean found = false;
        boolean foundDot = false;
        for(int i = 0; i < mail.length(); i++)
        {
            if(mail.charAt(i) == '@')
            {
                found = true;
            }
            if(mail.charAt(i) == '.')
            {
                foundDot = true;
            }
        }
        if(!found || !foundDot)
            throw new IllegalArgumentException("Invalid E-mail address!");
        this.email = mail;
    }

    /**
     * Method to return the first name of the student
     * @return first name
     */
    public String getFirstName()
    {
        return this.firstName;
    }

    /**
     * Method to return the last name of the student
     * @return last name
     */
    public String getLastName()
    {
        return this.lastName;
    }

    /**
     * Method to return the student ID of the student
     * @return student ID
     */
    public String getStudentID()
    {
        return this.studentID;
    }

    /**
     * Method to return the email ID of student
     * @return E-mail ID
     */
    public String getEmail()
    {
        return this.email;
    }

    /**
     * Method to return the major of the student
     * @return major
     */
    public String getMajor()
    {
        return this.major;
    }

    /**
     * Method to return the age of the student
     * @return age
     */
    public int getAge()
    {
        return this.age;
    }

    /**
     * Method to return the string representation of the student
     * @return string of student details
     */
    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + studentID + "), Age: " + age +
                ", Email: " + email + ", Major: " + major;
    }

    /**
     * Method to check if two objects are equal
     * @param a another object
     * @return true if both are same else false
     */
    @Override
    public boolean equals(Object a)
    {
        if(this.getClass() != a.getClass())
            return false;
        Student student = (Student) a;
        return (this.getFirstName().equals(student.getFirstName()) &&
                this.getLastName().equals(student.getLastName()) &&
                this.getStudentID().equals(student.getStudentID()));
    }

    /**
     * Method to add an assignment to the users assignment list
     * @param a assignment to be added
     * @return true if assignment added successfully else false
     */
    public boolean addAssignment(Assignment a)
    {
        if(a == null)
            return false;
        else
        {
            this.assignments.addLast(a);
            return true;
        }
    }

    /**
     * Method to return the assignment list
     * @return list of assignments
     */
    public List<Assignment> getAssignments()
    {
        return this.assignments;
    }

    /**
     * Method to automatically deletes completed assignments when user decides to clear out
     */
    public void deleteCompletedAssignments()
    {
        this.assignments.removeIf(a -> a.getStatus() == AssignmentStatus.COMPLETED);
    }

    public void deleteAssignment(UUID assignmentID) {
        for(Assignment a : this.assignments)
        {
            if(a.getAssignmentID() == assignmentID)
            {
                assignments.remove(a);
                break;
            }
        }
    }
}