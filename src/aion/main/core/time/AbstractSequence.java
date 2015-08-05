package aion.main.core.time;

import aion.main.core.AbstractItem;

/**
 *
 * @author Fanaen
 */
public abstract class AbstractSequence extends AbstractItem {
    
    // -- Attribute --
    private boolean autoStart;
    
    // -- Getters and setters --
    public boolean isAutoStart() {
        return autoStart;
    }

    public void setAutoStart(boolean autoStart) {
        this.autoStart = autoStart;
    }
}
