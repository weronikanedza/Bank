package BaseServer;

import java.io.File;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.Queue;

public class LogServer
    extends Thread
{
    private Queue<String> logServerQueue = new PriorityQueue<>();

    private boolean isRunning;

    LogServer()
    {
        super("LogServer");

        this.start();
        this.isRunning = true;
    }

    @Override
    public void run()
    {
        String path = "C:\\Log";
        while(isRunning)
        {
            if(!logServerQueue.isEmpty())
            {
                // Take message from queue
                String tempMessage;
                synchronized (logServerQueue)
                {
                    tempMessage = logServerQueue.poll();
                }
                // Split this message
                String[] message = tempMessage.split(" ");

                // Return local date
                String localDate;
                {
                    Date date = new Date();

                    Integer day = date.getDate();
                    Integer month = 1 + date.getMonth();
                    Integer year = 1900 + date.getYear();

                    localDate = day.toString() + '.' + month.toString() + '.' + year.toString();
                }

                // Create folder for specific client
                File file = CreateFolder(path, localDate, message);

                // Create file
                if(file != null)
                {

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

    File CreateFolder(String mPath, String mLocalDate, String[] mMessage)
    {
        if(mMessage[0].equals("logIn"))
        {
            if(mMessage[1].equals("true"))
            {
                mPath += "\\" + mLocalDate + "\\" + mMessage[2];
            }
            else
            {
                System.out.println("Error: Something wrong with folder creator!");
                return null;
            }
        }
        else
        {
            System.out.println("something else");
        }

        File file = new File(mPath);
        file.mkdirs();

        return file;
    }

    void AddMessageToLog(String message)
    {
        synchronized (logServerQueue)
        {
            logServerQueue.offer(message);
        }
    }
}
