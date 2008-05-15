package fr.lip6.move.coloane.core.ui.menus;

import fr.lip6.move.coloane.core.ui.UserInterface;

import org.eclipse.jface.action.Action;



public class UIAction extends Action {
	private UserInterface ui;
	private String rootMenuName;
	private String referenceName;
	private String serviceName;

	public UIAction(UserInterface userInterface, String rootMenu, String reference, String service) {
		super(service);
		this.ui = userInterface;
		this.rootMenuName = rootMenu;
		this.referenceName = reference;
		this.serviceName = service;
	}

	public final void run() {
		ui.askForService(rootMenuName, referenceName, serviceName);
	}
}
