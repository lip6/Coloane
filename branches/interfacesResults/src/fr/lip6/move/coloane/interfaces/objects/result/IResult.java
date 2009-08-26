package fr.lip6.move.coloane.interfaces.objects.result;

import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.command.ICommand;

import java.util.List;
import java.util.Map;

/**
 * This interface describe a result sent by a tool.
 */
public interface IResult {

	/**
	 * Return the name of the result (Name of the called tool is preferred).
	 * 
	 * @return the result name.
	 */
	String getResultName();

	/**
	 * Return the sub-result list contained in the sub-result.
	 * 
	 * @return the sub-result list.
	 */
	List<ISubResult> getSubResults();

	/**
	 * Return the list of results in the form of text.
	 * 
	 * @return the list of textuals results.
	 */
	List<List<String>> getTextualResults();

	/**
	 * @return la liste d'informations associées au résultat
	 */
	Map<Integer, List<ITip>> getTips();

	/**
	 * @return le nouveau graphe qui doit être affiché dans une nouvelle fenêtre
	 */
	IGraph getNewGraph();

	/**
	 * @return les modifications à faire sur le graphe courant
	 */
	List<ICommand> getModificationsOnCurrentGraph();
}
