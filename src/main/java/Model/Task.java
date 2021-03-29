package Model;

import java.util.Comparator;

public class Task{
    private int id;
    private int arrivalTime;
    private int processingTime;

    public Task(int id, int arrivalTime, int processingTime)
    {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.processingTime = processingTime;
    }
    public int getId() {
        return id;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public static Comparator<Task> monomialComparator = new Comparator<Task>() {

        public int compare(Task t1, Task t2) {
            //ascending order
            if(t1.getArrivalTime() > t2.getArrivalTime())
                return 1;
            else
                return -1;

        }};

}
