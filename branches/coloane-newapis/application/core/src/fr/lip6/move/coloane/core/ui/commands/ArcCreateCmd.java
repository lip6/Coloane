package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.interfaces.formalism.IArcFormalism;
import fr.lip6.move.coloane.interfaces.model.INode;

import org.eclipse.gef.commands.Command;

/**
 * Premiere etape de la creation d'un lien entre deux noeuds !<br>
 * Cette commande est créée lors du premier clic sur le noeud de depart.<br>
 * Elle sert a stocker des informations sur le noeud de depart.<br>
 * @see ArcCompleteCmd
 */
public class ArcCreateCmd extends Command {
	/** Le noeud source */
	private final INode source;

	/** Le formalisme de l'arc */
	private final IArcFormalism arcFormalism;


	/**
	 * Création d'un arc entre deux element
	 * @param arcSource Le noeud source de l'arc
	 * @param arcFormalism Le formalisme de l'arc créé
	 */
	public ArcCreateCmd(INode arcSource, IArcFormalism arcFormalism) {
		super(Messages.ArcCreateCmd_0);
		this.source = arcSource;
		this.arcFormalism = arcFormalism;
	}

	/** {@inheritDoc} */
	@Override
	public final boolean canExecute() {
		return true;
	}

	/**
	 * @return Le noeud source de l'arc
	 */
	public final INode getSource() {
		return source;
	}

	/**
	 * @return Le formalisme de l'arc créé
	 */
	public final IArcFormalism getArcFormalism() {
		return arcFormalism;
	}
}
