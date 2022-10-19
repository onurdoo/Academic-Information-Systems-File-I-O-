package com.company;

public class Courses {
    private int courseId; // Field that holds ID of course
    private String courseName; // Field that holds name of course

    //created parameter constructor
    public Courses(int courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }
    //created getter and setter methods
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    //created toString method by override
    @Override
    public String toString() {
        return  courseId + " " +courseName;
    }
}
