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

import fr.lip6.move.coloane.interfaces.model.INode;

public class InstanceVariable extends CompositeModelVariable {

	private String type;

	public InstanceVariable(INode inst, String type) {
		super(inst.getAttribute("name").getValue());
		setId("i_" + inst.getId() +"_"+ getName());
		this.type = inst.getAttribute("type").getValue() +  " (" + type + ")";
	}
	
	public String getDescription() {
		return "Instance "+ getInstanceName();
	}

	public String getInstanceName() {
		return getName()+ ":" + type;
	}

}
