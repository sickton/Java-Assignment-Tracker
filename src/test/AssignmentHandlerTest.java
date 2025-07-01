package test;

import model.Data.AssignmentStatus;
import model.Data.AssignmentType;
import view.Handlers.AssignmentHandler;
import model.Users.Student;
import model.Data.Assignment;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class AssignmentHandlerTest {

    private AssignmentHandler handler;

    @Before
    public void setup() {
        handler = new AssignmentHandler("Jane", "Doe", 10, "JD1", "JD1@XYZ.com", "Computer Science");
        Assignment a = new Assignment("CSC", 101, AssignmentType.CODE, "Variables coding", "Code 3 classes", AssignmentStatus.ASSIGNED, LocalDate.now().minusDays(5));
        Assignment a1 = new Assignment("CH", 101, AssignmentType.HOMEWORK, "Homework 1", "On WebAssign", AssignmentStatus.UNASSIGNED, LocalDate.parse("2025-07-20"));
        Assignment a2 = new Assignment("PHY", 201, AssignmentType.LAB, "Laws of Motion Lab", "Check on lab website", AssignmentStatus.PENDING, LocalDate.parse("2025-07-11"));
        Assignment a3 = new Assignment("EC", 205, AssignmentType.QUIZ, "Economic methods", "25 questions quiz on moodle", AssignmentStatus.ASSIGNED, LocalDate.parse("2025-07-11"));
        Assignment a4 = new Assignment("CSC", 246, AssignmentType.HOMEWORK, "Operating Systems Intro", "7 pages reading", AssignmentStatus.COMPLETED, LocalDate.parse("2025-07-01"));
        handler.addAssignment(a);
        handler.addAssignment(a1);
        handler.addAssignment(a2);
        handler.addAssignment(a3);
        handler.addAssignment(a4);
    }

    @Test
    public void testAddAssignment() {
        assertEquals(5, handler.getAssignments().size());
    }

    @Test
    public void testOverdueAssignments()
    {
        assertEquals(1, handler.getOverdueAssignments().size());
    }

    @Test
    public void testAssignmentsByType()
    {
        assertEquals(2, handler.getAssignmentsByType(AssignmentType.HOMEWORK).size());
        assertEquals(1, handler.getAssignmentsByType(AssignmentType.LAB).size());
        assertEquals(1, handler.getAssignmentsByType(AssignmentType.QUIZ).size());
        assertEquals(1, handler.getAssignmentsByType(AssignmentType.CODE).size());
        assertEquals(0, handler.getAssignmentsByType(AssignmentType.ESSAY).size());
    }

    @Test
    public void testAssignmentOnDueDate()
    {
        assertEquals(2, handler.getAssignmentsOnDueDate(LocalDate.parse("2025-07-11")).size());
        assertEquals("PHY", handler.getAssignmentsOnDueDate(LocalDate.parse("2025-07-11")).getFirst().getCourseCode());
        assertEquals("EC", handler.getAssignmentsOnDueDate(LocalDate.parse("2025-07-11")).get(1).getCourseCode());
    }

    @Test
    public void testEditAssignment()
    {
         Assignment a = new Assignment("CH", 101, AssignmentType.HOMEWORK, "Homework 1", "On WebAssign", AssignmentStatus.UNASSIGNED, LocalDate.parse("2025-07-20"));
         handler.editAssignment(a, a.getCourseCode(), a.getCourseNumber(), a.getAssignmentType(), a.getTitle(), a.getDescription(), a.getStatus(), LocalDate.parse("2025-07-10"));
         handler.editAssignment(a, a.getCourseCode(), a.getCourseNumber(), a.getAssignmentType(), a.getTitle(), a.getDescription(), AssignmentStatus.COMPLETED, a.getDueDate());
         handler.editAssignment(a, a.getCourseCode(), a.getCourseNumber(), a.getAssignmentType(), a.getTitle(), "On Pearson's", a.getStatus(), a.getDueDate());
         handler.editAssignment(a, a.getCourseCode(), a.getCourseNumber(), a.getAssignmentType(), "Homework 0", a.getDescription(), a.getStatus(), a.getDueDate());
         handler.editAssignment(a, a.getCourseCode(), a.getCourseNumber(), AssignmentType.PROJECT, a.getTitle(), a.getDescription(), a.getStatus(), a.getDueDate());
         Assignment exp = handler.getAssignmentsByType(AssignmentType.PROJECT).getFirst();
         assertEquals("CH", exp.getCourseCode());
         assertEquals(101, exp.getCourseNumber());
         assertEquals(AssignmentType.PROJECT, exp.getAssignmentType());
         assertEquals("Homework 0", exp.getTitle());
         assertEquals("On Pearson's", exp.getDescription());
         assertEquals(AssignmentStatus.COMPLETED, exp.getStatus());
         assertEquals("2025-07-10", exp.getDueDate().toString());
    }
}
