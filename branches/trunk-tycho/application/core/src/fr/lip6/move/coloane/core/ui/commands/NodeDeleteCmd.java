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
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.ArrayList;
import java.util.List;

/**
 * Delete a node from the model
 */
public class NodeDeleteCmd extends CheckableCmd {

	/** Graph Model that holds the node to delete */
	private final IGraph graph;

	/** Node to delete */
	private final INode node;

	/** List of outgoings arcs (backup in case of undo) */
	private List<IArc> outArcs = null;

	/** List of incoming arcs (backup in case of undo) */
	private List<IArc> inArcs = null;

	/** List of links (backup in case of undo) */
	private List<ILink> links = new ArrayList<ILink>();

	/** List of tips (backup in case of undo) */
	private List<ICoreTip> tips;

	/** The current session */
	private ISession session;

	/**
	 * Constructor
	 * @param graph The graph that owns the node to delete
	 * @param node The node to delete
	 */
	public NodeDeleteCmd(IGraph graph, INode node) {
		super(Messages.NodeDeleteCmd_0);
		this.graph = graph;
		this.node = node;
		this.session = SessionManager.getInstance().getCurrentSession();
		
		//The node and its associated arcs must be locally checked after the changes 
		addCheckableElement(node);
		for (IArc arc : node.getIncomingArcs()) {
			addCheckableElement(arc);
		}
		for (IArc arc : node.getOutgoingArcs()) {
			addCheckableElement(arc);
		}
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		// Backup the list of associated tips
		this.tips = new ArrayList<ICoreTip>(this.session.getTipForObject(this.node.getId()));
		// Backup the list of associated sticky links
		if (this.node instanceof ILinkableElement) {
			this.links.addAll(((ILinkableElement) this.node).getLinks());
		}

		// Backup a list of incoming and outgoing arcs in case of undo operation
		this.outArcs = new ArrayList<IArc>(this.node.getOutgoingArcs());
		for (IArc arc : outArcs) {
			this.tips.addAll(this.session.getTipForObject(arc.getId()));
			if (arc instanceof ILinkableElement) {
				this.links.addAll(((ILinkableElement) arc).getLinks());
			}
		}
		this.inArcs = new ArrayList<IArc>(this.node.getIncomingArcs());
		for (IArc arc : this.inArcs) {
			this.tips.addAll(this.session.getTipForObject(arc.getId()));
			if (arc instanceof ILinkableElement) {
				this.links.addAll(((ILinkableElement) arc).getLinks());
			}
		}
		this.redo(); // Execute
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		session.removeTips(tips);
		graph.deleteNode(node);
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		this.graph.addNode(this.node);

		// Add incoming arcs
		for (IArc arc : inArcs) {
			this.graph.addArc(arc);
		}

		// Add outgoing arcs
		for (IArc arc : outArcs) {
			this.graph.addArc(arc);
		}

		// Add sticky links
		for (ILink link : this.links) {
			link.connect();
		}
		
		this.session.addAllTips(tips);
	}
}
