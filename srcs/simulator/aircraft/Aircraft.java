package srcs.simulator.aircraft;

import srcs.simulator.*;
import srcs.simulator.aircraft.*;

public class Aircraft
{
    protected long          id;
    protected String        name;
    protected Coordinates   coordinates;

    protected Aircraft(long p_id, String p_name, Coordinates p_coordinate)
    {
        this.id = p_id;
        this.name = p_name;
        this.coordinates = p_coordinate;
    }

    public String toString()
    {
       return "|* " + this.id + ": " + this.name + ", " + this.coordinates
           + " *|";
    }
}
