package srcs.simulator.aircraft;

import srcs.simulator.*;
import srcs.simulator.aircraft.*;
import java.util.*;
import java.lang.*;

public class AircraftFactory
{
    private static long idCounter;
    private static AircraftFactory instance;
    private String[] aircraftTypes =
    {"JetPlane", "Helicopter", "Baloon"};

    private AircraftFactory()
    {
        this.idCounter = 0;
    }

    private Flyable produce(String p_type, String p_name,
            Coordinates p_coordinates)
    {
        int i = 0;
        while (i < 3)
        {
            String type = this.aircraftTypes[i];
            if (type.equals(p_type))
            {
                this.idCounter++;
                if (i == 0)
                {
                    return new JetPlane(this.idCounter, p_name,
                        p_coordinates);
                }
                else if (i == 1)
                {
                    return new Helicopter(this.idCounter, p_name,
                        p_coordinates);
                }
                else if (i == 2)
                {
                    return new Baloon(this.idCounter, p_name,
                        p_coordinates);
                }
            }
            i++;
        }
        return null;
    }

    public static AircraftFactory getInstance()
    { 
        if (instance == null)
            instance = new AircraftFactory();
        return instance;
    }


    public static Flyable newAircraft(String p_type, String p_name,
            Coordinates p_coordinates)
    {
        AircraftFactory factory = AircraftFactory.getInstance();
        Flyable flyable = factory.produce(p_type, p_name, p_coordinates);
        return flyable;
    }
}
