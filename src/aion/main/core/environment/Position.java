/*
 *  Aiôn, an enhanced multiple stopwatch designed for behavioral studies.
 *  Copyright (C) 2015  Elouan Poupard-Cosquer
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
