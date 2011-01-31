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
package fr.lip6.move.coloane.interfaces.model;

import fr.lip6.move.coloane.interfaces.formalism.IArcFormalism;

import java.util.List;

import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.geometry.Point;

/**
 * Définition d'un arc
 */
public interface IArc extends IElement {

	/** ID pour la propriete lors d'un changement des arcs entants */
	String INFLEXPOINT_PROP = "Arc.InflexPoint"; //$NON-NLS-1$

	/** ID pour le changement de couleur */
	String COLOR_PROP = "Arc.Color"; //$NON-NLS-1$

	/** ID pour le changement de courbure de l'arc */
	String CURVE_PROP = "Arc.Curve"; //$NON-NLS-1$

	/**
	 * @return source de l'arc.
	 */
	INode getSource();

	/**
	 * @return cible de l'arc.
	 */
	INode getTarget();

	/**
	 * @return le FormalismElement décrivant cet arc.
	 */
	IArcFormalism getArcFormalism();

	/**
	 * @return les informations graphique liée à cet arc.
	 */
	IArcGraphicInfo getGraphicInfo();

	/**
	 * Ajoute un point d'inflexion à l'index spécifié.
	 * @param p coordonnées du point d'inflexion.
	 * @param index index du point d'inflexion.
	 */
	void addInflexPoint(Point p, int index);

	/**
	 * Ajoute un point d'inflexion.
	 * @param p coordonnées du point d'inflexion.
	 */
	void addInflexPoint(Point p);

	/**
	 * Suppression du point d'inflexion situé à l'index spécifié.
	 * @param index index du point d'inflexion.
	 */
	void removeInflexPoint(int index);

	/**
	 * Suppression de tous les points d'inflexion
	 */
	void removeAllInflexPoints();

	/**
	 * Modification des coordonnées d'un point d'inflexion.
	 * @param index index du point d'inflexion.
	 * @param p nouvelles coordonnées.
	 */
	void modifyInflexPoint(int index, Point p);

	/**
	 * Déplace tous les points d'inflexions.
	 * @param dx Déplacement en abcisses
	 * @param dy Déplacement en ordonnées
	 */
	void modifyInflexPoints(int dx, int dy);

	/**
	 * @param index Numéro d'index du point d'inflexion
	 * @return Le point d'inflexion situé à l'index spécifié.
	 */
	AbsoluteBendpoint getInflexPoint(int index);

	/**
	 * @return Une liste nom modifiable de tous les points d'inflexions.
	 */
	List<AbsoluteBendpoint> getInflexPoints();

	/**
	 * Reconnecte cet arc à deux nouveaux noeuds.
	 * @param newSource Nouvelle source de l'arc
	 * @param newTarget Nouvelle cible de l'arc
	 */
	void reconnect(INode newSource, INode newTarget);

	/**
	 * Mettre a jour la position des attributs de l'arc en fonction de la
	 * position des noeuds source et cible.
	 */
	void updateAttributesPosition();

	/**
	 * Mettre à jours les tips attachés à cette élément
	 */
	void updateTips();
}
