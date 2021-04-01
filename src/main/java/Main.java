import Controller.SimulationManager;

public class Main {
    public static void main(String[] args)
    {
        //System.out.println("TEST");
        int timeLimit = 200;
        int maxProcessingTime = 9;
        int minProcessingTime = 3;
        int maxArrivalTime = 100;
        int minArrivalTime = 10;
        int numberOfServers = 20;
        int numberOfTasks = 1000;
        SimulationManager simulationManager = new SimulationManager(timeLimit,maxProcessingTime,minProcessingTime,numberOfServers,numberOfTasks,minArrivalTime,maxArrivalTime);
        Thread thread = new Thread(simulationManager);
        thread.start();
    }
}
