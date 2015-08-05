package aion.main.core.time;

/**
 *
 * @author Fanaen
 */
public class Sequence extends AbstractSequence {
    
    // -- Attributes --
    private int nbSecond;
    private transient long estimatedTime;
    

    // -- Constructors --
    public Sequence(String name) {
        this.name = name;
    }
    
    // -- Methods --
    @Override
    public boolean isLeaf()
    {
        return true;
    }
    
    // -- Getters and setters --
    public int getNbSecond() {
        return nbSecond;
    }

    public void setNbSecond(int nbSecond) {
        this.nbSecond = nbSecond;
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

}
