package srcs.simulator.weatherProvider;

import srcs.simulator.*;
import java.util.*;

public class WeatherProvider
{
    private String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};
    private static WeatherProvider instance;
    private Random rand;

    private WeatherProvider()
    {
        this.rand = new Random(System.currentTimeMillis());
    }

    public static WeatherProvider getInstance()
    {
        if (instance == null)
            instance = new WeatherProvider();
        return instance;
    }

    public String chooseWeather(Coordinates p_coord)
    {
        long    seed;
        Random  rand;
        int     index;

        seed = p_coord.seedFromCoord();
        index = (int)((this.rand.nextInt(4) + seed) % 4);
        return this.weather[index];
    }

    public static String getCurrentWeather(Coordinates p_coordinates)
    {
        WeatherProvider instance = WeatherProvider.getInstance();
        return instance.chooseWeather(p_coordinates);
    }
}
