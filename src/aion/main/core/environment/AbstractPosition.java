package aion.main.core.environment;

import aion.main.core.AbstractItem;
import aion.main.core.Shortcut;
import aion.main.core.time.AbstractSequence;

/**
 *
 * @author Fanaen
 */
public abstract class AbstractPosition extends AbstractItem {
    
    // -- Attributes --
    protected Shortcut shortcut;
    protected transient long time; // ms
    protected transient long estimatedTime; // sec
    protected transient long count;
    
    // -- Getters and setters --
    public Shortcut getShortcut()
    {
        return shortcut;
    }

    public void setShortcut(Shortcut shortcut)
    {
        this.shortcut = shortcut;
    }
    
    // -- Time methods --
    public void addTime(long time)
    {
        this.time += time;
    }
    
    public long getTime()
    {
        return time;
    }
    
    public void resetTime()
    {
        time = 0;
    }
    
    public void incrementEstimatedTime()
    {
        estimatedTime++;
    }
    
    public long getEstimatedTime()
    {
        return estimatedTime;
    }
    
    public void resetEstimatedTime()
    {
        estimatedTime = 0;
    }
    
    // -- Count Methods --
    public void incrementCount()
    {
        count++;
    }
    
    public void resetCount()
    {
        count = 0;
    }
    
    public long getCount()
    {
        return count;
    }
}
