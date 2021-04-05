package Controller;

import Model.Task;
import View.GUI;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SimulationManager implements Runnable{
    private GUI gui;
    private String file;
    private int peakHour;
    private int maxClientsInQueues;
    private float avarageWaitingTime;
    private int timeLimit;
    private int maxProcessingTime;
    private int minProcessingTime;
    private int maxArrivalTime;
    private int minArrivalTime;
    private int numberOfServers;
    private int numberOfTasks;
    private float avarageServiceTime;

    private int stop;
    private Scheduler scheduler;
    private List<Task> generatedTasks;

    public SimulationManager(GUI gui, int timeLimit, int maxProcessingTime, int minProcessingTime, int numberOfServers, int numberOfTasks, int minArrivalTime, int maxArrivalTime)
    {
        this.stop = 0;
        this.gui = gui;
        file = "output.txt";
        avarageServiceTime = 0;
        maxClientsInQueues = 0;
        peakHour = 0;
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
            avarageServiceTime += randomProcessing;
        }
        avarageServiceTime = avarageServiceTime/ numberOfTasks;
    }

    public void setStop(int stop)
    {
        this.stop = 1;
    }

    public void writeOutput(int currentTime, boolean lastTime)
    {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter printWriter = new PrintWriter(fileWriter);

        //System.out.println("Time " + currentTime);
        printWriter.println("Time " + currentTime);
        gui.addTextOnConsole("Time " + currentTime + "\n");
        //System.out.print("Waiting clients: ");
        printWriter.print("Waiting clients: ");
        gui.addTextOnConsole("Waiting clients: ");
        for (Task t:generatedTasks)
        {
            //System.out.print("(" + t.getId() + " " + t.getArrivalTime() +" "+t.getProcessingTime() + ") ");
            printWriter.print("(" + t.getId() + " " + t.getArrivalTime() +" "+t.getProcessingTime() + ") ");
            gui.addTextOnConsole("(" + t.getId() + " " + t.getArrivalTime() +" "+t.getProcessingTime() + ") ");
        }
        //System.out.println();
        printWriter.println();
        gui.addTextOnConsole("\n");

        for(int j = 1; j<= numberOfServers; j++)
        {
            //System.out.print("QUEUE " + j + ": ");
            //System.out.println(scheduler.getServers().get(j - 1).writeElementsInServer());
            printWriter.print("QUEUE " + j + ": ");
            printWriter.println(scheduler.getServers().get(j - 1).writeElementsInServer());
            gui.addTextOnConsole("QUEUE " + j + ": ");
            gui.addTextOnConsole(scheduler.getServers().get(j - 1).writeElementsInServer() + "\n");
        }
        //System.out.println();
        printWriter.println();
        gui.addTextOnConsole("\n");
        if(lastTime)
        {
            //System.out.println("Avarage Service Time: " + avarageServiceTime);
            printWriter.println("Avarage Service Time: " + avarageServiceTime);
            gui.addTextOnConsole("Avarage Service Time: " + avarageServiceTime + "\n");
            //System.out.println("Peak Hour: " + peakHour);
            printWriter.println("Peak Hour: " + peakHour);
            gui.addTextOnConsole("Peak Hour: " + peakHour + "\n");
            //System.out.println("Average Waiting Time: " + avarageWaitingTime);
            printWriter.println("Average Waiting Time: " + avarageWaitingTime);
            gui.addTextOnConsole("Average Waiting Time: " + avarageWaitingTime + "\n");
        }

        printWriter.close();
    }


    @Override
    public void run() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        writer.print("");
        writer.close();
        int currentTime = 0;
        int currentClientsInQueues;
        while (currentTime < timeLimit && (generatedTasks.size() > 0 || scheduler.getClientsInServers() > 0)  && stop == 0)
        {

            while(generatedTasks.size() > 0 && generatedTasks.get(0).getArrivalTime() == currentTime)
            {
                Task task = generatedTasks.remove(0);
                scheduler.dispachTask(task);
            }
            currentClientsInQueues = scheduler.getClientsInServers();
            if(currentClientsInQueues > maxClientsInQueues)
            {
                peakHour = currentTime;
                maxClientsInQueues = currentClientsInQueues;
            }

            writeOutput(currentTime,false);

            currentTime++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(stop == 0)
        {
            avarageWaitingTime = scheduler.getTotalWaitingAtQueues() / (float)(numberOfTasks - generatedTasks.size() - scheduler.getClientsInServers());
            //System.out.println("AAAAAAAAAA " + scheduler.getTotalWaitingAtQueues());
            writeOutput(currentTime,true);
        }
        scheduler.stopServers();
    }
}
