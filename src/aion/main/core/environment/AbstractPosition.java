/*
 *  Aiôn, an enhanced multiple stopwatch designed for behavioral studies.
 *  Copyright (C) 2015  Elouan Poupard-Cosquer
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package aion.main.core.environment;

import aion.main.core.AbstractItem;
import aion.main.core.Shortcut;

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
