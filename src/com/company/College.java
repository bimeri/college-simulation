package com.company;

public class College {
    public static void main(String[] args){
        CollegeConfiguration collegeConfiguration = new CollegeConfiguration();
        Thread thread = new Thread(collegeConfiguration);
        thread.start();
        try {
            System.in.read();
            collegeConfiguration.stopThread();
            thread.interrupt();
        } catch (Exception exception){
            System.out.println("Task completed successfully");
        }
        System.out.println("The end");
    }
}
