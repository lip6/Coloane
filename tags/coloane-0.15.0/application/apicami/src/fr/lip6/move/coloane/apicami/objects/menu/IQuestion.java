package fr.lip6.move.coloane.apicami.objects.menu;

/**
 * Définition d'une question envoyée par la plate-forme
 */
public interface IQuestion {

	/** Constantes */
	int TYPE_TEXT = 1;
	int TYPE_TEXT_VARIABLE = 2;
	int TYPE_SUBMENU = 3;
	int TYPE_OBJECT = 4;

	int BEHAVIOR_MOSTONE = 1;
	int BEHAVIOR_MORETHANZERO = 2;
	int BEHAVIOR_ONECHOICE = 3;
	int BEHAVIOR_MORETHANONE = 4;

	/**
	 * @return Le parent de la question
	 */
	String getParent();

	/**
	 * @return le nom de la question
	 */
	String getName();

	/**
	 * @return le type de la question
	 */
	int getType();

	/**
	 * @return le comportement de la question
	 */
	int getBehavior();

	/**
	 * @return la visibilité de la question
	 */
	boolean isVisible();

	/**
	 * @return la possiblité du service à s'arrêter
	 */
	boolean isStopAuthorized();

	/**
	 * @return la possiblité d'afficher des boites de dialogues
	 */
	boolean isDialogAllowed();

	/**
	 * @return la validation de l'élément
	 */
	boolean isValid();

	/**
	 * @return le formalisme de résultat du service
	 */
	String getOutputFormalism();
}
