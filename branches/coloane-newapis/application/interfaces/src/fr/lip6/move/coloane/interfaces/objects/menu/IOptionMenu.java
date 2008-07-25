package fr.lip6.move.coloane.interfaces.objects.menu;

/**
 * Définition d'une option (case à cocher ou bouton radio)
 */
public interface IOptionMenu extends IItemMenu {

	/**
     * @return <code>true</code> si l'option est cochée, <code>false</code> sinon
     */
    boolean isValidated();
}
