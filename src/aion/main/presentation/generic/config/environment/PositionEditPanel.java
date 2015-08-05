/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aion.main.presentation.generic.config.environment;

import aion.main.core.Shortcut;
import aion.main.core.environment.Position;
import java.awt.Point;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Fanaen
 */
public class PositionEditPanel extends javax.swing.JPanel {

    // -- Attributes --
    DefaultMutableTreeNode node;
    Position item;
    PanelListener listener;

    
    
    // -- Getters and setters --
    public DefaultMutableTreeNode getNode() {
        return node;
    }

    public void setNode(DefaultMutableTreeNode node) {
        this.node = node;
    }

    public Position getItem() {
        return item;
    }

    public void setItem(Position sequence) {
        this.item = sequence;
        
        updateData();
    }
    
    
    /**
     * Creates new form PositionEditPanel
     */
    public PositionEditPanel(Position sequence, DefaultMutableTreeNode node, PanelListener listener) {
        initComponents();
        this.listener = listener;
        this.node = node;
        this.item = sequence;
        
        updateData();
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

        labelTitle = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        shortcut = new javax.swing.JTextField();
        spinnerX = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        spinnerY = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();

        labelTitle.setText("Edition de la position");

        jLabel1.setText("Raccourci");

        shortcut.setEditable(false);
        shortcut.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                shortcutActionPerformed(evt);
            }
        });
        shortcut.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                shortcutKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                shortcutKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                shortcutKeyTyped(evt);
            }
        });

        spinnerX.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), null, null, Integer.valueOf(1)));
        spinnerX.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                spinnerXStateChanged(evt);
            }
        });

        jLabel2.setText("Coordonnée X");

        spinnerY.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), null, null, Integer.valueOf(1)));
        spinnerY.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                spinnerYStateChanged(evt);
            }
        });

        jLabel3.setText("Coordonnée Y");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spinnerX)
                            .addComponent(shortcut)
                            .addComponent(spinnerY))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(shortcut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinnerX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinnerY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(25, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void shortcutActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_shortcutActionPerformed
    {//GEN-HEADEREND:event_shortcutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_shortcutActionPerformed

    private void shortcutKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_shortcutKeyTyped
    {//GEN-HEADEREND:event_shortcutKeyTyped
        
        
    }//GEN-LAST:event_shortcutKeyTyped

    private void shortcutKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_shortcutKeyReleased
    {//GEN-HEADEREND:event_shortcutKeyReleased
        
    }//GEN-LAST:event_shortcutKeyReleased

    private void shortcutKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_shortcutKeyPressed
    {//GEN-HEADEREND:event_shortcutKeyPressed
        Shortcut code = new Shortcut(evt);
        item.setShortcut(code);
        shortcut.setText(code.getStringCode());
    }//GEN-LAST:event_shortcutKeyPressed

    private void spinnerXStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_spinnerXStateChanged
    {//GEN-HEADEREND:event_spinnerXStateChanged
        Point point = item.getPosition();
        point.x = (int) spinnerX.getValue();
        item.setPosition(point);
    }//GEN-LAST:event_spinnerXStateChanged

    private void spinnerYStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_spinnerYStateChanged
    {//GEN-HEADEREND:event_spinnerYStateChanged
        Point point = item.getPosition();
        point.y = (int) spinnerY.getValue();
        item.setPosition(point);
    }//GEN-LAST:event_spinnerYStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JTextField shortcut;
    private javax.swing.JSpinner spinnerX;
    private javax.swing.JSpinner spinnerY;
    // End of variables declaration//GEN-END:variables

    // -- Methods --
    private void updateData() {
        labelTitle.setText("Edition de la position : " + item.getName());
        
        spinnerX.setValue(item.getPosition().x);
        spinnerY.setValue(item.getPosition().y);
        
        shortcut.setText(item.getShortcutStringCode());
    }
    
    public interface PanelListener {
        public void onRemove(DefaultMutableTreeNode node, Position item);
    }
}