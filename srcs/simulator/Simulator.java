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

    static void checkRange(String key, int val, String line)
            throws CustomValidationException
    {
        if (key.equals("Longitude"))
        {
            if (val < 0 || val > 180)
                throw new CustomValidationException(line + "\n" +
                        "Longitude out of range, shold be between 0 - 180.");
        }
        else if (key.equals("Latitude"))
        {
            if (val < 0 || val > 90)
                throw new CustomValidationException(line + "\n" +
                    "Latitude out of range, shold be between 0 - 90.");
        }
        else if (key.equals("Height"))
        {
            if (val < 0 || val > 100)
                throw new CustomValidationException(line + "\n" +
                    "Height out of range, shold be between 0 - 100.");
        }
    }

    static void checkAirCraftType(String type, String line)
            throws CustomValidationException
    {
        if (!type.equals("Baloon") && !type.equals("Helicopter") &&
                !type.equals("JetPlane"))
                throw new CustomValidationException(line + "\n" +
                    "Aircraft type not recognised. " + 
                     "Should be one of (Baloon|Helicopter|JetPlane)");
    }

    public static void main(String args[]) throws Exception
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
                    System.out.println("Invalid simulation count: "
                            + simulations);
                    System.exit(1);
                }
                while ((line = reader.readLine()) != null)
                {
                    int longitude, latitude, height;
                    String type, name;

                    type = line.split(" ")[0];
                    name = line.split(" ")[1];
                    Simulator.checkAirCraftType(type, line);
                    longitude = Integer.parseInt(line.split(" ")[2]);
                    Simulator.checkRange("Longitude", longitude, line);
                    latitude = Integer.parseInt(line.split(" ")[3]);
                    Simulator.checkRange("Latitude", latitude, line);
                    height = Integer.parseInt(line.split(" ")[4]);
                    Simulator.checkRange("Height", height, line);
                    Coordinates p_coordinates = new Coordinates
                    (longitude, latitude, height);

                    Flyable flyable = AircraftFactory.newAircraft
                    (type, name, p_coordinates);
                    flyables.add(flyable);
                }

                for (Flyable flyable: flyables)
                {
                   flyable.registerTower(weatherTower);
                }

                int i = 0;
                while (i < simulations)
                {
                   weatherTower.changeWeather(); 
                   i++;
                }
            }
        }
        catch (NumberFormatException e)
        {
           System.out.println("Could not found number in correct position.");
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
        catch (CustomValidationException e)
        {
           System.out.println(e.getMessage());
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
           System.out.println("Please provide correct simulation file");
        }
        finally
        {
            Logger.closeFile();
        }
    }
}
