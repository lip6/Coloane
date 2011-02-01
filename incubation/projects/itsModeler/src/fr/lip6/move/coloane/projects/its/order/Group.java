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
package fr.lip6.move.coloane.projects.its.order;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Group implements Ordering {

	private String name;
	
	private List<Ordering> children = new ArrayList<Ordering> ();

	public Group(String name) {
		this.name = name;
	}
	
	public Domain getDomain() {
		return Domain.SDD;
	}

	public String getName() {
		return name;
	}
	
	public void addChild (Ordering o)  {
		children.add(o);
	}

	public Iterator<Ordering> iterator() {
		return children.iterator();
	}

	public int getVarIndex(String value) {
		for (int i = 0; i < children.size(); i++) {
			if (children.get(i).getName().equals(value)) {
				return i;
			}
		}
		return 0;
	}

	public void insertVarAtIndex(String value, int index) {
		children.add(index, new Variable(value));
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
