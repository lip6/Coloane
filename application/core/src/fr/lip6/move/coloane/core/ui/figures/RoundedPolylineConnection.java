/*******************************************************************************
 * Copyright (c) 2000, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package fr.lip6.move.coloane.core.ui.figures;

import org.eclipse.draw2d.AnchorListener;
import org.eclipse.draw2d.ArrowLocator;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.ConnectionRouter;
import org.eclipse.draw2d.DelegatingLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * An implementation of {@link Connection} based on Polyline.  PolylineConnection adds
 * the following additional features:
 * <UL>
 * <LI>
 *   A {@link ConnectionRouter} may be provided which will be used to determine the
 *   connections points.
 * <LI>
 *   Children may be added. The bounds calculation is extended such that the bounds is
 *   the smallest Rectangle which is large enough to display the Polyline and all of its
 *   children figures.
 * <LI>
 *   A {@link DelegatingLayout} is set as the default layout.  A delegating layout allows
 *   children to position themselves via {@link Locator Locators}.
 * </UL>
 * <P>
 */
public class RoundedPolylineConnection extends RoundedPolyline implements Connection, AnchorListener {

	private ConnectionAnchor startAnchor, endAnchor;
	private ConnectionRouter connectionRouter = ConnectionRouter.NULL;
	private RotatableDecoration startArrow, endArrow;

	{
		setLayoutManager(new DelegatingLayout());
		addPoint(new Point(0, 0));
		addPoint(new Point(100, 100));
	}

	/**
	 * Hooks the source and target anchors.
	 * @see Figure#addNotify()
	 */
	@Override
	public final void addNotify() {
		super.addNotify();
		hookSourceAnchor();
		hookTargetAnchor();
	}

	/**
	 * Called by the anchors of this connection when they have moved, revalidating this
	 * polyline connection.
	 * @param anchor the anchor that moved
	 */
	@Override
	public final void anchorMoved(ConnectionAnchor anchor) {
		revalidate();
	}

	/**
	 * Returns the bounds which holds all the points in this polyline connection. Returns any
	 * previously existing bounds, else calculates by unioning all the children's
	 * dimensions.
	 * @return the bounds
	 */
	@Override
	public final Rectangle getBounds() {
		if (bounds == null) {
			super.getBounds();
			for (int i = 0; i < getChildren().size(); i++) {
				IFigure child = (IFigure) getChildren().get(i);
				bounds.union(child.getBounds());
			}
		}
		return bounds;
	}

	/**
	 * Returns the <code>ConnectionRouter</code> used to layout this connection. Will not
	 * return <code>null</code>.
	 * @return this connection's router
	 */
	@Override
	public final ConnectionRouter getConnectionRouter() {
		return connectionRouter;
	}

	/**
	 * Returns this connection's routing constraint from its connection router.  May return
	 * <code>null</code>.
	 * @return the connection's routing constraint
	 */
	@Override
	public final Object getRoutingConstraint() {
		if (getConnectionRouter() != null) {
			return getConnectionRouter().getConstraint(this);
		} else {
			return null;
		}
	}

	/**
	 * @return the anchor at the start of this polyline connection (may be null)
	 */
	@Override
	public final ConnectionAnchor getSourceAnchor() {
		return startAnchor;
	}

	/**
	 * @return the source decoration (may be null)
	 */
	protected final RotatableDecoration getSourceDecoration() {
		return startArrow;
	}

	/**
	 * @return the anchor at the end of this polyline connection (may be null)
	 */
	@Override
	public final ConnectionAnchor getTargetAnchor() {
		return endAnchor;
	}

	/**
	 * @return the target decoration (may be null)
	 *
	 * @since 2.0
	 */
	protected final RotatableDecoration getTargetDecoration() {
		return endArrow;
	}

	/**
	 *
	 */
	private void hookSourceAnchor() {
		if (getSourceAnchor() != null) {
			getSourceAnchor().addAnchorListener(this);
		}
	}

	/**
	 *
	 */
	private void hookTargetAnchor() {
		if (getTargetAnchor() != null) {
			getTargetAnchor().addAnchorListener(this);
		}
	}

	/**
	 * Layouts this polyline. If the start and end anchors are present, the connection router
	 * is used to route this, after which it is laid out. It also fires a moved method.
	 */
	@Override
	@SuppressWarnings("deprecation")
	public final void layout() {
		if (getSourceAnchor() != null && getTargetAnchor() != null) {
			getConnectionRouter().route(this);
		}

		Rectangle oldBounds = bounds;
		super.layout();
		bounds = null;

		if (!getBounds().contains(oldBounds)) {
			getParent().translateToParent(oldBounds);
			getUpdateManager().addDirtyRegion(getParent(), oldBounds);
		}

		repaint();
		fireMoved();
	}

	/**
	 * Called just before the receiver is being removed from its parent. Results in removing
	 * itself from the connection router.
	 *
	 * @since 2.0
	 */
	@Override
	public final void removeNotify() {
		unhookSourceAnchor();
		unhookTargetAnchor();
		getConnectionRouter().remove(this);
		super.removeNotify();
	}

	/**
	 * @see edu.mit.csail.relo.eclipse.gef.IFigure#revalidate()
	 */
	@Override
	public final void revalidate() {
		super.revalidate();
		getConnectionRouter().invalidate(this);
	}

	/**
	 * Sets the connection router which handles the layout of this polyline. Generally set by
	 * the parent handling the polyline connection.
	 * @param cr the connection router
	 */
	@Override
	public final void setConnectionRouter(ConnectionRouter cr) {
		ConnectionRouter router = cr;
		if (router == null) {
			router = ConnectionRouter.NULL;
		}
		if (connectionRouter != router) {
			connectionRouter.remove(this);
			Object old = connectionRouter;
			connectionRouter = router;
			firePropertyChange(Connection.PROPERTY_CONNECTION_ROUTER, old, router);
			revalidate();
		}
	}

	/**
	 * Sets the routing constraint for this connection.
	 * @param cons the constraint
	 */
	@Override
	public final void setRoutingConstraint(Object cons) {
		if (getConnectionRouter() != null) {
			getConnectionRouter().setConstraint(this, cons);
		}
		revalidate();
	}

	/**
	 * Sets the anchor to be used at the start of this polyline connection.
	 * @param anchor the new source anchor
	 */
	@Override
	public final void setSourceAnchor(ConnectionAnchor anchor) {
		unhookSourceAnchor();
		//No longer needed, revalidate does this.
		//getConnectionRouter().invalidate(this);
		startAnchor = anchor;
		if (getParent() != null) {
			hookSourceAnchor();
		}
		revalidate();
	}

	/**
	 * Sets the decoration to be used at the start of the {@link Connection}.
	 * @param dec the new source decoration
	 * @since 2.0
	 */
	public final void setSourceDecoration(RotatableDecoration dec) {
		if (getSourceDecoration() != null) {
			remove(getSourceDecoration());
		}
		startArrow = dec;
		if (dec != null) {
			add(dec, new ArrowLocator(this, ConnectionLocator.SOURCE));
		}
	}

	/**
	 * Sets the anchor to be used at the end of the polyline connection. Removes this listener
	 * from the old anchor and adds it to the new anchor.
	 * @param anchor the new target anchor
	 */
	@Override
	public final void setTargetAnchor(ConnectionAnchor anchor) {
		unhookTargetAnchor();
		//No longer needed, revalidate does this.
		//getConnectionRouter().invalidate(this);
		endAnchor = anchor;
		if (getParent() != null) {
			hookTargetAnchor();
		}
		revalidate();
	}

	/**
	 * Sets the decoration to be used at the end of the {@link Connection}.
	 * @param dec the new target decoration
	 */
	public final void setTargetDecoration(RotatableDecoration dec) {
		if (getTargetDecoration() != null) {
			remove(getTargetDecoration());
		}
		endArrow = dec;
		if (dec != null) {
			add(dec, new ArrowLocator(this, ConnectionLocator.TARGET));
		}
	}

	/**
	 *
	 */
	private void unhookSourceAnchor() {
		if (getSourceAnchor() != null) {
			getSourceAnchor().removeAnchorListener(this);
		}
	}

	/**
	 *
	 */
	private void unhookTargetAnchor() {
		if (getTargetAnchor() != null) {
			getTargetAnchor().removeAnchorListener(this);
		}
	}
}
