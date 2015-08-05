/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aion.main.core;

import java.io.Serializable;

/**
 *
 * @author Fanaen
 */
public abstract class AbstractItem implements Serializable
{
    // -- Attribute --
    protected String name;
    protected int number;
    
    // -- Getters and setters --
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
    public abstract boolean isLeaf();
}
