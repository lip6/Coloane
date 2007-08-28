package fr.lip6.move.coloane.ui.commands;

import fr.lip6.move.coloane.ui.model.IArcImpl;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

/**
 * Commande de suppression d'un point d'inflexion
 */
public class InflexDeleteCmd extends Command {

	private IArcImpl arc;
	private Point position;
	private int index;

	/**
	 * Constructeur
	 * @param arcModel L'arc concerne par le point d'inflexion
	 * @param p La position
	 * @param i L'index
	 */
	public InflexDeleteCmd(IArcImpl arcModel, Point p, int i) {
		this.arc = arcModel;
		this.position = p;
		this.index = i;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public final void execute() {
		this.redo();
		super.execute();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public final void undo() {
		this.arc.addInflexPoint(this.position, this.index);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public final void redo() {
		this.arc.removeInflexPoint(this.index);
	}


}
