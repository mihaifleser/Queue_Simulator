package Model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;
    private AtomicInteger stopThread;
    private AtomicInteger totalWaitingInQueue;
    public Server()
    {
        tasks = new LinkedBlockingQueue<Task>();
        waitingPeriod = new AtomicInteger(0);
        totalWaitingInQueue = new AtomicInteger(0);
        stopThread = new AtomicInteger(0);
    }
    public int getWaitingPeriod()
    {
        return waitingPeriod.get();
    }

    public void stopThread()
    {
        stopThread.set(1);
    }

    public LinkedBlockingQueue getTasks()
    {
        return new LinkedBlockingQueue(tasks);
    }
    public int getNumberOfTasks()
    {
        return tasks.size();
    }

    public void addTask(Task newTask)
    {
        tasks.add(newTask);
        waitingPeriod.set(waitingPeriod.get() + newTask.getProcessingTime());
    }
    @Override
    public void run() {
        while(stopThread.get() == 0)
        {
            try {
                Task currentTask;
                if(tasks.size() > 0)
                {
                    Thread.sleep(tasks.peek().getProcessingTime() * 1000);
                    currentTask = tasks.poll();
                    waitingPeriod.set(waitingPeriod.get() - currentTask.getProcessingTime());
                    for(Task t:tasks)
                    {
                        t.increaseWaitingTime(currentTask.getProcessingTime());
                    }
                    totalWaitingInQueue.set(totalWaitingInQueue.get() + currentTask.getWaitingTime());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getTotalWaitingInQueues()
    {
        return totalWaitingInQueue.get();
    }

    public String writeElementsInServer()
    {
        String result = "";
        for (Task t:tasks) {
            result = result + "( "+ t.getId() + " " + t.getArrivalTime() + " " + t.getProcessingTime() + ") ";
        }
        return result;
    }
}
