package fr.lip6.move.coloane.core.ui.editpart;

import fr.lip6.move.coloane.core.model.AbstractPropertyChange;
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
	 * Creates the associated figure</br>
	 * For the attributes, the figure is, in fact, a <b>label</b>.
	 * @return IFigure
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
	 * If the attribute value matches its default value, the attribute should not be displayed (hidden)</br>
	 * But, the formalism can specify exceptions! In that case, the defaultValueDrawable status has to be checked too.
	 * @return <code>true</code> if the attribute has to be displayed; <code>false</code> otherwise
	 */
	private boolean computeVisibility(IAttribute attribute) {
		// Check the current value and the default value
		if (attribute.getAttributeFormalism().getDefaultValue().equals(attribute.getValue())) {
			if (!attribute.getAttributeFormalism().isDefaultValueDrawable()) {
				return false;
			} 
		}
		return true;
	}

	/**
	 * Compute the location of an attribute considering its parent type (arc, node or graph...).<br>
	 * If the attribute has already some location information, they have to be used instead !
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

		// The label is filled with the attribute value !
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
		// Allow the edition of the attribute value directly on the editor
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
			performDirectEdit();
		}
	}

	/** {@inheritDoc} */
	public final void propertyChange(PropertyChangeEvent event) {
		LOGGER.finest("Evénement pour un attribut: " + event.getPropertyName());  //$NON-NLS-1$
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
		}
	}

	/** {@inheritDoc} */
	public final EditPartListener getSelectionEditPartListener() {
		return editPartListener;
	}
}
