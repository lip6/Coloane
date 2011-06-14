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
package fr.lip6.move.coloane.core.formalisms.checkers;

import fr.lip6.move.coloane.interfaces.formalism.IAttributeChecker;
import fr.lip6.move.coloane.interfaces.formalism.ICheckerResult;
import fr.lip6.move.coloane.interfaces.model.IAttribute;

/**
 * Use this checker to check if an attribute has a non empty value.
 * 
 * @author Jean-Baptiste Voron
 * @author Florian David
 */
public final class EmptyAttributeChecker implements IAttributeChecker {

	/**
	 * {@inheritDoc}
	 */
	public ICheckerResult performCheck(IAttribute attribute) {
		return new CheckerResult(attribute.getValue().isEmpty(), "Attribute " + attribute.getName() + " should not be empty");  //$NON-NLS-1$//$NON-NLS-2$
	}
}
