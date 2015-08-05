/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aion.main.presentation.generic;

import aion.main.core.Experience;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Fanaen
 */
public class VersionListPanel extends BuilderPanel {

    // -- Attributes --
    private String name;
    
    /**
     * Creates new form VersionListPanel
     */
    public VersionListPanel(String name) {
        initComponents();
        this.name = name;
        
        // -- Base directory --
        File directory = new File("Data/"+name);
        if(!directory.exists() || !directory.isDirectory()) return;
        
        // -- List initialisation --
        DefaultListModel listModel = new DefaultListModel();
        
        File[] subfiles = directory.listFiles();
        for(int i=0 ; i<subfiles.length; i++) {
            if(subfiles[i].isFile()) {
                String subfileName = subfiles[i].getName();
                listModel.addElement(subfileName.replaceAll("\\.ser$", ""));
            }
        }
        listModel.addElement("+ Nouvelle version");
        versionList.setModel(listModel);
        versionList.setSelectedIndex(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        versionList = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jScrollPane1.setViewportView(versionList);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Version de la configuration");

        jLabel2.setText("Vous pouvez en réutiliser une ou en créer une nouvelle.");

        jLabel3.setText("La version détermine le découpage temporel utilisé dans le fichier de résultat.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(8, 8, 8)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList versionList;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onNext() {
        String version = (String) versionList.getSelectedValue();
        Experience experience = null;
        
        try {
            if(version.equals("+ Nouvelle version")) // Create a new version
            {
                experience = deserialisation("Data/"+name+"/Original.ser");
                NewVersionPanel panel = new NewVersionPanel(experience);
                nextPanel(panel);
            }
            else // Reuse a previous version --
            {
                experience = deserialisation("Data/"+name+"/"+version+".ser");
                DataSetListPanel panel = new DataSetListPanel(experience);
                nextPanel(panel);
            }
        }
        catch(Exception e)
        {
            (new JOptionPane()).showMessageDialog(null, 
                    "La version de l'application ne correspond pas ou bien l'objet est corrompu.", 
                    "Impossible de lire le fichier", 
                    JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    private Experience deserialisation(String string) {
        Experience experience = null;
        try {
            FileInputStream fichier = new FileInputStream(string);
            ObjectInputStream ois = new ObjectInputStream(fichier);
            experience = (Experience) ois.readObject();
        }
        catch (java.io.IOException ex) {
            Logger.getLogger("MyLog").log(Level.SEVERE, null, ex); 
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger("MyLog").log(Level.SEVERE, null, ex); 
        }
        
        return experience;
    }
}