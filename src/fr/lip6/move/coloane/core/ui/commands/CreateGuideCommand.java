package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.core.ui.rulers.EditorGuide;
import fr.lip6.move.coloane.core.ui.rulers.EditorRuler;
import org.eclipse.gef.commands.Command;

/**
 * Une commande dédiée a la creation d'un guide
 */
public class CreateGuideCommand extends Command {
	/** Le guide concerné */
	private EditorGuide guide;

	/** La règle à laquelle est attachée le guide */
	private EditorRuler ruler;

	/** La position du guide */
	private int position;

	/**
	 * Constructeur
	 * @param ruler La regle qui permet la définition du guide
	 * @param position La position du guide
	 */
	public CreateGuideCommand(EditorRuler ruler, int position) {
		super(Messages.CreateGuideCommand_0);
		this.ruler = ruler;
		this.position = position;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		if (guide == null) {
			guide = new EditorGuide(!ruler.isHorizontal());
		}
		guide.setPosition(position);
		ruler.addGuide(guide);
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		ruler.removeGuide(guide);
	}
}
