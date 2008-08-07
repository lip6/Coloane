package fr.lip6.move.coloane.interfaces.model.command;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IGraph;

/**
 * Description d'une commande de modification sur le modèle<br>
 * Plusieurs types de commandes existent, mais elles implémentent toutes cette interface.<br>
 * Elle oblige les commandes aux actions suivantes :
 * <ul>
 * 	<li>Execute</li>
 * 	<li>Undo</li>
 * 	<li>Redo</li>
 * </ul>
 *
 * @author Jean-Baptiste Voron
 */
public interface ICommand {

	/**
	 * Execute la commande
	 * @param graph Le graphe qui est concerné par cette commande
	 * @throws ModelException En cas de problème lors de l'execution de la commande
	 */
	void execute(IGraph graph) throws ModelException;

	/**
	 * Annule les effets de la commande
	 * @param graph Le graphe qui est concerné par cette commande
	 * @throws ModelException En cas de problème lors de l'annulation de la commande
	 */
	void undo(IGraph graph) throws ModelException;

	/**
	 * Réapplique les effets de la commandes
	 * @param graph Le graphe qui est concerné par cette commande
	 * @throws ModelException En cas de problème lors de la re-execution de la commande
	 */
	void redo(IGraph graph) throws ModelException;
}
