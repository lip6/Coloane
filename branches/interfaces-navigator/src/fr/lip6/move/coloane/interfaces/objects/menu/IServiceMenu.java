package fr.lip6.move.coloane.interfaces.objects.menu;

/**
 * Cette interface définie une entrée dans un menu permettant l'appel d'un service
 */
public interface IServiceMenu extends IItemMenu {

    /**
     * @return l'identifiant du service associé à l'item
     */
	String getAssociatedService();
}
