package Utils;

import model.Data.Assignment;
import model.Users.Student;
import view.Handlers.AssignmentHandler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AssignmentWriter {

    public static void saveUpdatesToFile(AssignmentHandler assignments)
    {
        List<Student> students = new ArrayList<Student>();
        try {
            FileInputStream f = new FileInputStream("output/studentList/students.txt");
            Scanner sc = new Scanner(f);
            while(sc.hasNext())
            {
                String line = sc.nextLine();
                String[] parts = line.split("\t");
                if (parts.length == 6) {
                    String first = parts[0];
                    String last = parts[1];
                    String id = parts[2];
                    String major = parts[3];
                    int age = Integer.parseInt(parts[4]);
                    String email = parts[5];
                    students.add(new Student(first, last, age, id, email, major));
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Student s = assignments.getStudent();
        List<Assignment> assignmentList = assignments.getAssignments();
        try {
            boolean found = false;
            for(Student x: students)
            {
                if(x.equals(s)) {
                    found = true;
                }
            }
            if(!found) {
                PrintWriter pw = new PrintWriter(new FileOutputStream("output/studentList/students.txt", true));
                PrintWriter pw1 = new PrintWriter(new FileOutputStream("output/assignmentList/" + s.getStudentID() + ".txt"));
                pw.println(s.getFirstName() + "\t" + s.getLastName() + "\t" + s.getStudentID() + "\t" + s.getMajor() + "\t"
                        + s.getAge() + "\t" + s.getEmail());
                for (Assignment a : assignmentList) {
                    String assignmentDetails = a.getCourseCode() + "\t" + a.getCourseNumber() + "\t" + a.getTitle() + "\t"
                            + a.getDescription() + "\t" + a.getAssignmentType() + "\t" + a.getStatus() + "\t"
                            + a.getPriority() + "\t" + a.getDueDate() + "\n";
                    pw1.println(assignmentDetails);
                }
                pw.close();
                pw1.close();
            }
            else
            {
                PrintWriter pw1 = new PrintWriter(new FileOutputStream("output/assignmentList/" + s.getStudentID() + ".txt"));
                for (Assignment a : assignmentList) {
                    String assignmentDetails = a.getCourseCode() + "\t" + a.getCourseNumber() + "\t" + a.getTitle() + "\t"
                            + a.getDescription() + "\t" + a.getAssignmentType() + "\t" + a.getStatus() + "\t"
                            + a.getPriority() + "\t" + a.getDueDate();
                    pw1.println(assignmentDetails);
                }
                pw1.close();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}