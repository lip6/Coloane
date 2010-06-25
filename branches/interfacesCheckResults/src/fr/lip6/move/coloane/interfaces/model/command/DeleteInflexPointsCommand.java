package fr.lip6.move.coloane.interfaces.model.command;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.AbsoluteBendpoint;

/**
 * Commande de suppression de tous les points d'inflexion<br>
 *
 * @author Jean-Baptiste Voron
 */
public class DeleteInflexPointsCommand implements ICommand {
	private Map<IArc, List<AbsoluteBendpoint>> backupList;

	/**
	 * Constructeur
	 */
	public DeleteInflexPointsCommand() {
		this.backupList = new HashMap<IArc, List<AbsoluteBendpoint>>();
	}

	/**
	 * {@inheritDoc}
	 */
	public final void execute(IGraph graph) throws ModelException {
		for (IArc arc : graph.getArcs()) {
			// Sauvegarde de la liste des points d'inflexion
			this.backupList.put(arc, new ArrayList<AbsoluteBendpoint>(arc.getInflexPoints()));
			arc.removeAllInflexPoints();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public final void redo(IGraph graph) throws ModelException {
		this.execute(graph);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void undo(IGraph graph) {
		for (IArc arc : this.backupList.keySet()) {
			for (AbsoluteBendpoint p : this.backupList.get(arc)) {
				arc.addInflexPoint(p);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {
		return "Suppression de tous les points d'inlfexion";
	}
}
