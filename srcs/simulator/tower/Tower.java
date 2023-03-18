package srcs.simulator.tower;

import java.util.*;
import srcs.simulator.aircraft.*;
import srcs.simulator.*;

public class Tower {
    private ArrayList<Flyable> observers = new ArrayList<Flyable>();

    public void register(Flyable p_flyable)
    {
        this.observers.add(p_flyable);
    }

    public void unregister(Flyable p_flable)
    {
        this.observers.remove(p_flable);
    }
    
    protected void conditionChanged()
    {
        for (Flyable flyObj: this.observers)
        {
            flyObj.updateConditions();
        }
    }
}

