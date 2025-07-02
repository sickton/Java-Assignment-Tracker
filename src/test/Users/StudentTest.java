package test.Users;

import model.Data.Assignment;
import model.Data.AssignmentStatus;
import model.Data.AssignmentType;
import model.Users.Student;
import org.junit.*;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class StudentTest {

    private Student s;

    @Before
    public void setUp()
    {
        s = new Student("Example", "Person", 20,"examper", "examper@university.abc", "Business");
    }

    @Test
    public void testGetters()
    {
        assertEquals("Example", s.getFirstName());
        assertEquals("Person", s.getLastName());
        assertEquals(20, s.getAge());
        assertEquals("examper", s.getStudentID());
        assertEquals("examper@university.abc", s.getEmail());
        assertEquals("Business", s.getMajor());
    }

    @Test
    public void testAddAssignments()
    {
        Assignment a = new Assignment("CSC", 200, AssignmentType.LAB, "Lab 1", "", AssignmentStatus.PENDING, LocalDate.parse("2025-07-08"));
        s.addAssignment(a);
        assertEquals(1, s.getAssignments().size());
        Assignment exp = s.getAssignments().getFirst();
        assertEquals(exp.getCourseCode(), a.getCourseCode());
        assertEquals(exp.getCourseNumber(), a.getCourseNumber());
        assertEquals(exp.getTitle(), a.getTitle());
        assertEquals(exp.getDescription(), a.getDescription());
    }

    @Test
    public void testDeleteCompletedAssignments()
    {
        Assignment a = new Assignment("CSC", 200, AssignmentType.LAB, "Lab 1", "", AssignmentStatus.PENDING, LocalDate.parse("2025-07-08"));
        Assignment a1 = new Assignment("CH", 101, AssignmentType.HOMEWORK, "Homework 1", "", AssignmentStatus.COMPLETED, LocalDate.parse("2025-07-20"));
        Assignment a2 = new Assignment("PY", 205, AssignmentType.HOMEWORK, "Homework 1", "Numericals from chapter 1", AssignmentStatus.COMPLETED, LocalDate.parse("2025-07-14"));
        Assignment a3 = new Assignment("EC", 205, AssignmentType.READING, "Report 1", "", AssignmentStatus.UNASSIGNED, LocalDate.parse("2025-07-08"));
        s.addAssignment(a);
        s.addAssignment(a1);
        s.addAssignment(a3);
        s.addAssignment(a2);
        s.deleteCompletedAssignments();
        assertEquals(2, s.getAssignments().size());
    }

    @Test
    public void testInvalidInputs()
    {
        assertThrows(IllegalArgumentException.class, () -> new Student(null, "last", 10, "sahsio", "sahsio@uni.adasd", "CSC"));
        assertThrows(IllegalArgumentException.class, () -> new Student("first", null, 10, "sahsio", "sahsio@uni.adasd", "CSC"));
        assertThrows(IllegalArgumentException.class, () -> new Student("first", "last", 0, "sahsio", "sahsio@uni.adasd", "CSC"));
        assertThrows(IllegalArgumentException.class, () -> new Student("", "last", 10, "sahsio", "sahsio@uni.adasd", "CSC"));
        assertThrows(IllegalArgumentException.class, () -> new Student("first", "", 10, "sahsio", "sahsio@uni.adasd", "CSC"));
        assertThrows(IllegalArgumentException.class, () -> new Student("first", "last", 10, "sahsio", "sahsiouni.adasd", "CSC"));
        assertThrows(IllegalArgumentException.class, () -> new Student("first1", "last", 10, "sahsio", "sahsio@uni.adasd", "CSC"));
    }

    @Test
    public void testToString()
    {
        assertEquals("Example Person (examper), Age: 20, Email: examper@university.abc, Major: Business", s.toString());
    }
}