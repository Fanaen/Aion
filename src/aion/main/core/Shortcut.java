/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
