package Controller;

import Model.Server;
import Model.Task;


import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<Server> servers;
    private int maxNoServers;
    private int maxTasksPerServer;

    public Scheduler(int maxNoServers, int maxTasksPerServer)
    {
        servers = new ArrayList<Server>();
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

    public int getClientsInServers()
    {
        int nrOfClients = 0;
        for(Server server: servers)
        {
            nrOfClients += server.getNumberOfTasks();
        }
        return nrOfClients;
    }

    public void dispachTask(Task newTask)
    {
        Server minServer = getServerWithMinimumWaitingTime();
        minServer.addTask(newTask);
    }

    public void stopServers()
    {
        for(Server server:servers)
        {
            server.stopThread();
        }
    }

    public boolean areTasksInServers()
    {
        for(Server server:servers)
            if(server.getTasks().size() > 0)
                return true;

        return false;
    }

    public List<Server> getServers()
    {
        return servers;
    }
}
