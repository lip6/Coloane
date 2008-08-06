package fr.lip6.move.coloane.interfaces.model.command;

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
	 */
	void execute();

	/**
	 * Annule les effets de la commande
	 */
	void undo();

	/**
	 * Réapplique les effets de la commandes
	 */
	void redo();
}
