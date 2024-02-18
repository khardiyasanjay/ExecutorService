package org.example.es_callable.future_interface;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ParallelDataRetrievalAllTaskDone {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5); // Create a thread pool with 5 threads

        // Define a list of data retrieval tasks (Callable)
        List<Callable<String>> dataRetrievalTasks = new ArrayList<>();
        dataRetrievalTasks.add(new DataRetrievalTask("Source 1"));
        dataRetrievalTasks.add(new DataRetrievalTask("Source 2"));
        dataRetrievalTasks.add(new DataRetrievalTask("Source 3"));

        try {
            // Submit all tasks to the executor service
            List<Future<String>> futures = executorService.invokeAll(dataRetrievalTasks);

            // Process the results once all tasks are completed
            for (Future<String> future : futures) {
                String result = future.get(); // Retrieve the result (blocking call)
                System.out.println("Retrieved data: " + result);
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executorService.shutdown(); // Shutdown the executor service
    }

    static class DataRetrievalTask implements Callable<String> {
        private final String dataSource;

        public DataRetrievalTask(String dataSource) {
            this.dataSource = dataSource;
        }

        @Override
        public String call() throws Exception {
            // Simulate data retrieval from a data source (e.g., database, API)
            System.out.println("executing the task in call method for data source " + dataSource);
            Thread.sleep(2000); // Simulate latency
            return "Data from " + dataSource;
        }
    }
}
