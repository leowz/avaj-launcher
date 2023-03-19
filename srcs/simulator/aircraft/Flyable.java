package srcs.simulator.aircraft;

import srcs.simulator.tower.*;

abstract public interface Flyable
{
    public abstract void updateConditions();
    public void registerTower(WeatherTower p_tower);
	public String toString();
}
