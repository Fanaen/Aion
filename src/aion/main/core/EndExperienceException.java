/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aion.main.core;

/**
 *
 * @author Fanaen
 */
public class EndExperienceException extends Exception {

    /**
     * Creates a new instance of
     * <code>EndExperienceException</code> without detail message.
     */
    public EndExperienceException() {
        
    }

    /**
     * Constructs an instance of
     * <code>EndExperienceException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public EndExperienceException(String msg) {
        super(msg);
    }
}
