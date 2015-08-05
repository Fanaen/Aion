package aion.main.core.subject;

import aion.main.core.time.TimeSchema;
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
public class SubjectSchema implements Serializable {

   // -- Attributes --
    private List<AbstractSubject> list = new LinkedList<>();
    private transient Stack<FolderIterator> stack = null;
    
    // -- Methods --
    private Subject getNextAbstractItem() {
        AbstractSubject item = stack.peek().nextItem();       
            
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
            return (Subject) item;
        }
        else { // -- Get a non-leaf child --
            stack.push(new FolderIterator((SubjectList) item));
            return getNextAbstractItem();
        }
    }
    
    public Subject getNextItem() {
        
        // -- Beginning --
        if(stack == null) {
            reset();
        }
        
        return getNextAbstractItem();
    }
    
    public void reset() {
        stack = new Stack<>();
        stack.add(new SubjectSchema.FolderIterator(list));
    }
    
    // -- Class --
    public class FolderIterator {
        private List<AbstractSubject> list = null;
        private ListIterator<AbstractSubject> iterator = null;
        private SubjectList parent = null;
        private AbstractSubject currentItem = null;
        private boolean root = false;
        private int i = 0;

        private FolderIterator(List<AbstractSubject> list)
        {
            root = true;
            this.list = list;
            iterator = list.listIterator();
        }

        private FolderIterator(SubjectList itemList)
        {
            parent = itemList;
        }

        private AbstractSubject nextItem()
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
            return (currentItem instanceof Subject);
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
        
    // -- Getters and setters --
    public List<AbstractSubject> getList() {
        return list;
    }

    public void setList(List<AbstractSubject> list) {
        this.list = list;
    }
    
    public Stack<FolderIterator> getStack()
    {
        return stack;
    }
}
