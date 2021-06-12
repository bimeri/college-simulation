package com.company;

public class Student extends Thread {
    public static int studentId;
    public static boolean isSittingDown;
    public static Classroom classroomObject;

    public Student(int studentId, Classroom classRoomObject){
        this.studentId = studentId;
        if(classRoomObject.getStudentAndVisitorSemaphore().availablePermits() > 0){
            this.classroomObject = classRoomObject;
        }
    }
    // student can enter a class if the class is not full, and lecture is not yet running
    public void enterClass(){
        if(this.classroomObject != null && !this.classroomObject.checkClassFilledState()){
            studentSitDown();
        }
    }
    // student must sit down first for lecture to begin
    public boolean getIsSittingDown(){
        return this.isSittingDown;
    }

    // letting student to sit down by adding counter to the number of students sitting down
    public void studentSitDown(){
        try {
            this.isSittingDown = true;
            this.classroomObject.getStudentAndVisitorSemaphore().tryAcquire();
            this.classroomObject.classFull++;
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }

    // student can leave class, if class is over
    public void leave(){
        if(!this.classroomObject.isLectureStarted && this.classroomObject.teacherName.equals("")) {
            this.classroomObject.getStudentAndVisitorSemaphore().release();
            this.isSittingDown = false;
        }
    }
    @Override
    public void run(){
        enterClass();
    }

}