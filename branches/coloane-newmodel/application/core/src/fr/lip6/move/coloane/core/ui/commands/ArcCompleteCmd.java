package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.core.exceptions.BuildException;
import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.motor.formalisms.Formalism;
import fr.lip6.move.coloane.core.motor.formalisms.elements.Arc;
import fr.lip6.move.coloane.core.motor.formalisms.elements.FormalismElement;
import fr.lip6.move.coloane.core.ui.model.interfaces.IArc;
import fr.lip6.move.coloane.core.ui.model.interfaces.INode;

import org.eclipse.gef.commands.Command;

/**
 * Deuxieme etape de la creation d'un lien entre deux noeuds !<br>
 * Cette commande est creee lors du second clic (donc sur l'element d'arrivee).
 */
public class ArcCompleteCmd extends Command {

	/** Noeud source */
	private final INode source;

	/** Noeud cible */
	private final INode target;

	/** Element de base du formalisme (arc) */
	private final Arc arcFormalism;

	/** L'arc */
	private IArc arc;

	/**
	 * Connexion de l'arc
	 * @param source noeud source
	 * @param target noeud cible
	 * @param base elementBase
	 */
	public ArcCompleteCmd(INode arcSource, INode arcTarget, Arc arcFormalism) {
		this.source = arcSource;
		this.target = arcTarget;
		this.arcFormalism = arcFormalism;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	@Override
	public final boolean canExecute() {

		// La connexion est-elle autorisee par le formalisme ?
		if (!arcFormalism.isLinkAllowed(source.getNodeFormalism(), target.getNodeFormalism())) {
			return false;
		}

		// Evite les doublons en renvoyant faux si le lien existe deja
		for (IArc a : source.getSourceArcs()) {
			if (a.getTarget().equals(target)) {
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
		// Construction de l'arc
		arc = new ArcImplAdapter(this.source, this.target, this.formalism);
		arc.setModelAdapter(source.getModelAdapter());
		this.redo();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	@Override
	public final void redo() {
		try {
			source.getModelAdapter().addArc(arc);
		} catch (BuildException e) {
			Coloane.getLogger().warning("Impossible d'ajouter l'arc au modele " + e.getMessage()); //$NON-NLS-1$
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public final void undo() {
		try {
			source.getModelAdapter().removeArc(arc);
		} catch (BuildException e) {
			Coloane.getLogger().warning("Impossible de supprimer l'arc du modele " + e.getMessage()); //$NON-NLS-1$
		}
	}
}
