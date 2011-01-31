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
package fr.lip6.move.coloane.core.ui.editpart;

import fr.lip6.move.coloane.core.model.interfaces.ILink;
import fr.lip6.move.coloane.core.model.interfaces.ILinkableElement;
import fr.lip6.move.coloane.core.model.interfaces.IStickyNote;
import fr.lip6.move.coloane.core.ui.commands.LinkDeleteCmd;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

/**
 * EditPart that manages sticky links.
 * 
 * @author Clément Démoulins
 * @author Jean-Baptiste Voron
 * 
 * @see ILink
 * @see IStickyNote
 */
public class LinkEditPart extends AbstractConnectionEditPart {

	/** {@inheritDoc} */
	@Override
	protected final IFigure createFigure() {
		PolylineConnection arc = new PolylineConnection();
		// TODO: Must be set in properties 
		arc.setLineStyle(Graphics.LINE_DOT);
		arc.setForegroundColor(ColorConstants.gray);
		return arc;
	}

	/** {@inheritDoc} */
	@Override
	protected final void createEditPolicies() {
		// Makes the connection show a feedback, when selected by the user.
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, new ConnectionEndpointEditPolicy());

		// Some basic behavior properties
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ComponentEditPolicy() {

			// How to delete a sticky link ?
			@Override
			protected Command createDeleteCommand(GroupRequest deleteRequest) {
				// Fetch the link model and the node element
				ILink link = (ILink) getHost().getModel();
				ILinkableElement linkableElement = link.getElement();

				IGraph graph;
				// The linkable element is either an element (arc, node, ...) or an attribute
				if (linkableElement instanceof IElement) {
					IElement element = (IElement) linkableElement;
					graph = (IGraph) element.getParent();
				} else if (linkableElement instanceof IAttribute) {
					IElement element = ((IAttribute) linkableElement).getReference();
					graph = (IGraph) element.getParent();
				} else {
					return null; // Deleting the link is not possible
				}
				LinkDeleteCmd deleteCommand = new LinkDeleteCmd(graph, link);
				return deleteCommand;
			}
		});
	}
}
