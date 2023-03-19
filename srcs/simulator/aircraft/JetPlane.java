package srcs.simulator.aircraft;

import srcs.simulator.aircraft.*;
import srcs.simulator.*;
import srcs.simulator.tower.*;
import srcs.simulator.logger.*;

public class JetPlane extends Aircraft implements Flyable
{
    protected WeatherTower weatherTower;

    public JetPlane(long p_id, String p_name, Coordinates p_coordinate)
    {
        super(p_id, p_name, p_coordinate);
		this.type = "JetPlane";
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
				Coordinates.coordFactory(oldLo, oldLa + 10, oldH + 2);
			this.speak(old_coord, new_coord, weather,
					" Good nice weather to fly jet!");
			this.changeCoordinates(new_coord);
		}
		else if (weather.equals("RAIN"))
		{
			Coordinates old_coord = this.coordinates;
			Coordinates new_coord =
				Coordinates.coordFactory(oldLo, oldLa + 5, oldH);
			this.speak(old_coord, new_coord, weather,
					" It's raining. Better watch out for lightings.");
			this.changeCoordinates(new_coord);
		}
		else if (weather.equals("FOG"))
		{
			Coordinates old_coord = this.coordinates;
			Coordinates new_coord =
				Coordinates.coordFactory(oldLo, oldLa + 1, oldH);
			this.speak(old_coord, new_coord, weather,
					" It's foggy. But my jet goes out of it very easily.");
			this.changeCoordinates(new_coord);
		}
		else if (weather.equals("SNOW"))
		{
			Coordinates old_coord = this.coordinates;
			Coordinates new_coord =
				Coordinates.coordFactory(oldLa, oldLo, oldH - 7);
			this.speak(old_coord, new_coord, weather,
					" OMG! Winter is coming!");
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
