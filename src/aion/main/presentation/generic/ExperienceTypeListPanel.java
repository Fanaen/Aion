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

/**
 *
 * @author Fanaen
 */
public class ExperienceTypeListPanel extends BuilderPanel
{

    /**
     * Creates new form ExperienceListPanel
     */
    public ExperienceTypeListPanel()
    {
        initComponents();
        experienceList.setSelectedIndex(0);
        setItem(0);
    }
    
    public void setItem(int i)
    {
        titleLabel.setText((String)experienceList.getModel().getElementAt(i));
        
        String str = "";
        switch(i)
        {
            case 0:
                str += "Expérience de type générique\n\n";
                str += "Il s'agit de l'interface d'expérimentation la plus complète, qui exploite au maximum les possibilités du logiciel. \n\n";
                str += "Tournez vous vers cette option si les autres ne vous conviennent pas. ";
                break;
            case 1:
                str += "Plus Maze\n\n";
                str += "Cette interface a été prévue pour le labyrinthe en plus. Les différentes positions possibles sont donc déjà configurées. \n\n";
        }
        descriptionPane.setText(str);
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
        descriptionPane = new javax.swing.JEditorPane();
        titleLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        experienceList = new javax.swing.JList();

        descriptionPane.setEditable(false);
        jScrollPane1.setViewportView(descriptionPane);

        titleLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Nom de l'expérience");
        titleLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        experienceList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Générique", "Plus Maze" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        experienceList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        experienceList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                experienceListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(experienceList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void experienceListValueChanged(javax.swing.event.ListSelectionEvent evt)//GEN-FIRST:event_experienceListValueChanged
    {//GEN-HEADEREND:event_experienceListValueChanged
        setItem(experienceList.getSelectedIndex());
    }//GEN-LAST:event_experienceListValueChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane descriptionPane;
    private javax.swing.JList experienceList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onNext()
    {
        switch(experienceList.getSelectedIndex())
        {
            case 0:
                GenericConfigurationPanel panel = new GenericConfigurationPanel();
                nextPanel(panel);
                break;
            case 1:
                break;
        }
    }
}
