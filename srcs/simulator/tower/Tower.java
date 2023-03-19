package srcs.simulator.tower;

import java.util.*;
import srcs.simulator.aircraft.*;
import srcs.simulator.logger.*;
import srcs.simulator.*;

public class Tower {
    private List<Flyable> observers = new ArrayList<Flyable>();
    private List<Flyable> obs_to_unregi = new ArrayList<Flyable>();
	String type;

	public Tower()
	{
		this.type = "Tower";
	}

    public void register(Flyable p_flyable)
    {
        this.observers.add(p_flyable);
		Logger.logLine("Tower says: " + p_flyable + " registered to " +
				this.type);
    }

    public void unregister(Flyable p_flyable)
    {
		this.obs_to_unregi.add(p_flyable);
		Logger.logLine("Tower says: " + p_flyable + " unregistered to " +
				this.type);
    }
    
    protected void conditionChanged()
    {
		int	size;
		int i;
		Flyable flyObj;
		
		size = this.observers.size();
		i = 0;
		while (i < size)
		{
			flyObj = this.observers.get(i);
            flyObj.updateConditions();
			i++;
		}
		while (!this.obs_to_unregi.isEmpty())
		{
			Flyable obj = this.obs_to_unregi.remove(0);
			this.observers.remove(obj);
		}
    }
}

