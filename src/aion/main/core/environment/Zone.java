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

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Fanaen
 */
public class Zone extends AbstractPosition {
    
    // -- Attributes -- 
    private List<AbstractPosition> list = new LinkedList<>();
    private ListIterator<AbstractPosition> iterator = null;

    // -- Getters and setters --
    public List<AbstractPosition> getList() {
        return list;
    }

    public void setList(List<AbstractPosition> sequenceList) {
        this.list = sequenceList;
    }
    

    // -- Constructor --
    public Zone(String name) {
        this.name = name;
        list = new LinkedList();
    }
    
    // -- Methods --
    public void addItem(AbstractPosition item) {
        list.add(item);
    }
    
    public AbstractPosition getNextItem() {
        if(iterator == null) iterator = list.listIterator();
        
        try {
            return iterator.next();
        }
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean isLeaf()
    {
        return false;
    }

}
