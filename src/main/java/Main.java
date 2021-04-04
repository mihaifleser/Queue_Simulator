import Controller.GuiController;
import Controller.SimulationManager;
import View.GUI;

public class Main {
    public static void main(String[] args)
    {
        //System.out.println("TEST");
        int timeLimit = 60;
        int maxProcessingTime = 9;
        int minProcessingTime = 3;
        int maxArrivalTime = 30;
        int minArrivalTime = 2;
        int numberOfServers = 4;
        int numberOfTasks = 30;
        //SimulationManager simulationManager = new SimulationManager(timeLimit,maxProcessingTime,minProcessingTime,numberOfServers,numberOfTasks,minArrivalTime,maxArrivalTime);
        //Thread thread = new Thread(simulationManager);
        //thread.start();

        GUI gui = new GUI();
        GuiController controller = new GuiController(gui);
        controller.initialise();
    }
}
