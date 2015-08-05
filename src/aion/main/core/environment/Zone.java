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
