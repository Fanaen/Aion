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

package aion.main.core;

import java.awt.event.KeyEvent;
import java.io.Serializable;

/**
 *
 * @author Fanaen
 */
public class Shortcut implements Serializable
{
    // -- Attributes --
    private boolean ctrl;
    private boolean alt;
    private boolean shift;
    private int code;
    
    // -- Constructor --
    public Shortcut() {
        ctrl = false;
        alt = false;
        shift = false;
        code = 0;
    }
    
    public Shortcut(KeyEvent evt) {        
        ctrl = evt.isControlDown();
        alt = evt.isAltDown();
        shift = evt.isShiftDown();
        code = evt.getKeyCode();
    }
    
    // -- Methods --
    public String getStringCode()
    {
        String str = "";
        if(ctrl) str += "Ctrl;";
        if(alt) str += "Alt;";
        if(shift) str += "Shift;";
        
        if(code != KeyEvent.VK_CONTROL && code != KeyEvent.VK_SHIFT && code != KeyEvent.VK_ALT)
        {
            str += KeyEvent.getKeyText(code);
        }
        
        return str;
    }
    
    public boolean equals(KeyEvent evt)
    {
        return ( 
            (evt.isControlDown() == ctrl) &&
            (evt.isAltDown() == alt) &&
            (evt.isShiftDown() == shift) && 
            (evt.getKeyCode() == code) 
        );
    }
}
