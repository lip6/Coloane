package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.core.model.interfaces.ILocatedElement;
import fr.lip6.move.coloane.core.ui.rulers.EditorGuide;
import fr.lip6.move.coloane.core.ui.rulers.EditorRuler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.gef.commands.Command;

/**
 * Suppression d'un guide associée à une règle
 */
public class DeleteGuideCommand extends Command {

	/** La règle à laquelle est attaché le guide */
	private EditorRuler ruler;

	/** Le guide concerné */
	private EditorGuide guide;

	/** Sauvegarde des éléments de modèle attaché au guide... en cas d'annulation */
	private Map<ILocatedElement, Integer> oldElements;

	/**
	 * Constructeur
	 * @param guide Le guide à supprimer
	 * @param ruler La règle à laquelle est attachée le guide
	 */
	public DeleteGuideCommand(EditorGuide guide, EditorRuler ruler) {
		super(Messages.DeleteGuideCommand_0);
		this.guide = guide;
		this.ruler = ruler;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		oldElements = new HashMap<ILocatedElement, Integer>(guide.getMap());
		Iterator<ILocatedElement> iter = oldElements.keySet().iterator();
		while (iter.hasNext()) {
			guide.detachElement(iter.next());
		}
		ruler.removeGuide(guide);
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		ruler.addGuide(guide);
		Iterator<ILocatedElement> iter = oldElements.keySet().iterator();
		while (iter.hasNext()) {
			ILocatedElement locatedElement = iter.next();
			guide.attachElement(locatedElement, ((Integer) oldElements.get(locatedElement)).intValue());
		}
	}
}
