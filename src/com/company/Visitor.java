package com.company;

public class Visitor extends  Thread{
    public boolean isSittingDown = false;
    public Classroom classroom;

    public Visitor(Classroom classroom){
        if(classroom.getStudentAndVisitorSemaphore().availablePermits() > 0){}
        this.classroom = classroom;
    }

    // visitor can enter the class at any time
    public void enterClass(){
        if (!this.classroom.checkClassFilledState()) {
            this.classroom.classVisitorsFull += 1;
            sitDown();
        }
    }

    private void sitDown() {
       try{
           this.isSittingDown = true;
           this.classroom.getStudentAndVisitorSemaphore().acquire();
       } catch (Exception exception){
           exception.printStackTrace();
       }
    }

    // visitors can leave at any time
    public void leave() {
        classroom.getStudentAndVisitorSemaphore().release();
    }

    @Override
    public void run() {
        enterClass();
    }
}
