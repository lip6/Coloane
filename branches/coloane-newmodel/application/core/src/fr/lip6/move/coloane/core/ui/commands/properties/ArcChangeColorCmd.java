package fr.lip6.move.coloane.core.ui.commands.properties;

import fr.lip6.move.coloane.core.ui.model.interfaces.IArc;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.graphics.Color;

/**
 * Commande pour changer la couleur d'un arc.
 */
public class ArcChangeColorCmd extends Command {
	private Color oldColor;
	private Color newColor;
	private IArc arc;

	/**
	 * @param arc Arc à modifier
	 * @param color Nouvelle couleur
	 */
	public ArcChangeColorCmd(IArc arc, Color color) {
		this.arc = arc;
		this.newColor = color;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public final void execute() {
		oldColor = arc.getGraphicInfo().getColor();
		redo();
	}


	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	@Override
	public final void redo() {
		arc.getGraphicInfo().setColor(newColor);
	}


	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public final void undo() {
		arc.getGraphicInfo().setColor(oldColor);
	}

}
