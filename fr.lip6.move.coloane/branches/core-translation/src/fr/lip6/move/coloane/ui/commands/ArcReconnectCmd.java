package fr.lip6.move.coloane.ui.commands;

import java.util.Iterator;

import org.eclipse.gef.commands.Command;

import fr.lip6.move.coloane.exceptions.BuildException;
import fr.lip6.move.coloane.main.Coloane;
import fr.lip6.move.coloane.motor.formalism.Formalism;
import fr.lip6.move.coloane.ui.model.IArcImpl;
import fr.lip6.move.coloane.ui.model.INodeImpl;

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
	public ArcReconnectCmd(IArcImpl arc) {

		if (arc == null) {
			throw new IllegalArgumentException();
		}

		this.arc = arc;
		this.oldSource = arc.getSource();
		this.oldTarget = arc.getTarget();
	}


	/**
	 * Savoir si on peut executer
	 * @return booleen
	 */
	public boolean canExecute() {

		// Est-ce qu'on peut connecter la nouvelle source ?
		if ((this.newSource != null) && !checkSourceReconnection()) {  
			return false;
		}

		// Est-ce qu'on peut connecter la nouvelle cible ?
		if ((this.newTarget != null) && !checkTargetReconnection()) {
			return false;
		}


		Formalism form = this.arc.getFormalism();

		if ((this.newSource != null) && !form.isLinkAllowed(newSource.getElementBase(),oldTarget.getElementBase())) {
			return false;
		}

		if ((this.newTarget != null) && !form.isLinkAllowed(oldSource.getElementBase(),newTarget.getElementBase())) {
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
		for (Iterator i = this.newSource.getSourceArcs().iterator(); i.hasNext();) {
			IArcImpl existingConnection = (IArcImpl) i.next();
			if (existingConnection.getTarget().equals(this.oldTarget) && existingConnection.equals(this.arc)) {
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
		for (Iterator i = newTarget.getTargetArcs().iterator(); i.hasNext();) {
			IArcImpl existingConnection = (IArcImpl) i.next();
			if (existingConnection.getSource().equals(oldSource) && existingConnection.equals(arc)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Executer
	 */
	public void execute() {
		if (newSource != null) {
			try {
				this.arc.reconnect(newSource, oldTarget);
			} catch (BuildException e) {
				System.err.println("Echec ! : "+e.getMessage()); //$NON-NLS-1$
			}
		} else if (newTarget != null) {
			try {
				this.arc.reconnect(oldSource, newTarget);
			} catch (BuildException e) {
				System.err.println("Echec ! : "+e.getMessage()); //$NON-NLS-1$
			}
		} else {
			throw new IllegalStateException("Should not happen"); //$NON-NLS-1$
		}

	}

	/**
	 * Annule la reconnexion
	 */
	public void undo() {
		try {
			this.arc.reconnect(oldSource, oldTarget);
		} catch (BuildException e) {
			System.err.println("Echec ! : "+e.getMessage()); //$NON-NLS-1$
		}
	}

	
	
	public void setNewSource(INodeImpl connectionSource) {
		if (connectionSource == null) {
			throw new IllegalArgumentException();
		}
		setLabel(Coloane.traduction.getString("ui.commands.ArcReconnectCmd.0")); //$NON-NLS-1$
		newSource = connectionSource;
		newTarget = null;
	}

	public void setNewTarget(INodeImpl connectionTarget) {
		if (connectionTarget == null) {
			throw new IllegalArgumentException();
		}
		setLabel(Coloane.traduction.getString("ui.commands.ArcReconnectCmd.1")); //$NON-NLS-1$
		newSource = null;
		newTarget = connectionTarget;
	}

}
