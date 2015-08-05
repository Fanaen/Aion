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
