package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.core.motor.formalism.ElementBase;
import fr.lip6.move.coloane.core.ui.model.INodeImpl;

import org.eclipse.gef.commands.Command;

/**
 * Premiere etape de la creation d'un lien entre deux noeuds !<br>
 * Cette commande est creee lors du premier clic sur l'element de depart.<br>
 * Elle sert a stocker des informations sur le noeud de depart.<br>
 */
public class ArcCreateCmd extends Command {

	/** Le noeud source */
	private final INodeImpl source;

	/** L'element de base (indication sur le formalisme de l'arc) */
	private final ElementBase formalism;


	/**
	 * Creation d'un arc entre deux element
	 *
	 * @param source Le noeud source de l'arc
	 * @param base Le formalisme de base pour la creation de l'arc
	 */
	public ArcCreateCmd(INodeImpl arcSource, ElementBase arcFormalism) {
		this.source = arcSource;
		this.formalism = arcFormalism;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	@Override
	public final boolean canExecute() {
		return true;
	}

	/**
	 * Getter de l'element source de l'arc
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
		return formalism;
	}
}
