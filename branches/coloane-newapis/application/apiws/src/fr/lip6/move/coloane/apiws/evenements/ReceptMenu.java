package fr.lip6.move.coloane.apiws.evenements;

import java.util.ArrayList;

import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.IRootMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.IUpdateMenu;
import fr.lip6.move.wrapper.ws.WrapperStub.LMenuModification;
import fr.lip6.move.wrapper.ws.WrapperStub.MMenu;

/**
 * Cette classe représent l'objet (qui définie des menus) à envoyer aux observateurs d'événements: réception de menus.
 */
public class ReceptMenu implements IReceptMenu {

	/**
	 * Constructeur des menus à envoyer aux observateurs.
	 * @param menus les menus reçu de la part du wrapper à traduire
	 * pour être comprehensible par le core de Coloane.
	 */
	public ReceptMenu(MMenu menus) {
		// TODO Completer le condtructeur de ReceptMenu
	}

	/**
	 * Constructeur des modifications à faire sur les menu à envoyer aux observateurs.
	 * @param modification les modification reçu de la part du wrapper à traduire
	 * pour être comprehensible par le core de Coloane.
	 */
	public ReceptMenu(LMenuModification modification) {
		// TODO Completer le condtructeur de ReceptMenu
	}

	/**
	 * Constructeur des menus et des modifications à faire sur les menu à envoyer aux observateurs.
	 * @param menus les menus reçu de la part du wrapper à traduire
	 * pour être comprehensible par le core de Coloane.
	 * @param modification les modification reçu de la part du wrapper à traduire
	 * pour être comprehensible par le core de Coloane.
	 */
	public ReceptMenu(MMenu menus, LMenuModification modification) {
		// TODO Completer le condtructeur de ReceptMenu
	}

	/**
	 * {@inheritDoc}
	 */
	public ArrayList<IRootMenu> getMenus() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public ArrayList<IUpdateMenu> getUpdateMenus() {
		// TODO Auto-generated method stub
		return null;
	}

}
