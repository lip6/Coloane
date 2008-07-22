package fr.lip6.move.coloane.apiws.evenements;

import java.util.ArrayList;

import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.IRootMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.IUpdateMenu;
import fr.lip6.move.wrapper.ws.WrapperStub.MMenu;

public class ReceptMenu implements IReceptMenu {

	/**
	 * Constructeur du menu à envoyer aux observateurs.
	 * @param menus Menu reçu de la part du wrapper.
	 */
	public ReceptMenu(MMenu menus) {
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
