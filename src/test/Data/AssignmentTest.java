package test.Data;

import model.Data.Assignment;
import model.Data.AssignmentStatus;
import model.Data.AssignmentType;
import model.Data.Priority;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class AssignmentTest {

    private Assignment a;

    @Before
    public void setUp()
    {
        a = new Assignment("CSC", 316, AssignmentType.PROJECT, "Data Structures Project 1", "Project part 1"
        , AssignmentStatus.ASSIGNED, LocalDate.parse("2025-07-10"));
    }

    @Test
    public void testGetters()
    {
        Assignment exp = new Assignment("CSC", 316, AssignmentType.PROJECT, "Data Structures Project 1", "Project part 1"
                , AssignmentStatus.ASSIGNED, LocalDate.parse("2025-07-10"));
        assertEquals("CSC", a.getCourseCode());
        assertEquals(316, a.getCourseNumber());
        assertEquals(AssignmentType.PROJECT, a.getAssignmentType());
        assertEquals("Data Structures Project 1", a.getTitle());
        assertEquals("Project part 1", a.getDescription());
        assertEquals(AssignmentStatus.ASSIGNED, a.getStatus());
        assertEquals("2025-07-10", a.getDueDate().toString());
        assertEquals(Priority.MEDIUM, a.getPriority());
    }

    @Test
    public void testEdits()
    {
        a.editCourseCode("CS");
        assertEquals("CS", a.getCourseCode());
        a.editDescription("Project Reading");
        assertEquals("Project Reading", a.getDescription());
        a.editCourseNumber(216);
        assertEquals(216, a.getCourseNumber());
        a.editTitle("Intro to Software practices");
        assertEquals("Intro to Software practices", a.getTitle());
        a.editType(AssignmentType.READING);
        assertEquals(AssignmentType.READING, a.getAssignmentType());
        a.setNewDueDate(LocalDate.parse("2025-07-15"));
        assertEquals("2025-07-15", a.getDueDate().toString());
    }

    @Test
    public void testInvalidInputs()
    {
        assertThrows(IllegalArgumentException.class, () -> a.editCourseCode(""));
        assertThrows(IllegalArgumentException.class, () -> a.editStatus(null));
        assertThrows(IllegalArgumentException.class, () -> a.editTitle(null));
        assertThrows(IllegalArgumentException.class, () -> a.editDescription(null));
        assertThrows(IllegalArgumentException.class, () -> a.editCourseNumber(0));
    }

    @Test
    public void testToString()
    {
        assertEquals("CSC 316\nData Structures Project 1\n2025-07-10", a.toString());
    }

    @Test
    public void testOverdueDetection() {
        a.setNewDueDate(LocalDate.now().minusDays(1));
        a.checkOverDue(a.getDueDate());
        assertEquals(AssignmentStatus.OVERDUE, a.getStatus());
    }

    @Test
    public void testPriorityCalculation() {
        a.setNewDueDate(LocalDate.now().plusDays(1));
        a.setPriorityOnDate(a.getDueDate());
        assertEquals(Priority.HIGH, a.getPriority());

        a.setNewDueDate(LocalDate.now().plusDays(5));
        a.setPriorityOnDate(a.getDueDate());
        assertEquals(Priority.HIGH, a.getPriority());

        a.setNewDueDate(LocalDate.now().plusDays(15));
        a.setPriorityOnDate(a.getDueDate());
        assertEquals(Priority.LOW, a.getPriority());
    }

}
