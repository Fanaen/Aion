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

package aion.main.presentation.generic.config.environment;

import aion.main.core.environment.AbstractPosition;
import aion.main.core.environment.EnvironmentSchema;
import aion.main.core.environment.Position;
import aion.main.core.environment.Zone;
import aion.main.presentation.generic.ActionOnItem;
import aion.main.presentation.generic.treeview.GenericTreeListener;
import aion.main.presentation.generic.treeview.GenericTreePanel;
import java.awt.Point;
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
public class EnvironmentConfigModule implements GenericTreeListener {
    
    // -- Attributes --
    private GenericTreePanel tab = new GenericTreePanel(this, "positions");
    public EnvironmentSchema schema = new EnvironmentSchema();
    
    int i = 0;

    // -- Overrided methods --
    @Override
    public void onAddItem(DefaultMutableTreeNode node) {
        Position position = new Position(node.toString());
        position.setPosition(new Point(i, 0));
        i++;
        schema.getList().add(position);
    }

    @Override
    public void onAddFolder(DefaultMutableTreeNode node) {
        schema.getList().add(new Zone(node.toString()));
    }


    @Override
    public void onRenameItem(DefaultMutableTreeNode node) {
        findItem(node, ActionOnItem.None).setName(node.toString());
    }

    @Override
    public void onSelectItem(DefaultMutableTreeNode node) {
        Position item = (Position) findItem(node, ActionOnItem.None);
        if(item != null) {
            PositionEditPanel panel = new PositionEditPanel(item, node, new PositionEditPanel.PanelListener () {

                @Override
                public void onRemove(DefaultMutableTreeNode node, Position itemToRemove)
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
        Zone itemList = (Zone) findItem(node, ActionOnItem.None);
        if(itemList != null) {
            ZoneEditPanel panel = new ZoneEditPanel(itemList, node, new ZoneEditPanel.PanelListener () {

                @Override
                public void onRemove(DefaultMutableTreeNode node, Zone item)
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
        List<AbstractPosition> list = new LinkedList<>(), existingList = null;

        // -- Set the node list as AbstractSequences --
        for (DefaultMutableTreeNode node : nodes) {
            list.add(0, findItem(node, ActionOnItem.None));
        }

        AbstractPosition item = findItem(parent, ActionOnItem.None);
        if(item == null || !(item instanceof Zone)) return;
        existingList = ((Zone) item).getList();

        // -- Add nodes to AbstractSequence List --
        for (AbstractPosition abstractItem : list) {
            existingList.add(index, abstractItem);
        }    
        
        // -- Remove old nodes --
        int i = 0;
        for (DefaultMutableTreeNode node : nodes) {
            findItem(node, ActionOnItem.Remove, i);
            i--;
        }
    }

    public AbstractPosition findItem(DefaultMutableTreeNode node, ActionOnItem action) {
        return findItem(node, action, 0);
    }
    
    public AbstractPosition findItem(DefaultMutableTreeNode node, ActionOnItem action, int coef) {
        TreeNode[] path = node.getPath();
        int targetLevel = node.getLevel(), currentLevel = 0;

        if(path.length >= 2) {
            TreeNode parent = path[0];

            List<AbstractPosition> itemList = schema.getList();

            // -- Follow the treenode path --
            for (TreeNode treeNode : path) {
                DefaultMutableTreeNode defaultTreeNode;
                try {
                    defaultTreeNode = (DefaultMutableTreeNode) treeNode;
                    currentLevel = defaultTreeNode.getLevel();

                    // Skip root --
                    if(currentLevel > 0) {
                        int index = parent.getIndex(treeNode);
                        if(index == -1) 
                        {
                            return null;
                        }
                        else
                        {
                            index += coef; // Compensation for removing --
                        }

                        AbstractPosition item = itemList.get(index);

                        if(currentLevel < targetLevel) {
                            if(item instanceof Zone) {
                                itemList = ((Zone) item).getList();
                            }
                        }
                        else {
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
