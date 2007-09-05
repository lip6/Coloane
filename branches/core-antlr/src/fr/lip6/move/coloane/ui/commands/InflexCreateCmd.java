package fr.lip6.move.coloane.ui.commands;

import fr.lip6.move.coloane.ui.model.IArcImpl;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

/**
 * Commande de creation d'un point d'inflexion
 */
public class InflexCreateCmd extends Command {

	/** Arc concerne par le point d'inflexion */
	private IArcImpl arc;

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
	public InflexCreateCmd(IArcImpl arcModel, Point p, int i) {
		this.arc = arcModel;
		this.position = p;
		this.index = i;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public final void execute() {
		this.redo();
		super.execute();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public final void undo() {
		this.arc.removeInflexPoint(this.index);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	@Override
	public final void redo() {
		this.arc.addInflexPoint(this.position, this.index);
	}

}

