package fr.lip6.move.coloane.ui.commands;

import fr.lip6.move.coloane.exceptions.BuildException;
import fr.lip6.move.coloane.main.Coloane;
import fr.lip6.move.coloane.motor.formalism.ElementBase;
import fr.lip6.move.coloane.motor.formalism.Formalism;
import fr.lip6.move.coloane.ui.model.ArcImplAdapter;
import fr.lip6.move.coloane.ui.model.IArcImpl;
import fr.lip6.move.coloane.ui.model.INodeImpl;

import org.eclipse.gef.commands.Command;

/**
 * Deuxieme etape de la creation d'un lien entre deux noeuds !<br>
 * Cette commande est creee lors du second clic (donc sur l'element d'arrivee).
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

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	@Override
	public final boolean canExecute() {

		Formalism form = formalism.getFormalism();

		// La connexion est-elle autorisee par le formalisme ?
		if (!form.isLinkAllowed(source.getElementBase(), target.getElementBase())) {
			return false;
		}

		// Evite les doublons en renvoyant faux si le lien existe deja
		for (IArcImpl a : source.getSourceArcs()) {
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
		try {
			// Construction de l'arc
			arc = new ArcImplAdapter(this.source, this.target, this.formalism);
			arc.setModelAdapter(source.getModelAdapter());
			this.redo();
		} catch (BuildException e) {
			Coloane.getLogger().warning("Impossible d'ajouter un arc (" + source.getId() + "," + source.getId() + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
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
