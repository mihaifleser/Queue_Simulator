package Controller;

import Model.Server;
import Model.Task;
import org.codehaus.plexus.classworlds.strategy.Strategy;
import sun.util.resources.Bundles;

import java.util.List;

public class Scheduler {
    private List<Server> servers;
    private int maxNoServers;
    private int maxTasksPerServer;
    private Strategy strategy;

    public Scheduler(int maxNoServers, int maxTasksPerServer)
    {
        this.maxNoServers = maxNoServers;
        this.maxTasksPerServer = maxTasksPerServer;
        for(int i = 0; i < maxNoServers; i++)
        {
            Server server = new Server();
            servers.add(server);
            Thread thread = new Thread(server);
            thread.start();
        }
    }
    private Server getServerWithMinimumWaitingTime()
    {
        Server minServer = servers.get(0);
        for (Server server: servers) {
            if(server.getWaitingPeriod() < minServer.getWaitingPeriod())
                minServer = server;
        }
        return minServer;
    }

    public void dispachTask(Task newTask)
    {
        Server minServer = getServerWithMinimumWaitingTime();
        minServer.addTask(newTask);
    }

    public List<Server> getServers()
    {
        return servers;
    }
}
