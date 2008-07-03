 package fr.lip6.move.coloane.core.ui.editpart;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.model.AbstractPropertyChange;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IAttributeGraphicInfo;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;

/**
 * Cet EditPart est responsable de la gestion des attributs.
 */
public class AttributeEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener, org.eclipse.gef.NodeEditPart {

	private static final int GAP = 20;
	private static final int MINGAP = 20;
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

		// Si le referent est un noeud, on agit sur la position de l'attribut
		Point attributePosition;
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

		// Recupere la figure du modele
		GraphEditPart modelEditPart = (GraphEditPart) getParent();

		// On doit maintenant veririfer qu'aucune autre figure ne se trouve a proximite

		// Comme aucun texte n'est ajoute dans la figure pour le moment... verifie que le point x+5 et y+5 est libre aussi
		Point attributePositionZone = new Point(attributePosition.x + MINGAP, attributePosition.y + MINGAP);

		while ((modelEditPart.getFigure().findFigureAt(attributePosition) != null) || (modelEditPart.getFigure().findFigureAt(attributePositionZone) != null)) {
			attributePosition.y = attributePosition.y + MINGAP; // Deplacement de 5 vers le bas si une figure est deja disposee
			attributePositionZone.y = attributePositionZone.y + MINGAP;
		}

		// Stocke les information de positionnement
		attribut.getGraphicInfo().setLocation(attributePosition.x, attributePosition.y);

		// Positionnement graphique
		figure.setLocation(attributePosition);

		return figure;
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
		if (attribut.isBold()) { type = type & SWT.BOLD; }
		if (attribut.isItalic()) { type = type & SWT.ITALIC; }
		Font f = new Font(null, "arial", attribut.getSize(), type);

		attributeFigure.setText(attribut.getValue());
		attributeFigure.setFont(f);

		// On doit creer l'espace pour l'attribut
		Rectangle bounds = new Rectangle(attribut.getGraphicInfo().getLocation(), new Dimension(attributeFigure.getTextBounds().width, attributeFigure.getTextBounds().height));
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), bounds);

		// Il faut avertir FrameKit
		Coloane.notifyModelChange(attribut.getReference());
	}

	/**
	 * Traitements a effectuer lors de la reception d'un evenement sur l'EditPart
	 * @param property L'evenement qui a ete leve
	 */
	public final void propertyChange(PropertyChangeEvent property) {
		String prop = property.getPropertyName();

		if (IAttribute.SELECT_LIGHT_PROP.equals(prop)) {
			((Label) getFigure()).setBackgroundColor(ColorConstants.lightGray);
			return;
		} else if (IAttribute.SELECT_HEAVY_PROP.equals(prop)) {
			((Label) getFigure()).setForegroundColor(ColorConstants.blue);
			return;
		} else if (IAttribute.UNSELECT_LIGHT_PROP.equals(prop)) {
			((Label) getFigure()).setBackgroundColor(ColorConstants.white);
			return;
		} else if (IAttribute.UNSELECT_HEAVY_PROP.equals(prop)) {
			((Label) getFigure()).setForegroundColor(ColorConstants.black);
			return;
		}

		refreshVisuals();
	}


	/**
	 * Regles de gestion de l'objet
	 */
	@Override
	protected final void createEditPolicies() {

		/* Ensemble de regles concernant la selection/deselection de l'objet */
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new SelectionEditPolicy() {

			// Comportement lorsque l'objet est selectionne
			@Override
			protected void setSelectedState(int state) {
				super.setSelectedState(state);
				IElement ref = ((IAttribute) getModel()).getReference();
				if (ref instanceof INode) {
					if (state > 0) {
						((INode) ref).setSelect(true);
						((Label) getFigure()).setForegroundColor(ColorConstants.blue);
					} else {
						((INode) ref).setSelect(false);
						((Label) getFigure()).setForegroundColor(ColorConstants.black);
					}
				} else if (ref instanceof IArc) {
					if (state > 0) {
						((IArc) ref).setSelect(true);
						((Label) getFigure()).setForegroundColor(ColorConstants.blue);
					} else {
						((IArc) ref).setSelect(false);
						((Label) getFigure()).setForegroundColor(ColorConstants.black);
					}
				}
			}

			@Override
			protected void hideSelection() { }

			@Override
			protected void showSelection() { }
		});

		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new GraphicalNodeEditPolicy() {

			@Override
			protected Command getConnectionCompleteCommand(CreateConnectionRequest arg0) { return null;	}

			@Override
			protected Command getConnectionCreateCommand(CreateConnectionRequest arg0) { return null; }

			@Override
			protected Command getReconnectSourceCommand(ReconnectRequest arg0) { return null; }

			@Override
			protected Command getReconnectTargetCommand(ReconnectRequest arg0) { return null; }
		}
		);


	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
	 */
	public final ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart arg0) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef.Request)
	 */
	public final ConnectionAnchor getSourceConnectionAnchor(Request arg0) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
	 */
	public final ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart arg0) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.Request)
	 */
	public final ConnectionAnchor getTargetConnectionAnchor(Request arg0) {
		return null;
	}

	/**
	 * Installation des ecouteurs de l'objet
	 */
	@Override
	public final void activate() {
		if (!isActive()) {
			super.activate();
			((AbstractPropertyChange) getModel()).addPropertyChangeListener(this);
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
		}
	}

}
