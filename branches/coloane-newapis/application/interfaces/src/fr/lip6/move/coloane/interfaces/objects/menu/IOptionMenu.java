package fr.lip6.move.coloane.interfaces.objects.menu;

/**
 * Cette intreface définie une option.
 */
public interface IOptionMenu {

	/**
     * Récupére la validité d'une option
     * @return true si l'option est cochée, false sinon
     */
    boolean isValidated();

    /**
     * Modifie la validité d'une option
     * @param validated la nouvelle validité d'une option
     */
    void setValidated(boolean validated);
}
