package fr.lip6.move.coloane.interfaces.objects.menu;

/**
 * Définition d'une option (case à cocher ou bouton radio)
 */
public interface IOptionMenu extends IItemMenu {
	/** Type checkbox pour une option : sélection 0 ou 1 jusqu'à N */
	int TYPE_CHECKBOX = 0;

	/** Type checkbox pour une option : sélection au plus 1 */
	int TYPE_RADIO = 1;

	/**
     * @return <code>true</code> si l'option est cochée, <code>false</code> sinon
     */
    boolean isValidated();

    /**
     * @return le type de l'option
     */
    int getType();
}
