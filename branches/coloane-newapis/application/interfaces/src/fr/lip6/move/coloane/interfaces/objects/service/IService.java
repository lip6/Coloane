package fr.lip6.move.coloane.interfaces.objects.service;


/**
 * Définition d'une question envoyée par la plate-forme
 */
public interface IService {

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
	 * @return le nom de la racine du menu dans lequel est défini le service
	 */
	String getRoot();

	/**
	 * @return le nom du parent du service
	 */
	String getParent();

	/**
	 * @return le nom du service
	 */
	String getName();

	/**
	 * @return le type du service
	 */
	int getType();

	/**
	 * La cardinalité est interprétée différemmnet selon le type du service.<br>
	 * En général, si le service est du type objet, alors la cardinalité désigne le nombre d'éléments qui doivent être
	 * sélectionnés sur le modèle pour que le service s'exécute normalement.
	 * @return le comportement de la question
	 */
	int getCardinality();

	/**
	 * @return la possiblité du service à s'arrêter
	 */
	boolean isStopAuthorized();

	/**
	 * @return la possiblité d'afficher des boites de dialogues
	 */
	boolean isInteractive();

	/**
	 * @return <code>true</code> si l'élément doit être coché (s'applique pour les cases à cocher)
	 */
	boolean isChecked();

	/**
	 * @return le formalisme de résultat du service
	 */
	String getOutputFormalism();
}
