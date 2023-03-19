package srcs.simulator.aircraft;

import srcs.simulator.*;
import srcs.simulator.aircraft.*;
import srcs.simulator.tower.*;
import srcs.simulator.logger.*;

public class Helicopter extends Aircraft implements Flyable
{
    protected WeatherTower weatherTower;

    public Helicopter(long p_id, String p_name, Coordinates p_coordinate)
    {
        super(p_id, p_name, p_coordinate);
		this.type = "Helicopter";
    }

    public void updateConditions()
    {
        String weather = this.weatherTower.getWeather(this.coordinates);
		int oldLa = this.coordinates.getLatitude();
		int oldLo = this.coordinates.getLongitude();
		int oldH = this.coordinates.getHeight();

		if (weather.equals("SUN"))
		{
			Coordinates old_coord = this.coordinates;
			Coordinates new_coord =
				Coordinates.coordFactory(oldLo + 10, oldLa, oldH + 2);
			this.speak(old_coord, new_coord, weather,
					" This is hot.");
			this.changeCoordinates(new_coord);
		}
		else if (weather.equals("RAIN"))
		{
			Coordinates old_coord = this.coordinates;
			Coordinates new_coord =
				Coordinates.coordFactory(oldLo + 5, oldLa, oldH);
			this.speak(old_coord, new_coord, weather,
					" A good raining day to fly helicopter.");
			this.changeCoordinates(new_coord);
		}
		else if (weather.equals("FOG"))
		{
			Coordinates old_coord = this.coordinates;
			Coordinates new_coord =
				Coordinates.coordFactory(oldLo + 1, oldLa, oldH);
			this.speak(old_coord, new_coord, weather,
					" No worry about the fog. I will keep the heli steady.");
			this.changeCoordinates(new_coord);
		}
		else if (weather.equals("SNOW"))
		{
			Coordinates old_coord = this.coordinates;
			Coordinates new_coord =
				Coordinates.coordFactory(oldLo, oldLa, oldH - 12);
			this.speak(old_coord, new_coord, weather,
					" My rotor is going to freeze!");
			this.changeCoordinates(new_coord);
		}
    }

	public void land()
	{
		super.land();
		weatherTower.unregister(this);
	}

    public void registerTower(WeatherTower p_tower)
    {
        this.weatherTower = p_tower;
        this.weatherTower.register(this);
    }
}
