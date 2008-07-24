package fr.lip6.move.coloane.apiws.evenements;

import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.IRootMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.IUpdateMenu;
import fr.lip6.move.wrapper.ws.WrapperStub.LMenuModification;
import fr.lip6.move.wrapper.ws.WrapperStub.MMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe représent l'objet (qui définie des menus) à envoyer aux observateurs d'événements: réception de menus.
 */
public class ReceptMenu implements IReceptMenu {

	private List<IRootMenu> menus;

	private List<IUpdateMenu> updateMenus;

	/**
	 * Constructeur des menus à envoyer aux observateurs.
	 * @param menus les menus reçu de la part du wrapper à traduire
	 * pour être comprehensible par le core de Coloane.
	 */
	public ReceptMenu(MMenu menus) {
		// TODO Completer le condtructeur de ReceptMenu
		this.menus = new ArrayList<IRootMenu>();
		this.updateMenus = new ArrayList<IUpdateMenu>();
	}

	/**
	 * Constructeur des modifications à faire sur les menu à envoyer aux observateurs.
	 * @param updateMenus les modification reçu de la part du wrapper à traduire
	 * pour être comprehensible par le core de Coloane.
	 */
	public ReceptMenu(LMenuModification updateMenus) {
		// TODO Completer le condtructeur de ReceptMenu
		this.menus = new ArrayList<IRootMenu>();
		this.updateMenus = new ArrayList<IUpdateMenu>();
	}

	/**
	 * Constructeur des menus et des modifications à faire sur les menu à envoyer aux observateurs.
	 * @param menus les menus reçu de la part du wrapper à traduire
	 * pour être comprehensible par le core de Coloane.
	 * @param updateMenus les modification reçu de la part du wrapper à traduire
	 * pour être comprehensible par le core de Coloane.
	 */
	public ReceptMenu(MMenu menus, LMenuModification updateMenus) {
		// TODO Completer le condtructeur de ReceptMenu
		this.menus = new ArrayList<IRootMenu>();
		this.updateMenus = new ArrayList<IUpdateMenu>();
	}

	/**
	 * {@inheritDoc}
	 */
	public final List<IRootMenu> getMenus() {
		return menus;
	}

	/**
	 * {@inheritDoc}
	 */
	public final List<IUpdateMenu> getUpdateMenus() {
		return updateMenus;
	}

}
