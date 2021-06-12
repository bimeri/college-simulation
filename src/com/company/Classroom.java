package com.company;

import java.util.concurrent.Semaphore;

public class Classroom {
    public String classroomName;
    public int classroomSize;
    public String teacherName;
    public boolean isLectureStarted = false;
    public int classFull = 0; // full here both visitors and students
    public int classVisitorsFull =  0;
    // declare a semaphore that will continually control the flow of entry of student, visitors and lecturer
    public Semaphore lecturersSemaphore = new Semaphore(1);
    public Semaphore studentAndVisitorSemaphore;

    public Classroom(String classroomName, int classroomSize, int permitValue){
        this.studentAndVisitorSemaphore = new Semaphore(permitValue);
        this.classroomName = classroomName;
        this.classroomSize = classroomSize;
    }

    public boolean checkClassFilledState(){
        if(classroomSize == classFull){
            return true;
        }
        return false;
    }

    public Semaphore getStudentAndVisitorSemaphore(){
        return this.studentAndVisitorSemaphore;
    }
    public Semaphore getLecturersSemaphore(){
        return this.lecturersSemaphore;
    }
}
