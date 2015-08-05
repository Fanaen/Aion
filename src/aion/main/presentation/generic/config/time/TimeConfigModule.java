/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aion.main.presentation.generic.config.time;

import aion.main.core.time.AbstractSequence;
import aion.main.core.time.Sequence;
import aion.main.core.time.SequenceList;
import aion.main.core.time.TimeSchema;
import aion.main.presentation.generic.ActionOnItem;
import aion.main.presentation.generic.treeview.GenericTreeListener;
import aion.main.presentation.generic.treeview.GenericTreePanel;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

/**
 *
 * @author Fanaen
 */
public class TimeConfigModule implements GenericTreeListener {
    
    // -- Attributes --
    private GenericTreePanel tab;
    public TimeSchema schema;
    
    // -- Constructor --
    public TimeConfigModule() {
        tab = new GenericTreePanel(this, "séquences");
        schema = new TimeSchema();
    }
    
    public TimeConfigModule(TimeSchema timeSchema, Object model) {
        tab = new GenericTreePanel(this, "séquences", model);
        schema = timeSchema;
    }

    // -- Overrided methods --
    @Override
    public void onAddItem(DefaultMutableTreeNode node) {
        schema.getList().add(new Sequence(node.toString()));
    }

    @Override
    public void onAddFolder(DefaultMutableTreeNode node) {
        schema.getList().add(new SequenceList(node.toString()));
    }


    @Override
    public void onRenameItem(DefaultMutableTreeNode node) {
        findItem(node, ActionOnItem.None).setName(node.toString());
    }

    @Override
    public void onSelectItem(DefaultMutableTreeNode node) {
        Sequence item = (Sequence) findItem(node, ActionOnItem.None);
        if(item != null) {
            SequenceEditPanel panel = new SequenceEditPanel(item, node, new SequenceEditPanel.PanelListener () {

                @Override
                public void onRemove(DefaultMutableTreeNode node, Sequence itemToRemove)
                {
                    tab.removeNode(node);
                    findItem(node, ActionOnItem.Remove);

                    tab.setEditPanel(new JPanel());
                }
            });

            tab.setEditPanel(panel);
        }
    }

    @Override
    public void onRemoveItem(DefaultMutableTreeNode node)
    {
        findItem(node, ActionOnItem.Remove);
        tab.setEditPanel(new JPanel());
    }

    @Override
    public void onSelectFolder(DefaultMutableTreeNode node) {
        SequenceList itemList = (SequenceList) findItem(node, ActionOnItem.None);
        if(itemList != null) {
            SequenceListEditPanel panel = new SequenceListEditPanel(itemList, node, new SequenceListEditPanel.PanelListener () {

                @Override
                public void onRemove(DefaultMutableTreeNode node, SequenceList item)
                {
                    tab.removeNode(node);
                    findItem(node, ActionOnItem.Remove);

                    tab.setEditPanel(new JPanel());
                }
            });

            tab.setEditPanel(panel);
        }
    }

    @Override
    public void onMoveItem(DefaultMutableTreeNode parent, DefaultMutableTreeNode[] nodes, int index) {
        List<AbstractSequence> list = new LinkedList<>(), existingList = null;

        // -- Set the node list as AbstractSequences --
        for (DefaultMutableTreeNode node : nodes) {
            list.add(0, findItem(node, ActionOnItem.None));
        }

        AbstractSequence item = findItem(parent, ActionOnItem.None);
        if(item == null || !(item instanceof SequenceList)) return;
        existingList = ((SequenceList) item).getList();

        // -- Add nodes to AbstractSequence List --
        for (AbstractSequence abstractItem : list) {
            existingList.add(index, abstractItem);
        }    
        
        // -- Remove old nodes --
        int i = 0;
        for (DefaultMutableTreeNode node : nodes) {
            findItem(node, ActionOnItem.Remove, i);
            i--;
        }
    }

    public AbstractSequence findItem(DefaultMutableTreeNode node, ActionOnItem action) {
        return findItem(node, action, 0);
    }
    
    public AbstractSequence findItem(DefaultMutableTreeNode node, ActionOnItem action, int coef) {
        TreeNode[] path = node.getPath();
        int targetLevel = node.getLevel(), currentLevel = 0;

        if(path.length >= 2) {
            TreeNode parent = path[0];

            List<AbstractSequence> itemList = schema.getList();

            // -- Follow the treenode path --
            for (TreeNode treeNode : path) {
                DefaultMutableTreeNode defaultTreeNode;
                try {
                    defaultTreeNode = (DefaultMutableTreeNode) treeNode;
                    currentLevel = defaultTreeNode.getLevel();

                    // Skip root --
                    if(currentLevel > 0) {
                        int index = parent.getIndex(treeNode);
                        if(index == -1)  return null;
                        
                        if(currentLevel < targetLevel) {
                            AbstractSequence item = itemList.get(index);
                        
                            if(item instanceof SequenceList) {
                                itemList = ((SequenceList) item).getList();
                            }
                        }
                        else {
                            AbstractSequence item = itemList.get(index + coef);
                            if(action == ActionOnItem.Remove) {
                                itemList.remove(item);
                            }
                            return item;
                        }
                    }
                }
                catch(Exception ex) {
                    Logger.getLogger("MyLog").log(Level.SEVERE, null, ex);
                }

                parent = treeNode;
            }
        }
        return null;
    }
        
    // -- Getters and setters --
    public GenericTreePanel getTab()
    {
        return tab;
    }

    public void setTab(GenericTreePanel tab)
    {
        this.tab = tab;
    }
}
