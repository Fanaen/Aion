package aion.main.core.time;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fanaen
 */
public class TimeSchema implements Serializable {

    // -- Attributes --
    private List<AbstractSequence> list = new LinkedList<>();
    private transient Stack<FolderIterator> stack = null;
    
    // -- Methods --
    private Sequence getNextAbstractItem() {
        AbstractSequence item = stack.peek().nextItem();
            
        if(item == null) { // -- End of folder --
            stack.pop();
            
            if(stack.empty()) {
                return null; // -- End of the whole list --
            }
            else {
                return getNextAbstractItem();
            }
        }
        else if(item.isLeaf()) { // -- Get a leaf child --
            return (Sequence) item;
        }
        else { // -- Get a non-leaf child --
            stack.add(new FolderIterator((SequenceList) item));
            return getNextAbstractItem();
        }
    }
    
    public Sequence getNextItem() {
        
        // -- Beginning --
        if(stack == null) {
            reset();
        }
        else if(stack.isEmpty())
        {
            reset();
        }
        
        return getNextAbstractItem();
    }

    public void reset() {
        stack = new Stack<>();
        stack.add(new FolderIterator(list));
    }
    
    // -- Class --
    public class FolderIterator {
        private List<AbstractSequence> list = null;
        private ListIterator<AbstractSequence> iterator = null;
        private SequenceList parent = null;
        private AbstractSequence currentItem = null;
        private boolean root = false;
        private int i = 0;

        private FolderIterator(List<AbstractSequence> list)
        {
            root = true;
            this.list = list;
            iterator = list.listIterator();
        }

        private FolderIterator(SequenceList itemList)
        {
            parent = itemList;
            parent.reset();
        }

        private AbstractSequence nextItem()
        {
            currentItem = null;
            
            try {
                if(root) {
                    currentItem = (iterator.hasNext()) ? iterator.next() : null;
                }
                else { 
                    currentItem = parent.getNextItem();
                }
                if(currentItem !=null) i++;
            }
            catch(Exception ex) {
                Logger.getLogger("MyLog").log(Level.SEVERE, null, ex);
            }
            
            return currentItem;
        }
        
        // -- Information --
        public String getParentTitle()
        {
            return (root) ? "Liste des sujets" : parent.getName();
        }
        
        public String getItemTitle()
        {
            return currentItem.getName();
        }
        
        public boolean isLeaf()
        {
            return (currentItem instanceof Sequence);
        }
        
        public int size()
        {
            return (root) ? list.size() : parent.size();
        }

        public int currentNb()
        {
            return i;
        }
    }
    
    // -- Listeners --
    private class ChronoListener implements Chrono.Listener {

        @Override
        public Sequence onSequenceFinished(Sequence sequence) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    // -- Getters and setters --
    public List<AbstractSequence> getList() {
        return list;
    }

    public void setList(List<AbstractSequence> list) {
        this.list = list;
    }

    public Stack<FolderIterator> getStack()
    {
        return stack;
    }
}
