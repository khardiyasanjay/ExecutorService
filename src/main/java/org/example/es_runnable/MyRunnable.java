package org.example.es_runnable;

public class MyRunnable implements Runnable{
    @Override
    public void run() {
        // Task to be executed by a thread
        System.out.println("Executing the task in run method");
    }
}
