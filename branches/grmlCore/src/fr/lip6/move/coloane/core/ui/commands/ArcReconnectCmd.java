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

import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.INode;

/**
 * Command that allows the re-connection of an arc.<br>
 * The aim of this command is to allow to change source <b>or</b> the target of an arc without destroying it.<br>
 *
 * @author Jean-Baptiste Voron
 */
public class ArcReconnectCmd extends CheckableCmd {

	/** The considered arc */
	private IArc arc;

	/** New source node */
	private INode newSource;

	/** New target node */
	private INode newTarget;

	/** Old source node */
	private final INode oldSource;

	/** Old target node */
	private final INode oldTarget;

	/**
	 * Build the command for a given arc
	 * @param arc The arc that will be reconnected somewhere
	 */
	public ArcReconnectCmd(IArc arc) {
		super(Messages.ArcReconnectCmd_0);

		this.arc = arc;
		this.oldSource = arc.getSource();
		this.oldTarget = arc.getTarget();

		// Describes elements that must be checked
		addCheckableElement(arc);
		addCheckableElement(newSource);
		addCheckableElement(newTarget);
		addCheckableElement(oldSource);
		addCheckableElement(oldTarget);
	}

	/**
	 * Set the new source for the arc
	 * @param newSource The new source
	 */
	public final void setNewSource(INode newSource) {
		this.newSource = newSource;
		this.newTarget = null;
	}

	/**
	 * Set the new target for the arc
	 * @param newTarget The new target
	 */
	public final void setNewTarget(INode newTarget) {
		this.newSource = null;
		this.newTarget = newTarget;
	}

	/**
	 * @return whether this operation can be executed
	 */
	@Override
	public final boolean canExecute() {

		// Can we connect the arc to the new source ?
		if ((this.newSource != null) && !checkReconnection()) {
			return false;
		}

		// Can we connect the arc to the new target ?
		if ((this.newTarget != null) && !checkReconnection()) {
			return false;
		}

		// Fetch the formalism
		IFormalism formalism = this.arc.getArcFormalism().getFormalism();

		// Is the connection allowed by the formalism ?
		if ((this.newSource != null) && !formalism.isLinkAllowed(newSource, oldTarget, this.arc.getArcFormalism())) {
			return false;
		}
		if ((this.newTarget != null) && !formalism.isLinkAllowed(oldSource, newTarget, this.arc.getArcFormalism())) {
			return false;
		}

		return true;
	}

	/**
	 * Check that an arc is not already existing between source and target.<br>
	 * @return <true> if the connection is new...
	 */
	private boolean checkReconnection() {
		// If the source has changed, check that an arc is not already set up
		if ((newSource != null) && (newSource != oldSource)) {
			for (IArc existingConnection : this.newSource.getOutgoingArcs()) {
				if (existingConnection.getTarget().getId() == this.oldTarget.getId()) {
					return false;
				}
			}
			return true;
		}

		// If the target has changed, check that an arc is not already set up
		if ((newTarget != null) && (newTarget != oldTarget)) {
			for (IArc existingConnection : this.newTarget.getIncomingArcs()) {
				if (existingConnection.getSource().getId() == this.oldSource.getId()) {
					return false;
				}
			}
			return true;
		}

		return true;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		this.redo();
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		INode sourceToConnect = newSource;
		INode targetToConnect = newTarget;

		// If the source has not changed
		if (newSource == null) {
			sourceToConnect = oldSource;
		}

		// If the target has not changed
		if (newTarget == null) {
 			targetToConnect = oldTarget;
		}

		// Reconnect
		this.arc.reconnect(sourceToConnect, targetToConnect);
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		this.arc.reconnect(oldSource, oldTarget);
	}
}
