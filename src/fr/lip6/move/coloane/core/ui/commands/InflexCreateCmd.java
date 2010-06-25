package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.interfaces.model.IArc;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

/**
 * Commande de creation d'un point d'inflexion
 */
public class InflexCreateCmd extends Command {

	/** Arc concerne par le point d'inflexion */
	private IArc arc;

	/** La position du point d'inflexion */
	private Point position;

	/** L'index du point d'inflexion */
	private int index;

	/**
	 * Constructeur
	 * @param arcModel L'arc
	 * @param p La position
	 * @param i L'index
	 */
	public InflexCreateCmd(IArc arcModel, Point p, int i) {
		super(Messages.InflexCreateCmd_0);
		this.arc = arcModel;
		this.position = p;
		this.position.x = Math.max(this.position.x, 0);
		this.position.y = Math.max(this.position.y, 0);
		this.index = i;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		this.redo();
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		this.arc.removeInflexPoint(this.index);
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		this.arc.addInflexPoint(this.position, this.index);
	}

}

