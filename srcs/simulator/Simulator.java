package srcs.simulator;

import java.lang.*;
import java.util.*;
import java.io.*;
import srcs.simulator.logger.*;
import srcs.simulator.aircraft.*;
import srcs.simulator.tower.*;
import srcs.simulator.weatherProvider.*;
import srcs.simulator.*;

public class Simulator
{
    private static WeatherTower weatherTower;
    private static List<Flyable> flyables = new ArrayList<Flyable>();

    public static void main(String args[]) throws InterruptedException
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            String line = reader.readLine();
            if (line != null)
            {
                weatherTower = new WeatherTower();
                int simulations = Integer.parseInt(line.split(" ")[0]);
                if (simulations < 0)
                {
                    System.out.println("Invalid simuations count "
                            + simulations);
                    System.exit(1);
                }
                while ((line = reader.readLine()) != null)
                {
                    Coordinates p_coordinates = new Coordinates(
                        Integer.parseInt(line.split(" ")[2]),
                        Integer.parseInt(line.split(" ")[3]), 
                        Integer.parseInt(line.split(" ")[4]));

                    Flyable flyable = AircraftFactory.newAircraft
                    (line.split(" ")[0], line.split(" ")[1], p_coordinates);
                    flyables.add(flyable);
                }

                for (Flyable flyable: flyables)
                {
                   flyable.registerTower(weatherTower);
                }

                int i = 1;
                while (i <= simulations)
                {
                   weatherTower.changeWeather(); 
                   i++;
                }
            }
        }
        catch (FileNotFoundException e)
        {
           System.out.println("Couldn't find file " + args[0]);
        }
        catch (IOException e)
        {
           System.out.println("There was an error while reading the file"
                   + args[0]);
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
           System.out.println("Specify simulation file");
        }
        finally
        {
            Logger.closeFile();
        }
    }
}
