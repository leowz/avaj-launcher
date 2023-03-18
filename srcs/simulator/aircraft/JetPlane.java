package srcs.simulator.aircraft;

import srcs.simulator.aircraft.*;
import srcs.simulator.*;
import srcs.simulator.tower.*;

public class JetPlane extends Aircraft implements Flyable
{
    protected WeatherTower weatherTower;

    public JetPlane(long p_id, String p_name, Coordinates p_coordinate)
    {
        super(p_id, p_name, p_coordinate);
    }

    public void updateConditions()
    {
    }

    public void registerTower(WeatherTower p_tower)
    {
        this.weatherTower = p_tower;
    }
}
