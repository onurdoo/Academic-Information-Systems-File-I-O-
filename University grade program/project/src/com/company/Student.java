package com.company;

import java.util.ArrayList;

public class Student {
    private String name;  // Field that holds name of student
    private String surname;  // Field that holds surname of student
    private int studentId;  // Field that holds ID of student

    //parameter constructor methods created
    public Student(int studentId, String name, String surname) {
        this.studentId = studentId;
        this.name = name;
        this.surname = surname;
    }

 //created getter and setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setID(int studentId) {
        this.studentId = studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    //created toString method by override
    @Override
    public String toString() {
        return studentId + " " + name + " " + surname;
    }
}
