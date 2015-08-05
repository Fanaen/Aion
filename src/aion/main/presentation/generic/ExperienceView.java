/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aion.main.presentation.generic;

import aion.main.core.EndExperienceException;
import aion.main.core.Experience;
import aion.main.core.environment.Position;
import aion.main.presentation.generic.experience.GenericExperiencePanel;
import java.awt.BorderLayout;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.InternalFrameListener;

/**
 *
 * @author Fanaen
 */
public class ExperienceView extends javax.swing.JInternalFrame {

    // -- Attributes --
    private GenericExperiencePanel genericPanel;
    private Experience experience;
    
    /**
     * Creates new form ExperienceView
     */
    public ExperienceView(Experience experience)
    {
        this.experience = experience;
        genericPanel = new GenericExperiencePanel(experience);
        
        initComponents();
        setLayout(new BorderLayout());
        add(genericPanel, BorderLayout.CENTER);
        
        try {
            experience.prepareExperience(true);
        } catch (EndExperienceException ex) {
            Logger.getLogger("MyLog").log(Level.SEVERE, null, ex);
        }
        
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();

        jMenu1.setText("jMenu1");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Experience");
        setVerifyInputWhenFocusTarget(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 546, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 465, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        
    }//GEN-LAST:event_formKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    // End of variables declaration//GEN-END:variables

    // -- Getters and setters --

    
}