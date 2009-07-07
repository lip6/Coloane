package fr.lip6.move.coloane.interfaces.objects.result;

import java.util.List;
import java.util.Map;

/**
 * Définition d'un sous-résultat<br>
 * Un sous-résultat peut contenir d'autres réstulats.
 *
 * @author Jean-Baptiste
 */
public interface ISubResult {

	/**
	 * @return Tous les sous-résultats
	 */
	List<ISubResult> getChildren();

	/**
	 * @return La liste des objets désignés par la plate-forme
	 */
	List<Integer> getObjectsDesignation();

	/**
	 * @return La liste des objets qui doivent être mis en valeur
	 */
	List<Integer> getObjectsOutline();

	/**
	 * @return La liste des attributs qui doivent être mis en valeur
	 */
	Map<Integer, List<String>> getAttributesOutline();

	/**
	 * @return La liste des résultats textuels
	 */
	List<List<String>> getTextualResults();

	/**
	 * @return Le nom de l'ensemble de résultats
	 */
	String getName();

	/**
	 * @return Une information sur le nom de l'ensemble de résultats
	 */
	String getInformation();
	
	/**
	 * @return Le menu des résultats textuels
	 */
	List<String> getTextualResultsMenu();
}
