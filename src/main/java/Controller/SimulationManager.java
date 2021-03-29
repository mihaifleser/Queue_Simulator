package Controller;

import Model.Task;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SimulationManager implements Runnable{
    private int timeLimit;
    private int maxProcessingTime;
    private int minProcessingTime;
    private int maxArrivalTime;
    private int minArrivalTime;
    private int numberOfServers;
    private int numberOfTasks;

    private Scheduler scheduler;
    private List<Task> generatedTasks;

    public SimulationManager(int timeLimit, int maxProcessingTime, int minProcessingTime, int numberOfServers, int numberOfTasks, int minArrivalTime, int maxArrivalTime)
    {
        this.timeLimit = timeLimit;
        this.maxProcessingTime = maxProcessingTime;
        this.minProcessingTime = minProcessingTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minArrivalTime = minArrivalTime;
        this.numberOfServers = numberOfServers;
        this.numberOfTasks = numberOfTasks;
        scheduler = new Scheduler(numberOfServers,numberOfTasks);
        generateNRandomTasks();
        Collections.sort(generatedTasks, Task.monomialComparator);
    }

    private void generateNRandomTasks()
    {
        for(int i = 0 ; i < numberOfTasks; i++)
        {
            int randomArrival;
            int randomProcessing;
            Random random = new Random();
            randomArrival = random.nextInt(maxArrivalTime - minArrivalTime) + minArrivalTime;
            randomProcessing = random.nextInt(maxProcessingTime - minProcessingTime) + minProcessingTime;
            Task task = new Task(0,randomArrival,randomProcessing);
            generatedTasks.add(task);
        }
    }





    @Override
    public void run() {

    }
}
