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

package aion.main.core.time;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Fanaen
 */
public class SequenceList extends AbstractSequence {
    
    // -- Attributes -- 
    private List<AbstractSequence> list = new LinkedList<>();
    private ListIterator<AbstractSequence> iterator = null;

    // -- Getters and setters --
    public List<AbstractSequence> getList() {
        return list;
    }

    public void setList(List<AbstractSequence> sequenceList) {
        this.list = sequenceList;
    }
    

    // -- Constructor --
    public SequenceList(String name) {
        this.name = name;
        list = new LinkedList();
    }
    
    // -- Methods --
    public void addItem(AbstractSequence item) {
        list.add(item);
    }
    
    public AbstractSequence getNextItem() {
        if(iterator == null) reset();
        
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

    int size()
    {
        return list.size();
    }

    public void reset() {
        iterator = list.listIterator();
    }
    
}
