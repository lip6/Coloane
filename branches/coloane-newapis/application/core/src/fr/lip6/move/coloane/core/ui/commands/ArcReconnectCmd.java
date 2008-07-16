package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.INode;

import org.eclipse.gef.commands.Command;

/**
 * Commande permettant la re-connexion d'un arc.<br>
 * Le but de cette commande est de permettre de changer la source ou la cible d'un arc sans pour autant le detruire.
 */
public class ArcReconnectCmd extends Command {

	/** L'arc qu'on manipule */
	private IArc arc;

	/** Noeud source */
	private INode newSource;

	/** Noeud cible */
	private INode newTarget;

	/** Ancien noeud source */
	private final INode oldSource;

	/** Ancien noeud cible */
	private final INode oldTarget;

	/**
	 * Constructeur de la commande
	 * @param a L'arc concerné par la reconnexion
	 */
	public ArcReconnectCmd(IArc a) {
		this.arc = a;
		this.oldSource = a.getSource();
		this.oldTarget = a.getTarget();
	}

	/**
	 * Indique la nouvelle source de l'arc
	 * @param connectionSource Le nouveau noeud source
	 */
	public final void setNewSource(INode connectionSource) {
		newSource = connectionSource;
		newTarget = null;
	}

	/**
	 * Indique la nouvelle cible de l'arc
	 * @param connectionTarget Le nouveau noeud cible
	 */
	public final void setNewTarget(INode connectionTarget) {
		newSource = null;
		newTarget = connectionTarget;
	}


	/**
	 * Savoir si on peut executer
	 * @return booleen
	 */
	@Override
	public final boolean canExecute() {

		// Est-ce qu'on peut connecter la nouvelle source ?
		if ((this.newSource != null) && !checkSourceReconnection()) {
			return false;
		}

		// Est-ce qu'on peut connecter la nouvelle cible ?
		if ((this.newTarget != null) && !checkTargetReconnection()) {
			return false;
		}

		// Recuperation du formalisme
		IFormalism formalism = this.arc.getArcFormalism().getFormalism();

		// Est-ce que la connexion est autorisée ?
		if ((this.newSource != null) && !formalism.isLinkAllowed(newSource, oldTarget)) {
			return false;
		}

		// Est-ce que la connexion est autorisée ?
		if ((this.newTarget != null) && !formalism.isLinkAllowed(oldSource, newTarget)) {
			return false;
		}

		return true;
	}

	/**
	 * Verification que le noeud source n'est pas deja connecte...
	 * Retourne FALSE si la connexion existe deja.
	 * @return booleen
	 */
	private boolean checkSourceReconnection() {
		if (newSource == oldSource) {
			return true;
		}

		for (IArc existingConnection : this.newSource.getOutcomingArcs()) {
			if (existingConnection.getTarget().getId() == this.oldTarget.getId()) {
				return false;
			}
		}
		return true;
	}


	/**
	 * Verification que le noeud cible n'est pas deja connecte de la meme facon...
	 * Retourne FALSE si la connexion oldsource -> newtarget existe deja.
	 * @return booleen
	 */
	private boolean checkTargetReconnection() {
		if (newTarget == oldTarget) {
			return true;
		}

		for (IArc existingConnection : newTarget.getIncomingArcs()) {
			if (existingConnection.getSource().getId() == this.oldSource.getId()) {
				return false;
			}
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public final void execute() {
		this.redo();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	@Override
	public final void redo() {
		INode sourceToConnect = newSource;
		INode targetToConnect = newTarget;

		// Si la source ne change pas...
		if (newSource == null) {
			sourceToConnect = oldSource;
		}

		// si la cible ne change pas...
		if (newTarget == null) {
 			targetToConnect = oldTarget;
		}

		this.arc.reconnect(sourceToConnect, targetToConnect);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public final void undo() {
		this.arc.reconnect(oldSource, oldTarget);
	}
}
