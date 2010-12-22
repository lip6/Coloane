/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jean-Baptiste VORON (LIP6) - Project Head / Initial contributor
 *   Clément DÉMOULINS (LIP6) - Project Manager
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.core.ui.views;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;

/**
 * Tree structure for the model of the Models Navigator.
 *
 * @author Clément Démoulins
 */
public class Tree {

	private Tree parent = null;
	private List<Tree> children = new ArrayList<Tree>();
	
	private String name;
	private Object element;
	private ImageDescriptor icon;

	/**
	 * @param name name of the element
	 */
	public Tree(String name) {
		this.name = name;
	}

	/**
	 * @param name name of the element
	 * @param element element contains in this node
	 */
	public Tree(String name, Object element) {
		this.name = name;
		this.element = element;
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public final void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the element
	 */
	public final Object getElement() {
		return element;
	}

	/**
	 * @param element the element to set
	 */
	public final void setElement(Object element) {
		this.element = element;
	}

	/**
	 * @return the icon
	 */
	public final ImageDescriptor getIcon() {
		return icon;
	}

	/**
	 * @param icon the icon to set
	 */
	public final void setIcon(ImageDescriptor icon) {
		this.icon = icon;
	}

	/**
	 * @return the parent
	 */
	public final Tree getParent() {
		return parent;
	}

	/**
	 * @return a readonly list of the children
	 */
	public final List<Tree> getChildren() {
		return Collections.unmodifiableList(children);
	}

	/**
	 * Add a child to this node, if the child was attached to another node it's previously removed.
	 * @param child child to add
	 */
	public final void addChild(Tree child) {
		if (child.getParent() != null) {
			child.getParent().removeChild(child);
		}
		children.add(child);
		child.setParent(this);
	}
	
	/**
	 * @param child child to remove
	 * @return <code>true</code> if the child was removed
	 */
	public final boolean removeChild(Tree child) {
		boolean r = children.remove(child);
		if (r) {
			child.setParent(null);
		}
		return r;
	}

	/**
	 * @param parent the parent to set
	 */
	final void setParent(Tree parent) {
		this.parent = parent;
	}
}
