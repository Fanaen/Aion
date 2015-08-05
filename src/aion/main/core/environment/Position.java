package aion.main.core.environment;

import java.awt.Point;
import java.util.List;
import javax.swing.JButton;

/**
 *
 * @author Fanaen
 */
public class Position extends AbstractPosition {
    
    // -- Attributes --
    private Point position;
    
    private transient List<Zone> parents;
    
    
    // -- Constructors --
    public Position(String name) {
        this.name = name;
    }
    
    // -- Methods --
    @Override
    public boolean isLeaf()
    {
        return true;
    }
    
    // -- Getters and setters --

    public Point getPosition()
    {
        return position;
    }

    public void setPosition(Point position)
    {
        this.position = position;
    }

    public String getShortcutStringCode() {
        return (this.shortcut != null) ? shortcut.getStringCode() : "";
    }

    public List<Zone> getParents() {
        return parents;
    }

    public void setParents(List<Zone> parents) {
        this.parents = parents;
    }

    public void update(JButton button) {
        int nbSecond = (int) estimatedTime;
        String time = "(" + (int) (nbSecond / 60) + "min " + (nbSecond % 60) +")";
        
        button.setText("<html>"+ name + "<br> " + count + " - " + time + "</html>");
    }

    public void addIncrementEstimatedTime() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
}
