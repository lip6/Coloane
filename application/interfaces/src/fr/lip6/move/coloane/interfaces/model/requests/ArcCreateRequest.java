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
package fr.lip6.move.coloane.interfaces.model.requests;

import fr.lip6.move.coloane.interfaces.formalism.IArcFormalism;
import fr.lip6.move.coloane.interfaces.model.INode;

/**
 * Ask the core project to create an arc
 *
 * @author Jean-Baptiste Voron
 */
public class ArcCreateRequest implements IRequest {
	/** Arc source */
	private INode source;
	/** Arc target */
	private INode target;
	/** The formalism used by the new arc */
	private IArcFormalism formalism;

	/**
	 * Constructor
	 * @param source The source of the new arc
	 * @param target The target of the new arc
	 * @param formalism The formalism used by this arc (describe its properties)
	 */
	public ArcCreateRequest(INode source, INode target, IArcFormalism formalism) {
		this.source = source;
		this.target = target;
		this.formalism = formalism;
	}

	/**
	 * @return the source of the new arc
	 */
	public final INode getSource() {
		return source;
	}

	/**
	 * @return the target of the new arc
	 */
	public final INode getTarget() {
		return target;
	}

	/**
	 * @return The formalism used by the new arc
	 */
	public final IArcFormalism getFormalism() {
		return formalism;
	}

	/**
	 * {@inheritDoc}
	 */
	public final int getRequestType() {
		return IRequest.ARC_CREATE_REQUEST;
	}
}
