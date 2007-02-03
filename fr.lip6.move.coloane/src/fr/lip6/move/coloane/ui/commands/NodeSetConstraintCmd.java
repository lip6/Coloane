package fr.lip6.move.coloane.ui.commands;



import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;

import fr.lip6.move.coloane.ui.model.NodeImplAdapter;

/**
 * @author yutao
 *
 * Added by Dzung NGUYEN
 */
public class NodeSetConstraintCmd extends Command {

	/** Enregistre la nouvelle taille et le nouvel endroit */
	private final Rectangle newBounds;
    /** Enregistre l'ancienne taille et le nouvel endroit */
	private Rectangle oldBounds;
	/** Une requête pour déplacer/redimensionner */
	private final ChangeBoundsRequest request;

	/** Noeud à manipuler */
	private final NodeImplAdapter node;

    /**
     * Constructeur
     * @param node noeud
     * @param req requete
     * @param newBounds Nouvelles limites
     */
    public NodeSetConstraintCmd(NodeImplAdapter node, ChangeBoundsRequest req,
			Rectangle newBounds) {
		if (node == null || req == null || newBounds == null) {
			throw new IllegalArgumentException();
		}
		this.node = node;
		this.request = req;
		this.newBounds = newBounds.getCopy();
		setLabel("move / resize");
	}

    /**
     * Savoir si on peur executer
     * @return booleen
     */
	public boolean canExecute() {
		Object type = request.getType();
		
		// Only move command allowed
		return (RequestConstants.REQ_MOVE.equals(type)
				|| RequestConstants.REQ_MOVE_CHILDREN.equals(type));
	}

	/**
     * Executer
     *
	 */
	public void execute() {
		oldBounds = new Rectangle(node.getGraphicInfo().getLocation(), node
				.getGraphicInfo().getSize());
		redo();
	}

	/**
     * Refaire
     *
	 */
	public void redo() {
		node.getGraphicInfo().setSize(newBounds.getSize());
		node.getGraphicInfo().setLocation(newBounds.getLocation());
	}

	/**
     * Annuler
     *
	 */
	public void undo() {
		node.getGraphicInfo().setSize(oldBounds.getSize());
		node.getGraphicInfo().setLocation(oldBounds.getLocation());
	}

}
