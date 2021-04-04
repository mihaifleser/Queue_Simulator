package Controller;

import View.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiController {
    private GUI gui;
    private SimulationManager simulationManager;
    public GuiController(GUI gui)
    {
        this.gui = gui;
        this.simulationManager = new SimulationManager(gui,0,0,0,0,0,0,0);
    }

    public void initialise()
    {
        gui.initialise();
        gui.setActionOnStartButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    simulationManager.setStop(1);
                    gui.setTextOnConsole("");
                    int timeLimit = Integer.parseInt(gui.getInputTimeText());
                    int maxProcessingTime = Integer.parseInt(gui.getInputServiceMaxText());
                    int minProcessingTime = Integer.parseInt(gui.getInputServiceMinText());
                    int maxArrivalTime = Integer.parseInt(gui.getInputArrivalMaxText());
                    int minArrivalTime = Integer.parseInt(gui.getInputArrivalMinText());
                    int numberOfServers = Integer.parseInt(gui.getInputQueuesText());
                    int numberOfTasks = Integer.parseInt(gui.getInputClientsText());
                    simulationManager = new SimulationManager(gui,timeLimit,maxProcessingTime,minProcessingTime,numberOfServers,numberOfTasks,minArrivalTime,maxArrivalTime);
                    Thread thread = new Thread(simulationManager);
                    thread.start();
                } catch (Exception exception)
                {
                    gui.addTextOnConsole("Error: can't parse one of the number\n");
                }


            }
        });

    }
}
