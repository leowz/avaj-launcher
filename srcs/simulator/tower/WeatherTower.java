package srcs.simulator.tower;

import java.util.*;
import srcs.simulator.tower.*;
import srcs.simulator.aircraft.*;
import srcs.simulator.*;
import srcs.simulator.weatherProvider.*;

public class WeatherTower extends Tower {

	public WeatherTower()
	{
		this.type = "WeatherTower";
	}

    public String getWeather(Coordinates p_coordinates)
    {
        String weather = WeatherProvider.getCurrentWeather(p_coordinates);
        return weather;
    }

    public void changeWeather()
    {
        this.conditionChanged();
    }
}

