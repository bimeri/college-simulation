package com.company;

public class Monitor extends Thread{
        public Classroom classroom[];

        Monitor(Classroom classroom[]) {
            this.classroom = classroom;
        }

    @Override
    public void run() {
        System.out.println("=======================================================================");
        System.out.println("Classroom\tLecturer\tStatus\tStudents\tVisitor");
        System.out.println("=======================================================================");
        for (int c = 0; c < classroom.length; c++) {
            System.out.print(classroom[c].classroomName);
            if (classroom[c].teacherName != null) {
                System.out.print("\t|\t" + classroom[c].teacherName);
            } else {
                System.out.print("\t|\t");
            }
            System.out.print("\t|\t" + classroom[c].isLectureStarted + "\t\t");
            if (classroom[c].teacherName != null) {
                System.out.print(classroom[c].classFull);
            }
            if (classroom[c].teacherName != null) {
                System.out.print("\t\t|\t" + classroom[c].classVisitorsFull);
            }
            System.out.println();
        }
    }
}
