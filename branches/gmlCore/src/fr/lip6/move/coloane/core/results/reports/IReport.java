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
package fr.lip6.move.coloane.core.results.reports;

import fr.lip6.move.coloane.core.ui.panels.ResultTreeImpl;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;

/**
 * Cette interface doit être implémentée par les classes qui gèrent
 * des résultats de services en provenance de FrameKit. Une seule méthode
 * doit être définie : {@link #build(IResultsCom)}
 *
 * @author Clement Demoulins
 */
public interface IReport {
	/**
	 * Construction de l'arbre des résultats qui seront affiches dans la fenetre "resultats"
	 * @param result Objet contenant les données brutes en provenance de la Com
	 * @return Arbre des résultats
	 */
	ResultTreeImpl build(IResult result);
}
