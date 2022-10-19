package com.company;

public class Grades {
    private int ID; // Field that holds students ID numbers
    private int courseID; // Field that holds courses ID numbers
    private int midterm1; // Field that holds first midterm score
    private int midterm2; // Field that holds second midterm score
    private int finalExam; // Field that holds final grade
    private boolean midterm2Check; // Field that Checks the 2nd midterm exam


    //parameter constructor methods created that takes name surname and grades
    public Grades(int ID, int courseID, int midterm1, int midterm2, int finalExam, boolean midterm2Check) {
        this.ID = ID;
        this.courseID = courseID;
        this.midterm1 = midterm1;
        this.midterm2 = midterm2;
        this.finalExam = finalExam;
        this.midterm2Check = midterm2Check;
    }

    public Grades(int ID, int courseID, int midterm1, int finalExam, boolean midterm2Check) {
        this.ID = ID;
        this.courseID = courseID;
        this.midterm1 = midterm1;
        this.finalExam = finalExam;
        this.midterm2Check = midterm2Check;
    }
    //created methods to calculate average
    public double calculateFinalGrade() {
        return (getMidterm1() * 0.4) + (getFinalExam() * 0.6);
    }

    public double calculateFinalGrade2() {
        return ((getMidterm1() + getMidterm2()) * 0.25) + (getFinalExam() * 0.5);
    }

//created getter and setter methods
    public int getMidterm1() {
        return midterm1;
    }

    public void setMidterm1(int midterm1) {
        this.midterm1 = midterm1;
    }

    public int getMidterm2() {
        return midterm2;
    }

    public void setMidterm2(int midterm2) {
        this.midterm2 = midterm2;
    }

    public int getFinalExam() {
        return finalExam;
    }

    public void setFinalExam(int finalExam) {
        this.finalExam = finalExam;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public boolean isMidterm2Check() {
        return midterm2Check;
    }

    public void setMidterm2Check(boolean midterm2Check) {
        this.midterm2Check = midterm2Check;
    }

    //created toString methods for different outputs
    @Override
    public String toString() {
        return ID + " " + courseID + " " + midterm1 + " " + midterm2 + " " + finalExam;
    }

    public String toString2() {
        return courseID + " " + midterm1 + " " + midterm2 + " " + finalExam;
    }

    public String toString3() {
        return courseID + " " + midterm1 + " " + finalExam;
    }
}
