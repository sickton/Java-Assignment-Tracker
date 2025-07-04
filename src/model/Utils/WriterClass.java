package model.Utils;

import model.Data.Assignment;
import model.Exceptions.WriterException;
import model.Users.Student;
import view.Handlers.AssignmentHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WriterClass {

    private static final String pathToStudentFile = "output/studentList/students.txt";

    public static String saveStudentDetails(Student s)
    {
        List<Student> list = loadStudentFromFile();
        for(Student x : list)
        {
            if(x.equals(s))
                return "User already registered in System!";
        }
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(pathToStudentFile, true));
            pw.println(s.getFirstName() + "\t" + s.getLastName() + "\t" + s.getAge() + "\t" + s.getStudentID() + "\t"
            + s.getEmail() + "\t" + s.getMajor());
            pw.close();
            return "Sign Up Successful!";
        } catch (FileNotFoundException e) {
            throw new WriterException();
        }
    }

    private static List<Student> loadStudentFromFile()
    {
        try {
            List<Student> list = new ArrayList<Student>();
            FileInputStream f = new FileInputStream(pathToStudentFile);
            Scanner sc = new Scanner(f);
            while(sc.hasNextLine())
            {
                String line = sc.nextLine();
                String[] parts = line.split("\t");
                String first = parts[0];
                String last = parts[1];
                int age = Integer.parseInt(parts[2]);
                String studentId = parts[3];
                String mail = parts[4];
                String major = parts[5];
                list.add(new Student(first, last, age, studentId, mail, major));
            }
            sc.close();
            f.close();
            return list;
        } catch (IOException e) {
            throw new WriterException();
        }
    }

    public static String saveAssignmentsOfHandle(AssignmentHandler a)
    {
        String pathToAssignmentFile = "output/assignmentList/" + a.getStudent().getStudentID() + ".txt";
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(pathToAssignmentFile));
            List<Assignment> list = a.getAssignments();
            for(Assignment x : list)
            {
                String summary = x.getCourseCode() + "\t" + x.getCourseNumber() + "\t" +
                        x.getTitle() + "\t" + x.getDescription() + "\t" + x.getAssignmentType()
                        + "\t" + x.getStatus() + "\t" + x.getPriority() + "\t" + x.getDueDate();
                pw.println(summary);
            }
            pw.close();
            return "Saved Successfully !";
        } catch (FileNotFoundException e) {
            throw new WriterException();
        }

    }
}