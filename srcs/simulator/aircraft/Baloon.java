package srcs.simulator.aircraft;

import srcs.simulator.aircraft.*;
import srcs.simulator.*;
import srcs.simulator.tower.*;
import srcs.simulator.logger.*;

public class Baloon extends Aircraft implements Flyable
{
    protected WeatherTower weatherTower;

    Baloon(long p_id, String p_name, Coordinates p_coordinate)
    {
        super(p_id, p_name, p_coordinate);
		this.type = "Baloon";
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
				Coordinates.coordFactory(oldLo + 2, oldLa, oldH + 4);
			this.speak(old_coord, new_coord, weather,
					" Let's enjoy the good weather and take some pics.");
			this.changeCoordinates(new_coord);
		}
		else if (weather.equals("RAIN"))
		{
			Coordinates old_coord = this.coordinates;
			Coordinates new_coord =
				Coordinates.coordFactory(oldLo, oldLa, oldH - 5);
			this.speak(old_coord, new_coord, weather,
					" Damn rain. You will mess up my baloon!");
			this.changeCoordinates(new_coord);
		}
		else if (weather.equals("FOG"))
		{
			Coordinates old_coord = this.coordinates;
			Coordinates new_coord =
				Coordinates.coordFactory(oldLo, oldLa, oldH - 3);
			this.speak(old_coord, new_coord, weather,
					" Big Fog, bad for my baloon, need to go down.");
			this.changeCoordinates(new_coord);
		}
		else if (weather.equals("SNOW"))
		{
			Coordinates old_coord = this.coordinates;
			Coordinates new_coord =
				Coordinates.coordFactory(oldLo, oldLa, oldH - 15);
			this.speak(old_coord, new_coord, weather,
					" It's snowning. We're gonna crash!");
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
