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
