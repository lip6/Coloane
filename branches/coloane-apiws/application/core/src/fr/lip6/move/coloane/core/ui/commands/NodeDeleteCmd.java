package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.core.exceptions.BuildException;
import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.ui.model.IArcImpl;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;
import fr.lip6.move.coloane.core.ui.model.INodeImpl;

import java.util.List;

import org.eclipse.gef.commands.Command;

/**
 * Commande de suppression d'un noeud du modele
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
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public final void execute() {
		// Sauvegarde une copie des listes d'arcs entrants et sortant en cas d'annulation
		sourceConnections = node.getSourceArcs();
		targetConnections = node.getTargetArcs();
		this.redo(); // Execute
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	@Override
	public final void redo() {
		try {
			model.removeNode(node);
		} catch (BuildException e) {
			Coloane.getLogger().warning("Impossible de supprimer le noeud : " + e.getMessage()); //$NON-NLS-1$
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
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
			Coloane.getLogger().warning("Impossible d'annuler la suppression du noeud : " + e.getMessage()); //$NON-NLS-1$
		}
	}
}
