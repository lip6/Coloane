package fr.lip6.move.coloane.ui.commands;

import fr.lip6.move.coloane.motor.formalism.ElementBase;
import fr.lip6.move.coloane.ui.model.INodeImpl;

import org.eclipse.gef.commands.Command;

/**
 * Premiere etape de la creation d'un lien entre deux noeuds !
 * Cette commande est creee lors du premier clic sur l'element de depart.
 * Elle sert a stocker des informations sur le noeud de depart
 */

public class ArcCreateCmd extends Command {

	/** Le noeud source */
	private final INodeImpl source;

	/** L'element de base (indication sur le formalisme de l'arc) */
	private final ElementBase arcFormalism;


	/**
	 * Creation d'un arc entre deux element
	 *
	 * @param source Le noeud source de l'arc
	 * @param base Le formalisme de base pour la creation de l'arc
	 */
	public ArcCreateCmd(INodeImpl arcSource, ElementBase base) {
		this.source = arcSource;
		this.arcFormalism = base;
	}

	/**
	 * Savoir si on peut creer un arc
	 * @return booleen
	 */
	public final boolean canExecute() {
		return true;
	}

	/**
	 * Getter de Source
	 * @return source
	 */
	public final INodeImpl getSource() {
		return source;
	}

	/**
	 * Getter du formalisme de l'arc
	 * @return elementBase
	 */
	public final ElementBase getElementBase() {
		return arcFormalism;
	}
}
