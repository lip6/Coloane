package fr.lip6.move.coloane.interfaces.objects.result;

/**
 * Description d'une information renvoyée par la plate-forme durant un résultat.<br>
 * Parfois, certains information sont renvoyées par la plate-forme et doivent être affichées
 * sur le modèle sans pour autant le modifier... Ces informations sont appelées <b>tips</b>
 *
 * @author Jean-Baptiste Voron
 */
public interface ITip {

	/**
	 * @return l'identifiant de l'objet concerné
	 */
	int getIdObject();

	/**
	 * @return le nom de l'information
	 */
	String getName();

	/**
	 * @return la valeur de l'information
	 */
	String getValue();

}
