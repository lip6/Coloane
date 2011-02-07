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

import fr.lip6.move.coloane.core.model.interfaces.ILocatedElement;
import fr.lip6.move.coloane.core.ui.ColoaneEditor;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.PlatformUI;

/**
 * Move an object (that is supposed to move : {@link ILocatedElement})
 *
 * @author Clément Démoulins
 * @author Jean-Baptiste Voron
 */
public class LocatedElementSetConstraintCmd extends Command {

	/** New location */
	private final Point newLocation;
	/** Old location */
	private Point oldLocation;
	
	/** Difference between the new and the old locations */
	private Dimension delta;

	/** Model object to move */
	private final ILocatedElement element;

	/** List of arcs for which inflex points have to be moved too */
	private final List<IArc> arcsForPI = new ArrayList<IArc>();

	/** List of arcs for which attributes have to be moved too */
	private final List<IAttribute> arcsForAttr = new ArrayList<IAttribute>();
	private final Map<IArc, Dimension> arcsMiddleDelta = new HashMap<IArc, Dimension>();

	/** Attributes that have to be moved too */
	private final List<IAttribute> attributes = new ArrayList<IAttribute>();

	/**
	 * Constructor
	 * @param element Model object to move
	 * @param newLocation New location for the model object
	 */
	public LocatedElementSetConstraintCmd(ILocatedElement element, Point newLocation) {
		super(Messages.NodeSetConstraintCmd_0);
		if (element == null || newLocation == null) {
			throw new NullPointerException();
		}
		this.element = element;
		this.newLocation = newLocation.getCopy();
		this.newLocation.x = Math.max(this.newLocation.x, 0);
		this.newLocation.y = Math.max(this.newLocation.y, 0);

		// Need to get the current selection to control if arcs are involved in the move
		ColoaneEditor ce = (ColoaneEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		GraphicalViewer viewer = (GraphicalViewer) ce.getAdapter(GraphicalViewer.class);

		// List of selected objects (try to know if arcs are involved in this move)
		StructuredSelection s = (StructuredSelection) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getSite().getSelectionProvider().getSelection();
		List< ? > selection = s.toList();

		// If the element is a node, some arcs may be involved in the move
		// If source node and target node are selected, arc has to be moved too (and also inflex point)
		if (element instanceof INode) {
			INode node = (INode) element;

			// Deal with incoming arcs.
			// Attributes have to be moved too.
			// Inflex point may be moved too.
			for (IArc in : node.getIncomingArcs()) {
				INode src = in.getSource();
				// If the source node is also moved, the arc will be moved automatically (but not its inflex point)
				if (selection.contains(viewer.getEditPartRegistry().get(src))) {
					arcsForPI.add(in);
				}

				// If the target node is moved... The arc will be moved too (but not its attributes)
				for (IAttribute arcAttr : in.getAttributes()) {
					if (!selection.contains(viewer.getEditPartRegistry().get(arcAttr))) {
						arcsForAttr.add(arcAttr);
					}
				}
			}
			
			// If the source node is moved... The outgoing arcs will be moved too (but not their attributes)
			for (IArc out : node.getOutgoingArcs()) {
				for (IAttribute arcAttr : out.getAttributes()) {
					if (!selection.contains(viewer.getEditPartRegistry().get(arcAttr))) {
						arcsForAttr.add(arcAttr);
					}
				}
			}
		}

		// Generally, attributes of the moved element must be moved too
		if (element instanceof IElement) {
			IElement modelElement = (IElement) element;

			for (IAttribute attr : modelElement.getDrawableAttributes()) {
				if (!selection.contains(viewer.getEditPartRegistry().get(attr))) {
					attributes.add(attr);
				}
			}
		}
	}

	/**
	 * A node can always be moved
	 * @return true
	 */
	@Override
	public final boolean canExecute() {
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		oldLocation = new Point(element.getLocationInfo().getLocation());
		delta = newLocation.getDifference(oldLocation);

		// Update middle point of arcs where attributes will  be moved
		for (IAttribute arcAttr : arcsForAttr) {
			if (arcAttr.getReference() instanceof IArc) {
				IArc arc = (IArc) arcAttr.getReference();
				arc.getGraphicInfo().updateMiddlePoint();
			}
		}

		// Build a map of delta move for arcs's attributes (needed for the undo operation)
		for (IAttribute arcAttr : arcsForAttr) {
			if (arcAttr.getReference() instanceof IArc) {
				IArc arc = (IArc) arcAttr.getReference();
				Point oldMiddle = arc.getGraphicInfo().getMiddlePoint();
				Point newMiddle = arc.getGraphicInfo().findMiddlePoint();
				Dimension middleDelta = newMiddle.getDifference(oldMiddle);
				arcsMiddleDelta.put(arc, middleDelta);
			}
		}

		this.redo();
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		element.getLocationInfo().setLocation(newLocation);

		// Move inflex points
		for (IArc arc : arcsForPI) {
			arc.modifyInflexPoints(delta.width, delta.height);
		}
		
		// Move arc's attributes
		for (IAttribute arcAttr : arcsForAttr) {
			if (arcAttr.getReference() instanceof IArc) {
				IArc arc = (IArc) arcAttr.getReference();
				Dimension middleDelta = arcsMiddleDelta.get(arc);
				if (middleDelta != null) {
					arcAttr.getGraphicInfo().setLocation(arcAttr.getGraphicInfo().getLocation().getTranslated(middleDelta));
				}
			}
		}
		
		// Move node attributes
		for (IAttribute attr : attributes) {
			attr.getGraphicInfo().setLocation(attr.getGraphicInfo().getLocation().getTranslated(delta));
		}
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		// Undo the move of the node
		element.getLocationInfo().setLocation(oldLocation);

		// The move of inflex points
		for (IArc arc : arcsForPI) {
			arc.modifyInflexPoints(-delta.width, -delta.height);
		}
		
		// Arc's attribute
		for (IAttribute arcAttr : arcsForAttr) {
			if (arcAttr.getReference() instanceof IArc) {
				IArc arc = (IArc) arcAttr.getReference();
				Dimension middleDelta = arcsMiddleDelta.get(arc);
				if (middleDelta != null) {
					arcAttr.getGraphicInfo().setLocation(arcAttr.getGraphicInfo().getLocation().getTranslated(middleDelta.getNegated()));
				}
			}
		}
		// Node's attribute
		for (IAttribute attr : attributes) {
			attr.getGraphicInfo().setLocation(attr.getGraphicInfo().getLocation().getTranslated(delta.getNegated()));
		}
	}
}
