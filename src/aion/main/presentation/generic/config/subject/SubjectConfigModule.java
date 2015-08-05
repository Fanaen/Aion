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

package aion.main.presentation.generic.config.subject;

import aion.main.core.subject.AbstractSubject;
import aion.main.core.subject.Subject;
import aion.main.core.subject.SubjectList;
import aion.main.core.subject.SubjectSchema;
import aion.main.presentation.generic.ActionOnItem;
import aion.main.presentation.generic.treeview.GenericTreeListener;
import aion.main.presentation.generic.treeview.GenericTreePanel;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 *
 * @author Fanaen
 */
public class SubjectConfigModule implements GenericTreeListener {
    
    // -- Attributes --
    private GenericTreePanel tab = new GenericTreePanel(this, "sujets");
    public SubjectSchema schema = new SubjectSchema();

    // -- Overrided methods --
    @Override
    public void onAddItem(DefaultMutableTreeNode node) {
        schema.getList().add(new Subject(node.toString()));
    }

    @Override
    public void onAddFolder(DefaultMutableTreeNode node) {
        schema.getList().add(new SubjectList(node.toString()));
    }


    @Override
    public void onRenameItem(DefaultMutableTreeNode node) {
        findItem(node, ActionOnItem.None).setName(node.toString());
    }

    @Override
    public void onSelectItem(DefaultMutableTreeNode node) {
        Subject item = (Subject) findItem(node, ActionOnItem.None);
        if(item != null) {
            SubjectEditPanel panel = new SubjectEditPanel(item, node, new SubjectEditPanel.PanelListener () {

                @Override
                public void onRemove(DefaultMutableTreeNode node, Subject itemToRemove)
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
    public void onSelectFolder(DefaultMutableTreeNode node) {
        SubjectList itemList = (SubjectList) findItem(node, ActionOnItem.None);
        if(itemList != null) {
            SubjectListEditPanel panel = new SubjectListEditPanel(itemList, node, new SubjectListEditPanel.PanelListener () {

                @Override
                public void onRemove(DefaultMutableTreeNode node, SubjectList item)
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
    public void onMoveItem(DefaultMutableTreeNode parent, DefaultMutableTreeNode[] nodes, int index) {
        List<AbstractSubject> list = new LinkedList<>(), existingList = null;

        // -- Set the node list as AbstractSequences --
        for (DefaultMutableTreeNode node : nodes) {
            list.add(0, findItem(node, ActionOnItem.None));
        }

        AbstractSubject item = findItem(parent, ActionOnItem.None);
        if(item == null || !(item instanceof SubjectList)) return;
        existingList = ((SubjectList) item).getList();

        // -- Add nodes to AbstractSequence List --
        for (AbstractSubject abstractItem : list) {
            existingList.add(index, abstractItem);
        }    
        
        // -- Remove old nodes --
        int i = 0;
        for (DefaultMutableTreeNode node : nodes) {
            findItem(node, ActionOnItem.Remove, i);
            i--;
        }
    }

    public AbstractSubject findItem(DefaultMutableTreeNode node, ActionOnItem action) {
        return findItem(node, action, 0);
    }
    
    public AbstractSubject findItem(DefaultMutableTreeNode node, ActionOnItem action, int coef) {
        TreeNode[] path = node.getPath();
        int targetLevel = node.getLevel(), currentLevel = 0;

        if(path.length >= 2) {
            TreeNode parent = path[0];

            List<AbstractSubject> itemList = schema.getList();

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
                            AbstractSubject item = itemList.get(index);
                        
                            if(item instanceof SubjectList) {
                                itemList = ((SubjectList) item).getList();
                            }
                        }
                        else {
                            AbstractSubject item = itemList.get(index + coef);
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
