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
package fr.lip6.move.coloane.core.results;

import fr.lip6.move.coloane.core.model.interfaces.ILocatedElement;
import fr.lip6.move.coloane.core.ui.commands.ArcCompleteCmd;
import fr.lip6.move.coloane.core.ui.commands.AttributeEditCmd;
import fr.lip6.move.coloane.core.ui.commands.AttributeSetConstraintCmd;
import fr.lip6.move.coloane.core.ui.commands.InflexCreateCmd;
import fr.lip6.move.coloane.core.ui.commands.InflexDeleteCmd;
import fr.lip6.move.coloane.core.ui.commands.LocatedElementSetConstraintCmd;
import fr.lip6.move.coloane.core.ui.commands.NodeCreateCmd;
import fr.lip6.move.coloane.core.ui.commands.NodeDeleteCmd;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.requests.ArcCreateRequest;
import fr.lip6.move.coloane.interfaces.model.requests.AttributeChangeValueRequest;
import fr.lip6.move.coloane.interfaces.model.requests.AttributePositionRequest;
import fr.lip6.move.coloane.interfaces.model.requests.AttributesResetPositionRequest;
import fr.lip6.move.coloane.interfaces.model.requests.IRequest;
import fr.lip6.move.coloane.interfaces.model.requests.InflexPointCreateRequest;
import fr.lip6.move.coloane.interfaces.model.requests.InflexPointsDeleteRequest;
import fr.lip6.move.coloane.interfaces.model.requests.NodeCreateRequest;
import fr.lip6.move.coloane.interfaces.model.requests.NodeDeleteRequest;
import fr.lip6.move.coloane.interfaces.model.requests.NodePositionRequest;

import java.util.logging.Logger;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;

public class CommandFactory {
	/** Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/**
	 * Produce a GEF command according to the request
	 * @param request The request coming from the result object
	 * @return the corresponding command
	 */
	public static Command createCommand(IRequest request, IGraph graph) {

		switch (request.getRequestType()) {
		// New Arc
		case IRequest.ARC_CREATE_REQUEST:
			ArcCreateRequest arcCreateRequest = (ArcCreateRequest) request;
			return new ArcCompleteCmd(arcCreateRequest.getSource(), arcCreateRequest.getTarget(), arcCreateRequest.getFormalism());

		// Change attribute position
		case IRequest.ATTRIBUTE_POSITION_REQUEST:
			AttributePositionRequest attributePositionRequest = (AttributePositionRequest) request;
			return new AttributeSetConstraintCmd(attributePositionRequest.getAttribute(), attributePositionRequest.getNewPosition());

		// Change attribute value
		case IRequest.ATTRIBUTE_CHANGEVALUE_REQUEST:
			AttributeChangeValueRequest attributeChangeValueRequest = (AttributeChangeValueRequest) request;
			return new AttributeEditCmd(attributeChangeValueRequest.getAttribute(), attributeChangeValueRequest.getNewValue());

		// Reset the position of attributes attached to an object
		case IRequest.ATTRIBUTE_RESET_POSITION_REQUEST:
			AttributesResetPositionRequest attributesResetPositionRequest = (AttributesResetPositionRequest) request;
			CompoundCommand resetAttributesPositionCommand = new CompoundCommand();
			for (IAttribute attribute : attributesResetPositionRequest.getParentObject().getAttributes()) {
				resetAttributesPositionCommand.add(new AttributeSetConstraintCmd(attribute, new Point(-1, -1)));
			}
			return resetAttributesPositionCommand;

		// Create a new inflex point
		case IRequest.INFLEXPOINT_CREATE_REQUEST:
			InflexPointCreateRequest inflexPointCreateRequest = (InflexPointCreateRequest) request;
			return new InflexCreateCmd(inflexPointCreateRequest.getArc(), inflexPointCreateRequest.getPosition(), inflexPointCreateRequest.getIndex());

		// Remove all inflex points associated to an arc
		case IRequest.INFLEXPOINTS_DELETE_REQUEST:
			InflexPointsDeleteRequest inflexPointsDeleteRequest = (InflexPointsDeleteRequest) request;
			// Build a compound command that delete all inflex points of the considered arc
			CompoundCommand deleteInflexPointsCommand = new CompoundCommand();
			for (int i = 0; i < inflexPointsDeleteRequest.getArc().getInflexPoints().size(); i++) {
				deleteInflexPointsCommand.add(new InflexDeleteCmd(inflexPointsDeleteRequest.getArc(), i));
			}
			return deleteInflexPointsCommand;

		// Create a node
		case IRequest.NODE_CREATE_REQUEST:
			NodeCreateRequest nodeCreateRequest = (NodeCreateRequest) request;
			return new NodeCreateCmd(graph, nodeCreateRequest.getFormalism(), nodeCreateRequest.getInitialPosition());

		// Remove a node
		case IRequest.NODE_DELETE_REQUEST:
			NodeDeleteRequest nodeDeleteRequest = (NodeDeleteRequest) request;
			return new NodeDeleteCmd(graph, nodeDeleteRequest.getElement());

		// Move a node
		case IRequest.NODE_POSITION_REQUEST:
			NodePositionRequest nodePositionRequest = (NodePositionRequest) request;
			return new LocatedElementSetConstraintCmd((ILocatedElement) nodePositionRequest.getElement(), nodePositionRequest.getNewPosition());

		default:
			LOGGER.warning("A request has not been recognized by the factory"); //$NON-NLS-1$
			return null;
		}
	}
}
