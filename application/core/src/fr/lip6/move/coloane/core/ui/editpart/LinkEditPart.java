package fr.lip6.move.coloane.core.ui.editpart;

import fr.lip6.move.coloane.core.model.interfaces.ILink;
import fr.lip6.move.coloane.core.model.interfaces.ILinkableElement;
import fr.lip6.move.coloane.core.model.interfaces.IStickyNote;
import fr.lip6.move.coloane.core.ui.commands.LinkDeleteCommand;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

/**
 * EditPart des liens (ILink).
 */
public class LinkEditPart extends AbstractConnectionEditPart {

	/** {@inheritDoc} */
	@Override
	protected final IFigure createFigure() {
		PolylineConnection arc = new PolylineConnection();
		arc.setLineStyle(Graphics.LINE_DOT);
		arc.setForegroundColor(ColorConstants.gray);
		return arc;
	}

	/** {@inheritDoc} */
	@Override
	protected final void createEditPolicies() {
		// Selection handle edit policy.
		// Makes the connection show a feedback, when selected by the user.
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, new ConnectionEndpointEditPolicy());

		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ComponentEditPolicy() {
			@Override
			protected Command createDeleteCommand(GroupRequest deleteRequest) {
				// Il faut retrouver le graphe
				IGraph graph;
				ILink link = (ILink) getHost().getModel();
				// on recherche l'élément qui n'est pas la note
				ILinkableElement linkableElement = link.getSource();
				if (linkableElement instanceof IStickyNote) {
					linkableElement = link.getTarget();
				}
				// on récupère le parent de cette élément qui doit être un IGraph
				if (linkableElement instanceof IElement) {
					IElement element = (IElement) linkableElement;
					graph = (IGraph) element.getParent();
				} else if (linkableElement instanceof IAttribute) {
					IElement element = ((IAttribute) linkableElement).getReference();
					graph = (IGraph) element.getParent();
				} else {
					return null; // La suppression n'est pas possible
				}

				LinkDeleteCommand deleteCommand = new LinkDeleteCommand(graph, link);
				return deleteCommand;
			}
		});
	}

}
