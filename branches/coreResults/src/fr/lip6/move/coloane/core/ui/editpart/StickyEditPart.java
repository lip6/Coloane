package fr.lip6.move.coloane.core.ui.editpart;

import fr.lip6.move.coloane.core.model.AbstractPropertyChange;
import fr.lip6.move.coloane.core.model.StickyNoteModel;
import fr.lip6.move.coloane.core.model.interfaces.ILink;
import fr.lip6.move.coloane.core.model.interfaces.ILinkableElement;
import fr.lip6.move.coloane.core.model.interfaces.IStickyNote;
import fr.lip6.move.coloane.core.ui.commands.LinkCompleteCommand;
import fr.lip6.move.coloane.core.ui.commands.LinkCreateCommand;
import fr.lip6.move.coloane.core.ui.commands.LinkReconnectCommand;
import fr.lip6.move.coloane.core.ui.commands.StickyNoteDeleteCmd;
import fr.lip6.move.coloane.core.ui.figures.sticky.StickyNoteFigure;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gef.requests.ReconnectRequest;

/**
 * EditPart pour la gestion des notes.
 */
public class StickyEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener, NodeEditPart {

	private ChopboxAnchor connectionAnchor;

	/** {@inheritDoc} */
	@Override
	protected final void createEditPolicies() {
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new LabelDirectEditPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ComponentEditPolicy() {

			// On autorise la suppression de l'element
			@Override
			protected Command createDeleteCommand(GroupRequest deleteRequest) {
				IGraph parent = (IGraph) getHost().getParent().getModel();
				IStickyNote child = (IStickyNote) getHost().getModel();
				Command cmd =  new StickyNoteDeleteCmd(parent, child);
				return cmd;
			}

		});

		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new GraphicalNodeEditPolicy() {
			@Override
			protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
				Command cmd = null;
				if (request.getStartCommand() instanceof LinkCreateCommand) {
					ILinkableElement source = ((LinkCreateCommand) request.getStartCommand()).getSource();
					if (!(source instanceof IStickyNote)) {
						// On cherche le graph
						IGraph graph;
						if (source instanceof IElement) {
							graph = (IGraph) ((IElement) source).getParent();
						} else if (source instanceof IAttribute) {
							graph = (IGraph) ((IAttribute) source).getReference().getParent();
						} else {
							return null; // si on a aucun moyen de récupérer le graph on annule tout
						}

						ILinkableElement target = (ILinkableElement) getHost().getModel();
						cmd = new LinkCompleteCommand(graph, source, target);
					}
				}
				return cmd;
			}

			@Override
			protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
				Command cmd = null;
				if (request.getNewObjectType() == ILink.class) {
					cmd = new LinkCreateCommand((ILinkableElement) getHost().getModel());
				}
				request.setStartCommand(cmd);
				return cmd;
			}

			@Override
			protected Command getReconnectSourceCommand(ReconnectRequest request) {
				return null;
			}

			@Override
			protected Command getReconnectTargetCommand(ReconnectRequest request) {
				Command cmd = null;
				if (request.getConnectionEditPart() instanceof LinkEditPart) {
					ILink link = (ILink) request.getConnectionEditPart().getModel();
					IStickyNote newNote = (IStickyNote) getHost().getModel();
					cmd = new LinkReconnectCommand(link, newNote, link.getElement());
				}
				return cmd;
			}
		});

	}

	/** {@inheritDoc} */
	@Override
	protected final IFigure createFigure() {
		StickyNoteFigure label = new StickyNoteFigure();
		label.setSize(getStickyNote().getSize());
		label.setLocation(getStickyNote().getLocation());
		connectionAnchor  = new ChopboxAnchor(label);
		return label;
	}

	/**
	 * @return le modèle
	 */
	private StickyNoteModel getStickyNote() {
		return (StickyNoteModel) getModel();
	}

	/**
	 * Affiche une zone d'édition de l'attribut.
	 */
	private void performDirectEdit() {
		new StickyEditManager(this, new StickyCellEditorLocator((StickyNoteFigure) getFigure())).show();
	}

	/** {@inheritDoc} */
	@Override
	public final void performRequest(Request request) {
		if (request.getType() == RequestConstants.REQ_DIRECT_EDIT) {
			performDirectEdit();
		}
	}

	/** {@inheritDoc} */
	public final void propertyChange(PropertyChangeEvent evt) {
		String prop = evt.getPropertyName();

		if (prop.equals(IStickyNote.VALUE_PROP)) {
			refreshVisuals();
		} else if (prop.equals(IStickyNote.LOCATION_PROP)) {
			refreshVisuals();
		} else if (prop.equals(IStickyNote.RESIZE_PROP)) {
			refreshVisuals();
		} else if (prop.equals(INode.INCOMING_ARCS_PROP)) {
			refreshTargetConnections();
		}
	}

	/** {@inheritDoc} */
	@Override
	protected final void refreshVisuals() {
		((StickyNoteFigure) getFigure()).setText(getStickyNote().getLabelContents());

		StickyNoteModel sticky = (StickyNoteModel) getModel();

		Rectangle bounds = new Rectangle(sticky.getLocation(), sticky.getSize());
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), bounds);

		super.refreshVisuals();
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
	public final ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
		return connectionAnchor;
	}

	/** {@inheritDoc} */
	public final ConnectionAnchor getSourceConnectionAnchor(Request request) {
		return connectionAnchor;
	}

	/** {@inheritDoc} */
	public final ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
		return connectionAnchor;
	}

	/** {@inheritDoc} */
	public final ConnectionAnchor getTargetConnectionAnchor(Request request) {
		return connectionAnchor;
	}

	/** {@inheritDoc} */
	@Override
	protected final List<ILink> getModelTargetConnections() {
		return ((ILinkableElement) getModel()).getLinks();
	}
}
