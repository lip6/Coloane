package fr.lip6.move.coloane.apiws.objects.menu;

import fr.lip6.move.coloane.apiws.stubs.WrapperStub.MenuModification;
import fr.lip6.move.coloane.interfaces.objects.menu.IUpdateMenu;

/**
 * Cette classe définie les modification sur un menu
 *
 * @author Monir CHAOUKI
 */
public class UpdateMenuImpl implements IUpdateMenu {

	private String rootName;

	private String serviceName;

	private boolean state;

	/**
	 * Constructeur
	 * @param modification les modification
	 */
	public UpdateMenuImpl(MenuModification modification) {
		this.rootName = modification.getRoot();
		this.serviceName = modification.getQuestion();
		if (modification.getType() == 0) { this.state = false; } else {  this.state = true; }
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getRootName() {
		return rootName;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getServiceName() {
		return serviceName;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean getState() {
		return state;
	}

}
