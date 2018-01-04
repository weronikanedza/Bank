package BaseServer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
                File file = CreateFolder(path, message);

                // Create file
                file = new File(file.getPath() + "\\" + localDate + ".txt");

                if(!file.exists())
                    try
                    {
                        file.createNewFile();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                FileWriter fw;
                BufferedWriter bw = null;
                try
                {
                    fw = new FileWriter(file.getAbsoluteFile(), true);

                    bw = new BufferedWriter(fw);

                    // Add message to file
                    bw.write(tempMessage + "\r\n");


                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    try
                    {
                        bw.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
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

    File CreateFolder(String mPath, String[] mMessage)
    {
        mPath += "\\" + mMessage[2];

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

    void AddMessageToLog(String message, String login, Object object)
    {
        String localTime;
        {
            Date time = new Date();

            Integer seconds = time.getSeconds();
            Integer minutes = time.getMinutes();
            Integer hours = time.getHours();

            localTime = hours.toString() + ':' + minutes.toString() + ':' + seconds.toString();
        }


        synchronized (logServerQueue)
        {

            logServerQueue.offer(localTime + " " + message + " " + login + " " + object.toString() + "\r\t");
        }
    }
}
