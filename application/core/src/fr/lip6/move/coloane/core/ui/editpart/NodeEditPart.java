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

import fr.lip6.move.coloane.core.formalisms.elements.GraphicalDescription;
import fr.lip6.move.coloane.core.model.AbstractPropertyChange;
import fr.lip6.move.coloane.core.model.interfaces.ICoreTip;
import fr.lip6.move.coloane.core.model.interfaces.ILink;
import fr.lip6.move.coloane.core.model.interfaces.ILinkableElement;
import fr.lip6.move.coloane.core.model.interfaces.ISpecialState;
import fr.lip6.move.coloane.core.model.interfaces.IStickyNote;
import fr.lip6.move.coloane.core.session.SessionManager;
import fr.lip6.move.coloane.core.ui.ColoaneEditor;
import fr.lip6.move.coloane.core.ui.commands.ArcCompleteCmd;
import fr.lip6.move.coloane.core.ui.commands.ArcCreateCmd;
import fr.lip6.move.coloane.core.ui.commands.ArcReconnectCmd;
import fr.lip6.move.coloane.core.ui.commands.LinkCompleteCmd;
import fr.lip6.move.coloane.core.ui.commands.LinkCreateCmd;
import fr.lip6.move.coloane.core.ui.commands.LinkReconnectCmd;
import fr.lip6.move.coloane.core.ui.commands.NodeDeleteCmd;
import fr.lip6.move.coloane.core.ui.figures.INodeFigure;
import fr.lip6.move.coloane.core.ui.figures.nodes.EllipseNode;
import fr.lip6.move.coloane.core.ui.prefs.ColorsPrefs;
import fr.lip6.move.coloane.interfaces.formalism.IArcFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IGraphicalDescription;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.ILocationInfo;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.model.INodeGraphicInfo;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartListener;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

/**
 * EditPart in charge of nodes management
 *
 * @author Jean-Baptiste Voron
 * {@link}
 */
public class NodeEditPart extends AbstractGraphicalEditPart implements ISelectionEditPartListener, PropertyChangeListener, org.eclipse.gef.NodeEditPart {
	/** Core Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	private boolean select = false;
	private boolean special = false;
	private boolean highlight = false;
	private boolean attributeSelect = false;

	private ConnectionAnchor connectionAnchor;

	/** The list of all alternative figures for this node */
	private List<INodeFigure> alternativeFigures = new ArrayList<INodeFigure>();

	/** Listen to changes in attributes selection state.<br>
	 *  If an attribute is selected, the parent node is highlighted !
	 *  @see AttributeEditPart
	 */
	private EditPartListener editPartListener = new EditPartListener.Stub() {
		/** {@inheritDoc} */
		@Override
		public void selectedStateChanged(EditPart editPart) {
			switch(editPart.getSelected()) {
			case EditPart.SELECTED:
			case EditPart.SELECTED_PRIMARY:
				attributeSelect = true;
				refreshVisuals();
				break;
			case EditPart.SELECTED_NONE:
				attributeSelect = false;
				refreshVisuals();
				break;
			default:
				break;
			}
		}
	};

	/**
	 * Create the figure associated to the node.<br>
	 * The figure is defined in a graphical description object {@link GraphicalDescription}.<br>
	 * A node can define several graphical descriptions. The first one is considered as default one.<br>
	 * Other are stacked to the first one waiting for to be set to visible state.
	 * @return IFigure
	 */
	@Override
	protected final IFigure createFigure() {
		// In fact, the figure is a container with a stacklayout manager to be able to switch between alternate figures
		IFigure nodeContainer = new Figure();
		nodeContainer.setLayoutManager(new StackLayout());

		INode node = (INode) getModel();
		INodeGraphicInfo nodeGraphicalInfo = node.getGraphicInfo();

		for (IGraphicalDescription graphicalDescription : nodeGraphicalInfo.getAllNodeFormalismGraphicalDescriptions()) {
			INodeFigure nodeFigure;

			// Check whether this figure is the default one (the first)
			if (this.alternativeFigures.isEmpty()) {
				nodeFigure = (INodeFigure) graphicalDescription.getAssociatedFigure();
				if (nodeFigure == null) {
					LOGGER.warning("No node figure found. The default figure will be used"); //$NON-NLS-1$
					// TODO: Change the default figure (by a big point ?)
					nodeFigure = new EllipseNode();
				}
				nodeFigure.setVisible(true);
			} else {
				nodeFigure = (INodeFigure) graphicalDescription.getAssociatedFigure();
				nodeFigure.setVisible(false);
			}

			// Add the created figure to the stack container and to the list of alternative figures
			alternativeFigures.add(nodeFigure);

			// Tell the figure what is its model element
			nodeFigure.setModelElement((INode) node);

			// Some graphical considerations
			nodeFigure.setSize(nodeGraphicalInfo.getSize());
			nodeFigure.setForegroundColor(nodeGraphicalInfo.getForeground());
			nodeFigure.setBackgroundColor(nodeGraphicalInfo.getBackground());
			nodeContainer.add(nodeFigure);
		}
		return nodeContainer;
	}

	/**
	 * Since the node figure is a container of several graphical representations, update of one figure is <i>easy</i>.<br>
	 * This method returns the inside figure (which is currently visible)
	 * @return The figure (inside the container) that must be updated
	 */
	private INodeFigure getRealFigure() {
		for (INodeFigure figure : this.alternativeFigures) {
			if (figure.isVisible()) {
				return figure;
			}
		}
		return alternativeFigures.get(0);
	}

	/**
	 * Update the figures thanks to the information contained into the model
	 */
	@Override
	protected final void refreshVisuals() {
		// If the node has no more parent, that means that it has been deleted.
		// There is no more reason to draw it again
		if (getParent() == null) {
			return;
		}

		// Update the figure
		getRealFigure().setForegroundColor(((INode) getModel()).getGraphicInfo().getForeground());
		getRealFigure().setBackgroundColor(((INode) getModel()).getGraphicInfo().getBackground());
		getRealFigure().setLineWidth(1);

		if (select) {
			getRealFigure().setForegroundColor(ColorsPrefs.getColor("COLORNODE")); //$NON-NLS-1$
			getRealFigure().setLineWidth(3);
		} else if (special) {
			getRealFigure().setForegroundColor(ColorConstants.red);
			getRealFigure().setLineWidth(3);
		} else if (attributeSelect) {
			getRealFigure().setBackgroundColor(ColorsPrefs.getColor("COLORNODE_HIGHLIGHT")); //$NON-NLS-1$
		} else if (highlight) {
			getRealFigure().setBackgroundColor(ColorsPrefs.getColor("COLORNODE_MOUSE")); //$NON-NLS-1$
		}

		INode nodeModel = (INode) getModel();
		Rectangle bounds = new Rectangle(nodeModel.getGraphicInfo().getLocation(), nodeModel.getGraphicInfo().getSize());
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), bounds);
	}

	/**
	 * Handle events caught by this EditPart
	 * @param property The event that has been caught
	 */
	public final void propertyChange(PropertyChangeEvent property) {
		String prop = property.getPropertyName();

		// Event that announce a link change
		if (INode.INCOMING_ARCS_PROP.equals(prop)) {
			LOGGER.finest("Update incoming arcs"); //$NON-NLS-1$
			refreshTargetConnections();
		} else if (INode.OUTGOING_ARCS_PROP.equals(prop)) {
			LOGGER.finest("Update outgoing arcs"); //$NON-NLS-1$
			refreshSourceConnections();

		// Event that announce a color change
		} else if (INode.FOREGROUND_COLOR_PROP.equalsIgnoreCase(prop)) {
			getRealFigure().setForegroundColor((Color) property.getNewValue());
			refreshVisuals();
		} else if (INode.BACKGROUND_COLOR_PROP.equalsIgnoreCase(prop)) {
			getRealFigure().setBackgroundColor((Color) property.getNewValue());
			refreshVisuals();

		// Event that announce a size change
		} else if (INode.RESIZE_PROP.equalsIgnoreCase(prop)) {
			INodeFigure nodeFigure = getRealFigure();
			Rectangle oldRect = nodeFigure.getClientArea();
			nodeFigure.setSize((Dimension) property.getNewValue());
			((GraphEditPart) getParent()).getFigure().repaint(oldRect);
			refreshVisuals();

		// Event that announce that the node has been selected as SPECIAL node !
		} else if (ISpecialState.SPECIAL_STATE_CHANGE.equals(prop)) {
			special = (Boolean) property.getNewValue();
			refreshVisuals();

		// Event that announce a location change
		} else if (ILocationInfo.LOCATION_PROP.equals(prop)) {
			refreshVisuals();

		// Event that announce a switch of graphical feature
		} else if (INode.ALTERNATE_PROP.equalsIgnoreCase(prop)) {
			INodeFigure oldFigure = this.alternativeFigures.get((Integer) property.getOldValue());
			INodeFigure newFigure = this.alternativeFigures.get((Integer) property.getNewValue());
			if (!(oldFigure.equals(newFigure))) {
				oldFigure.setVisible(false);
				newFigure.setVisible(true);

				Rectangle oldRect = newFigure.getClientArea();
				figure.setSize((Dimension) ((INode) getModel()).getGraphicInfo().getSize());
				((GraphEditPart) getParent()).getFigure().repaint(oldRect);
				refreshVisuals();
				refreshVisuals();
			}
		} else if (INode.VALUE_PROP.equalsIgnoreCase(prop)) {
			refreshVisuals();
		}
	}

	/**
	 * Node Management.<br>
	 * Define some rules about the node behavior.
	 */
	@Override
	protected final void createEditPolicies() {
		// Selection policy
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new SelectionEditPolicy() {
			// What to do when object is selected ?
			@Override
			protected void showSelection() {
				select = true;
				refreshVisuals();
			}
			// What to do when object is unselected
			@Override
			protected void hideSelection() {
				select = false;
				refreshVisuals();
			}
		});

		// Basic policy
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ComponentEditPolicy() {

			// A node can be removed/deleted. It is done by a dedicated DeleteCommand
			@Override
			protected Command createDeleteCommand(GroupRequest deleteRequest) {
				IGraph parent = (IGraph) getHost().getParent().getModel();
				INode child = (INode) getHost().getModel();
				Command cmd =  new NodeDeleteCmd(parent, child);
				return cmd;
			}
		});

		// Connections between nodes
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new GraphicalNodeEditPolicy() {

			/**
			 * First step of connection creation.<br>
			 * When a user click on a start node, the create command {@link ArcCreateCmd} or {@link LinkCreateCmd} is called.
			 * @return the creation command or <code>null</code> if the connection is not possible from this node
			 * @see getConnectionCompleteCommand
			 *
			 */
			@Override
			protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
				INode source = (INode) getHost().getModel();
				Command cmd = null;

				// The connection is either an arc or an sticky link
				if (request.getNewObjectType() == IArc.class) {
					cmd = new ArcCreateCmd(source, (IArcFormalism) request.getNewObject());
				} else if (request.getNewObjectType() == ILink.class && source instanceof ILinkableElement) {
					cmd = new LinkCreateCmd((ILinkableElement) source);
				}

				request.setStartCommand(cmd);
				return cmd;
			}

			/**
			 * Second step of connection creation.<br>
			 * When a user chooses an ending node, the complete command {@link ArcCompleteCommand} is created.
			 * @return the command or <code>null</code> if the connection is not possible to this node
			 */
			@Override
			protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
				Command cmd = null;

				// In case of Arc connection
				if (request.getStartCommand() instanceof ArcCreateCmd) {
					INode source = ((ArcCreateCmd) request.getStartCommand()).getSource();
					INode target = (INode) getHost().getModel();
					IArcFormalism arcFormalism = ((ArcCreateCmd) request.getStartCommand()).getArcFormalism();
					cmd = new ArcCompleteCmd(source, target, arcFormalism);
				// In case of StickyLink connection
				} else if (request.getStartCommand() instanceof LinkCreateCmd) {
					ILinkableElement source = ((LinkCreateCmd) request.getStartCommand()).getSource();
					if (source instanceof IStickyNote) {
						ILinkableElement target = (ILinkableElement) getHost().getModel();
						cmd = new LinkCompleteCmd((IStickyNote) source, target);
					}
				}
				return cmd;
			}

			/**
			 * Source of arcs or sticky links connections can be changed at any time without destroying the existing connection.<br>
			 * This operation is done thanks to the {@link ArcReconnectCmd} or {@link LinkReconnectCmd} command.<br>
			 */
			@Override
			protected Command getReconnectSourceCommand(ReconnectRequest request) {
				Command cmd = null;

				// If case of Arc reconnection (source reconnection)
				if (request.getConnectionEditPart() instanceof ArcEditPart) {
					IArc arc = (IArc) request.getConnectionEditPart().getModel();
					INode newSource = (INode) getHost().getModel();
					ArcReconnectCmd reconnectCmd = new ArcReconnectCmd(arc);
					reconnectCmd.setNewSource(newSource);
					cmd = reconnectCmd;
				// If case of Sticky Link reconnection (source reconnection)
				} else if (request.getConnectionEditPart() instanceof LinkEditPart) {
					ILink link = (ILink) request.getConnectionEditPart().getModel();
					ILinkableElement newElement = (ILinkableElement) getHost().getModel();
					cmd = new LinkReconnectCmd(link, link.getNote(), newElement);
				}
				return cmd;
			}

			/**
			 * Only target of arcs can be changed without destroying the connection.
			 * This operation is done thanks to the {@link ArcReconnectCmd} command.<br>
			 */
			@Override
			protected Command getReconnectTargetCommand(ReconnectRequest request) {
				Command cmd = null;

				if (request.getConnectionEditPart() instanceof ArcEditPart) {
					IArc arc = (IArc) request.getConnectionEditPart().getModel();
					INode newTarget = (INode) getHost().getModel();
					ArcReconnectCmd reconnectCmd = new ArcReconnectCmd(arc);
					reconnectCmd.setNewTarget(newTarget);
					cmd = reconnectCmd;
				}

				return cmd;
			}
		});

		// Add listeners to able able to detect when the mouse is over/out the node
		getFigure().addMouseMotionListener(new MouseMotionListener.Stub() {
			@Override
			public void mouseEntered(MouseEvent me) {
				highlight = true;
				int previousState = getSelected();
				setSelected(ISelectionEditPartListener.HIGHLIGHT);
				setSelected(previousState);
			}

			@Override
			public void mouseExited(MouseEvent me) {
				highlight = false;
				int previousState = getSelected();
				setSelected(ISelectionEditPartListener.HIGHLIGHT_NONE);
				setSelected(previousState);
			}
		});
	}

	/**
	 * Create anchors to deal with connections
	 * @return ConnectionAnchor
	 */
	protected final ConnectionAnchor getConnectionAnchor() {
		if (connectionAnchor == null) {
			connectionAnchor = getRealFigure().getConnectionAnchor();
		}
		return connectionAnchor;
	}

	/**
	 * @return a list of all outgoing connections (arcs or sticky links)
	 */
	@Override
	protected final List<Object> getModelSourceConnections() {
		List<Object> sources = new ArrayList<Object>();
		sources.addAll(((INode) getModel()).getOutgoingArcs());
		sources.addAll(((ILinkableElement) getModel()).getLinks());
		return sources;
	}

	/**
	 * @return a list of all incoming connections (arcs or tip links)
	 */
	@Override
	protected final List<Object> getModelTargetConnections() {
		List<Object> targets = new ArrayList<Object>();
		targets.addAll(((INode) getModel()).getIncomingArcs());
		for (ICoreTip tip : SessionManager.getInstance().getCurrentSession().getTipForObject(((INode) getModel()).getId())) {
			targets.add(((ICoreTip) tip).getArcModel());
		}
		return targets;
	}

	/** {@inheritDoc} */
	public final ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
		return getConnectionAnchor();
	}

	/** {@inheritDoc} */
	public final ConnectionAnchor getSourceConnectionAnchor(Request request) {
		return getConnectionAnchor();
	}

	/** {@inheritDoc} */
	public final ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
		return getConnectionAnchor();
	}

	/** {@inheritDoc} */
	public final ConnectionAnchor getTargetConnectionAnchor(Request request) {
		return getConnectionAnchor();
	}

	/** {@inheritDoc} */
	@Override
	public final void activate() {
		if (!isActive()) {
			super.activate();
			((AbstractPropertyChange) getModel()).addPropertyChangeListener(this);
		}
	}

	/** {@inheritDoc} */
	@Override
	public final void deactivate() {
		if (isActive()) {
			super.deactivate();
			((AbstractPropertyChange) getModel()).removePropertyChangeListener(this);
		}
	}

	/** {@inheritDoc} */
	public final EditPartListener getSelectionEditPartListener() {
		return editPartListener;
	}

	/** {@inheritDoc} */
	@Override
	public final void performRequest(Request req) {
		INode node = (INode) getModel();

		// Double click on the node : follow the link (if exist)
		if (req.getType().equals(RequestConstants.REQ_OPEN)) {
			String path = node.getNodeLink().replaceAll("(.*)@.*", "$1"); //$NON-NLS-1$ //$NON-NLS-2$
			IResource res = ResourcesPlugin.getWorkspace().getRoot().findMember(path);
			if (res instanceof IFile) {
				try {
					IEditorPart editor = IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), (IFile) res);
					if (editor instanceof ColoaneEditor) {
						ColoaneEditor coloaneEditor = (ColoaneEditor) editor;
						int id = Integer.valueOf(((INode) getModel()).getNodeLink().replaceAll(".*@(.*)", "$1")); //$NON-NLS-1$ //$NON-NLS-2$
						INode targetNode = coloaneEditor.getGraph().getNode(id);

						// Interface doesn't exist
						if (targetNode == null || !targetNode.isInterface()) {
							return;
						}

						GraphicalViewer viewer = (GraphicalViewer) editor.getAdapter(GraphicalViewer.class);
						viewer.deselectAll();
						viewer.appendSelection((EditPart) viewer.getEditPartRegistry().get(targetNode));
					}
				} catch (PartInitException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
