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

    public Server()
    {
        tasks = new LinkedBlockingQueue<Task>();
        waitingPeriod = new AtomicInteger(0);
    }
    public int getWaitingPeriod()
    {
        return waitingPeriod.get();
    }

    public LinkedBlockingQueue getTasks()
    {
        return new LinkedBlockingQueue(tasks);
    }

    public void addTask(Task newTask)
    {
        tasks.add(newTask);
        waitingPeriod.set(waitingPeriod.get() + newTask.getProcessingTime());
    }
    @Override
    public void run() {
        while(true)
        {
            try {
                Task currentTask;
                if(tasks.size() > 0)
                {
                    Thread.sleep(tasks.peek().getProcessingTime() * 1000);
                    currentTask = tasks.poll();
                    waitingPeriod.set(waitingPeriod.get() - currentTask.getProcessingTime());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
