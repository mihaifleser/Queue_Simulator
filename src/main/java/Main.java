import Controller.GuiController;
import Controller.SimulationManager;
import View.GUI;

public class Main {
    public static void main(String[] args)
    {
        GUI gui = new GUI();
        GuiController controller = new GuiController(gui);
        controller.initialise();
    }
}
