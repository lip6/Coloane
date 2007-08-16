package fr.lip6.move.coloane.ui.commands;

import fr.lip6.move.coloane.exceptions.BuildException;
import fr.lip6.move.coloane.motor.formalism.ElementBase;
import fr.lip6.move.coloane.motor.formalism.Formalism;
import fr.lip6.move.coloane.ui.model.ArcImplAdapter;
import fr.lip6.move.coloane.ui.model.IArcImpl;
import fr.lip6.move.coloane.ui.model.INodeImpl;

import java.util.Iterator;

import org.eclipse.gef.commands.Command;


/**
 * Deuxieme etape de la creation d'un lien entre deux noeuds ! Cette commande
 * est creee lors du second clic (donc sur l'element d'arrivee).
 */

public class ArcCompleteCmd extends Command {

	/** Noeud source */
	private final INodeImpl source;

	/** Noeud cible */
	private final INodeImpl target;

	/** Element de base du formalisme (arc) */
	private final ElementBase formalism;

	/** L'arc */
	private IArcImpl arc;

	/**
	 * Connexion de l'arc
	 * @param source noeud source
	 * @param target noeud cible
	 * @param base elementBase
	 */
	public ArcCompleteCmd(INodeImpl arcSource, INodeImpl arcTarget, ElementBase arcFormalism) {
		this.source = arcSource;
		this.target = arcTarget;
		this.formalism = arcFormalism;
	}

	/**
	 * Savoir si l'action est executable
	 * @return booleen
	 */
	@Override
	public final boolean canExecute() {

		Formalism form = formalism.getFormalism();

		// La connexion est-elle autorisee par le formalisme ?
		if (!form.isLinkAllowed(source.getElementBase(), target.getElementBase())) {
			return false;
		}

		// Evite les doublons en renvoyant faux si le lien existe deja
		for (Iterator iter = source.getSourceArcs().iterator(); iter.hasNext();) {
			IArcImpl a = (IArcImpl) iter.next();
			if (a.getTarget().equals(target)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Creation de la connexion
	 * Creation de l'objet arc adapte
	 */
	@Override
	public final void execute() {
		try {
			// Construction de l'arc
			arc = new ArcImplAdapter(this.source, this.target, this.formalism);
			arc.setModelAdapter(source.getModelAdapter());
			this.redo();
		} catch (BuildException e) {
			System.err.println("Echec ! : " + e.getMessage());
		}
	}

	/**
	 * Executer (ou Refaire)
	 * Ajout de l'arc au modele
	 */
	@Override
	public final void redo() {
		try {
			source.getModelAdapter().addArc(arc);
		} catch (BuildException e) {
			System.err.println("Echec ! : " + e.getMessage());
		}
	}

	/**
	 * Annuler
	 * Suppression de l'arc du modele generique
	 */
	@Override
	public final void undo() {
		source.getModelAdapter().removeArc(arc);
	}
}
