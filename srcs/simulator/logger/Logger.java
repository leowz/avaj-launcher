package srcs.simulator.logger;

import java.io.*;

public class Logger
{
    private static Logger instance;
    private BufferedWriter writer;

    private Logger() {
        String path = "simulation.txt";
        File file;
        try
        {
            file = new File(path);
            file.delete();
            this.writer = new BufferedWriter(new FileWriter(file, true));
        }
        catch (IOException e)
        {
            System.out.println("Error: open file fail");
        }
    }

    public static void logLine(String msg)
    {
		Logger logger = Logger.getInstance();
		try
		{
			logger.writer.write(msg);
			logger.writer.newLine();
		}
		catch (IOException e)
		{
			System.out.println("Error: Could not write to file");
		}
    }

    public static void closeFile()
    {
		Logger logger = Logger.getInstance();
        try
        {
            logger.writer.close();
        }
        catch (IOException e)
        {
           System.out.println("Error: Close file error");
        }
    }

    public static Logger getInstance()
    {
        if (instance == null)
        {
           instance = new Logger();
        }
        return instance;
    }
}
