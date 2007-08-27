package fr.lip6.move.coloane.ui.commands;

import fr.lip6.move.coloane.motor.formalism.Formalism;
import fr.lip6.move.coloane.ui.model.IArcImpl;
import fr.lip6.move.coloane.ui.model.INodeImpl;

import org.eclipse.gef.commands.Command;

/**
 * Commande permettant la de-re connexion d'un arc.<br>
 * Le but de cette commande est de permettre de changer la source ou la cible d'un arc
 * sans pour autant le detruire.
 */
public class ArcReconnectCmd extends Command {

	/** L'arc augmente qu'on manipule */
	private IArcImpl arc;

	/** Noeud source */
	private INodeImpl newSource;

	/** Noeud cible */
	private INodeImpl newTarget;

	/** Ancien noeud source */
	private final INodeImpl oldSource;

	/** Ancien noeud cible */
	private final INodeImpl oldTarget;

	/**
	 * Reconnecter
	 * @param connection arc
	 */
	public ArcReconnectCmd(IArcImpl a) {
		this.arc = a;
		this.oldSource = a.getSource();
		this.oldTarget = a.getTarget();
	}

	/**
	 * Indique la nouvelle source de l'arc
	 * @param connectionSource
	 */
	public final void setNewSource(INodeImpl connectionSource) {
		newSource = connectionSource;
		newTarget = null;
	}

	/**
	 * Indique la nouvelle cible de l'arc
	 * @param connectionTarget
	 */
	public final void setNewTarget(INodeImpl connectionTarget) {
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


		Formalism form = this.arc.getFormalism();

		if ((this.newSource != null) && !form.isLinkAllowed(newSource.getElementBase(), oldTarget.getElementBase())) {
			return false;
		}

		if ((this.newTarget != null) && !form.isLinkAllowed(oldSource.getElementBase(), newTarget.getElementBase())) {
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

		for (IArcImpl existingConnection : this.newSource.getSourceArcs()) {
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

		for (IArcImpl existingConnection : newTarget.getTargetArcs()) {
			if (existingConnection.getSource().getId() == this.oldSource.getId()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Executer
	 */
	@Override
	public final void execute() {
		this.redo();
	}

	/**
	 * Executer (ou Refaire)
	 */
	@Override
	public final void redo() {
		INodeImpl sourceToConnect = newSource;
		INodeImpl targetToConnect = newTarget;

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

	/**
	 * Annule la reconnexion
	 */
	@Override
	public final void undo() {
		this.arc.reconnect(oldSource, oldTarget);
	}
}
