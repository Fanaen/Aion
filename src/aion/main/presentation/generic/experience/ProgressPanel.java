/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aion.main.presentation.generic.experience;

import aion.main.core.Experience;
import aion.main.core.Experience.ExperienceListener;
import aion.main.core.environment.Position;
import aion.main.core.subject.AbstractSubject;
import aion.main.core.subject.SubjectSchema;
import aion.main.core.time.Sequence;
import aion.main.core.time.TimeSchema;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author Fanaen
 */
public class ProgressPanel extends javax.swing.JPanel
{
    private List<JPanel> panelList = new LinkedList<>();
    private ProgressSubPanel lastPanel = null;
    private Experience experience;
    
    /**
     * Creates new form TimePanel
     */
    public ProgressPanel(final Experience experience)
    {
        this.experience = experience;
        
        initComponents();
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layout);
        
        
        experience.addListener(new ExperienceListener() {

            @Override
            public void onSubjectStarted()
            {
                
            }

            public void onSubjectFinished() { } 
            public void onSequenceStarted() 
            {
                removeItems();
                Stack<SubjectSchema.FolderIterator> subjectStack = experience.getSubjectSchema().getStack();
                Stack<TimeSchema.FolderIterator> sequenceStack = experience.getTimeSchema().getStack();
                boolean root = true;
                int nbItem = 0, itemNb = 0;
                
                ProgressSubPanel panel = null;
                
                // -- Compute Subjects --
                for (SubjectSchema.FolderIterator folder : subjectStack)
                {
                    panel = new ProgressSubPanel();
                    
                    nbItem = folder.size();
                    itemNb = folder.currentNb();
                    
                    panel.setTitle("Groupe de sujet - " + folder.getParentTitle());
                    panel.setProgress((float) (itemNb - 1) / nbItem, itemNb + " / " + nbItem);
                    
                    panelList.add(panel);
                    add(panel);
                    
                    if(folder.isLeaf())
                    {
                        panel = new ProgressSubPanel();
                        panel.setTitle("Sujet - " + folder.getItemTitle());
                    }
                }
                
                // -- Compute Sequences --
                for (TimeSchema.FolderIterator folder : sequenceStack)
                {
                    if(root) 
                    {
                        root = false;
                    }
                    else
                    {
                        panel = new ProgressSubPanel();
                        panel.setTitle("Groupe de séquence - " + folder.getParentTitle());
                    }
                    
                    nbItem = folder.size();
                    itemNb = folder.currentNb();

                    panel.setProgress((float) (itemNb - 1) / nbItem, itemNb + " / " + nbItem);
                    
                    panelList.add(panel);
                    add(panel);
                    
                    if(folder.isLeaf())
                    {
                        panel = new ProgressSubPanel();
                        panel.setTitle("Séquence - " + folder.getItemTitle());
                        panelList.add(panel);
                        add(panel);
                        lastPanel = panel;
                    }
                }
                
                // -- Update UI --
                updateDisplay();
            } 
            public void onSequenceFinished() { } 
            public void onMove(long time, Position position) { } 
            public void onExperienceFinished() { } 

            
            public void onSequenceChronoStarted() {
                
            }
            public void onSequenceChronoFinished() { }

            public void onEstimatedUpdate(Sequence sequence, Position position) {
                int nbSecond = (int) sequence.getEstimatedTime();
                float progress = (float) nbSecond / (float) sequence.getNbSecond();
                int percent = (int) (progress * 100);
                String time = "(" + (int) (nbSecond / 60) + "min " + (nbSecond % 60) +")";
                
                lastPanel.setProgress(progress, percent + "% - " + time);
            }
            
        });
    }
    
    // -- Methods --
    public void removeItems()
    {
        for (JPanel panel : panelList) { remove(panel); }
        panelList.clear();
    }
    
    public void updateDisplay() 
    {
        this.updateUI();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2));

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(32767, 30));
        jPanel1.setMinimumSize(new java.awt.Dimension(0, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 102));
        jLabel1.setText("Avancement");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addContainerGap(303, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 267, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}
