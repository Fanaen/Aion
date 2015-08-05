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

package aion.main.presentation.generic;

import aion.main.core.Experience;
import aion.main.presentation.MainView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Fanaen
 */
public class ExperienceSavePanel extends BuilderPanel 
{

    private static class ExperienceSaveException extends Exception {
        String code;
        public ExperienceSaveException(String code) {
            this.code = code;
        }

        @Override
        public String getMessage() {
            return code;
        }        
    }
    
    // -- Attributes --
    private Experience experience;
    private String path = "";
    
    /**
     * Creates new form ExperienceSavePanel
     */
    public ExperienceSavePanel(Experience experience) {
        initComponents();
        
        this.experience = experience;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        nameTextBox = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        datasetTextBox = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Nom de l'expérience");

        nameTextBox.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                nameTextBoxCaretUpdate(evt);
            }
        });
        nameTextBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nameTextBoxKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nameTextBoxKeyTyped(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(204, 0, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Veuillez ne pas mettre de caractères spéciaux.");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Nom du jeu de données");

        datasetTextBox.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                datasetTextBoxCaretUpdate(evt);
            }
        });
        datasetTextBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                datasetTextBoxKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                datasetTextBoxKeyTyped(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(204, 0, 51));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Veuillez ne pas mettre de caractères spéciaux.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(nameTextBox))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(datasetTextBox))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(datasetTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap(31, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nameTextBoxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameTextBoxKeyTyped
        
    }//GEN-LAST:event_nameTextBoxKeyTyped

    private void nameTextBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameTextBoxKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameTextBoxKeyPressed

    private void nameTextBoxCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_nameTextBoxCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_nameTextBoxCaretUpdate

    private void datasetTextBoxCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_datasetTextBoxCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_datasetTextBoxCaretUpdate

    private void datasetTextBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_datasetTextBoxKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_datasetTextBoxKeyPressed

    private void datasetTextBoxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_datasetTextBoxKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_datasetTextBoxKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField datasetTextBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField nameTextBox;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onNext() {
       try {
            if(nameTextBox.getText().matches("^[a-zA-Z0-9 ]+$") && datasetTextBox.getText().matches("^[a-zA-Z0-9 ]+$") ) { // Valid text --

                createExpDirectory(); 
                saveExperience();
                runExperience();
                finish();
            }
            else {
                Logger.getLogger("MyLog").log(Level.INFO,  "Erreur de nommage.");
                (new JOptionPane()).showMessageDialog(null, 
                        "Veuillez ne pas mettre de caractères spéciaux dans les noms.", 
                        "Nom incorrect", 
                        JOptionPane.ERROR_MESSAGE);
            }
       }
       catch(ExperienceSaveException ex)
       {
           Logger.getLogger("MyLog").log(Level.SEVERE, ex.getMessage(), ex);
           (new JOptionPane()).showMessageDialog(null, 
                        ex.getMessage(), 
                        "Erreur de dossier", 
                        JOptionPane.ERROR_MESSAGE);
       }
    }
    
    public void runExperience() {
        ExperienceView view = new ExperienceView(experience);
        MainView.addFrame(view);
    }

    private void createExpDirectory() throws ExperienceSaveException {
        
        // -- Base directory --
        File directory = new File("Data");
        if(!directory.exists() || !directory.isDirectory()) directory.mkdir();
        
        if(!directory.exists() || !directory.isDirectory()) {
            throw new ExperienceSaveException("Le dossier Data n'existe pas et n'a pas pu être créé.");
        } 
        
        // -- Experience directory --
        Calendar today = Calendar.getInstance();
        String name =
                today.get(Calendar.YEAR) +
                String.format("%02d", today.get(Calendar.MONTH)) +
                String.format("%02d", today.get(Calendar.DAY_OF_MONTH)) +
                " " + nameTextBox.getText();
        path = "Data/"+name;
        experience.setName(name);
        experience.setDataset(datasetTextBox.getText());
        
        File expDirectory = new File(path);
        
        // -- Check if the directory exists, and create it --
        if(expDirectory.exists()) {
            throw new ExperienceSaveException("Le dossier "+ path +" existe déjà.");
        }
        else {
            if(!expDirectory.mkdir()) {
                throw new ExperienceSaveException( "Le dossier "+ path +" n'a pas pu être créé.");
            }
        }
    }

    private void saveExperience() {
        try {
            FileOutputStream fichier = new FileOutputStream(path+"/Original.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fichier);
            oos.writeObject(experience);
            oos.flush();
            oos.close();
        }
        catch (java.io.IOException ex) {
             Logger.getLogger("MyLog").log(Level.SEVERE, null, ex);
        }
    }
}
