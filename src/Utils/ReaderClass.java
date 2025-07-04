package Utils;

import model.Data.*;
import model.Exceptions.ReaderException;
import view.Handlers.AssignmentHandler;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReaderClass {

    private static final String pathToStudentFile = "output/studentList/students.txt";
    private static final String halfPath = "output/assignmentList/";

    public static Map<String, AssignmentHandler> loadMap() {
        try {
            FileInputStream f = new FileInputStream(pathToStudentFile);
            Map<String, AssignmentHandler> map = new HashMap<>();
            Scanner sc = new Scanner(f);

            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\t");
                if (parts.length < 6) continue;

                String first = parts[0];
                String last = parts[1];
                int age = Integer.parseInt(parts[2]);
                String studentID = parts[3];
                String mail = parts[4];
                String major = parts[5];

                AssignmentHandler handler = new AssignmentHandler(first, last, age, studentID, mail, major);
                map.put(studentID, handler);

                File assignmentFile = new File(halfPath + studentID + ".txt");
                if (!assignmentFile.exists()) continue;

                Scanner fileScanner = new Scanner(assignmentFile);
                while (fileScanner.hasNextLine()) {
                    String assignmentLine = fileScanner.nextLine().trim();
                    if (assignmentLine.isEmpty()) continue;

                    String[] partLine = assignmentLine.split("\t");
                    if (partLine.length < 8) continue;

                    String courseCode = partLine[0];
                    int courseNumber = Integer.parseInt(partLine[1]);
                    String title = partLine[2];
                    String description = partLine[3];
                    AssignmentType type = AssignmentType.valueOf(partLine[4]);
                    AssignmentStatus status = AssignmentStatus.valueOf(partLine[5]);
                    Priority priority = Priority.valueOf(partLine[6]);
                    LocalDate dueDate = LocalDate.parse(partLine[7]);

                    handler.addAssignment(new Assignment(courseCode, courseNumber, type, title, description, status, dueDate));
                }
                fileScanner.close();
            }

            sc.close();
            return map;

        } catch (Exception e) {
            throw new ReaderException("Error while reading student or assignment files: " + e.getMessage());
        }
    }
}
