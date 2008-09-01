package fr.lip6.move.coloane.core.ui.editpart;

import fr.lip6.move.coloane.core.model.AbstractPropertyChange;
import fr.lip6.move.coloane.core.model.StickyNoteModel;
import fr.lip6.move.coloane.core.model.interfaces.IStickyNote;
import fr.lip6.move.coloane.core.ui.commands.StickyNoteDeleteCmd;
import fr.lip6.move.coloane.core.ui.figures.sticky.StickyNoteFigure;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

/**
 * EditPart pour la gestion des notes.
 */
public class StickyEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener {

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
	}

	/** {@inheritDoc} */
	@Override
	protected final IFigure createFigure() {
		StickyNoteFigure label = new StickyNoteFigure();
		label.setSize(getStickyNote().getSize());
		label.setLocation(getStickyNote().getLocation());
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
}
