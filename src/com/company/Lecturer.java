package com.company;

public class Lecturer extends Thread{
    public String lecturerName;
    public Classroom classroom;
    public Student student[];
    public boolean isLectureRunning = false;

    public Lecturer(String lecturerName, Student student[], Classroom classroom){
        this.lecturerName = lecturerName;
        if (classroom.getStudentAndVisitorSemaphore().availablePermits() > 0) {
            this.classroom = classroom;
        }
        this.student = student;
    }

    // teacher entering the class
    public void enterClass() {
        // Checks whether classroom is full. If not full lecturer can enter
        if (classroom.getLecturersSemaphore().availablePermits() > 0) {
            try {
                this.classroom.teacherName = lecturerName;
                classroom.getLecturersSemaphore().acquire();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
    // check running state of lecture
    public boolean getIsLectureRunning() {
        return this.isLectureRunning;
    }

    //teacher starting lecture if all students are sitting
    public void startLecture() {
        int counter = 0;
        for (Student stud: student) {
            if (stud.classroomObject != null && classroom != null) {
                if (stud.classroomObject.classroomName == classroom.classroomName) {
                    if (stud.getIsSittingDown()) {
                        counter++;
                    }
                }
            }
        }
        if (classroom != null) {
            if (classroom.classFull == counter) {
                this.classroom.isLectureStarted = true;
                isLectureRunning = true;
            }
        }
    }

    // End lecture function
    public void leave() {
        // Check if lecture is running, if running end it and release semaphore
        if (classroom.isLectureStarted) {
            classroom.isLectureStarted = false;
            classroom.teacherName = "";
            classroom.getLecturersSemaphore().release();
            System.out.println(lecturerName + "'s lecture over!");
        }
    }

    @Override
    public void run() {
        enterClass();
    }
}
