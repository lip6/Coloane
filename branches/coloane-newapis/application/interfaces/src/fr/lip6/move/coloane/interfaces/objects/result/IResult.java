package fr.lip6.move.coloane.interfaces.objects.result;

import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.command.ICommand;

import java.util.List;

/**
 * Cette interface décrit un résultat renvoyé par la plate-forme
 */
public interface IResult {

	/**
	 * @return tous les sous-résultats
	 */
	List<ISubResult> getSubResults();

	/**
	 * @return le nom du menu racine qui contient le service qui fournit ces résultats
	 */
	String getRootName();

	/**
	 * @return le nom du service qui a été invoqué
	 */
	String getServiceName();

	/**
	 * @return la liste d'informations associées au résultat
	 */
	List<ITip> getTipsList();

	/**
	 * @return le nouveau graphe qui doit être affiché dans une nouvelle fenêtre
	 */
	IGraph getNewGraph();

	/**
	 * @return les modifications à faire sur le graphe courant
	 */
	List<ICommand> getModificationsOnCurrentGraph();
}
