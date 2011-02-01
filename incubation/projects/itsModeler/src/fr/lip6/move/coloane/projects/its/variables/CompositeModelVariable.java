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
 *   Yann THIERRY-MIEG (LIP6)
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.projects.its.variables;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.lip6.move.coloane.projects.its.IModelVariable;

public abstract class CompositeModelVariable extends AbstractModelVariable implements IModelVariable {

	public CompositeModelVariable(String name) {
		super(name);
	}

	private List<IModelVariable> children = new ArrayList<IModelVariable>();

	public Iterator<IModelVariable> iterator() {
		return children.iterator();
	}

	
	public void addChild (IModelVariable var) {
		children.add(var);
		var.setParent(this);
	}
}
