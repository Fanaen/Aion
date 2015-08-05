package aion.main.core.environment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
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
public class EnvironmentSchema implements Serializable {
    
    // -- Attributes --
    private List<AbstractPosition> list = new LinkedList<>();
    private transient Stack<FolderIterator> stack = null;
    
    // -- Methods --
    private Position getNextAbstractItem() { return (Position) getNextAbstractItem(false); }
    private AbstractPosition getNextAbstractItem(boolean folder) {
        AbstractPosition item = stack.peek().nextItem();       
            
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
            Position position = (Position) item;
            
            // -- Set parent list --
            List<Zone> parents = new ArrayList<>();
            for (Iterator<FolderIterator> it = stack.iterator(); it.hasNext();) {
                FolderIterator folderIt = it.next();
                if(!folderIt.root)
                {
                    parents.add(folderIt.parent);
                }                
            }
            position.setParents(parents);
            return position;
        }
        else { // -- Get a non-leaf child --
            stack.add(new FolderIterator((Zone) item));
            
            if(folder)
                return item;
            else
                return getNextAbstractItem();
                
        }
    }
    
    public Position getNextItem() {
        
        // -- Beginning --
        if(stack == null) {
            reset();
        }
        
        return getNextAbstractItem();
    }

    public AbstractPosition getNextAbstractPosition() {
        // -- Beginning --
        if(stack == null || stack.isEmpty()) {
            stack = new Stack<>();
            stack.add(new FolderIterator(list.listIterator()));
        }
        
        return getNextAbstractItem(true);
    }

    // -- Cleaning methods --
    public void cleanResultData() {
        recursiveCleaning(list);
    }
    
    private void recursiveCleaning(List<AbstractPosition> list) {
        for (Iterator<AbstractPosition> it = list.iterator(); it.hasNext();) {
            AbstractPosition abstractPosition = it.next();
            
            abstractPosition.resetCount();
            abstractPosition.resetEstimatedTime();
            abstractPosition.resetTime();
            
            if(abstractPosition instanceof Zone)
            {
                Zone zone = (Zone) abstractPosition;
                recursiveCleaning(zone.getList());
            }
        }
    }

    public void reset() {
        stack = new Stack<>();
        stack.add(new FolderIterator(list.listIterator()));
    }
    
    // -- Class --
    private class FolderIterator {
        private ListIterator<AbstractPosition> iterator = null;
        private Zone parent = null;
        private AbstractPosition currentItem = null;
        private boolean root = false;

        private FolderIterator(ListIterator<AbstractPosition> listIterator)
        {
            root = true;
            iterator = listIterator;
        }

        private FolderIterator(Zone itemList)
        {
            parent = itemList;
        }

        private AbstractPosition nextItem()
        {
            currentItem = null;
            
            try {
                if(root) {
                    currentItem = (iterator.hasNext()) ? iterator.next() : null;
                }
                else { 
                    currentItem = parent.getNextItem();
                }
            }
            catch(Exception e) 
            {
                Logger.getLogger("MyLog").log(Level.SEVERE, null, e);
            }
            
            return currentItem;
        }
    }
        
    // -- Getters and setters --
    public List<AbstractPosition> getList() {
        return list;
    }

    public void setList(List<AbstractPosition> list) {
        this.list = list;
    }
}
