/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aion.main.presentation.generic;

import aion.main.core.Experience;
import aion.main.presentation.MainView;
import aion.main.presentation.generic.experience.SimulationCore;
import aion.main.presentation.generic.experience.SimulationView;
import java.io.File;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Fanaen
 */
public class DataSetListPanel extends BuilderPanel {
    
    // -- Attributes --
    private Experience experience;
    
    /**
     * Creates new form DataSetListPanel
     */
    public DataSetListPanel(Experience experience) {
        initComponents();
        this.experience = experience;
        
        // -- Base directory --
        File directory = new File("Data/"+experience.getName());
        if(!directory.exists() || !directory.isDirectory()) return;
        
        // -- List initialisation --
        DefaultListModel listModel = new DefaultListModel();
        
        File[] subfiles = directory.listFiles();
        for(int i=0 ; i<subfiles.length; i++) {
            if(subfiles[i].isDirectory()) listModel.addElement(subfiles[i].getName());
        }
        listModel.addElement("+ Nouveau jeu de données");
        
        datasetList.setModel(listModel);
        datasetList.setSelectedIndex(0);
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
        datasetList = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        jScrollPane1.setViewportView(datasetList);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Jeu de données");

        jLabel2.setText("Utiliser un jeu existant pour le re-synthetiser");

        jLabel3.setText("Réaliser un nouveau jeu de données");

        jLabel4.setText("Vous pouvez :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel4))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addContainerGap(229, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList datasetList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onNext() {
        String dataset = (String) datasetList.getSelectedValue();
        
        if(dataset.equals("+ Nouveau jeu de données")) // Create a new dataset
        {
            String name = JOptionPane.showInputDialog(this, "Entrez le nom du jeu de données");
            experience.setDataset(name);
            
            ExperienceView view = new ExperienceView(experience);
            MainView.addFrame(view);
        }
        else // Reuse a previous dataset --
        {
            experience.setDataset(dataset);
            SimulationCore simulation = new SimulationCore(experience);
            SimulationView view = new SimulationView(simulation);
            MainView.addFrame(view);
        }
        
        finish();
    }
}