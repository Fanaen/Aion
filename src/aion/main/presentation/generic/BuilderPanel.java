/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aion.main.presentation.generic;

import javax.swing.JPanel;

/**
 *
 * @author Fanaen
 */
public abstract class BuilderPanel extends JPanel
{
    // -- Attributes --
    private ExperienceBuilderFrame builderFrame;
    
    // -- Methods --
    protected void nextPanel(BuilderPanel panel)
    {
        builderFrame.setBuilderPanel(panel);
    }
    
    protected void finish()
    {
        builderFrame.close();
    }
    
    // -- Abstract methods --
    public abstract void onNext();
    
    // -- Getters and setters --
    public ExperienceBuilderFrame getBuilderFrame()
    {
        return builderFrame;
    }

    public void setBuilderFrame(ExperienceBuilderFrame builderFrame)
    {
        this.builderFrame = builderFrame;
    }
    
}
