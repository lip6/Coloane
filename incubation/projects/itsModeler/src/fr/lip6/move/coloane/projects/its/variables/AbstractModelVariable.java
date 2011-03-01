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


public abstract class AbstractModelVariable implements IModelVariable {

	private String name;
	private IModelVariable parent;
	private String id;

	
	public AbstractModelVariable(String name) {
		this.name = name;
	}

	protected void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public IModelVariable getParent() {
		if (parent != null)
			return parent;
		return this;
	}

	public String getQualifiedName() {
		String s="";
		if (getParent() != this) {
			s = getParent().getQualifiedName() + ".";
		}
		return s + name;
	}

	public void setParent(IModelVariable parent) {
		this.parent = parent;
	}

	public String getId() {
		String s="";
		if (getParent() != this) {
			s = getParent().getId() + ".";
		}
		return s + id;
	}

}
