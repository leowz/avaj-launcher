package srcs.simulator.aircraft;

import srcs.simulator.*;
import srcs.simulator.aircraft.*;
import srcs.simulator.logger.*;

public class Aircraft
{
    protected long          id;
    protected String        name;
    protected Coordinates   coordinates;
	protected String		type;

    protected Aircraft(long p_id, String p_name, Coordinates p_coordinate)
    {
        this.id = p_id;
        this.name = p_name;
        this.coordinates = p_coordinate;
        this.type = "Aircraft";
    }

	public void changeCoordinates(Coordinates coord)
	{
		int longitude = coord.getLongitude();
		int latitude = coord.getLatitude();
		int height = coord.getHeight();

		if (longitude > 180)
			longitude = 180;
		if (longitude < 0)
			longitude = 0;
		if (latitude > 90)
			latitude = 90;
		if (latitude < 0)
			latitude = 0;
		if (height > 100)
			height = 100;
		if (height < 0)
			height = 0;
		Coordinates new_coord =
			Coordinates.coordFactory(longitude, latitude, height);
		this.coordinates = new_coord;
		if (this.coordinates.getHeight() < 1)
			this.land();
	}

	public void land()
	{
		Logger.logLine(this + " landing.");
	}

	public void speak(Coordinates old_coord, Coordinates new_coord,
			String weather, String msg)
	{
		Logger.logLine(this + ": " + "At" + old_coord +
				" Weather(" + weather + ")"  +
				msg + " Prepare to move to " + new_coord);
	}

    public String toString()
    {
       return this.type + "#" + this.name + "(" + this.id + ")";
    }
}
