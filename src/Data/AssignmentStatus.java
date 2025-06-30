package Data;

/**
 * Enum for tracking the status of the assignment with
 * three different status.
 * Completed : finished the assignment
 * Pending : assignment started, yet to be completed,
 *           typically one week after the assignment is assigned.
 * Overdue : passed the due date
 * Assigned : status given as soon as the assignment is opened/assigned
 * Unassigned : assignment yet to be opened by the professor.
 * @author Srivathsa
 */
public enum AssignmentStatus {
    COMPLETED,
    PENDING,
    OVERDUE,
    ASSIGNED,
    UNASSIGNED
}
