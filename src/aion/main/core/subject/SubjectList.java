package aion.main.core.subject;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Fanaen
 */
public class SubjectList extends AbstractSubject {
    
    // -- Attributes -- 
    private List<AbstractSubject> list = new LinkedList<>();
    private ListIterator<AbstractSubject> iterator = null;

    // -- Getters and setters --
    public List<AbstractSubject> getList() {
        return list;
    }

    public void setList(List<AbstractSubject> sequenceList) {
        this.list = sequenceList;
    }

    // -- Constructor --
    public SubjectList(String name) {
        this.name = name;
        list = new LinkedList();
    }
    
    // -- Methods --
    public void addItem(AbstractSubject item) {
        list.add(item);
    }
    
    public AbstractSubject getNextItem() {
        if(iterator == null) reset();
        
        try {
            return iterator.next();
        }
        catch (Exception e) {
            return null;
        }
    }
    
    public void reset() 
    {
        iterator = list.listIterator();
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

}
