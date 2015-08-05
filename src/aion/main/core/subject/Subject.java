package aion.main.core.subject;

/**
 *
 * @author Fanaen
 */
public class Subject extends AbstractSubject {
    
    // -- Constructors --
    public Subject(String name) {
        this.name = name;
    }
    
    // -- Methods --
    @Override
    public boolean isLeaf()
    {
        return true;
    }
}
