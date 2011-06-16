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

import fr.lip6.move.coloane.core.model.AbstractPropertyChange;
import fr.lip6.move.coloane.core.model.AttributeModel;
import fr.lip6.move.coloane.core.model.interfaces.ISpecialState;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.ILocationInfo;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Logger;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartListener;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.Font;

/**
 * This EditPart is in charge of managing attributes.<br>
 * All attributes are attached to the Graph EditPart because they have to be displayed in the graph drawing space.
 *
 * @author Jean-Baptiste Voron
 */
public class AttributeEditPart extends AbstractGraphicalEditPart implements ISelectionEditPartListener, PropertyChangeListener {
	/** Core Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	// Default spacing between attributes and their parent when displayed
	private static final int GAP = 20;
	// Default extra space needed to handle correctly some italic fonts (under MacOS)
	private static final int EXTRA_SPACE = 2;

	private boolean select = false;
	private boolean elementSelect = false;
	private boolean highlight = false;
	private boolean special = false;

	private Font font;

	/**
	 * Listening state modification from the attribute's parent.
	 */
	private EditPartListener editPartListener = new EditPartListener.Stub() {

		// When the state of current attribute's parent has changed...
		@Override
		public void selectedStateChanged(EditPart part) {
			switch(part.getSelected()) {
			case EditPart.SELECTED:
			case EditPart.SELECTED_PRIMARY:
				elementSelect = true;
				break;
			case EditPart.SELECTED_NONE:
				elementSelect = false;
				break;
			case ISelectionEditPartListener.HIGHLIGHT:
				highlight = true;
				break;
			case ISelectionEditPartListener.HIGHLIGHT_NONE:
				highlight = false;
				break;
			default:
				break;
			}
			// Need to refresh visuals (perhaps some graphical attributes has been changed) !
			refreshVisuals();
		}
	};

	/**
	 * Creates the associated figure.
	 * For the attributes, the figure is, in fact, a <b>label</b>.
	 * @return IFigure the associated figure.
	 */
	@Override
	protected final IFigure createFigure() {
		Label figure = new Label();
		figure.setOpaque(true); // Opacity (!)

		// Fetch the associated attribute model object
		IAttribute attribute = (IAttribute) getModel();

		// Compute the visibility of the attribute
		figure.setVisible(computeVisibility(attribute));

		// Compute the location of the label (try to avoid overlaps)
		Point attributeLocation = computeLocation(attribute);

		// Store graphical location and set the figure position into the editor
		attribute.getGraphicInfo().setLocation(attributeLocation);
		figure.setLocation(attributeLocation);
		return figure;
	}

	/**
	 * If the attribute value matches its default value, the attribute should not be displayed (hidden).
	 * But, the formalism can specify exceptions! In that case, the defaultValueDrawable status has to be checked too.
	 * @param attribute the attribute to query
	 * @return <code>true</code> if the attribute has to be displayed; <code>false</code> otherwise
	 */
	private boolean computeVisibility(IAttribute attribute) {
		// Check the current value and the default value
		if (attribute.getAttributeFormalism().getDefaultValue().equals(attribute.getValue()) && attribute.getAttributes().isEmpty()) {
			if (!attribute.getAttributeFormalism().isDefaultValueDrawable()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Compute the location of an attribute considering its parent type (arc, node or graph...).<br>
	 * If the attribute has already some location information, they have to be used instead !
	 * @param attribute the attribute to query
	 * @return the better location (try to avoid overlaps between attributes)
	 */
	private Point computeLocation(IAttribute attribute) {

		Point attributePosition;

		// If the attribute parent is a node, the attribute if moved
		if (attribute.getReference() instanceof INode) {

			// If the attribute has no location information yet
			if ((attribute.getGraphicInfo().getLocation().x <= 0) && (attribute.getGraphicInfo().getLocation().y <= 0)) {
				Point refLocation = ((INode) attribute.getReference()).getGraphicInfo().getLocation();

				// Check if a deltaLocation is specified
				Point deltaLocation = attribute.getAttributeFormalism().getDeltaLocation();
				if (!deltaLocation.equals(Point.SINGLETON.setLocation(0, 0))) {
					// Compute a new location given the default GAP according to the node position
					attributePosition = new Point(refLocation.x + deltaLocation.x, refLocation.y + deltaLocation.y);

				// If not, just use the GAP constant to locate attributes near their parent object
				} else {
					attributePosition = new Point(refLocation.x + GAP, refLocation.y - GAP);
				}

			// If the attribute has some location information
			} else {
				// Just put the attribute where it must be
				attributePosition = new Point(attribute.getGraphicInfo().getLocation().x, attribute.getGraphicInfo().getLocation().y);
			}

		// It the attribute parent is an arc
		} else if (attribute.getReference() instanceof IArc) {
			// If the attribute has no location information yet
			if ((attribute.getGraphicInfo().getLocation().x <= 0) && (attribute.getGraphicInfo().getLocation().y <= 0)) {
				attributePosition = ((IArc) attribute.getReference()).getGraphicInfo().findMiddlePoint();

			//If the attribute has already some location information
			} else {
				attributePosition = new Point(attribute.getGraphicInfo().getLocation().x, attribute.getGraphicInfo().getLocation().y);
			}

		// If the attribute parent is the graph itself
		} else if (attribute.getReference() instanceof IGraph) {
			attributePosition = new Point(attribute.getGraphicInfo().getLocation().x, attribute.getGraphicInfo().getLocation().y);

		// In any other cases, the attribute position is set to (0,0)
		// It means that the attribute is attached to something strange... (TODO: Is the situation really possible ?)
		} else {
			attributePosition = new Point(0, 0);
		}

		return attributePosition;
	}

	/**
	 * Refresh view side according model information.
	 */
	@Override
	protected final void refreshVisuals() {
		IAttribute attribut = (IAttribute) getModel();
		Label attributeFigure = (Label) getFigure();

		// Update graphical representation
		getFigure().setForegroundColor(attribut.getGraphicInfo().getForeground());
		getFigure().setBackgroundColor(attribut.getGraphicInfo().getBackground());

		// Select state (attribute only or parent ?)
		// TODO: Should be set in a property page
		if (this.select || this.elementSelect) {
			getFigure().setForegroundColor(ColorConstants.blue);
		}
		// Highlight state (mouseover event)
		// TODO: Should be set in a property page
		if (this.highlight) {
			getFigure().setBackgroundColor(ColorConstants.lightGray);
		}
		// Special state (coming from problem view)
		if (special) {
			getFigure().setForegroundColor(ColorConstants.red);
		}

		// Font update
		if (this.font == null || this.font.isDisposed()) {
			this.font = JFaceResources.getDefaultFont();
			if (attribut.getAttributeFormalism().isBold()) {
				this.font = JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT);
			}
			if (attribut.getAttributeFormalism().isItalic()) {
				this.font = JFaceResources.getFontRegistry().getItalic(JFaceResources.DEFAULT_FONT);
			}
			attributeFigure.setFont(this.font);
		}

		attributeFigure.setText(attribut.getValue());

		// Graphical space (i.e. bounds) for the attribute is set here.
		Rectangle bounds = new Rectangle(attribut.getGraphicInfo().getLocation(), new Dimension(attributeFigure.getTextBounds().width + EXTRA_SPACE, attributeFigure.getTextBounds().height));
		if (getParent() != null) {
			((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), bounds);
		}
	}

	/**
	 * Some policies (behaviors) applying to the object
	 */
	@Override
	protected final void createEditPolicies() {
		// Allow the edition of the attribute value directly on the editor (only for standard attributes)
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new AttributeDirectEditPolicy());

		// All rules about select state
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new SelectionEditPolicy() {
			@Override
			protected void hideSelection() {
				select = false;
				refreshVisuals();
			}

			@Override
			protected void showSelection() {
				select = true;
				refreshVisuals();
			}
		});
	}

	/** {@inheritDoc} */
	@Override
	public final void activate() {
		if (!isActive()) {
			super.activate();
			((AbstractPropertyChange) getModel()).addPropertyChangeListener(this);
			if (getParent() instanceof GraphEditPart) {
				GraphEditPart graphEditPart = (GraphEditPart) getParent();
				EditPart parent = graphEditPart.getParentAttributeEditPart(this);
				if (parent != null) {
					addEditPartListener(((ISelectionEditPartListener) parent).getSelectionEditPartListener());
					parent.addEditPartListener(editPartListener);
				}
			}
		}
	}

	/** {@inheritDoc} */
	@Override
	public final void deactivate() {
		if (isActive()) {
			super.deactivate();
			((AbstractPropertyChange) getModel()).removePropertyChangeListener(this);
			if (getParent() instanceof GraphEditPart) {
				GraphEditPart graphEditPart = (GraphEditPart) getParent();
				EditPart parent = graphEditPart.getParentAttributeEditPart(this);
				if (parent != null) {
					setSelected(EditPart.SELECTED_NONE);
					removeEditPartListener(((ISelectionEditPartListener) parent).getSelectionEditPartListener());
					parent.removeEditPartListener(editPartListener);
				}
			}
		}
	}

	/**
	 * Draw a rectangle useful to edit the attribute value
	 * @see #createEditPolicies()
	 */
	private void performDirectEdit() {
		Label label = (Label) getFigure();
		IAttribute model = (IAttribute) getModel();
		new AttributeEditManager(this, model, new AttributeCellEditorLocator(label)).show();
	}

	/** {@inheritDoc} */
	@Override
	public final void performRequest(Request request) {
		if (request.getType() == RequestConstants.REQ_DIRECT_EDIT) {
			// Editing is allowed only for standard attributes
			if (getModel() instanceof AttributeModel) {
				performDirectEdit();
			} else {
				LOGGER.finer("This attribute cannot be edited since it is computed from several attributes value"); //$NON-NLS-1$
			}
		}
	}

	/** {@inheritDoc} */
	public final void propertyChange(PropertyChangeEvent event) {
		String prop = event.getPropertyName();

		// When the value of the attribute is modified somewhere
		if (IAttribute.VALUE_PROP.equals(prop)) {
			IAttribute attribute = (IAttribute) getModel();

			// Compute the visibility of the attribute
			getFigure().setVisible(computeVisibility(attribute));
			// Compute new location
			// attribute.getGraphicInfo().setLocation(computeLocation(attribute));

			refreshVisuals();

		// When the attribute is moved
		} else if (ILocationInfo.LOCATION_PROP.equals(prop)) {

			// Deal with the special case where the location of the attribute has been reseted (-1,-1).
			// In that case a new location has to be computed again.
			Point newLocation = (Point) event.getNewValue();
			if (newLocation.equals(Point.SINGLETON.setLocation(-1, -1))) {
				IAttribute attribute = (IAttribute) getModel();
				attribute.getGraphicInfo().setLocation(computeLocation(attribute));
			}
			// In all cases, the view must be refreshed
			refreshVisuals();
		} else if (ISpecialState.SPECIAL_STATE_CHANGE.equals(prop)) {
			special = (Boolean) event.getNewValue();
			refreshVisuals();
		}
	}

	/** {@inheritDoc} */
	public final EditPartListener getSelectionEditPartListener() {
		return editPartListener;
	}
}
