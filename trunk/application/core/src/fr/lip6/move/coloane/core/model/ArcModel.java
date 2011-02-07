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
package fr.lip6.move.coloane.core.model;

import fr.lip6.move.coloane.core.model.interfaces.ILink;
import fr.lip6.move.coloane.core.model.interfaces.ILinkableElement;
import fr.lip6.move.coloane.interfaces.formalism.IArcFormalism;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IArcGraphicInfo;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

/**
 * Describe an <b>arc</b> model object
 *
 * @author Jean-Baptiste Voron
 * @author Clément Démoulins
 */
public class ArcModel extends AbstractElement implements IArc, ILinkableElement {
	/** logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** The arc formalism */
	private final IArcFormalism arcFormalism;
	/** All graphical information about this arc */
	private final IArcGraphicInfo graphicInfo;

	/** Source */
	private INode source;
	/** Target */
	private INode target;
	
	/** List of bend points */
	private List<AbsoluteBendpoint> inflexPoints = new ArrayList<AbsoluteBendpoint>();

	/**
	 * Links (used by StickyNote objects)
	 * @see StickyNoteModel
	 * @see LinkModel
	 */
	private List<ILink> links = new ArrayList<ILink>();

	/**
	 * Constructor
	 *
	 * @param parent The object that owns the arc (often the graph object)
	 * @param arcFormalism The formalism that describe the arc
	 * @param id Arc ID
	 * @param source Arc source
	 * @param target Arc target
	 */
	ArcModel(IElement parent, IArcFormalism arcFormalism, int id, INode source, INode target) {
		super(id, parent, arcFormalism.getAttributes(), arcFormalism.getComputedAttributes());
		LOGGER.finest("Build an arc: " + arcFormalism.getName() + ", #" + source.getId() + " -> #" + target.getId()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		if (source == null || target == null) {
			throw new NullPointerException("Source and target nodes must be not null"); //$NON-NLS-1$
		}
		this.arcFormalism = arcFormalism;
		this.source = source;
		this.target = target;
		this.graphicInfo = new ArcGraphicInfo(this);
	}

	/**
	 * Delete all links attached to this arc
	 */
	final void delete() {
		// Browse all links and tell the source that the link will be destroyed
		for (ILink link : new ArrayList<ILink>(links)) {
			link.disconnect();
		}

		// The sticky links list should be empty (due to link.disconnect())
		if (!links.isEmpty()) {
			LOGGER.warning("The sticky link list is not clean... cleaning it now"); //$NON-NLS-1$
			links.clear();
		}
	}

	/** {@inheritDoc} */
	public final INode getSource() {
		return source;
	}

	/** {@inheritDoc} */
	public final INode getTarget() {
		return target;
	}

	/** {@inheritDoc} */
	public final IArcFormalism getArcFormalism() {
		return arcFormalism;
	}

	/** {@inheritDoc} */
	public final IArcGraphicInfo getGraphicInfo() {
		return graphicInfo;
	}

	/** {@inheritDoc} */
	public final void addInflexPoint(Point p, int index) {
		LOGGER.finest("Add a bendpoint at location: " + p + " and index:" + index);  //$NON-NLS-1$//$NON-NLS-2$
		inflexPoints.add(index, new AbsoluteBendpoint(p));
		graphicInfo.updateMiddlePoint();
		firePropertyChange(IArc.INFLEXPOINT_PROP, null, this);
	}

	/** {@inheritDoc} */
	public final void addInflexPoint(Point p) {
		LOGGER.finest("Add a bendpoint at location: " + p);  //$NON-NLS-1$
		inflexPoints.add(new AbsoluteBendpoint(p));
		graphicInfo.updateMiddlePoint();
		firePropertyChange(IArc.INFLEXPOINT_PROP, null, this);
	}

	/** {@inheritDoc} */
	public final void removeInflexPoint(int index) {
		LOGGER.finest("Remove the bendpoint at index: " + index);  //$NON-NLS-1$
		inflexPoints.remove(index);
		graphicInfo.updateMiddlePoint();
		firePropertyChange(IArc.INFLEXPOINT_PROP, null, this);
	}

	/** {@inheritDoc} */
	public final void removeAllInflexPoints() {
		LOGGER.finest("Remove all bendpoints");  //$NON-NLS-1$
		inflexPoints.clear();
		graphicInfo.updateMiddlePoint();
		firePropertyChange(IArc.INFLEXPOINT_PROP, null, this);
	}

	/** {@inheritDoc} */
	public final void modifyInflexPoint(int index, Point p) {
		LOGGER.finest("Move the bendpoint at location: " + p + " and at index: " + index);  //$NON-NLS-1$//$NON-NLS-2$
		inflexPoints.get(index).setLocation(p);
		graphicInfo.updateMiddlePoint();
		firePropertyChange(IArc.INFLEXPOINT_PROP, null, this);
	}

	/** {@inheritDoc} */
	public final void modifyInflexPoints(int dx, int dy) {
		LOGGER.finest("Move all the bendpoints accoring to the delta location (" + dx + ", " + dy + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		for (AbsoluteBendpoint inflexPoint : inflexPoints) {
			inflexPoint.translate(dx, dy);
		}
		firePropertyChange(IArc.INFLEXPOINT_PROP, null, this);
	}

	/** {@inheritDoc} */
	public final AbsoluteBendpoint getInflexPoint(int index) {
		return inflexPoints.get(index);
	}

	/** {@inheritDoc} */
	public final List<AbsoluteBendpoint> getInflexPoints() {
		return Collections.unmodifiableList(inflexPoints);
	}

	/** {@inheritDoc} */
	public final void reconnect(INode newSource, INode newTarget) {
		LOGGER.finest("Reconnect the arc #" + newSource.getId() + " -> #" + newTarget.getId()); //$NON-NLS-1$ //$NON-NLS-2$
		((NodeModel) source).removeOutgoingArc(this);
		((NodeModel) target).removeIncomingArc(this);

		this.source = newSource;
		((NodeModel) source).addOutgoingArc(this);
		this.target = newTarget;
		((NodeModel) target).addIncomingArc(this);
	}

	/** {@inheritDoc} */
	public final void updateAttributesPosition() {
		// Current positions
		Point oldMiddlePoint = this.graphicInfo.getMiddlePoint();

		// New middle point computation
		Point newMiddlePoint = this.graphicInfo.findMiddlePoint();

		// Span computation
		Dimension delta = newMiddlePoint.getDifference(oldMiddlePoint);

		// Update locations
		for (IAttribute attr : this.getDrawableAttributes()) {
			Point newAttrLocation = attr.getGraphicInfo().getLocation().getTranslated(delta);
			attr.getGraphicInfo().setLocation(newAttrLocation);
		}

		this.graphicInfo.updateMiddlePoint();
	}

	/** {@inheritDoc} */
	public final void updateTips() {
		firePropertyChange(INode.INCOMING_ARCS_PROP, null, null);
	}

	/** {@inheritDoc} */
	public final void propertyChange(PropertyChangeEvent evt) {
		String prop = evt.getPropertyName();

		if (IAttribute.VALUE_PROP.equals(prop)) {
			// Follow Up
			// TODO: Must be better explained
			firePropertyChange(prop, evt.getOldValue(), evt.getNewValue());
		}
	}

	/** {@inheritDoc} */
	public final void addLink(ILink link) {
		links.add(link);
		firePropertyChange(INode.OUTGOING_ARCS_PROP, null, link);
	}

	/** {@inheritDoc} */
	public final List<ILink> getLinks() {
		return Collections.unmodifiableList(links);
	}

	/** {@inheritDoc} */
	public final boolean removeLink(ILink link) {
		boolean res = links.remove(link);
		firePropertyChange(INode.OUTGOING_ARCS_PROP, null, link);
		return res;
	}

	/** {@inheritDoc} */
	@Override
	public final String toString() {
		return "Arc #" + getId(); //$NON-NLS-1$
	}
}
