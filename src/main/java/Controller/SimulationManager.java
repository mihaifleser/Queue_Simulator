package Controller;

import Model.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
        generatedTasks = new ArrayList<Task>();
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
            Task task = new Task(i,randomArrival,randomProcessing);
            generatedTasks.add(task);
        }
    }

    public void writeOutput(int currentTime)
    {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("output.txt", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter printWriter = new PrintWriter(fileWriter);

        System.out.println("Time " + currentTime);
        printWriter.println("Time " + currentTime);
        System.out.print("Waiting clients: ");
        printWriter.print("Waiting clients: ");
        for (Task t:generatedTasks)
        {
            System.out.print("(" + t.getId() + " " + t.getArrivalTime() +" "+t.getProcessingTime() + ") ");
            printWriter.print("(" + t.getId() + " " + t.getArrivalTime() +" "+t.getProcessingTime() + ") ");
        }
        System.out.println();
        printWriter.println();
        for(int j = 1; j<= numberOfServers; j++)
        {
            System.out.print("QUEUE " + j + ": ");
            System.out.println(scheduler.getServers().get(j - 1).writeElementsInServer());
            printWriter.print("QUEUE " + j + ": ");
            printWriter.println(scheduler.getServers().get(j - 1).writeElementsInServer());
        }
        System.out.println();
        printWriter.println();
        printWriter.close();
    }


    @Override
    public void run() {
        int currentTime = 0;
        while (currentTime < timeLimit && (generatedTasks.size() > 0 || scheduler.areTasksInServers()) )
        {

            while(generatedTasks.size() > 0 && generatedTasks.get(0).getArrivalTime() == currentTime)
            {
                Task task = generatedTasks.remove(0);
                scheduler.dispachTask(task);
            }

            writeOutput(currentTime);
            currentTime++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        writeOutput(currentTime);
        scheduler.stopServers();
    }
}
