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

import java.util.Collections;
import java.util.Iterator;

public class Variable implements Ordering {

	private String name;

	public Variable(String name) {
		this.name = name;
	}
	
	public Domain getDomain() {
		return Domain.Integer;
	}

	public String getName() {
		return name;
	}

	@SuppressWarnings("unchecked")
	public Iterator<Ordering> iterator() {
		return Collections.EMPTY_LIST.iterator();
	}

	public int getVarIndex(String value) {
		return 0;
	}

	public void insertVarAtIndex(String value, int index) {
		throw new UnsupportedOperationException();
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
