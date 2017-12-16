package BaseServer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.Queue;

public class LogServer
    extends Thread
{
    private Queue<String> logServerQueue = new PriorityQueue<>();
    private Object object = new Object();

    private boolean isRunning;

    LogServer()
    {
        this.start();
        this.isRunning = true;
    }

    @Override
    public void run()
    {
        Writer output = null;


        while(isRunning)
        {
            if(!logServerQueue.isEmpty())
            {
                try
                {
                    output = new BufferedWriter(new FileWriter("BaseServerLog.txt", true));
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                String message;

                synchronized (logServerQueue)
                {
                    message = logServerQueue.poll();
                }

                try
                {
                    output.append(message + "\n");
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

                try
                {
                    if(output != null)
                        output.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

            }
            else
            {
                try
                {
                    sleep(5);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }

    }

    void addMessageToLog(String message)
    {
        synchronized (logServerQueue)
        {
            logServerQueue.offer(message);
        }
    }

    void showMessage(String message)
    {
        synchronized (object)
        {
            System.out.println(new Date().toString() + " " + message);
        }
    }

}
