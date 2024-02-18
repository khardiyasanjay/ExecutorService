package org.example.es_callable;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        //task to be executed by a thread
        System.out.println("Executing the task in call method");
        Thread.sleep(4000);
        return 42;
    }
}
