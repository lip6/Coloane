package fr.lip6.move.coloane.ui.commands;

import fr.lip6.move.coloane.exceptions.BuildException;
import fr.lip6.move.coloane.main.Translate;
import fr.lip6.move.coloane.ui.model.IArcImpl;
import fr.lip6.move.coloane.ui.model.IModelImpl;
import fr.lip6.move.coloane.ui.model.INodeImpl;

import java.util.List;

import org.eclipse.gef.commands.Command;

/**
 * Commande de suppression d'un noeud du modele
 * @see INodeImpl
 */
public class NodeDeleteCmd extends Command {

	/** Noeud a retirer */
	private final INodeImpl node;

	/** Modele contenant le noeud */
	private final IModelImpl model;

	/** Garder une copie des connexions sortantes du noeud */
	private List<IArcImpl> sourceConnections = null;

	/** Garder une copie des connexions entrantes vers le noeud */
	private List<IArcImpl> targetConnections = null;

	/**
	 * Constructeur
	 * @param model Modele augmente
	 * @param node Noeud augemente a supprimer
	 */
	public NodeDeleteCmd(IModelImpl m, INodeImpl n) {
		this.model = m;
		this.node = n;

		// Indication de l'evenement
		setLabel(Translate.getString("ui.commands.NodeDeleteCmd.1")); //$NON-NLS-1$
	}

	/**
	 * Execution de la commande
	 */
	@Override
	public final void execute() {
		// Sauvegarde une copie des listes d'arcs entrants et sortant en cas d'annulation
		sourceConnections = node.getSourceArcs();
		targetConnections = node.getTargetArcs();

		this.redo(); // Execute
	}

	/**
	 * Execution de la commande (REDO)
	 * @throws BuildException
	 */
	@Override
	public final void redo() {
		try {
			model.removeNode(node);
		} catch (BuildException e) {
			System.err.println("Impossible de supprimer le noeud");
		}
	}

	/**
	 * Annulation de la commande (UNDO)
	 * --> Ajout du noeud qu'on vient de supprimer
	 */
	@Override
	public final void undo() {
		try {
			model.addNode(node);

			// Ajout des arcs entrants
			for (IArcImpl arcIn : targetConnections) {
				model.addArc(arcIn);
			}

			// Ajout des arcs sortants
			for (IArcImpl arcOut : sourceConnections) {
				model.addArc(arcOut);
			}
		} catch (BuildException e) {
			System.err.println("Impossible d'annuler la suppression du noeud");
		}
	}
}
