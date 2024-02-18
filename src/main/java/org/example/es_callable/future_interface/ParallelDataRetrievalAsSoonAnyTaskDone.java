package org.example.es_callable.future_interface;

import java.util.concurrent.*;

public class ParallelDataRetrievalAsSoonAnyTaskDone {
    public static void main(String[] args) {
        /**
        * If you want to process the results from Future objects as they become available,
        * rather than waiting for all tasks to complete, you can use the ExecutorCompletionService class.
        * This class allows you to submit tasks to an ExecutorService and retrieve completed tasks in
        * the order they finish.
         */


        ExecutorService executorService = Executors.newFixedThreadPool(5); // Create a thread pool with 5 threads
        ExecutorCompletionService<String> completionService = new ExecutorCompletionService<>(executorService);

        // Submit tasks to the completion service
        completionService.submit(new DataRetrievalTask("Source 1"));
        completionService.submit(new DataRetrievalTask("Source 2"));
        completionService.submit(new DataRetrievalTask("Source 3"));

        // Process the results as they become available
        for (int i = 0; i < 3; i++) {
            try {
                Future<String> future = completionService.take(); // Retrieve the next completed task
                String result = future.get(); // Retrieve the result (blocking call)
                System.out.println("Retrieved data: " + result);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
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
            System.out.println("Executing task in call method for data source " + dataSource);
            Thread.sleep(2000); // Simulate latency
            return "Data from " + dataSource;
        }
    }
}
