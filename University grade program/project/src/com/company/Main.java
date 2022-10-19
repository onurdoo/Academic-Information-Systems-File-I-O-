package com.company;
//Required libraries are imported
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

public class Main {

    public static void main(String[] args) {

        ArrayList<Student> students = new ArrayList<>(); //An arraylist was created to hold the data in the students.txt file
        ArrayList<Courses> courses = new ArrayList<>(); //An arraylist was created to hold the data in the courses.txt file
        ArrayList<Grades> grades = new ArrayList<>(); //An arraylist was created to hold the data in the grades.txt file

        Scanner fileIn = null;
        Scanner fileIn2 = null;
        Scanner fileIn3 = null;
        Scanner fileIn4 = null;

        //Variables type scanner has been created to read data from the input files.
        try {
            fileIn = new Scanner(new FileInputStream("commands.txt"));
            fileIn.useDelimiter(" ");

            fileIn2 = new Scanner(new FileInputStream("courses.txt"));
            fileIn2.useDelimiter(" ");

            fileIn3 = new Scanner(new FileInputStream("grades.txt"));
            fileIn3.useDelimiter(" ");

            fileIn4 = new Scanner(new FileInputStream("students.txt"));
            fileIn4.useDelimiter(" ");


        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(0);
        }
        //By reading courses.txt, the data is transferred to an arraylist named courses.
        while (fileIn2.hasNextLine()) {
            String[] courseData = fileIn2.nextLine().split(" ");//Read file line separated by " ".
            String courseIdString = courseData[0];
            int courseId;
            courseId = Integer.parseInt(courseIdString); //String data convert to Integer.
            String courseName = courseData[1];

            Courses newCourse = new Courses(courseId, courseName);
            courses.add(newCourse);
        }

        //By reading students.txt, the data is transferred to an arraylist named students.
        while (fileIn4.hasNextLine()) {
            String[] studentData = fileIn4.nextLine().split(" "); //Read file line separated by " ".
            String studentIdString = studentData[0];
            int studentId;
            studentId = Integer.parseInt(studentIdString); //String data convert to Integer.
            String studentName = studentData[1];
            String studentSurname = studentData[2];
            if (studentSurname.equals("")) {
                studentSurname = studentData[3];
            }

            Student newStudent = new Student(studentId, studentName, studentSurname);
            students.add(newStudent);
        }

        //By reading grades.txt, the data is transferred to an arraylist named grades.
        while (fileIn3.hasNextLine()) {

            String[] gradeDataString = fileIn3.nextLine().split(" ");//Read file line separated by " ".

            int courseId = Integer.parseInt(gradeDataString[0]); //String data convert to Integer.
            int studentId = Integer.parseInt(gradeDataString[1]);
            int midterm = Integer.parseInt(gradeDataString[2]);


            if (gradeDataString.length == 5) { //Objects are created according to the case of 2.midterm

                int midterm2 = Integer.parseInt(gradeDataString[3]);//String data convert to Integer.
                int finalGrade = Integer.parseInt(gradeDataString[4]);

                Grades newGrades = new Grades(studentId, courseId, midterm, midterm2, finalGrade, true);
                grades.add(newGrades);


            } else {
                int finalGrade = Integer.parseInt(gradeDataString[3]);

                Grades newGrades = new Grades(studentId, courseId, midterm, finalGrade, false);
                grades.add(newGrades);

            }

        }

        /*Commands are detected by reading data from commands.txt file and commands are executed with Switch statement*/
        while (fileIn.hasNextLine()) {
            String commandLine = "";
            String[] commandData = fileIn.nextLine().split(" ");
            for (String i : commandData) {
                if (isInteger(i)) {// Cheked command line with calling isInteger method
                    break;
                }
                commandLine = commandLine.concat(i);
            }

            /* Outputs are printed to the output.txt file with the object created from the FileWriter class.*/
            switch (commandLine) {
                case "LISTCOURSESALL" -> {

                    FileWriter myWriter = null;
                    try {
                        myWriter = new FileWriter("output.txt", true);
                        myWriter.write("COMMAND:\n" +
                                "LIST COURSES ALL\n" +
                                "RESULT:\n");
                        for (Courses i : courses) {
                            myWriter.write(i + "\n");
                        }
                        myWriter.write("\n");

                        myWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                case "LISTSTUDENTSFORCOURSE" -> {
                    int courseId = Integer.parseInt(commandData[4]); //String data convert to Integer.
                    FileWriter myWriter = null;
                    try {
                        myWriter = new FileWriter("output.txt", true);
                        myWriter.write("COMMAND:\n" +
                                "LIST STUDENTS FOR COURSE " + commandData[4] + "\n" +
                                "RESULT:\n");
                        for (Grades i : grades) {
                            if (courseId == i.getCourseID()) { //output with integer data, control statments in the command is writed to txt file.
                                for (Student j : students) {
                                    if (i.getID() == j.getStudentId()) {
                                        myWriter.write(j + "\n");
                                    }
                                }
                            }
                        }
                        myWriter.write("\n");

                        myWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                case "LISTCOURSESFORSTUDENT" -> {
                    int studentId = Integer.parseInt(commandData[4]); //String data convert to Integer.
                    FileWriter myWriter = null;
                    try {
                        myWriter = new FileWriter("output.txt", true);
                        myWriter.write("COMMAND:\n" +
                                "LIST COURSES FOR STUDENT " + commandData[4] + "\n" +
                                "RESULT:\n");
                        for (Grades i : grades) {
                            if (studentId == i.getID()) { //output with integer data, control statments in the command is writed to txt file.
                                for (Courses j : courses) {
                                    if (i.getCourseID() == j.getCourseId()) {
                                        myWriter.write(j + "\n");
                                    }
                                }
                            }
                        }
                        myWriter.write("\n");

                        myWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                case "LISTGRADESFORCOURSE" -> {
                    int courseId = Integer.parseInt(commandData[4]); //String data convert to Integer.
                    int studentId = Integer.parseInt(commandData[7]);
                    FileWriter myWriter = null;
                    try {
                        myWriter = new FileWriter("output.txt", true);
                        myWriter.write("COMMAND:\n" +
                                "LIST GRADES FOR COURSE " + commandData[4] + " AND STUDENT " + commandData[7] + "\n" +
                                "RESULT:\n");
                        for (Grades i : grades) {
                            if (courseId == i.getCourseID() && studentId == i.getID()) { //output with integer data, control statments in the command is writed to txt file.
                                myWriter.write(Integer.toString(i.getMidterm1()) + " ");
                                if (i.isMidterm2Check()) {
                                    myWriter.write(Integer.toString(i.getMidterm2()) + " ");
                                }
                                myWriter.write(Integer.toString(i.getFinalExam()) + "\n");

                            }
                        }
                        myWriter.write("\n");

                        myWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                case "LISTGRADESFORSTUDENT" -> {
                    int studentId = Integer.parseInt(commandData[4]); //String data convert to Integer.
                    FileWriter myWriter = null;
                    try {
                        myWriter = new FileWriter("output.txt", true);
                        myWriter.write("COMMAND:\n" +
                                "LIST GRADES FOR STUDENT " + commandData[4] + "\n" +
                                "RESULT:\n");
                        for (Grades i : grades) {
                            if (studentId == i.getID()) { //output with integer data, control statments in the command is writed to txt file.
                                if (i.isMidterm2Check()) {
                                    myWriter.write(i.toString2() + "\n");
                                } else {
                                    myWriter.write(i.toString3() + "\n");
                                }
                            }
                        }
                        myWriter.write("\n");

                        myWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                case "CALCULATEFINALGRADEFORCOURSE" -> {
                    int courseId = Integer.parseInt(commandData[4]); //String data convert to Integer.
                    int studentId = Integer.parseInt(commandData[7]);
                    FileWriter myWriter = null;
                    try {
                        myWriter = new FileWriter("output.txt", true);
                        myWriter.write("COMMAND:\n" +
                                "CALCULATE FINALGRADE FOR COURSE " + commandData[4] + " AND STUDENT "
                                + commandData[7] + "\n" + "RESULT:\n");
                        for (Grades i : grades) {
                            if (studentId == i.getID() && courseId == i.getCourseID()) { //output with integer data, control statments in the command is writed to txt file.
                                if (i.isMidterm2Check()) {
                                    myWriter.write(String.format("%.2f", i.calculateFinalGrade2()) + "\n");

                                } else {
                                    myWriter.write(String.format("%.2f", i.calculateFinalGrade()) + "\n");
                                }
                            }
                        }
                        myWriter.write("\n");

                        myWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                case "CALCULATEALLFINALGRADESFORSTUDENT" -> {
                    int studentId = Integer.parseInt(commandData[5]); //String data convert to Integer.
                    FileWriter myWriter = null;
                    try {
                        myWriter = new FileWriter("output.txt", true);
                        myWriter.write("COMMAND:\n" +
                                "CALCULATE ALL FINALGRADES FOR STUDENT " + commandData[5] + "\n" + "RESULT:\n");
                        for (Grades i : grades) {
                            if (studentId == i.getID()) { //output with integer data, control statments in the command is writed to txt file.
                                myWriter.write(i.getCourseID() + " ");
                                if (i.isMidterm2Check()) {
                                    myWriter.write(String.format("%.2f", i.calculateFinalGrade2()) + "\n");
                                } else {
                                    myWriter.write(String.format("%.2f", i.calculateFinalGrade()) + "\n");
                                }
                            }
                        }
                        myWriter.write("\n");

                        myWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                case "CALCULATEAVERAGEGRADESFORCOURSE" -> {
                    int courseId = Integer.parseInt(commandData[5]); //String data convert to Integer.
                    FileWriter myWriter = null;
                    try {
                        myWriter = new FileWriter("output.txt", true);
                        myWriter.write("COMMAND:\n" +
                                "CALCULATE AVERAGE GRADES FOR COURSE " + commandData[5] + "\n" + "RESULT:\n");
                        int totalMidterm1 = 0;
                        int totalMidterm2 = 0;
                        int totalFinalGrade = 0;
                        int studentNum = 0;
                        for (Grades i : grades) {
                            if (courseId == i.getCourseID()) { //output with integer data, control statments in the command is writed to txt file.
                                studentNum++;
                                totalMidterm1 += i.getMidterm1();
                                totalFinalGrade += i.getFinalExam();

                                if (i.isMidterm2Check()) {
                                    totalMidterm2 += i.getMidterm2();
                                }
                            }
                        }

                        //variables were created to calculate average grades
                        double averageMidterm1 = (double) totalMidterm1 / studentNum;
                        double averageMidterm2 = (double) totalMidterm2 / studentNum;
                        double averageFinalGrade = (double) totalFinalGrade / studentNum;
                        myWriter.write(String.format("%.2f", averageMidterm1) + " ");
                        for (Grades i : grades) {
                            if (i.getCourseID() == courseId) { //output with integer data, control statments in the command is writed to txt file.
                                if (i.isMidterm2Check()) {
                                    myWriter.write(String.format("%.2f", averageMidterm2) + " ");
                                    break;
                                }
                            }

                        }

                        myWriter.write(String.format("%.2f", averageFinalGrade) + "\n");
                        myWriter.write("\n");

                        myWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                case "FINDBESTOFCOURSE" -> {
                    int courseId = Integer.parseInt(commandData[4]); //String data convert to Integer.
                    //variables were created to calculate the best
                    double temp = 0;
                    double best = 0;
                    int bestId = 0;
                    FileWriter myWriter = null;
                    try {
                        myWriter = new FileWriter("output.txt", true);
                        myWriter.write("COMMAND:\n" +
                                "FIND BEST OF COURSE " + commandData[4] + "\n" + "RESULT:\n");
                        for (Grades i : grades) {
                            if (courseId == i.getCourseID()) { //output with integer data, control statments in the command is writed to txt file.

                                if (i.isMidterm2Check()) {
                                    temp = i.calculateFinalGrade2();
                                } else {
                                    temp = i.calculateFinalGrade();
                                }
                                if (temp > best) {
                                    best = temp;
                                    bestId = i.getID();
                                }
                            }
                        }
                        for (Student i : students) {
                            if (i.getStudentId() == bestId) {
                                myWriter.write(i + " ");
                                myWriter.write(String.format("%.2f", best) + "\n");

                            }
                        }
                        myWriter.write("\n");

                        myWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


    }

    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
