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
package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.core.model.interfaces.ICoreTip;
import fr.lip6.move.coloane.core.model.interfaces.ILink;
import fr.lip6.move.coloane.core.model.interfaces.ILinkableElement;
import fr.lip6.move.coloane.core.session.ISession;
import fr.lip6.move.coloane.core.session.SessionManager;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.util.Collection;

/**
 * Delete an arc from the graph
 *
 * @author Jean-Baptiste Voron
 */
public class ArcDeleteCmd extends CheckableCmd {
	/** Graph that holds the arc to remove */
	private IGraph graph;

	/** The arc */
	private final IArc arc;

	/** A list of links attached to this arc (backup in case of undo) */
	private Collection<ILink> links;

	/** List of tips associated to this arc (backup in case of undo) */
	private Collection<ICoreTip> tips;

	/** The session that holds the graph */
	private ISession session;

	/**
	 * Constructor
	 * @param arc The arc to remove from the graph
	 */
	public ArcDeleteCmd(IArc arc) {
		super(Messages.ArcDeleteCmd_0);
		// Fetch the graph from the arc
		this.graph = (IGraph) arc.getParent();
		this.arc = arc;
		this.session =  SessionManager.getInstance().getCurrentSession();

		// Check for local properties (and around the deleted arc)
		addCheckableElement(arc);
		addCheckableElement(arc.getSource());
		addCheckableElement(arc.getTarget());
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		// Backup tips
		this.tips = this.session.getTipForObject(this.arc.getId());
		// Backup links
		if (this.arc instanceof ILinkableElement) {
			this.links = ((ILinkableElement) this.arc).getLinks();
		}
		redo();
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		this.session.removeTips(this.tips);
		this.graph.deleteArc(this.arc);
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		this.graph.addArc(this.arc);

		// Add sticky links
		for (ILink link : this.links) {
			link.connect();
		}

		// Add tips
		this.session.addAllTips(this.tips);
	}
}
