package fr.lip6.move.coloane.interfaces.objects.result;

import java.util.List;

/**
 * Cette interface décrit un résultat renvoyé par la plate-forme
 */
public interface IResult {

	/**
	 * @return Tous les sous-résultats
	 */
	List<ISubResult> getSubResults();

	/**
	 * @return Le nom du menu racine qui contient le service qui fournit ces résultats
	 */
	String getRootName();

	/**
	 * @return Le nom du service qui a été invoqué
	 */
	String getServiceName();

}
