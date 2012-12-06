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
package fr.lip6.move.coloane.core.copypast;

import fr.lip6.move.coloane.core.copypast.container.GraphContainer;
import fr.lip6.move.coloane.core.ui.ColoaneEditor;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.Clipboard;

/**
 * Cut command
 */
public class CutCommand extends Command {
	/** The graph */
	private IGraph graph;

	/** The container of all copied objects */
	private GraphContainer container;

	/** Backup nodes */
	private List<INode> nodes = new ArrayList<INode>();
	/** Backup arcs */
	private Set<IArc> arcs = new HashSet<IArc>();

	/**
	 * Constructor
	 * @param editor The current editor
	 */
	public CutCommand(ColoaneEditor editor) {
		if (editor.getGraph() != null) {
			this.graph = editor.getGraph();
			container = new GraphContainer(editor.getGraph().getFormalism());
		}
	}

	/**
	 * Add a node to cut
	 * @param node The node to cut
	 */
	public final void addNode(INode node) {
		// Backup
		nodes.add(node);
		// Backup arcs
		arcs.addAll(node.getOutgoingArcs());
		arcs.addAll(node.getIncomingArcs());
		// Container for Paste action
		container.addNode(node);
	}

	/**
	 * Add an arc to cut
	 * @param arc The arc to cut
	 */
	public final void addArc(IArc arc) {
		// Backup
		arcs.add(arc);
		// Container for Paste action
		container.addArc(arc);
	}

	/** {@inheritDoc} */
	@Override
	public final boolean canExecute() {
		return !container.isEmpty();
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		if (!canExecute()) {
			return;
		}
		// Copy
		Clipboard.getDefault().setContents(container);
		redo();
	}

	/** {@inheritDoc} */
	@Override
	public final boolean canUndo() {
		return container != null;
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		for (IArc arc : arcs) {
			this.graph.deleteArc(arc);
		}
		for (INode node : nodes) {
			this.graph.deleteNode(node);
		}
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		for (INode node : nodes) {
			this.graph.addNode(node);
		}
		for (IArc arc : arcs) {
			this.graph.addArc(arc);
		}
	}
}
