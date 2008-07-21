package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.interfaces.model.IArc;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

/**
 * Commande de suppression d'un point d'inflexion
 */
public class InflexDeleteCmd extends Command {

	private IArc arc;
	private Point position;
	private int index;

	/**
	 * Constructeur
	 * @param arcModel L'arc concerne par le point d'inflexion
	 * @param p La position
	 * @param i L'index
	 */
	public InflexDeleteCmd(IArc arcModel, Point p, int i) {
		super(Messages.InflexDeleteCmd_0);
		this.arc = arcModel;
		this.position = p;
		this.index = i;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		this.redo();
		super.execute();
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		this.arc.addInflexPoint(this.position, this.index);
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		this.arc.removeInflexPoint(this.index);
	}


}
