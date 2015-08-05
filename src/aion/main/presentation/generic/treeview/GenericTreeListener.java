/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aion.main.presentation.generic.treeview;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Fanaen
 */
public interface GenericTreeListener {

    public void onAddItem(DefaultMutableTreeNode node);
    public void onAddFolder(DefaultMutableTreeNode node);
    
    public void onRenameItem(DefaultMutableTreeNode node);
    public void onRemoveItem(DefaultMutableTreeNode node);
    
    public void onSelectItem(DefaultMutableTreeNode node);
    public void onSelectFolder(DefaultMutableTreeNode node);
    
    public void onMoveItem(DefaultMutableTreeNode parent, DefaultMutableTreeNode[] nodes, int index);

    
}
