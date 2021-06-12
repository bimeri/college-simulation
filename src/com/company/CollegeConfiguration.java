package com.company;

import java.util.Random;

public class CollegeConfiguration implements Runnable {
    public boolean checker = true;
    public Classroom W201;
    public Classroom W202;
    public Classroom W101;
    public Classroom JS101;
    public Classroom classroom[] = new Classroom[4];
    public Student student[] = new Student[80];
    public Lecturer lecturer[] = new Lecturer[6];
    public Visitor visitor[] = new Visitor[5];

    // main function that execute the threads
    public void executeThread(){
        Random random = new Random();
        W201 = new Classroom("W201", 60, 60);
        W202 = new Classroom("W202", 60, 60);
        W101 = new Classroom("W101", 20, 20);
        JS101 = new Classroom("JS101", 30, 30);
        classroom[0] = W201;
        classroom[1] = W202;
        classroom[2] = W101;
        classroom[3] = JS101;
        visitor[0] = new Visitor(classroom[random.nextInt(4)]);
        visitor[1] = new Visitor(classroom[random.nextInt(4)]);
        visitor[2] = new Visitor(classroom[random.nextInt(4)]);
        visitor[3] = new Visitor(classroom[random.nextInt(4)]);
        visitor[4] = new Visitor(classroom[random.nextInt(4)]);
        lecturer[0] = new Lecturer("Osama", student, classroom[random.nextInt(4)]);
        lecturer[1] = new Lecturer("Barry", student, classroom[random.nextInt(4)]);
        lecturer[2] = new Lecturer("Faheem", student,  classroom[random.nextInt(4)]);
        lecturer[3] = new Lecturer("Alex", student, classroom[random.nextInt(4)]);
        lecturer[4] = new Lecturer("Aqeel", student, classroom[random.nextInt(4)]);
        lecturer[5] = new Lecturer("Waseem", student, classroom[random.nextInt(4)]);

        try {
            for (int x = 0; x < student.length; x++) {
                student[x] = new Student(x, classroom[random.nextInt(4)]);
                student[x].start();
            }

            for (int y = 0; y < student.length; y++) {
                student[y].join();
            }

            // Loop where visitors enter class
            for (int z = 0; z < visitor.length; z++) {
                visitor[z].start();
            }

            // Loop where lecturer enter class
            for (int t = 0; t < lecturer.length; t++) {
                lecturer[t].start();
            }
            for (int u = 0; u < lecturer.length; u++) {
                lecturer[u].startLecture();
            }

            for (int v = 0; v < lecturer.length; v++) {
                lecturer[v].join();
            }

            // Monitor thread for printing status of classes
            Monitor monitor = new Monitor(classroom);
            monitor.start();
            monitor.join();
            for (int l = 0; l < lecturer.length; l++) {
                lecturer[l].join();
            }
            for (int s = 0; s < lecturer.length; s++) {
                lecturer[s].leave();
            }
            // Release loop
            for (int t = 0; t < classroom.length; t++) {
                if (!classroom[t].isLectureStarted) {
                    for (int u = 0; u < student.length; u++) {
                        if (!student[u].getIsSittingDown()) {
                            student[u].leave();
                        }
                    }
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    public void stopThread() {
        checker = false;
    }

    public void run() {
        while (checker) {
            try {
                executeThread();
                Thread.sleep(3000);
                for (int i = 0; i < 2; i++) {
                    System.out.println("___________________**********Task Done ***********___________________");
                    System.out.println();
                }
            } catch (Exception exception) {
                System.out.println("Task completed successfully");
            }
        }
    }
}
