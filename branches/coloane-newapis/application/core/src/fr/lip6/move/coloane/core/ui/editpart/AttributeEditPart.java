 package fr.lip6.move.coloane.core.ui.editpart;

import fr.lip6.move.coloane.core.model.AbstractPropertyChange;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
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
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;

/**
 * Cet EditPart est responsable de la gestion des attributs.
 */
public class AttributeEditPart extends AbstractGraphicalEditPart implements ISelectionEditPartListener, PropertyChangeListener {
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	private static final int GAP = 20;
	private static final int MINGAP = 20;

	/**
	 * Permet d'écouter les changements de sélection de ses "parents"
	 */
	private EditPartListener editPartListener = new EditPartListener.Stub() {
		@Override
		public void selectedStateChanged(EditPart part) {
			switch(part.getSelected()) {
			case EditPart.SELECTED:
			case EditPart.SELECTED_PRIMARY:
				getFigure().setForegroundColor(ColorConstants.blue);
				break;
			case EditPart.SELECTED_NONE:
				getFigure().setForegroundColor(ColorConstants.black);
				break;
			case ISelectionEditPartListener.HIGHLIGHT:
				getFigure().setBackgroundColor(ColorConstants.lightGray);
				break;
			case ISelectionEditPartListener.HIGHLIGHT_NONE:
				getFigure().setBackgroundColor(ColorConstants.white);
				break;
			case ISelectionEditPartListener.SPECIAL:
				break;
			case ISelectionEditPartListener.SPECIAL_NONE:
				break;
			default:
				break;
			}
		}
	};

	/**
	 * Creation de la figure associee<br>
	 * Pour les attribut, on considere que la vue doit affiche un Label
	 * @return IFigure
	 */
	@Override
	protected final IFigure createFigure() {
		Label figure = new Label();
		figure.setOpaque(true);

		// Localisation
		IAttribute attribut = (IAttribute) getModel();

		// On cache la figure si l'attribut est à la valeur par défaut
		if (attribut.getAttributeFormalism().getDefaultValue().equals(attribut.getValue())) {
			figure.setVisible(false);
		}

		if (!attribut.getValue().equals(attribut.getAttributeFormalism().getDefaultValue())) {
			Point attributeLocation = calculLocation();

			// Stocke les information de positionnement
			attribut.getGraphicInfo().setLocation(attributeLocation);

			// Positionnement graphique
			figure.setLocation(attributeLocation);
		}

		return figure;
	}

	/**
	 * 
	 * @return
	 */
	private Point calculLocation() {
		IAttribute attribut = (IAttribute) getModel();
		Point attributePosition;

		// Si le referent est un noeud, on agit sur la position de l'attribut
		if (attribut.getReference() instanceof INode) {

			// Deux possibilites :
			// Pas d'information de positionnement -> on utilise les indication du noeud
			// Information de positionnement -> on les utilise

			// Cas 1
			if ((attribut.getGraphicInfo().getLocation().x == 0) && (attribut.getGraphicInfo().getLocation().y == 0)) {
				Point refLocation = ((INode) attribut.getReference()).getGraphicInfo().getLocation();
				attributePosition = new Point(refLocation.x + GAP, refLocation.y - GAP);

				// Cas 2
			} else {
				attributePosition = new Point(attribut.getGraphicInfo().getLocation().x, attribut.getGraphicInfo().getLocation().y);
			}

			// Si le referent est un arc
		} else if (attribut.getReference() instanceof IArc) {
			if ((attribut.getGraphicInfo().getLocation().x == 0) && (attribut.getGraphicInfo().getLocation().y == 0)) {
				attributePosition = ((IArc) attribut.getReference()).getGraphicInfo().findMiddlePoint();
				// Cas 2
			} else {
				attributePosition = new Point(attribut.getGraphicInfo().getLocation().x, attribut.getGraphicInfo().getLocation().y);
			}

			// Si le referent est le modele lui-meme
		} else if (attribut.getReference() instanceof IGraph) {
			attributePosition = new Point(attribut.getGraphicInfo().getLocation().x, attribut.getGraphicInfo().getLocation().y);

			// Dans tous les autres cas... On reset
		} else {
			attributePosition = new Point(0, 0);
		}

		// Recupere la figure du graphe
		GraphEditPart graphEditPart = (GraphEditPart) getParent();

		// On doit maintenant veririfer qu'aucune autre figure ne se trouve a proximite

		// Comme aucun texte n'est ajoute dans la figure pour le moment... verifie que le point x+5 et y+5 est libre aussi
		Point attributePositionZone = new Point(attributePosition.x + MINGAP, attributePosition.y + MINGAP);

		while ((graphEditPart.getFigure().findFigureAt(attributePosition) != null) || (graphEditPart.getFigure().findFigureAt(attributePositionZone) != null)) {
			attributePosition.y = attributePosition.y + MINGAP; // Deplacement de 5 vers le bas si une figure est deja disposee
			attributePositionZone.y = attributePositionZone.y + MINGAP;
		}
		return attributePosition;
	}

	/**
	 * Mise a jour de la vue a partir des informations du modele<br>
	 * La mise a jour utilise des methodes de parcours du modele et de moficiation de la vue
	 */
	@Override
	protected final void refreshVisuals() {

		IAttribute attribut = (IAttribute) getModel();
		Label attributeFigure = (Label) getFigure();

		// Affichage du texte dans le Label
		int type = SWT.NORMAL;
		if (attribut.getAttributeFormalism().isBold()) { type = type & SWT.BOLD; }
		if (attribut.getAttributeFormalism().isItalic()) { type = type & SWT.ITALIC; }
		Font f = new Font(null, "arial", attribut.getAttributeFormalism().getSize(), type); //$NON-NLS-1$

		attributeFigure.setText(attribut.getValue());
		attributeFigure.setFont(f);

		// On doit creer l'espace pour l'attribut
		Rectangle bounds = new Rectangle(attribut.getGraphicInfo().getLocation(), new Dimension(attributeFigure.getTextBounds().width, attributeFigure.getTextBounds().height));
		if (getParent() != null) {
			((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), bounds);
		}
	}

	/**
	 * Regles de gestion de l'objet
	 */
	@Override
	protected final void createEditPolicies() {
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new AttributeDirectEditPolicy());


		/* Ensemble de regles concernant la selection/deselection de l'objet */
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new SelectionEditPolicy() {

			// Comportement lorsque l'objet est selectionne
			@Override
			protected void setSelectedState(int state) {
				super.setSelectedState(state);
				if (state == SELECTED || state == SELECTED_PRIMARY) {
					((Label) getFigure()).setForegroundColor(ColorConstants.blue);
				} else {
					((Label) getFigure()).setForegroundColor(ColorConstants.black);
				}
			}

			@Override
			protected void hideSelection() { }

			@Override
			protected void showSelection() { }
		});

	}

	/**
	 * Installation des ecouteurs de l'objet
	 */
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

	/**
	 * Mise en veille des ecouteurs de l'objet
	 */
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
	 * Affiche une zone d'édition de l'attribut.
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
	public final void propertyChange(PropertyChangeEvent evt) {
		String prop = evt.getPropertyName();

		// Modification d'un attribut.
		if (IAttribute.VALUE_PROP.equals(prop)) {
			IAttribute model = (IAttribute) getModel();
			String oldValue = (String) evt.getOldValue();
			String newValue = (String) evt.getNewValue();
			if (oldValue.equals(model.getAttributeFormalism().getDefaultValue())) {
				LOGGER.finer("attribut de nouveau visible"); //$NON-NLS-1$
				model.getGraphicInfo().setLocation(calculLocation());
				getFigure().setVisible(true);
			} else if (newValue.equals(model.getAttributeFormalism().getDefaultValue())) {
				LOGGER.finer("attribut caché"); //$NON-NLS-1$
				model.getGraphicInfo().setLocation(new Point(0, 0));
				getFigure().setVisible(false);
			}
			refreshVisuals();

		// Deplacement d'un attribut.
		} else if (IAttribute.LOCATION_PROP.equals(prop)) {
			refreshVisuals();
		}
	}

	/** {@inheritDoc} */
	public final EditPartListener getSelectionEditPartListener() {
		return editPartListener;
	}
}
